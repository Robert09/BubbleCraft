package net.bubblecraft.handlers;

import java.util.Random;

import net.bubblecraft.main.BubbleCraft;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class RandomTPSign implements Listener {

	BubbleCraft plugin;

	public RandomTPSign(BubbleCraft instance) {
		plugin = instance;
		return;
	}

	@EventHandler
	public void onSignPlaced(SignChangeEvent e) {
		// RTP
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
		// RTP
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