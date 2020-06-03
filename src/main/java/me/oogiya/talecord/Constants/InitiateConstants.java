package me.oogiya.talecord.Constants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import me.oogiya.talecord.Main;

public class InitiateConstants {

	public static void init() {
		
		initiateBlocks();

		
	}
	
	private static void initiateBlocks() {
		List<Material> materials = new ArrayList<>();
		List<String> stringMaterials = Main.getPlugin().getConfig().getStringList("blocksToAnnounce");
		for (String mat : stringMaterials) {
			if (Material.getMaterial(mat) != null) {
				materials.add(Material.getMaterial(mat));
			}
		}
		if (materials != null) Announces.BLOCKS = materials;
	}
	
}
