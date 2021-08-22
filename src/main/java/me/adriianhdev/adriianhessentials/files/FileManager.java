package me.adriianhdev.adriianhessentials.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private static File file;
    private static FileConfiguration messages;

    //Finds or generate the custom file messages.
    public static void setup() {
        file = new File(Bukkit.getServer()
                              .getPluginManager()
                              .getPlugin("AdriianhEssentials")
                              .getDataFolder(), "messages.yml");

        if(!file.exists()){
            try{
                file.createNewFile();
            }catch(IOException e){
                //
            }

        }

        messages = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return messages;
    }

    public static void save(){
        try{
            messages.save(file);
        }catch(IOException e){
            Bukkit.getLogger().warning("Couldn't save file");
        }
    }

    public static void reload(){
        messages = YamlConfiguration.loadConfiguration(file);
    }
}
