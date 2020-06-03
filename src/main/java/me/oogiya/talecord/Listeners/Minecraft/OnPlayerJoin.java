package me.oogiya.talecord.Listeners.Minecraft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.oogiya.talecord.DiscordHandler;

public class OnPlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoinServer(PlayerJoinEvent e) {
	
		if (DiscordHandler.ready) {
			DiscordHandler.sendMessage(e.getPlayer().getDisplayName() + " has joined the server");
		}
		
	}
	
}
