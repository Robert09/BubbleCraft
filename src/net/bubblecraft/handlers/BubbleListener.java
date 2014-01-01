package net.bubblecraft.handlers;

import java.util.Random;

import net.bubblecraft.files.PlayerData;
import net.bubblecraft.main.BubbleCraft;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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
		PlayerData pd = BubbleCraft.pDataMap.get(p);
		
		if(pd.getString("Chat Color").isEmpty()) {
			e.setMessage(pd.getString("Chat Color" + e.getMessage()));
		}

		if (msg.contains("f u c k") || msg.contains("fuck")
				|| msg.contains("penis") || msg.contains("vagina") || msg.contains("pussy") || msg.contains("dick")
				|| msg.contains(" ass ")) {
			e.setMessage(troll);
			p.sendMessage(ChatColor.GREEN
					+ "Tisk Tisk you could be banned you know?");
		}
	}

	@EventHandler
	public void onSignPlaced(SignChangeEvent e) {
		if (e.getLine(0).equalsIgnoreCase("rtp")) {
			e.setLine(0, "§6[§3RTP§6]");
			e.setLine(1, "§6Teleport to");
			e.setLine(2, "§6random coords");
		}
	}

	@EventHandler
	public void interact(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK))
			return;
		if (e.getClickedBlock().getState() instanceof Sign) {
			Sign s = (Sign) e.getClickedBlock().getState();

			if (s.getLine(0).equalsIgnoreCase("§6[§3RTP§6]")) {
				Random rand = new Random();

				int n = 0 + 50000 + 1;
				int x = rand.nextInt() % n;
				int z = rand.nextInt() % n;

				Location loc = new Location(e.getPlayer().getWorld(), x, 60, z);
				e.getPlayer().teleport(loc);
				e.getPlayer().sendMessage(
						ChatColor.GOLD + "New coords: X: " + x + ", Y: 60"
								+ ", Z: " + z);
			}
		}
	}
}