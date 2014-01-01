package net.bubblecraft.cmds;

import net.bubblecraft.files.PlayerData;
import net.bubblecraft.main.BubbleCraft;
import net.bubblecraft.main.Messager;

import org.bukkit.entity.Player;

public class Chatcolor extends SubCommand {
	
	public void onCommand(Player p, String[] args) {
		PlayerData pd = BubbleCraft.pDataMap.get(p);
		
		if(args.length == 0) {
			Messager.getInstance().severe(p, "You must choose a chat color!");
		}else if(args.length == 1) {
			
			if(args[0].equalsIgnoreCase("red")) {
				pd.setString("ChatColor", "§4");
			}
			
			if(args[0].contains("gold")) {
				pd.setString("ChatColor", "§6");
			}
			
			if(args[0].contains("black")) {
				pd.setString("ChatColor", "§0");
			}
			
			if(args[0].contains("aqua")) {
				pd.setString("ChatColor", "§3");
			}
			
			if(args[0].contains("purple")) {
				pd.setString("ChatColor", "§5");
			}
		}
	}

	public String name() {
		return "chatcolor";
	}

	public String info() {
		return "Change the color of your chat messages!";
	}

	public String[] aliases() {
		return new String[] { "cc" };
	}

}
