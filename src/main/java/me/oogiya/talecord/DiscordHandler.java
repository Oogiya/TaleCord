package me.oogiya.talecord;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import me.oogiya.talecord.Commands.Discord.AddBlockBreakType;
import me.oogiya.talecord.Commands.Discord.ClearMessages;
import me.oogiya.talecord.Commands.Discord.DelayPerAnnounce;
import me.oogiya.talecord.Commands.Discord.RemoveBlockBreakType;
import me.oogiya.talecord.Utils.DiscordPermissionsHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordHandler extends ListenerAdapter{

	private static JDA jda = null;
	
	public static Boolean ready = false;
	
	public static TextChannel mainChannel = null;
	
	public static DiscordPermissionsHandler permissionHandler;
	
	public DiscordHandler() {
		permissionHandler = new DiscordPermissionsHandler();
	}
	
	@SuppressWarnings("deprecation")
	public void discordConnection(String secret) {
		try {
			
			jda = new JDABuilder().setToken(secret).setActivity(Activity.listening("to some 8bit juice kekw"))
					.build();
			jda.awaitReady();
			ready = true;
			
		} catch (IllegalArgumentException | LoginException | InterruptedException e) {
			Main.getPlugin().getLogger().severe("Error connection to discord");
			Bukkit.getPluginManager().disablePlugin(Main.getPlugin());
			return;
		}
		
		jda.addEventListener(new RemoveBlockBreakType());
		jda.addEventListener(new AddBlockBreakType());
		jda.addEventListener(new DelayPerAnnounce());
		jda.addEventListener(new ClearMessages());
		
		mainChannel = getChannel(Main.getPlugin().getConfig().getString("defaultChannel"));
	}
	
	public static void disconnectDiscord() {
		if (jda != null) {
            CompletableFuture<Void> shutdownTask = new CompletableFuture<>();
            jda.addEventListener(new ListenerAdapter() {
                @Override
                public void onShutdown(@NotNull ShutdownEvent event) {
                    shutdownTask.complete(null);
                }
            });
            jda.shutdown();
            try {
                shutdownTask.get(5, TimeUnit.SECONDS);
            } catch (TimeoutException | InterruptedException | ExecutionException e) {
                Main.getPlugin().getLogger().warning("JDA took too long to shut down, skipping");
            }
        }
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
