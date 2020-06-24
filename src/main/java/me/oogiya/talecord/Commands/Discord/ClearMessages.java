package me.oogiya.talecord.Commands.Discord;

import java.util.List;

import me.oogiya.talecord.DiscordHandler;
import me.oogiya.talecord.Constants.Commands;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ClearMessages extends ListenerAdapter{

	private void runCommand(Message discordMessage) {

		String[] msg = discordMessage.getContentRaw().split(" ");
		
		if (msg.length >= 2) {
			int msgAmount;
			try {
				msgAmount = Integer.parseInt(msg[1]);
			} catch (NumberFormatException ex) {
				msgAmount = 0;
			}
			//TODO: add exception
			List<Message> messages = discordMessage.getTextChannel().getHistory().retrievePast(msgAmount).complete();
			discordMessage.getTextChannel().deleteMessages(messages).queue();;
			
		} else {
			DiscordHandler.sendMessage("Specify message amount");
		}
		
	}
	
	

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		if (e.getMessage().getContentDisplay().startsWith(Commands.COMMAND_PREFIX + Commands.CLEAR)) {
			if (!e.getAuthor().isBot()) {
				runCommand(e.getMessage()); //TODO: add permissions
			}
		}
	}
	
}
