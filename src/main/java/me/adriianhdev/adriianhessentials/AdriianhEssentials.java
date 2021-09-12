package me.adriianhdev.adriianhessentials;

import me.adriianhdev.adriianhessentials.api.Loader;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdriianhEssentials extends JavaPlugin {

    private final PluginCore core = new PluginCore(this);

    @Override
    public void onEnable() {

        // Plugin startup logic
        getLogger().info("");
        getLogger().info("Loading AdriianhEssentials...");
        getLogger().info("This won't take long.");
        getLogger().info("");
        this.core.init();

        getLogger().info("");
        getLogger().info("Successful loading!");
        getLogger().info("Plugin version: " + getDescription().getVersion());
        getLogger().info("Author: " + getDescription().getAuthors());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("");
        getLogger().info("Unloading AdriianhEssentials...");
        getLogger().info("");
        getLogger().info("Plugin disabled. Thanks for using!");
    }
}
