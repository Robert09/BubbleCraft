package net.bubblecraft.main;

import java.io.File;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import net.bubblecraft.files.PlayerData;
import net.bubblecraft.files.SettingsManager;
import net.bubblecraft.handlers.BubbleListener;
import net.bubblecraft.handlers.BubblePerms;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
		
		// Config stuff.
		settings.setup(this);
		settings.getConfig().set("version", ver);
	}

	public void onDisable() {
		// Tells the console that the plugin has been disabled.
		PluginDescriptionFile pdfFile = this.getDescription();
		logger.info(pdfFile.getName() + " " + pdfFile.getVersion() + " is now disabled!");
	}
	
	// Player name.
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

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player p = (Player) sender;

		if (sender instanceof Player) {
			// Random Teleport
			if (label.equalsIgnoreCase("rtp")) {
				Random rand = new Random();

				int n = 0 + 50000 + 1;
				int x = rand.nextInt() % n;
				int z = rand.nextInt() % n;

				Location loc = new Location(p.getWorld(), x, 60, z);
				p.teleport(loc);
				p.sendMessage(ChatColor.GOLD + "New coords: X: " + x + ", Y: 60" + ", Z: " + z);
			}

			// Version
			if (label.equalsIgnoreCase("bversion")) {
				p.sendMessage(ChatColor.GREEN + settings.getConfig().getString("version"));
			}

			// Inventory checker.
			if (label.equalsIgnoreCase("bubsinv") && p.hasPermission(bp.seeInv)) {
				if (args.length == 0) {
					p.sendMessage(ChatColor.RED + "You must choose someones Inventory dummy!");
				} else if (args.length == 1) {
					if (!p.getName().contains("holden98")) {
						Player target = getServer().getPlayer(args[0]);

						if (target != null) {
							p.openInventory(target.getInventory());
						} else {
							p.sendMessage(ChatColor.RED + "That player is offline");
						}
					} else {
						p.sendMessage(ChatColor.RED + "You do not have permission FOOL");
					}
				}
			}

			// Reload config file.
			if (label.equalsIgnoreCase("BubbleLoad") && p.hasPermission(bp.reload)) {
				settings.reloadConfig();
				p.sendMessage(ChatColor.GREEN + "BubbleCraft Plugin has been reloaded!");
			}

			// Get The Bubble Of The Day.
			if (label.equalsIgnoreCase("botd")) {
				String msg = settings.getConfig().getString("BOTD").replace("%s", p.getServer().getServerName()).replace("%w", p.getWorld().getName());
				p.sendMessage(ChatColor.AQUA + "[" + ChatColor.GOLD + "BubbleCraft" + ChatColor.AQUA + "]" + msg);
			}

			// Set The Bubble Of The Day.
			if (label.equalsIgnoreCase("setbotd") && p.hasPermission(bp.setbotd)) {
				if (args.length == 0) {
					p.sendMessage(ChatColor.RED + "You must choose what to set the BOTD to!");
				} else {
					String message = " ";
					for (String part : args) {
						if (message != "")
							message += " ";
						message += part;
					}
					settings.getConfig().set("BOTD", message);
					settings.saveConfig();
					p.sendMessage(ChatColor.GREEN + "You have set the BOTD to: " + message);
				}
			}
			
			// Set home
			if (label.equalsIgnoreCase("setbhome")) {
				if (args.length == 0) {
					p.sendMessage(ChatColor.DARK_RED + "You must choose a name!");
				} else if (args.length == 1) {
					bh.setHome(p, args[0]);
					p.sendMessage(ChatColor.GREEN + "You set your home as: " + args[0] + "!");
				}
			}
			
			// Go Home
			if(label.equalsIgnoreCase("bhome")) {
				if(args.length == 0) {
					p.sendMessage(ChatColor.DARK_RED + "You must choose a home to go to!");
				}else if(args.length == 1) {
					bh.getHome(p, args[0]);
					p.sendMessage(ChatColor.GREEN + "Welcome to: " + args[0] + "!");
				}
			}

		} else {
			sender.sendMessage(ChatColor.RED + "These commands must be exacuted by a player not console!");
		}

		return true;
	}
}