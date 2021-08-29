package me.adriianhdev.adriianhessentials.managers;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FileManager extends YamlConfiguration {

    // The following variables are taken into account to allow the creation of the custom file
    private final String fileName; // This is a name of custom file
    private final File file; // This is object of custom file
    private final AdriianhEssentials plugin; // This plugin that generating file

    /*
     * Constructor #1
     *
     * This constructor will be in charge of assigning the necessary values
     * for the creation of the file, also to create them once it is invoked
     */
    private FileManager(AdriianhEssentials plugin, String fileName, String fileExtension, File folder) {
        this.plugin = plugin;
        this.fileName = fileName + (fileName.endsWith(fileExtension) ? "" : ".yml");
        this.file = new File(folder, fileName);
        this.create();
    }

    /*
     * Constructor #2
     *
     *  This second constructor is in charge of invoking the constructor one, likewise it is in
     * charge of giving the values to the constructor one providing the
     * location of the file from the plugin, also reducing the size of the constructor.
     */
    private FileManager(AdriianhEssentials plugin, String fileName, String fileExtension) {
        this(plugin, fileName, fileExtension, plugin.getDataFolder());
    }
    /*
     * Constructor #3
     *
     *  This last constructor and also important allows you to create your
     * customized file as such, it is in charge of assigning the most basic values, it is also in
     * charge of invoking the constructor number 2 thus completing the sequence.
     */
    public FileManager(AdriianhEssentials plugin, String fileName) {
        this(plugin, fileName, ".yml");
    }

    // This method is responsible for creating the file avoiding certain exceptions.
    public void create() {
        try {
            if (this.file.exists()) {
                load(file);
                save(file);
                return;
            }
            if (this.plugin.getResource(fileName) != null) {
                this.plugin.saveResource(this.fileName, false);
            } else {
                save(file);
            }
            load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }

    // This method takes care of reloading the file avoiding certain exceptions.
    public void reload() {
        try {
            load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    // This method is in charge of saving the file avoiding certain exceptions.
    public void save() {
        try {
            save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getString(String path) {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(super.getString(path)));
    }
}
