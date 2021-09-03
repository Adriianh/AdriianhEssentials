package me.adriianhdev.adriianhessentials.commands;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
import me.adriianhdev.adriianhessentials.managers.FileManager;
import me.adriianhdev.adriianhessentials.utils.AdventureUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {

    private final FileManager config;
    private final FileManager messages;
    private final PluginDescriptionFile description;

    public MainCommand(AdriianhEssentials plugin) {
        this.config = plugin.getFiles().getConfig();
        this.messages = plugin.getFiles().getMessages();
        this.description = plugin.getDescription();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!(args.length > 0)) {
            sender.sendMessage(messages.getComponent("error.messages.non-argument"));
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                config.reload();
                messages.reload();
                sender.sendMessage(messages.getComponent("messages.reload-config"));
                break;
            case "help":
                for(String string : messages.getStringList("messages.command-help")) {
                    sender.sendMessage(AdventureUtil.parse(string));
                }
                break;
            case "about":
                sender.sendMessage(
                        AdventureUtil.parse("<gradient:green:blue>AdriianhEssentials</gradient> <red>v"
                                + description.getVersion())
                );
                sender.sendMessage(AdventureUtil.parse("<blue>Made by: <green>" + description.getAuthors()));
                break;
            default:
                sender.sendMessage(messages.getComponent("messages.command-error"));
                break;
        }

        return true;
    }
}
