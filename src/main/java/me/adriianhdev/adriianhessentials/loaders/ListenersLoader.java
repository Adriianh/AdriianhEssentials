package me.adriianhdev.adriianhessentials.loaders;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
import me.adriianhdev.adriianhessentials.PluginCore;
import me.adriianhdev.adriianhessentials.api.Loader;
import me.adriianhdev.adriianhessentials.listeners.PlayerJoinListener;
import me.adriianhdev.adriianhessentials.listeners.PlayerQuitListener;
import org.bukkit.event.Listener;

public class ListenersLoader implements Loader {

    private final PluginCore pluginCore;

    public ListenersLoader(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {
        pluginCore.getPlugin().getServer().getLogger().info("Registering events...");
        registerListeners(
                new PlayerQuitListener(pluginCore),
                new PlayerJoinListener(pluginCore)
        );
    }

    private void registerListeners(Listener...listeners) {
        AdriianhEssentials plugin = pluginCore.getPlugin();
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(
                    listener,
                    plugin
            );
        }
    }
}
