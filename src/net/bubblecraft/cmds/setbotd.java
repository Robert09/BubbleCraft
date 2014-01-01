package net.bubblecraft.cmds;

import org.bukkit.entity.Player;

public class setbotd extends SubCommand {

	public void onCommand(Player p, String[] args) {
		
	}

	public String name() {
		return "setbotd";
	}

	public String info() {
		return "Sets the BOTD.";
	}

	public String[] aliases() {
		return new String[] {""};
	}

}
