package me.adriianhdev.adriianhessentials.listeners;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
import me.adriianhdev.adriianhessentials.managers.FileManager;
import me.adriianhdev.adriianhessentials.utils.AdventureUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final FileManager config;

    public PlayerJoinListener(AdriianhEssentials plugin) {
        this.config = plugin.getFiles().getConfig();
    }

    @EventHandler
    private void playerJoinMessage(PlayerJoinEvent event) {
        if (!config.getBoolean("join-events.message.enable")) return;

        event.joinMessage(AdventureUtil.parse(
                config.getString("join-events.message.line")
                        .replaceAll("%player%", event.getPlayer().getName())
                        .replaceAll("%displayname%", event.getPlayer().displayName().toString())
        ));
    }
}
