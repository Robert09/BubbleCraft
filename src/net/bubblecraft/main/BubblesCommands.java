package net.bubblecraft.main;

import java.util.ArrayList;
import java.util.Arrays;

import net.bubblecraft.cmds.SubCommand;
import net.bubblecraft.files.SettingsManager;
import net.bubblecraft.handlers.BubblePerms;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BubblesCommands implements CommandExecutor {
	// Variables
	BubblePerms bp = new BubblePerms();
	SettingsManager settings = new SettingsManager();
	BubbleCraft bc = new BubbleCraft();
	BubbleHome bh = new BubbleHome(bc);
	
	// Command Array
	private ArrayList<SubCommand> commands = new ArrayList<SubCommand>();
	
	public void setup() {
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			Messager.getInstance().severe(sender, "You must be a player to perform this command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("bubblecraft")) {
			if(args.length == 0) {
				for(SubCommand c : commands) {
					Messager.getInstance().info(p, "/bubblecraft" + c.name() + "("+ aliases(c) + ")" + " - " + c.info());
				}
				return true;
			}
			
			SubCommand target = get(args[0]);
			
			if(target == null) {
				Messager.getInstance().severe(p, "/bubblecraft " + args[0] + " is not a valid command!");
			}
			
			ArrayList<String> a = new ArrayList<String>();
			a.addAll(Arrays.asList(args));
			a.remove(0);
			args = a.toArray(new String[a.size()]);
			
			try {
				target.onCommand(p, args);
			}catch (Exception e) {
				Messager.getInstance().severe(p, "An error has occured" + e.getCause());
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	private String aliases(SubCommand cmd) {
		String fin = "";
		for(String a : cmd.aliases()) {
			fin += a + " | ";
		}
		
		return fin.substring(0, fin.lastIndexOf("| "));
	}
	
	private SubCommand get(String name) {
		for(SubCommand cmd : commands) {
			if(cmd.name().equalsIgnoreCase(name)) return cmd;
		}
		
		return null;
	}
	
}