package net.bubblecraft.cmds;

import net.bubblecraft.main.BubbleHome;
import net.bubblecraft.main.Messager;

import org.bukkit.entity.Player;

public class Sethome extends SubCommand {

	public void onCommand(Player p, String[] args) {
		if (args.length == 0) {
			Messager.getInstance().severe(p, "You must name your home!");
		} else if (args.length == 1) {
			BubbleHome.setHome(p, args[0]);
			Messager.getInstance().good(p, "You have set this area as: " + args[0]);
		}
	}

	public String name() {
		return "homeset";
	}

	public String info() {
		return "Sets the command senders home!";
	}

	public String[] aliases() {
		return new String[] { "homes", "hset", "hs" };
	}

}
