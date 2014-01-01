package net.bubblecraft.main;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

import net.bubblecraft.files.PlayerData;
import net.bubblecraft.files.SettingsManager;
import net.bubblecraft.handlers.BubbleListener;
import net.bubblecraft.handlers.BubblePerms;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BubbleCraft extends JavaPlugin implements Listener {
	private SettingsManager settings = SettingsManager.getInstance();
	private String ver = "2.2.0";
	private Logger logger = Logger.getLogger("Minecraft");
	public BubblePerms bp = new BubblePerms();
	public BubbleHome bh = new BubbleHome(this);
	public static HashMap<Player, PlayerData> pDataMap = new HashMap<Player, PlayerData>();
	
	public void onEnable() {
		// Tells the console that the plugin has been loaded.
		PluginDescriptionFile pdfFile = this.getDescription();
		logger.info(pdfFile.getName() + " " + pdfFile.getVersion() + " is now enabled!");
		
		// Setting up the plugin manager.
		PluginManager pm = getServer().getPluginManager();

		// Event Registers
		pm.registerEvents(new BubbleListener(this), this);

		// Permissions
		pm.addPermission(bp.seeInv);
		pm.addPermission(bp.reload);
		pm.addPermission(bp.setbotd);
		pm.addPermission(bp.chatcolor);
		
		// Commands
		BubblesCommands cm = new BubblesCommands();
		cm.setup();
		getCommand("bubblecraft").setExecutor(cm);;
		
		// Config stuff.
		settings.setup(this);
		settings.getConfig().set("version", ver);
	}

	public void onDisable() {
		// Tells the console that the plugin has been disabled.
		PluginDescriptionFile pdfFile = this.getDescription();
		logger.info(pdfFile.getName() + " " + pdfFile.getVersion() + " is now disabled!");
	}
	
	// Player File.
	public static void playerDataLoad(String name, Player p) {
		File dir = new File(Bukkit.getPluginManager().getPlugin("BubbleCraft").getDataFolder() + "/PlayerData");
		
		if(!dir.exists()) dir.mkdir();
		File file = new File(dir, name + ".yml");
		
		PlayerData pData = new PlayerData(file, name);
		
		if(!pData.load()) {
			throw new IllegalStateException("The Player File For Player:"+ name + " Was Not Loaded Correctly!");
		}else {
			pDataMap.put(p, pData);
		}
	}
}