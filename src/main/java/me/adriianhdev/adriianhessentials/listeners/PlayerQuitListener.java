package me.adriianhdev.adriianhessentials.listeners;

import me.adriianhdev.adriianhessentials.managers.FileManager;
import me.adriianhdev.adriianhessentials.utils.AdventureUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final FileManager config;

    public PlayerQuitListener(FileManager config) {
        this.config = config;
    }

    @EventHandler
    private void quitPlayerMessage(PlayerQuitEvent event) {
        if (!config.getBoolean("quit-events.message.enable")) return;

        event.quitMessage(AdventureUtil.parse(
                config.getString("quit-events.message.line")
                        .replaceAll("%player%", event.getPlayer().getName())
                        .replaceAll("%displayname%", event.getPlayer().displayName().toString())
        ));

    }
}
