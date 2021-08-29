package me.adriianhdev.adriianhessentials.listeners;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
import me.adriianhdev.adriianhessentials.managers.FileManager;
import me.adriianhdev.adriianhessentials.utils.AdventureUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final FileManager messages;

    public PlayerQuitListener(AdriianhEssentials plugin) {
        this.messages = plugin.getFiles().getMessages();
    }

    @EventHandler
    private void quitPlayerMessage(PlayerQuitEvent event) {
        if (!messages.getBoolean("quit-events.message.enable")) return;

        event.quitMessage(AdventureUtil.parse(
                messages.getString("quit-events.message.text")
                        .replaceAll("%player%", event.getPlayer().getName())
                        .replaceAll("%displayname%", event.getPlayer().displayName().toString())
        ));

    }
}
