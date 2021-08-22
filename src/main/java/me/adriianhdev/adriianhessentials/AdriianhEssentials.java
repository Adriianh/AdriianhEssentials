package me.adriianhdev.adriianhessentials;

import me.adriianhdev.adriianhessentials.commands.FlyCommand;
import me.adriianhdev.adriianhessentials.commands.GamemodeCommand;
import me.adriianhdev.adriianhessentials.commands.KillCommand;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class AdriianhEssentials extends JavaPlugin {

    private File customConfigFile;
    private FileConfiguration customConfig;

    @Override
    public void onEnable() {

        AdriianhEssentials plugin = this;
        // Plugin startup logic
        getLogger().info("");
        getLogger().info("Loading AdriianhEssentials...");
        getLogger().info("This won't take long.");
        getLogger().info("");

        getLogger().info("Registering commands...");
        this.registerCommands();
        getLogger().info("Registering files...");
        this.registerFiles();
        createCustomConfig();

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
    public void registerFiles(){
        //Setup config files
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Setup custom config files
    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "messages.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }

        customConfig= new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
