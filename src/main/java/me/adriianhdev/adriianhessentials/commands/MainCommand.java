package me.adriianhdev.adriianhessentials.commands;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
import me.adriianhdev.adriianhessentials.managers.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {

    private final FileManager config;
    private final FileManager messages;

    public MainCommand(AdriianhEssentials plugin) {
        this.config = plugin.getFiles().getConfig();
        this.messages = plugin.getFiles().getMessages();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(args.length > 0)) {
            sender.sendMessage("Unknow command");
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                config.reload();
                messages.reload();
                break;
        }

        return true;
    }
}
