package me.adriianhdev.adriianhessentials.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getLogger().info("This command must be executed by a player");
            return true;
        }

        Player player = (Player) sender;
        if (!(args.length > 0)) {
            player.sendMessage("You need to give an argument.");
            player.sendMessage("/gamemode [gamemode]");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "survival":
            case "0":
            case "s":
                if (!(player.hasPermission("adriianhessentials.gamemode.creative") || player.hasPermission("adriianh.gamemode.*"))) {
                    return true;
                }
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(color("&aGamemode set to &fsurvival"));
                player.showTitle(Title.title(
                        Component.text(color("&A&LSURVIVAL")),
                        Component.text(color("&7Gamemode changed to survival.")),
                        Title.Times.of(
                                Duration.ofMillis(500),
                                Duration.ofMillis(3000),
                                Duration.ofMillis(1000)
                        )
                ));
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 29);
                break;
            case "creative":
            case "c":
            case "1":
                if(player.hasPermission("adriianhessentials.gamemode.creative") || player.hasPermission("adriianh.gamemode.*")){
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(color("&aGamemode set to &fcreative"));
                    player.showTitle(Title.title(
                            Component.text(color("&A&lCREATIVE")),
                            Component.text(color("&7Gamemode changed to creative.")),
                            Title.Times.of(
                                    Duration.ofMillis(500),
                                    Duration.ofMillis(3000),
                                    Duration.ofMillis(1000)
                            )
                    ));
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 29);
                }
                break;
            case "adventure":
            case "a":
            case "2":
                if(player.hasPermission("adriianhessentials.gamemode.adventure") || player.hasPermission("adriianh.gamemode.*")){
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(color("&aGamemode set to &fadventure"));
                    player.showTitle(Title.title(
                            Component.text(color("&A&lADVENTURE")),
                            Component.text(color("&7Gamemode changed to adventure.")),
                            Title.Times.of(
                                    Duration.ofMillis(500),
                                    Duration.ofMillis(3000),
                                    Duration.ofMillis(1000)
                            )
                    ));
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 29);
                }
                break;
            case "spectator":
            case "sp":
            case "3":
                if(player.hasPermission("adriianhessentials.gamemode.spectator") || player.hasPermission("adriianh.gamemode.*")){
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(color("&aGamemode set to &fspectator"));
                    player.showTitle(Title.title(
                            Component.text(color("&A&lSPECTATOR")),
                            Component.text(color("&7Gamemode changed to spectator.")),
                            Title.Times.of(
                                    Duration.ofMillis(500),
                                    Duration.ofMillis(3000),
                                    Duration.ofMillis(1000)
                            )
                    ));
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 29);
                }
                break;
            default:
                player.sendMessage(color("&aThis gamemode doesn't exist."));
                break;
        }
        return false;
    }
    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
