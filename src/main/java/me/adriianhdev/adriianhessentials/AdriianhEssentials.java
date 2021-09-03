package me.adriianhdev.adriianhessentials;

import me.adriianhdev.adriianhessentials.api.Loader;
import me.adriianhdev.adriianhessentials.commands.*;
import me.adriianhdev.adriianhessentials.loaders.FilesLoader;
import me.adriianhdev.adriianhessentials.loaders.ListenersLoader;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdriianhEssentials extends JavaPlugin {

    private final FilesLoader filesLoader = new FilesLoader(this);

    @Override
    public void onEnable() {

        // Plugin startup logic
        getLogger().info("");
        getLogger().info("Loading AdriianhEssentials...");
        getLogger().info("This won't take long.");
        getLogger().info("");
        this.initLoaders(
                this.filesLoader,
                new ListenersLoader(this)
        );
        getLogger().info("Registering commands...");
        this.registerCommands();

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

    public void registerCommands(){
        //Command register
        getCommand("gamemode")
                .setExecutor(new GamemodeCommand(this));
        getCommand("kill")
                .setExecutor(new KillCommand(this));
        getCommand("fly")
                .setExecutor(new FlyCommand(this));
        getCommand("god")
                .setExecutor(new GodModeCommand(this));
        getCommand("adriianhessentials")
                .setExecutor(new MainCommand(this));
    }

    private void initLoaders(Loader...loaders) {
        for(Loader loader : loaders) {
            loader.load();
        }
    }

    public FilesLoader getFiles() {
        return filesLoader;
    }
}
