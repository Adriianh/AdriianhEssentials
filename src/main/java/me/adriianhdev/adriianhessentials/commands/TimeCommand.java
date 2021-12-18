package me.adriianhdev.adriianhessentials.commands;

import me.adriianhdev.adriianhessentials.PluginCore;
import me.adriianhdev.adriianhessentials.managers.FileManager;
import me.adriianhdev.adriianhessentials.utils.AdventureUtil;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.kerberos.KerberosTicket;
import java.time.Duration;

public class TimeCommand implements CommandExecutor {

    private final PluginCore pluginCore;
    private final FileManager messages;

    public TimeCommand(PluginCore pluginCore) {
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
            player.sendMessage("/time [time]");
            return true;
        }

        World world = player.getWorld();
        switch (args[0].toLowerCase()) {
            case "day":
                if (!(player.hasPermission("adriianhessentials.time.day") || player.hasPermission("adriianhessentials.time.*"))) {

                    world.setTime(1000);
                    player.sendMessage(AdventureUtil.parse(messages.getString("time-change.message")));

                    player.showTitle(Title.title(
                            AdventureUtil.parse(
                                    messages.getString("time-change.title")),
                            AdventureUtil.parse(
                                    messages.getString("time-change.subtitle")),
                            Title.Times.of(
                                    Duration.ofMillis(messages.getInt("time-change.times.fadeIn")),
                                    Duration.ofMillis(messages.getInt("time-change.times.stay")),
                                    Duration.ofMillis(messages.getInt("time-change.times.fadeOut"))
                            )
                    ));
                }
                break;
            case "night":
                if (!(player.hasPermission("adriianhessentials.time.day") || player.hasPermission("adriianhessentials.time.*"))) {

                    world.setTime(13000);
                    player.sendMessage(AdventureUtil.parse(messages.getString("time-change.message")));

                    player.showTitle(Title.title(
                            AdventureUtil.parse(
                                    messages.getString("time-change.title")),
                            AdventureUtil.parse(
                                    messages.getString("time-change.subtitle")),
                            Title.Times.of(
                                    Duration.ofMillis(messages.getInt("time-change.times.fadeIn")),
                                    Duration.ofMillis(messages.getInt("time-change.times.stay")),
                                    Duration.ofMillis(messages.getInt("time-change.times.fadeOut"))
                            )
                    ));
                }
                break;
            case "midnight":
                if (!(player.hasPermission("adriianhessentials.time.day") || player.hasPermission("adriianhessentials.time.*"))) {
                    world.setTime(18000);
                    player.sendMessage(AdventureUtil.parse(messages.getString("time-change.message")));

                    player.showTitle(Title.title(
                            AdventureUtil.parse(
                                    messages.getString("time-change.title")),
                            AdventureUtil.parse(
                                    messages.getString("time-change.subtitle")),
                            Title.Times.of(
                                    Duration.ofMillis(messages.getInt("time-change.times.fadeIn")),
                                    Duration.ofMillis(messages.getInt("time-change.times.stay")),
                                    Duration.ofMillis(messages.getInt("time-change.times.fadeOut"))
                            )
                    ));
                }
                break;
            case "noon":
                if (!(player.hasPermission("adriianhessentials.time.day") || player.hasPermission("adriianhessentials.time.*"))) {

                    world.setTime(6000);
                    player.sendMessage(AdventureUtil.parse(messages.getString("time-change.message")));

                    player.showTitle(Title.title(
                            AdventureUtil.parse(
                                    messages.getString("time-change.title")),
                            AdventureUtil.parse(
                                    messages.getString("time-change.subtitle")),
                            Title.Times.of(
                                    Duration.ofMillis(messages.getInt("time-change.times.fadeIn")),
                                    Duration.ofMillis(messages.getInt("time-change.times.stay")),
                                    Duration.ofMillis(messages.getInt("time-change.times.fadeOut"))
                            )
                    ));
                }
                break;

            default:
                player.sendMessage(AdventureUtil.parse(messages.getString("time-change.error.non-exist")));
                break;

        }
        return false;
    }
}
