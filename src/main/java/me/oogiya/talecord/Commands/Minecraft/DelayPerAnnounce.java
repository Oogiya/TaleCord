package me.oogiya.talecord.Commands.Minecraft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.oogiya.talecord.DiscordHandler;
import me.oogiya.talecord.Constants.Announces;
import me.oogiya.talecord.Constants.Commands;

public class DelayPerAnnounce implements CommandExecutor {

	private void getUsageMessage(Player player) {
		player.sendMessage("Usage: " + Commands.COMMAND_PREFIX + Commands.CHANGE_ANNOUNCE_DELAY
				+ " [seconds]");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (!cmd.getName().toLowerCase().equals(Commands.CHANGE_ANNOUNCE_DELAY)) return false;
		
		if (sender instanceof Player) {
			
			Player player = (Player)sender;
			
			if (player.isOp()) {
				
				if (args.length >= 1) {
					
					if (args[0].chars().allMatch(Character::isDigit)) {
						
						//Discord message
						DiscordHandler.sendMessage(Announces.MINECRAFT_TO_DISCORD_PERFIX + 
								"Announce delay changed from " + Announces.TIME_BETWEEN_ANNOUNCEMENTS
								+ " to " + args[0]);
						
						//Minecraft message
						player.sendMessage("Announce delay changed from " + Announces.TIME_BETWEEN_ANNOUNCEMENTS
								+ " to " + args[0]);
						Announces.TIME_BETWEEN_ANNOUNCEMENTS = Integer.valueOf(args[0]);
						
						return true;
						
					} else {
						getUsageMessage(player);
					}
					
				} else {
					getUsageMessage(player);
				}
				
			} else {
				player.sendMessage("Insufficient permissions");
			}
			
		} else { 
			sender.sendMessage("Unable to execute command");
		}
		
		return false;
	}

	
	
}
