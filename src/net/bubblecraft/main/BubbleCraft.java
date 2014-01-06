package net.bubblecraft.main;

import java.io.File;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import net.bubblecraft.files.BubbleHome;
import net.bubblecraft.files.PlayerData;
import net.bubblecraft.files.SettingsManager;
import net.bubblecraft.handlers.BubbleListener;
import net.bubblecraft.handlers.DaySign;
import net.bubblecraft.handlers.RandomTPSign;
import net.bubblecraft.main.Messager.MessageType;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BubbleCraft extends JavaPlugin  {
	private SettingsManager settings = SettingsManager.getInstance();
	private String ver = "2.2.0";
	private Logger logger = Logger.getLogger("Minecraft");
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
		pm.registerEvents(new DaySign(this), this);
		pm.registerEvents(new RandomTPSign(this), this);
		
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
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String name = cmd.getName();
		
		if(!(sender instanceof Player)) { Messager.getInstance().msg(sender, MessageType.BAD, "You must be a player!"); }
		Player p = (Player) sender;
		
		// BOTD
		if(name.equalsIgnoreCase("botd")) {
			Messager.getInstance().msg(sender, MessageType.INFO, settings.getConfig().getString("BOTD"));
		}
		// BOTD
		
		// Chat Color
		if(name.equalsIgnoreCase("chatcolor") && p.hasPermission("bc.chatcolor")) {
			if(!(sender instanceof Player)) {
				Messager.getInstance().msg(sender, MessageType.BAD, "You must be a player to use this command!");
			}
			
			PlayerData pd = BubbleCraft.pDataMap.get(p);
			
			if(args.length < 2) {
				Messager.getInstance().msg(p, MessageType.BAD, "You must choose a chat color!");
			}else {
				
				if(args[0].equalsIgnoreCase("red")) {
					pd.setString("ChatColor", "§4");
					Messager.getInstance().msg(p, MessageType.INFO, pd.getString("ChatColor") + "You have set your chat color to red!");
				}
				
				if(args[0].contains("gold")) {
					pd.setString("ChatColor", "§6");
				}
				
				if(args[0].contains("black")) {
					pd.setString("ChatColor", "§0");
				}
				
				if(args[0].contains("aqua")) {
					pd.setString("ChatColor", "§3");
				}
				
				if(args[0].contains("purple")) {
					pd.setString("ChatColor", "§5");
				}
			}
		}
		// Chat Color
		
		// Home
		if(name.equalsIgnoreCase("home")) {
			if(args.length == 0) { Messager.getInstance().msg(p, MessageType.BAD, "You must choose a home!"); }
			
			BubbleHome.getHome(p, args[0]);
			Messager.getInstance().msg(p, MessageType.GOOD, "Welcome to: " + args[0] + "!");
		}
		// Home
		
		// InveSee
		if(name.equalsIgnoreCase("invsee") && p.hasPermission("bc.invsee")) {
			Player target = Bukkit.getServer().getPlayer(args[0]);
			
			p.openInventory(target.getInventory());
			Messager.getInstance().msg(p, MessageType.INFO, "You are now viewing: " + args[0] + "'s inventory!");
		}
		// InvSee
		
		// RTP
		if(name.equalsIgnoreCase("rtp")) {
			Random rand = new Random();
			
			int n = 0 + 50000 + 1;
			int x = rand.nextInt() % n;
			int z = rand.nextInt() % n;
			
			Location loc = new Location(p.getWorld(), x, 60, z);
			p.teleport(loc);
			Messager.getInstance().msg(p, MessageType.GOOD, "You have been teleported to X:" + x + " Y:60" + " Z:" + z);
		}
		// RTP
		
		// Set Botd
		if(name.equalsIgnoreCase("setbotd") && p.hasPermission("bc.setbotd")) {
			if (args.length == 0) {
				Messager.getInstance().msg(p, MessageType.BAD, "You must choose what you want the BOTD to be!");
			} else {
				String message = "";
				for (String part : args) {
					message += part + " ";
				}
				SettingsManager.getInstance().getConfig().set("BOTD", message);
				SettingsManager.getInstance().saveConfig();
				Messager.getInstance().msg(p, MessageType.GOOD, "You have set the BOTD to: " + message);
			}
		}
		// Set Botd
		
		// Set Home
		if(name.equalsIgnoreCase("sethome")) {
			if (args.length == 0) {
				Messager.getInstance().msg(p, MessageType.BAD, "You must choose a name for your new house!");
			}
			
			BubbleHome.setHome(p, args[0]);
			Messager.getInstance().msg(p, MessageType.GOOD, "You have set this area as: " + args[0] + "!");
		}
		// Set Home
		return false;
	}
}