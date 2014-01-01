package net.bubblecraft.cmds;

import net.bubblecraft.main.BubbleHome;
import net.bubblecraft.main.Messager;

import org.bukkit.entity.Player;

public class Home extends SubCommand {
	
	public void onCommand(Player p, String[] args) {
		if(args.length == 0) {
			Messager.getInstance().severe(p, "You must choose which home to go to!");
		}else if(args.length == 1) {
			BubbleHome.getHome(p, args[0]);
			Messager.getInstance().good(p, "Welcome to: " + args[0] + p.getName() + "!");
		}
	}

	public String name() {
		return "gohome";
	}

	public String info() {
		return "Returns the command sender home!";
	}

	public String[] aliases() {
		return new String[] {"bh"};
	}
	
}
