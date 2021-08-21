package me.adriianhdev.adriianhessentials;

import me.adriianhdev.adriianhessentials.commands.GamemodeCommand;
import me.adriianhdev.adriianhessentials.commands.KillCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdriianhEssentials extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
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
    }

    public String color(String  text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
