package me.adriianhdev.adriianhessentials.commands;

import me.adriianhdev.adriianhessentials.PluginCore;
import me.adriianhdev.adriianhessentials.inventories.ArmorStandGUI;
import me.adriianhdev.adriianhessentials.managers.FileManager;
import me.adriianhdev.adriianhessentials.utils.SoundUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class ArmorStandCommand implements CommandExecutor {

    ArmorStandGUI gui = new ArmorStandGUI();

    private final PluginCore pluginCore;
    private final FileManager messages;

    public ArmorStandCommand(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
        this.messages = pluginCore.getFilesLoader().getMessages();
    }

    //Default valor of Sounds
    String sound = "ENTITY.EXPERIENCE_ORB.PICKUP";
    float volume = 1f;
    float pitch = 1f;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getLogger().info(messages.getString("error.message.non-player"));
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(messages.getString("armorstand-gui.message.text"));

        gui.openMenu(player);
        SoundUtil.playSound(
                sound,
                player,
                volume,
                pitch);

        return true;
    }
}
