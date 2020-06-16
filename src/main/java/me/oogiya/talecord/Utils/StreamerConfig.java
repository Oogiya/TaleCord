package me.oogiya.talecord.Utils;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.oogiya.talecord.Main;

public class StreamerConfig {

	private FileConfiguration STREAMERS;

	private File file;

	private String minecraftName = "";
	private String youtubeName = "";
	private String apiToken = "";

	public StreamerConfig() {
		config();
	}

	public String getMinecraftName() {
		return this.minecraftName;
	}

	public String getYoutubeName() {
		return this.youtubeName;
	}

	public String getApiToken() {
		return this.apiToken;
	}

	private void saveStreamersFile() {
		try {
			this.STREAMERS.save(this.file);
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}

	public void config() {
		file = new File(Main.getPlugin().getDataFolder() + "/streamers.yml");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		this.STREAMERS = YamlConfiguration.loadConfiguration(file);

		if (!this.STREAMERS.contains("Streamers")) {
			STREAMERS.set("Streamers.1.minecraftName", "[minecraftName]");
			STREAMERS.set("Streamers.1.youtubeName", "[youtubeName]");
			STREAMERS.set("Streamers.1.apiToken", "[apiToken]");

			saveStreamersFile();

		} else {
			ConfigurationSection section = this.STREAMERS.getConfigurationSection("Streamers");

			for (String streamer : section.getKeys(false)) {
				this.minecraftName = section.getString(streamer + ".minecraftName");
				this.youtubeName = section.getString(streamer + ".youtubeName");
				this.apiToken = section.getString(streamer + ".apiToken");
			}
		}
	}

}
