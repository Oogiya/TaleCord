package me.oogiya.talecord.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

public class EnumLists {

	public static List<String> MATERIAL_TYPES = new ArrayList<>();
	
	
	public static void initiateMaterialTypes() {
		
		for (Material mat : Material.values()) {
			MATERIAL_TYPES.add(mat.name());
		}
		
	}
	
}
