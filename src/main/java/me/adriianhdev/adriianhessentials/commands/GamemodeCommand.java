package me.adriianhdev.adriianhessentials.commands;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
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

public class GamemodeCommand implements CommandExecutor{

     AdriianhEssentials plugin = AdriianhEssentials.getPlugin(AdriianhEssentials.class);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length > 0){
                if(args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")){
                    if(player.hasPermission("adriianhessentials.gamemode.creative") || player.hasPermission("adriianh.gamemode.*")){
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
                    }
                }else if(args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")){
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
                }else if(args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")){
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
                }else if(args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spc")){
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
                }else{
                    player.sendMessage(color("&aThis gamemode doesn't exist."));
                }
            }else{
                player.sendMessage("You need to give an argument.");
                player.sendMessage("/gamemode [gamemode]");
            }

        }else{
            Bukkit.getLogger().info("This command must be executed by a player");
        }

        return false;
    }
    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
