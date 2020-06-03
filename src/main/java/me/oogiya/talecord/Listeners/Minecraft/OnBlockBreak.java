package me.oogiya.talecord.Listeners.Minecraft;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.oogiya.talecord.DiscordHandler;
import me.oogiya.talecord.Main;
import me.oogiya.talecord.Constants.Announces;

public class OnBlockBreak implements Listener {

	
	private HashMap<UUID, Integer> taskPerPlayer = new HashMap<UUID, Integer>();
	private HashMap<UUID, Integer> tickPerPlayer = new HashMap<UUID, Integer>();
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (Announces.BLOCKS.contains(e.getBlock().getType())) {
			if (DiscordHandler.ready) {
				if (!taskPerPlayer.containsKey(e.getPlayer().getUniqueId())) {
					DiscordHandler.sendMessage(e.getPlayer().getDisplayName() + " has mined " + e.getBlock().getType().name());	
					addToCounter(e.getPlayer().getUniqueId());
				}
			}
		}
	}
	
	private void addToCounter(UUID uuid) {
		
		if (!taskPerPlayer.containsKey(uuid)) {
			taskPerPlayer.put(uuid, 0);
			new BukkitRunnable() {
				int counter = Announces.TIME_BETWEEN_ANNOUNCEMENTS;
				@Override
				public void run() {
					if (counter <= 0) {
						taskPerPlayer.remove(uuid);
						this.cancel();
					}
					counter--;
					
				}
				
			}.runTaskTimer(Main.getPlugin(), 0L, 20L);
		}
	}
	
}
