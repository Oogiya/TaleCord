package me.oogiya.talecord.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.oogiya.talecord.Main;
import net.dv8tion.jda.api.entities.Role;

public class DiscordPermissionsHandler {

	private FileConfiguration PERMISSIONS;
	private HashMap<String, List<String>> ROLES; // Key: Role name // Value: Permissions

	private File file;

	public DiscordPermissionsHandler() {
		ROLES = new HashMap<>();
		initializePermissions();
	}

	public boolean isMemberHasCommandPermission(List<Role> roles, String command) {

		for (Role role : roles) {
			if (ROLES.keySet().contains(role.getName())) {
				if (ROLES.get(role.getName()).contains(command)) {
					return true;
				}
			}
		}

		return false;
	}

	private void savePermissionFile() {
		try {
			PERMISSIONS.save(this.file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void initializePermissions() {

		file = new File(Main.getPlugin().getDataFolder() + "/rolemanager.yml");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		PERMISSIONS = YamlConfiguration.loadConfiguration(file);

		if (!PERMISSIONS.contains("discordroles")) {

			PERMISSIONS.set("discordroles.1.name", "[rolename]");
			PERMISSIONS.set("discordroles.1.permissions",
					new ArrayList<String>(Arrays.asList("[permission1]", "[permission2]")));

			savePermissionFile();

		} else {
			ConfigurationSection section = PERMISSIONS.getConfigurationSection("discordroles");
			for (String perm : section.getKeys(false)) {

				ROLES.put(section.getString(perm + ".name"), section.getStringList(perm + ".permissions"));

			}
		}

	}

}
