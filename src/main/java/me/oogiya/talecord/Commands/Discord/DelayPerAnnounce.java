package me.oogiya.talecord.Commands.Discord;

import me.oogiya.talecord.DiscordHandler;
import me.oogiya.talecord.Constants.Announces;
import me.oogiya.talecord.Constants.Commands;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DelayPerAnnounce extends ListenerAdapter {

	private void getUsageMessage() {
		DiscordHandler.sendMessage("Usage: " + Commands.COMMAND_PREFIX + Commands.CHANGE_ANNOUNCE_DELAY
				+ " [seconds]");
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		
		if (e.getMessage().getContentDisplay().startsWith(Commands.COMMAND_PREFIX + Commands.CHANGE_ANNOUNCE_DELAY)) {
			
			String[] msg = e.getMessage().getContentRaw().split(" ");
			
			if (msg.length >= 2) {
				
				if (msg[1].chars().allMatch(Character::isDigit)) {
					
					DiscordHandler.sendMessage("Announce delay changed from " + Announces.TIME_BETWEEN_ANNOUNCEMENTS
							+ " to " + msg[1]);
					Announces.TIME_BETWEEN_ANNOUNCEMENTS = Integer.valueOf(msg[1]);
					
					
				} else {
					getUsageMessage();
				}
				
			} else {
				getUsageMessage();
			}
			
		}
		
	}
	
}
