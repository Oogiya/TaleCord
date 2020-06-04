package me.oogiya.talecord.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import me.oogiya.talecord.Main;

public class Announces {

	public static List<Material> BLOCKS = new ArrayList<>();
	
	public static int TIME_BETWEEN_ANNOUNCEMENTS = Main.getPlugin().getConfig().getInt("delayPerAnnounce");
	
	public static String MINECRAFT_TO_DISCORD_PERFIX = "[Game]";
	
}
