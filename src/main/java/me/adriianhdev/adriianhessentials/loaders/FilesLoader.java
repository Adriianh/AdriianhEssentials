package me.adriianhdev.adriianhessentials.loaders;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
import me.adriianhdev.adriianhessentials.PluginCore;
import me.adriianhdev.adriianhessentials.api.Loader;
import me.adriianhdev.adriianhessentials.managers.FileManager;
import org.bukkit.Bukkit;

public class FilesLoader implements Loader {

    private final AdriianhEssentials plugin;

    private FileManager config;
    private FileManager messages;

    public FilesLoader(PluginCore pluginCore){
        this.plugin = pluginCore.getPlugin();
    }

    @Override
    public void load() {
        Bukkit.getLogger().info("Registering files...");
        this.config = new FileManager(plugin, "config.yml");
        this.messages = new FileManager(plugin, "messages.yml");
    }

    public FileManager getConfig() {
        return config;
    }

    public FileManager getMessages() {
        return messages;
    }
}
