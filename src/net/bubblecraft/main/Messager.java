package net.bubblecraft.main;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;


public class Messager {
	
	public enum MessageType {
	
		INFO(ChatColor.AQUA),
		GOOD(ChatColor.GOLD),
		BAD(ChatColor.DARK_RED);
		
		private ChatColor color;
		
		MessageType(ChatColor color) {
			this.color = color;
		}
		
		public ChatColor getColor() {
			return color;
		}
	}

	private Messager() { }
	
	private static Messager instance = new Messager();
	
	public static Messager getInstance() {
		return instance;
	}
	
	private String prefix = ChatColor.DARK_AQUA + "["+ChatColor.GOLD+"BubbleCraft"+ChatColor.DARK_AQUA+"] " + ChatColor.RESET;
	
	public void msg(CommandSender s, MessageType type, String... messages) {
		for(String msg : messages) {
			s.sendMessage(prefix + type.getColor() + msg);
		}
	}
}
