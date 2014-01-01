package net.bubblecraft.cmds;

import java.util.Random;

import net.bubblecraft.main.Messager;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class RTP extends SubCommand {

	public void onCommand(Player p, String[] args) {
		Random rand = new Random();
		
		int n = 0 + 50000 + 1;
		int x = rand.nextInt() % n;
		int z = rand.nextInt() % n;
		
		Location loc = new Location(p.getWorld(), x, 60, z);
		p.teleport(loc);
		Messager.getInstance().good(p, "You have been teleported to X:" + x + " Y:60" + " Z:" + z);
	}

	public String name() {
		return "rtp";
	}

	public String info() {
		return "Randomly teleports the sender!";
	}

	public String[] aliases() {
		return new String[] {"rtp"};
	}

}