package me.adriianhdev.adriianhessentials;

import me.adriianhdev.adriianhessentials.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class AdriianhEssentials extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("");
        getLogger().info("Loading AdriianhEssentials...");
        getLogger().info("This won't take long.");
        getLogger().info("");

        getLogger().info("Registering commands...");
        this.registerCommands();
        getLogger().info("Registering files...");
        this.registerFiles();
        getLogger().info("Registering events...");
        this.registerEvents();

        getLogger().info("");
        getLogger().info("Successful loading!");
        getLogger().info("Plugin version: "+getDescription().getVersion());
        getLogger().info("Author: "+getDescription().getAuthors());
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
                .setExecutor(new GamemodeCommand());
        getCommand("kill")
                .setExecutor(new KillCommand());
        getCommand("fly")
                .setExecutor(new FlyCommand(this));
    }

    public void registerEvents(){
    }

    public void registerFiles(){
        //Setup config files
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Setup custom config files
    }
}
