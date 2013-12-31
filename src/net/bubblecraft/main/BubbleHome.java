package net.bubblecraft.main;

import net.bubblecraft.files.PlayerData;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BubbleHome {
	BubbleCraft plugin;
	
	public BubbleHome(BubbleCraft instance) {
		plugin = instance;
	}
	
	// Setting the home
	public void setHome(Player p, String str) {
		//String name = p.getName();
		PlayerData pd = BubbleCraft.pDataMap.get(p);
		
		int x = (int) p.getLocation().getX();
		int y = (int) p.getLocation().getY();
		int z = (int) p.getLocation().getZ();
		
		pd.setInt(str + ".x", x);
		pd.setInt(str + ".y", y);
		pd.setInt(str + ".z", z);
		pd.save();
	}
	
	// Getting the home
	public void getHome(Player p, String str) {
		PlayerData pd = BubbleCraft.pDataMap.get(p);
		
		int x = pd.getInt(str + ".x");
		int y = pd.getInt(str + ".y");
		int z = pd.getInt(str + ".z");
		
		p.teleport(new Location(p.getWorld(), x, y, z));
	}
}