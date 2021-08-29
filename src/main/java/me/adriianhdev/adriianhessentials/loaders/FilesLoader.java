package me.adriianhdev.adriianhessentials.loaders;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
import me.adriianhdev.adriianhessentials.api.Loader;
import me.adriianhdev.adriianhessentials.managers.FileManager;

public class FilesLoader implements Loader {

    private final AdriianhEssentials plugin;

    private FileManager config;
    private FileManager messages;

    public FilesLoader(AdriianhEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        plugin.getLogger().info("Registering files...");
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
