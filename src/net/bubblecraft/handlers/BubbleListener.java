package net.bubblecraft.handlers;

import net.bubblecraft.main.BubbleCraft;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class BubbleListener implements Listener {

	BubbleCraft plugin;

	public BubbleListener(BubbleCraft instance) {
		plugin = instance;
		return;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String name = p.getName();
		
		BubbleCraft.playerDataLoad(name, p);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onTalk(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String msg = e.getMessage().toLowerCase();
		String troll = ChatColor.LIGHT_PURPLE + "I'm a pretty little princess!";
		//String name = p.getPlayer().getName();

		if (msg.contains("f u c k") || msg.contains("fuck")
				|| msg.contains("penis") || msg.contains("vagina") || msg.contains("pussy") || msg.contains("dick")
				|| msg.contains(" ass ")) {
			e.setMessage(troll);
			p.sendMessage(ChatColor.GREEN
					+ "Tisk Tisk you could be banned you know?");
		}
	}
}