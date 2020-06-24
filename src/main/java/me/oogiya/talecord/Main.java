package me.oogiya.talecord;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.oogiya.talecord.Commands.Minecraft.AddBlockBreakType;
import me.oogiya.talecord.Commands.Minecraft.DelayPerAnnounce;
import me.oogiya.talecord.Commands.Minecraft.RemoveBlockBreakType;
import me.oogiya.talecord.Constants.Announces;
import me.oogiya.talecord.Constants.InitiateConstants;
import me.oogiya.talecord.Listeners.Minecraft.OnBlockBreak;
import me.oogiya.talecord.Listeners.Minecraft.OnPlayerJoin;
import me.oogiya.talecord.Network.SocketManager;
import me.oogiya.talecord.Network.StreamlabsManager;
import me.oogiya.talecord.Network.WebsocketServer;
import me.oogiya.talecord.Utils.EnumLists;

public class Main extends JavaPlugin {

	public static Main instance;

	private DiscordHandler discord;

	private SocketManager socketManager;

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

		WebsocketServer.runServer();
		StreamlabsManager labs = new StreamlabsManager("https://sockets.streamlabs.com");
		//socketManager = new SocketManager("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbiI6IkZDOUVFNTlCNUY5RUM4OEI2OERGIiwicmVhZF9vbmx5Ijp0cnVlLCJwcmV2ZW50X21hc3RlciI6dHJ1ZSwieW91dHViZV9pZCI6IlVDQnZ0UlhBa0JNME95MTJaZ3R1T2NCQSJ9.q5NPXGDWYdhq_WM3yBwZcFGSdMfAcbLgjsa-QoR6Br4");
	}

	public void registerCommands() {
		getCommand("addbtype").setExecutor(new AddBlockBreakType());
		getCommand("removebtype").setExecutor(new RemoveBlockBreakType());

		getCommand("delay").setExecutor(new DelayPerAnnounce());
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
