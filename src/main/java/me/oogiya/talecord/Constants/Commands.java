package me.oogiya.talecord.Constants;

import org.bukkit.configuration.file.FileConfiguration;

import me.oogiya.talecord.Main;

public class Commands {

	private static FileConfiguration config = Main.getPlugin().getConfig();
	
	public static String COMMAND_PREFIX = config.getString("discordCommandPrefix");
	
	public static String ADD_BLOCK_BREAK_TYPE = config.getString("discordAddBlockBreakTypeCommand");
	
	public static String REMOVE_BLOCK_BREAK_TYPE = config.getString("discordRemoveBlockBreakTypeCommands");
	
	public static String CHANGE_ANNOUNCE_DELAY = config.getString("changeDelayPerAnnounce");
	
	public static String CLEAR = "clear"; // TODO: add to config!!!
	
}
