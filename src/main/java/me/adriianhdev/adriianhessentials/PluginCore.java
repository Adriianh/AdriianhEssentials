package me.adriianhdev.adriianhessentials;

import me.adriianhdev.adriianhessentials.api.Core;
import me.adriianhdev.adriianhessentials.api.Loader;
import me.adriianhdev.adriianhessentials.loaders.CommandsLoader;
import me.adriianhdev.adriianhessentials.loaders.FilesLoader;
import me.adriianhdev.adriianhessentials.loaders.ListenersLoader;
import org.bukkit.Bukkit;


public class PluginCore implements Core {

    private final AdriianhEssentials plugin;

    private final FilesLoader filesLoader = new FilesLoader(this);


    public PluginCore(AdriianhEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init() {
        initLoaders(
                filesLoader,
                new CommandsLoader(this),
                new ListenersLoader(this)
        );

        Bukkit.getLogger().info("Registering commands...");
        Bukkit.getLogger().info("Registering events...");
    }

    private void initLoaders(Loader... loaders) {
        for (Loader loader : loaders) {
            loader.load();
        }
    }

    public FilesLoader getFilesLoader() {
        return filesLoader;
    }

    public AdriianhEssentials getPlugin() {
        return plugin;
    }

}
