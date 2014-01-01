package net.bubblecraft.main;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;


public class Messager {

	private Messager() { }
	
	private static Messager instance = new Messager();
	
	static Messager getInstance() {
		return instance;
	}
	
	public void info(CommandSender s, String msg) {
		msg(s, ChatColor.YELLOW, msg);
	}
	
	public void severe(CommandSender s, String msg) {
		msg(s, ChatColor.DARK_RED, msg);
	}
	
	public void good(CommandSender s, String msg) {
		msg(s, ChatColor.GREEN, msg);
	}
	
	public void msg(CommandSender s, ChatColor color, String msg) {
		s.sendMessage(color + msg);
	}
	
}
