package me.oogiya.talecord.Commands.Discord;

import org.bukkit.Material;

import me.oogiya.talecord.DiscordHandler;
import me.oogiya.talecord.Constants.Announces;
import me.oogiya.talecord.Constants.Commands;
import me.oogiya.talecord.Utils.EnumLists;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RemoveBlockBreakType extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		
		if (e.getMessage().getContentDisplay().startsWith(Commands.COMMAND_PREFIX + Commands.REMOVE_BLOCK_BREAK_TYPE)) {
			
			String[] msg = e.getMessage().getContentRaw().split(" ");
			
			if (msg.length < 2) {
				DiscordHandler.sendMessage("Please enter a valid block type");
			}
			
			
			else if (!EnumLists.MATERIAL_TYPES.contains(msg[1])) {
				DiscordHandler.sendMessage("Please enter a valid block type");
			}
			
			else if (Announces.BLOCKS.contains(Material.valueOf(msg[1]))) {
				Material mat = Material.getMaterial(msg[1]);
				try {
					Announces.BLOCKS.remove(mat);
					DiscordHandler.sendMessage("Stopped following break event for: " + msg[1]);
					DiscordHandler.sendMessage("Currently following: " + Announces.BLOCKS.toString());
					
				} catch (Exception ex) {
					ex.getStackTrace();
				}
			} else {
				DiscordHandler.sendMessage("Please enter a valid block type");
			}
		}
		
		
		
	}
	
}
