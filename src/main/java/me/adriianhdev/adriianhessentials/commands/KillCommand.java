package me.adriianhdev.adriianhessentials.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class KillCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage("You have just killed yourself.");
                player.setHealth(0);
                player.showTitle(Title.title(
                        Component.text(color("&C&lKILLED")),
                        Component.text(color("&7You just got killed.")),
                        Title.Times.of(
                                Duration.ofMillis(500),
                                Duration.ofMillis(3000),
                                Duration.ofMillis(1000)
                        )
                ));
            }else{
                Player target = Bukkit.getPlayerExact(args[0]);
                if(target instanceof Player){
                    target.sendMessage("You just got killed.");
                    target.setHealth(0);
                    player.sendMessage("You just killed " + target.displayName());
                    target.showTitle(Title.title(
                            Component.text(color("&C&lKILLED")),
                            Component.text(color("&7You just got killed.")),
                            Title.Times.of(
                                    Duration.ofMillis(500),
                                    Duration.ofMillis(3000),
                                    Duration.ofMillis(1000)
                            )
                    ));
                    player.showTitle(Title.title(
                            Component.text(color("&C&lKILLED")),
                            Component.text(color("&7You just killed a player.")),
                            Title.Times.of(
                                    Duration.ofMillis(500),
                                    Duration.ofMillis(3000),
                                    Duration.ofMillis(1000)
                            )
                    ));
                }else{
                    player.sendMessage("That player doesn't exist.");
                }
            }
        }



        return false;
    }
    public String color(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
