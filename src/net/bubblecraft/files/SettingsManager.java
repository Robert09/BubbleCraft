package net.bubblecraft.files;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class SettingsManager {

	static SettingsManager instance = new SettingsManager();
	private FileConfiguration config;
	private File cFile;

	public static SettingsManager getInstance() {
		return instance;
	}

	private SettingsManager() {

	}

	// Setting up the config file.
	public void setup(Plugin p) {
		config = p.getConfig();
		config.options().copyDefaults(true);
		cFile = new File(p.getDataFolder(), "config.yml");

		if (!cFile.exists()) {
			loadDefaults();
		}

		saveConfig();
	}

	// Load defaults for config file.
	private void loadDefaults() {
		String[] players = {};
		config.addDefault("BOTD", "Welcome to our %s! You are in the world, %w");
		config.addDefault("Players.list", Arrays.asList(players));
	}

	// Get the config file.
	public FileConfiguration getConfig() {
		return config;
	}

	// Save the config file.
	public void saveConfig() {
		try {
			config.save(cFile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger()
					.severe(ChatColor.RED + "Could not save config.yml");
		}
	}

	// Reload the config file.
	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(cFile);
	}
}