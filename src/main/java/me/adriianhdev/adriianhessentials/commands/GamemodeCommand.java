package me.adriianhdev.adriianhessentials.commands;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
import me.adriianhdev.adriianhessentials.PluginCore;
import me.adriianhdev.adriianhessentials.managers.FileManager;
import me.adriianhdev.adriianhessentials.utils.AdventureUtil;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class GamemodeCommand implements CommandExecutor {

    private final PluginCore pluginCore;
    private final FileManager messages;

    public GamemodeCommand(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
        this.messages = pluginCore.getFilesLoader().getMessages();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getLogger().info(messages.getString("error.message.non-player"));
            return true;
        }

        Player player = (Player) sender;
        if (!(args.length > 0)) {
            player.sendMessage(AdventureUtil.parse(
                    messages.getString("error.message.non-argument")));
            player.sendMessage("/gamemode [gamemode]");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "survival":
            case "0":
            case "s":
                if (!(player.hasPermission("adriianhessentials.gamemode.creative") || player.hasPermission("adriianh.gamemode.*"))) {

                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(AdventureUtil.parse(
                            messages.getString("gamemode.message")

                    ));
                    player.showTitle(Title.title(
                            AdventureUtil.parse(
                                    messages.getString("gamemode.title")),
                            AdventureUtil.parse(
                                    messages.getString("gamemode.subtitle")),
                            Title.Times.of(
                                    Duration.ofMillis(messages.getInt("gamemode.times.fadeIn")),
                                    Duration.ofMillis(messages.getInt("gamemode.times.stay")),
                                    Duration.ofMillis(messages.getInt("gamemode.times.fadeOut"))
                            )
                    ));
                }
                break;
            case "creative":
            case "c":
            case "1":
                if(player.hasPermission("adriianhessentials.gamemode.creative") || player.hasPermission("adriianh.gamemode.*")){
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(AdventureUtil.parse(
                            messages.getString("gamemode.messages")

                    ));
                    player.showTitle(Title.title(
                            AdventureUtil.parse(
                                    messages.getString("gamemode.title")),
                            AdventureUtil.parse(
                                    messages.getString("gamemode.subtitle")),
                            Title.Times.of(
                                    Duration.ofMillis(messages.getInt("gamemode.times.fadeIn")),
                                    Duration.ofMillis(messages.getInt("gamemode.times.stay")),
                                    Duration.ofMillis(messages.getInt("gamemode.times.fadeOut"))
                            )
                    ));
                }
                break;
            case "adventure":
            case "a":
            case "2":
                if(player.hasPermission("adriianhessentials.gamemode.adventure") || player.hasPermission("adriianh.gamemode.*")){
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(AdventureUtil.parse(
                            messages.getString("gamemode.message")

                    ));
                    player.showTitle(Title.title(
                            AdventureUtil.parse(
                                    messages.getString("gamemode.title")),
                            AdventureUtil.parse(
                                    messages.getString("gamemode.subtitle")),
                            Title.Times.of(
                                    Duration.ofMillis(messages.getInt("gamemode.times.fadeIn")),
                                    Duration.ofMillis(messages.getInt("gamemode.times.stay")),
                                    Duration.ofMillis(messages.getInt("gamemode.times.fadeOut"))
                            )
                    ));
                }
                break;
            case "spectator":
            case "sp":
            case "3":
                if(player.hasPermission("adriianhessentials.gamemode.spectator") || player.hasPermission("adriianh.gamemode.*")){
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(AdventureUtil.parse(
                            messages.getString("gamemode.message")

                    ));
                    player.showTitle(Title.title(
                            AdventureUtil.parse(
                                    messages.getString("gamemode.title")),
                            AdventureUtil.parse(
                                    messages.getString("gamemode.subtitle")),
                            Title.Times.of(
                                    Duration.ofMillis(messages.getInt("gamemode.times.fadeIn")),
                                    Duration.ofMillis(messages.getInt("gamemode.times.stay")),
                                    Duration.ofMillis(messages.getInt("gamemode.times.fadeOut"))
                            )
                    ));
                }
                break;
            default:
                player.sendMessage(AdventureUtil.parse(messages.getString("gamemode.error.non-exist")));
                break;
        }
        return false;
    }
}
