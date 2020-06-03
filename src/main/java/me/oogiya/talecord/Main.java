package me.oogiya.talecord;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.oogiya.talecord.Constants.Announces;
import me.oogiya.talecord.Constants.InitiateConstants;
import me.oogiya.talecord.Listeners.Minecraft.OnBlockBreak;
import me.oogiya.talecord.Listeners.Minecraft.OnPlayerJoin;

public class Main extends JavaPlugin{
	
	public static Main instance;
	
	private DiscordHandler discord;
	
	public void onEnable() {
		instance = this;
		
		saveDefaultConfig();
		
		InitiateConstants.init();
		
		getLogger().info("Blocks: " + Announces.BLOCKS);
		discord = new DiscordHandler();
		discord.discordConnection(Main.getPlugin().getConfig().getString("discordKey"));
		
		registerEvents();
	}
	
	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new OnPlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new OnBlockBreak(), this);
	}
	
	public static Main getPlugin() {
		return instance;
		
	}
	
	
}
