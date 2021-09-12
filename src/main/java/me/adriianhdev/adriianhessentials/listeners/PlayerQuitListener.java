package me.adriianhdev.adriianhessentials.listeners;

import me.adriianhdev.adriianhessentials.PluginCore;
import me.adriianhdev.adriianhessentials.loaders.FilesLoader;
import me.adriianhdev.adriianhessentials.utils.AdventureUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final PluginCore pluginCore;

    public PlayerQuitListener(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @EventHandler
    private void quitPlayerMessage(PlayerQuitEvent event) {
        FilesLoader messages = pluginCore.getFilesLoader();
        if (!messages.getConfig().getBoolean("quit-events.message.enable")) return;

        event.quitMessage(AdventureUtil.parse(
                messages.getConfig().getString("quit-events.message.text")
                        .replaceAll("%player%", event.getPlayer().getName())
                        .replaceAll("%displayname%", event.getPlayer().displayName().toString())
        ));

    }
}
