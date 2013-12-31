package net.bubblecraft.main;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messager {
	Logger log = Logger.getLogger("Minecraft");
	ChatColor GOLD = ChatColor.GOLD;
	ChatColor AQUA = ChatColor.AQUA;
	ChatColor GREEN = ChatColor.GREEN;
	ChatColor DRED = ChatColor.DARK_RED;
	
	Player p;
	
	public Messager(Player p) {
		this.p = p;
	}
	
	// Send the console a message.
	public void sendConsole(String msg) {
		log.info(AQUA+"["+GOLD+"BubbleCraft"+AQUA+"]"+DRED + msg);
	}
	
	// Send the player a message.
	public void sendPlayer(String msg) {
		p.sendMessage(AQUA+"["+GOLD+"BubbleCraft"+AQUA+"]"+DRED + msg);
	}
}
