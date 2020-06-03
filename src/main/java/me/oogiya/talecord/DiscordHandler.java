package me.oogiya.talecord;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;

import com.google.common.util.concurrent.ServiceManager.Listener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordHandler extends ListenerAdapter{

	private static JDA jda = null;
	
	public static Boolean ready = false;
	
	public static TextChannel mainChannel = null;
	
	public DiscordHandler() {

	}
	
	public void discordConnection(String secret) {
		try {
			
			jda = new JDABuilder().setToken(secret).setActivity(Activity.watching("kaki"))
					.build();
			jda.awaitReady();
			ready = true;
			
		} catch (IllegalArgumentException | LoginException | InterruptedException e) {
			Main.getPlugin().getLogger().severe("Error connection to discord");
			Bukkit.getPluginManager().disablePlugin(Main.getPlugin());
			return;
		}

		mainChannel = getChannel(Main.getPlugin().getConfig().getString("defaultChannel"));
		//jda.getPresence().setActivity(Activity.watching(String.valueOf(mainChannel.canTalk())));

	}
	
	public static void sendMessage(String message) {
		
		mainChannel.sendMessage(message).queue();
		
	}
	
	private TextChannel getChannel(String name){
		if (jda == null) return null;
		for (Guild g : jda.getGuilds())
			for (TextChannel c : g.getTextChannels()) {
				if (c.getName().equals(name)) return c;
			}
			return null;
        }
	
}
