package me.oogiya.talecord.Commands.Discord;

import me.oogiya.talecord.DiscordHandler;
import me.oogiya.talecord.Constants.Announces;
import me.oogiya.talecord.Constants.Commands;
import me.oogiya.talecord.Constants.Messages;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DelayPerAnnounce extends ListenerAdapter {

	private void runCommand(String[] msg) {
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
	
	private void getUsageMessage() {
		DiscordHandler.sendMessage("Usage: " + Commands.COMMAND_PREFIX + Commands.CHANGE_ANNOUNCE_DELAY
				+ " [seconds]");
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		
		if (e.getMessage().getContentDisplay().startsWith(Commands.COMMAND_PREFIX + Commands.CHANGE_ANNOUNCE_DELAY)) {
			if (!e.getAuthor().isBot()) {
				Member member = e.getMember();
				if (DiscordHandler.permissionHandler.isMemberHasCommandPermission(member.getRoles(), Commands.CHANGE_ANNOUNCE_DELAY)) {
					runCommand(e.getMessage().getContentRaw().split(" "));
				} else {
					DiscordHandler.sendMessage(Messages.INSUFFICIENT_PERMISSIONS);
				}
			}
		}
		
	}
	
}
