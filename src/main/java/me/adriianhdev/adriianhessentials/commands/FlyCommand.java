package me.adriianhdev.adriianhessentials.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.ArrayList;

public class FlyCommand implements CommandExecutor {

    private ArrayList<Player> list_of_flying_players = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 0){
                flyMethod(player);
            }else if(args.length == 1){
                if(player.hasPermission("adriianhessentials.fly.others")){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    flyMethod(target);
                }else{
                    player.sendMessage(color("&aYou don't have the required permission. &f(adriianhessentials.fly.others)"));
                    player.showTitle(Title.title(
                            Component.text(color("&C&lERROR")),
                            Component.text(color("&7You can't do this.")),
                            Title.Times.of(
                                    Duration.ofMillis(500),
                                    Duration.ofMillis(3000),
                                    Duration.ofMillis(1000)
                            )
                    ));
                }
            }

        }

        return true;
    }

    public String color(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private void flyMethod(Player player){
        if(player.hasPermission("adriianhessentials.fly")){
            if(list_of_flying_players.contains(player)){
                list_of_flying_players.remove(player);
                player.setAllowFlight(false);
                player.sendMessage(color("&aFly deactivated, now you can't fly."));
                player.showTitle(Title.title(
                        Component.text(color("&C&lFLY")),
                        Component.text(color("&7Now you can't fly.")),
                        Title.Times.of(
                                Duration.ofMillis(500),
                                Duration.ofMillis(3000),
                                Duration.ofMillis(1000)
                        )
                ));
            }else if(!list_of_flying_players.contains(player)){
                list_of_flying_players.add(player);
                player.setAllowFlight(true);
                player.sendMessage(color("&aFly activated, you can now fly."));
                player.showTitle(Title.title(
                        Component.text(color("&A&lFLY")),
                        Component.text(color("&7You can now fly.")),
                        Title.Times.of(
                                Duration.ofMillis(500),
                                Duration.ofMillis(3000),
                                Duration.ofMillis(1000)
                        )
                ));
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 29);
            }
        }
    }
}
