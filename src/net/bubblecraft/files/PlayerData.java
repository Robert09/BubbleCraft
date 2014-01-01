package net.bubblecraft.files;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerData {
	private YamlConfiguration pData;
	private File pDataFile;
	private String name;
	Logger log = Logger.getLogger("Minecraft");
	

	// Setting up the player file.
	public PlayerData(File pDataFile, String name) {
		this.pData = new YamlConfiguration();
		this.pDataFile = pDataFile;
		this.name = name;
	}

	// Loading the player file.
	public boolean load() {
		try {
			if (!pDataFile.exists()) {
				pDataFile.createNewFile();
				loadDefaults();
			}
			pData.load(pDataFile);
			save();
			return true;
		} catch (Exception e) {
			log.info("Player File For " + name + " Failed To Load Error Returned:/n" + e.getMessage());
			return false;
		}
	}
	
	// Saving the player file.
	public boolean save() {
		try {
			pData.save(pDataFile);
		} catch (IOException e) {
			log.info("Player File For " + name + " Failed To Save Error Returned:/n" + e.getMessage());
		}
		return true;
	}

	// Load the defaults of player file.
	private void loadDefaults() {
		pData.addDefault("Player.Name", name);
		pData.addDefault("Player.Chat Color", "§f");
		pData.options().copyDefaults(true);
	}
	
	// Set Strings.
	public void setString(String path, String item) {
		pData.set(path, item);
		save();
	}
	
	// Get Strings.
	public String getString(String path) {
		return path;
	}
	
	// Set Ints
	public void setInt(String path, int item) {
		pData.set(path, item);
		save();
	}
	
	// Get Ints.
	public int getInt(String path) {
		return pData.getInt(path);
	}
	
	// Set Worlds
	public void setWorld(String path, World world) {
		pData.set(path, world);
	}
	
	// Get Worlds
	public World getWorld(String path) {
		return (World) pData.get(path);
	}
}
