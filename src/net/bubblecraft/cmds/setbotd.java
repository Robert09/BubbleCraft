package net.bubblecraft.cmds;

import net.bubblecraft.files.SettingsManager;
import net.bubblecraft.main.Messager;

import org.bukkit.entity.Player;

public class Setbotd extends SubCommand {

	public void onCommand(Player p, String[] args) {
		if (args.length == 0) {
			Messager.getInstance().severe(p, "You must choose what you want the BOTD to be!");
		} else {
			String message = "";
			for (String part : args) {
				message += part + " ";
			}
			SettingsManager.getInstance().getConfig().set("BOTD", message);
			SettingsManager.getInstance().saveConfig();
			Messager.getInstance().good(p, "You have set the BOTD to: " + message);
		}
	}

	public String name() {
		return "setbotd";
	}

	public String info() {
		return "Sets the BOTD.";
	}

	public String[] aliases() {
		return new String[] { "sbotd" };
	}
}