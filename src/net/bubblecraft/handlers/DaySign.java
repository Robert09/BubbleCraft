package net.bubblecraft.handlers;

import net.bubblecraft.main.BubbleCraft;

import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class DaySign implements Listener {

	BubbleCraft plugin;

	public DaySign(BubbleCraft instance) {
		plugin = instance;
		return;
	}
	
	@EventHandler
	public void onSignPlaced(SignChangeEvent e) {
		// Time day
		if (e.getLine(1).equalsIgnoreCase("day")) {
			e.setLine(1, "§6[§3DAY§6]");
		}
	}

	@EventHandler
	public void interact(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
		// DAY
		if (e.getClickedBlock().getState() instanceof Sign) {
			Sign s = (Sign) e.getClickedBlock().getState();

			if (s.getLine(1).equalsIgnoreCase("§6[§3DAY§6]")) {
				World world = e.getPlayer().getWorld();
				
				world.setTime(0);
			}
		}
	}
}