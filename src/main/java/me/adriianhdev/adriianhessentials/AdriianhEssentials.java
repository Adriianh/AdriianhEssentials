package me.adriianhdev.adriianhessentials;

import me.adriianhdev.adriianhessentials.commands.FlyCommand;
import me.adriianhdev.adriianhessentials.commands.GamemodeCommand;
import me.adriianhdev.adriianhessentials.commands.KillCommand;
import me.adriianhdev.adriianhessentials.files.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdriianhEssentials extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        //Setup config files
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        FileManager.setup();
        FileManager.get().getDefaults();
        FileManager.get().options().copyDefaults(true);
        FileManager.save();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void commandRegister(){
        getCommand("gamemode")
                .setExecutor(new GamemodeCommand());
        getCommand("kill")
                .setExecutor(new KillCommand());
        getCommand("fly")
                .setExecutor(new FlyCommand(this));
    }

    public String color(String  text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
