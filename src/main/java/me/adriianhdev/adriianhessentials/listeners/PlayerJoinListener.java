package me.adriianhdev.adriianhessentials.listeners;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
import me.adriianhdev.adriianhessentials.managers.FileManager;
import me.adriianhdev.adriianhessentials.utils.AdventureUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final FileManager messages;

    public PlayerJoinListener(AdriianhEssentials plugin) {
        this.messages = plugin.getFiles().getMessages();
    }

    @EventHandler
    private void playerJoinMessage(PlayerJoinEvent event) {
        if (!messages.getBoolean("join-events.message.enable")) return;

        event.joinMessage(AdventureUtil.parse(
                messages.getString("join-events.message.text")
                        .replaceAll("%player%", event.getPlayer().getName())
                        .replaceAll("%displayname%", event.getPlayer().displayName().toString())
        ));
    }
}
