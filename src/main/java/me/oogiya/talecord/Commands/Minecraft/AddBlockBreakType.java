package me.oogiya.talecord.Commands.Minecraft;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.oogiya.talecord.DiscordHandler;
import me.oogiya.talecord.Constants.Announces;
import me.oogiya.talecord.Constants.Commands;
import me.oogiya.talecord.Utils.EnumLists;

public class AddBlockBreakType implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
		if (!cmd.getName().equalsIgnoreCase(Commands.ADD_BLOCK_BREAK_TYPE)) return false;
		
		if (sender instanceof Player) {
			Player player = (Player)sender;
			if (player.isOp()) {
				
				if (args.length < 1) {
					player.sendMessage("Please enter a valid block type debug: ");
					return false;
				}
				
				if (EnumLists.MATERIAL_TYPES.contains(args[0])) {
					Material mat = Material.getMaterial(args[0]);
					if (!Announces.BLOCKS.contains(mat)) {
						
						Announces.BLOCKS.add(mat);
						
						//Send in minecraft
						player.sendMessage("Started following break event for: " + args[0]);
						player.sendMessage("Currently following: " + Announces.BLOCKS.toString());
						
						//Send in discord
						DiscordHandler.sendMessage(Announces.MINECRAFT_TO_DISCORD_PERFIX + 
								"Started following break event for: " + args[0]);
						DiscordHandler.sendMessage(Announces.MINECRAFT_TO_DISCORD_PERFIX + 
								"Currently following: " + Announces.BLOCKS.toString());
						return true;
						
					} else {
						player.sendMessage("Block already exists.");
					}
					
				} else {
					player.sendMessage("Please enter a valid block type debug: " + args[0]);
					player.sendMessage(EnumLists.MATERIAL_TYPES.toString());
				}
				
			} else {
				player.sendMessage("Insufficient permissions");
			}
		} else {
			sender.sendMessage("Player type only command.");
		}
		
		return false;
	}

	
	
}
