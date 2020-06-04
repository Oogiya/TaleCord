package me.oogiya.talecord;


import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.oogiya.talecord.Commands.Minecraft.AddBlockBreakType;
import me.oogiya.talecord.Commands.Minecraft.RemoveBlockBreakType;
import me.oogiya.talecord.Constants.Announces;
import me.oogiya.talecord.Constants.InitiateConstants;
import me.oogiya.talecord.Listeners.Minecraft.OnBlockBreak;
import me.oogiya.talecord.Listeners.Minecraft.OnPlayerJoin;
import me.oogiya.talecord.Utils.EnumLists;

public class Main extends JavaPlugin{
	
	public static Main instance;
	
	private DiscordHandler discord;
	
	public void onEnable() {
		instance = this;
		
		saveDefaultConfig();
		
		InitiateConstants.init();
		EnumLists.initiateMaterialTypes();
		
		
		getLogger().info("Blocks: " + Announces.BLOCKS);
		discord = new DiscordHandler();
		discord.discordConnection(Main.getPlugin().getConfig().getString("discordKey"));
		
		registerEvents();
		registerCommands();
	}
	
	public void registerCommands() {
		getCommand("addbtype").setExecutor(new AddBlockBreakType());
		getCommand("removebtype").setExecutor(new RemoveBlockBreakType());
	}
	
	public void onDisable() {
		DiscordHandler.disconnectDiscord();
		Bukkit.getPluginManager().disablePlugin(this);
	}
	
	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new OnPlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new OnBlockBreak(), this);
	}
	
	public static Main getPlugin() {
		return instance;
		
	}
	
	
}
