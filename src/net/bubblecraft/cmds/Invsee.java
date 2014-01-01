package net.bubblecraft.cmds;

import org.bukkit.entity.Player;

public class Invsee extends SubCommand {

	public void onCommand(Player p, String[] args) {
		
	}

	public String name() {
		return "invsee";
	}

	public String info() {
		return "Lets you check other player inventories!";
	}

	public String[] aliases() {
		return new String[] { "is", "inv" };
	}

}