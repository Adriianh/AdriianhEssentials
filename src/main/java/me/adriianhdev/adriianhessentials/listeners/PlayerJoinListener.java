package me.adriianhdev.adriianhessentials.listeners;

import me.adriianhdev.adriianhessentials.PluginCore;
import me.adriianhdev.adriianhessentials.loaders.FilesLoader;
import me.adriianhdev.adriianhessentials.utils.AdventureUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final PluginCore pluginCore;

    public PlayerJoinListener(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @EventHandler
    private void playerJoinMessage(PlayerJoinEvent event) {
        FilesLoader messages = pluginCore.getFilesLoader();
        if (!messages.getConfig().getBoolean("join-events.message.enable")) return;

        event.joinMessage(AdventureUtil.parse(
                messages.getConfig().getString("join-events.message.text")
                        .replaceAll("%player%", event.getPlayer().getName())
                        .replaceAll("%displayname%", event.getPlayer().displayName().toString())
        ));
    }
}
