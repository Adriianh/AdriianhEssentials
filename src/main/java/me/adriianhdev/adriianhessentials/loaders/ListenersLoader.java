package me.adriianhdev.adriianhessentials.loaders;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
import me.adriianhdev.adriianhessentials.api.Loader;
import me.adriianhdev.adriianhessentials.listeners.PlayerJoinListener;
import me.adriianhdev.adriianhessentials.listeners.PlayerQuitListener;
import org.bukkit.event.Listener;

public class ListenersLoader implements Loader {

    private final AdriianhEssentials plugin;

    public ListenersLoader(AdriianhEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        plugin.getLogger().info("Registering events...");
        registerListeners(
                new PlayerJoinListener(plugin),
                new PlayerQuitListener(plugin)
        );
    }

    private void registerListeners(Listener...listeners) {
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(
                    listener,
                    plugin
            );
        }
    }
}
