package me.adriianhdev.adriianhessentials.commands;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
import me.adriianhdev.adriianhessentials.files.FileManager;
import me.adriianhdev.adriianhessentials.utils.SoundUtil;
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

    private AdriianhEssentials plugin;
    private ArrayList<Player> list_of_flying_players = new ArrayList<>();

    public FlyCommand(AdriianhEssentials plugin) {
        this.plugin = plugin;
    }

    //Default valor of Sounds - Activated
    String activatedSound = "ENTITY.EXPERIENCE_ORB.PICKUP";
    float volume = 1f;
    float pitch = 1f;
    //Default valor of Sounds - Deactivated
    String deactivatedSound = "BLOCK_ANVIL_BREAK";
    float dvolume = 1f;
    float dpitch = 1f;

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
        deactivatedSound = FileManager.get().getString("sounds.sound.deactivated-id");
        activatedSound = FileManager.get().getString("sounds.sound.deactivated-id");
        volume = FileManager.get().getInt("sounds.volume.activated");
        dvolume = FileManager.get().getInt("sounds.volume.deactivated");
        pitch = FileManager.get().getInt("sounds.pitch.activated");
        dpitch = FileManager.get().getInt("sounds.pitch.deactivated");


        if(player.hasPermission("adriianhessentials.fly")){
            if(list_of_flying_players.contains(player)){
                list_of_flying_players.remove(player);
                player.setAllowFlight(false);
                player.sendMessage(FileManager.get().getString("fly.messages.deactivated"));
                player.showTitle(Title.title(
                        Component.text(FileManager.get().getString("fly.title.deactivated")),
                        Component.text(FileManager.get().getString("fly.subtitle.deactivated")),
                        Title.Times.of(
                                Duration.ofMillis(500),
                                Duration.ofMillis(3000),
                                Duration.ofMillis(1000)
                        )
                ));
                SoundUtil.playSound(
                        deactivatedSound,
                        player,
                        dvolume,
                        dpitch);
            }else if(!list_of_flying_players.contains(player)){
                list_of_flying_players.add(player);
                player.setAllowFlight(true);
                player.sendMessage(FileManager.get().getString("fly.messages.activated"));
                player.showTitle(Title.title(
                        Component.text(FileManager.get().getString("fly.title.activated")),
                        Component.text(FileManager.get().getString("fly.subtitle.activated")),
                        Title.Times.of(
                                Duration.ofMillis(FileManager.get().getInt("fly.title.fadeIn")),
                                Duration.ofMillis(FileManager.get().getInt("fly.title.stay")),
                                Duration.ofMillis(FileManager.get().getInt("fly.title.fadeOut"))
                        )
                ));
                SoundUtil.playSound(
                        activatedSound,
                        player,
                        volume,
                        pitch);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 29);
            }
        }
    }
}
