package me.adriianhdev.adriianhessentials.commands;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
import me.adriianhdev.adriianhessentials.utils.AdventureUtil;
import me.adriianhdev.adriianhessentials.utils.SoundUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class FlyCommand implements CommandExecutor {

    private final FileConfiguration messages;

    public FlyCommand(AdriianhEssentials plugin) {
        this.messages = plugin.getConfig();
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

        if(!(sender instanceof Player)) {
            sender.sendMessage(AdventureUtil.parse(
                    messages.getString("error.message.non-player")));
            return true;
        }

        Player player = (Player) sender;

        if (!(args.length > 0)) {
            flyMethod(player);
            return true;
        }

        if (!(player.hasPermission("adriianhessentials.fly.others"))) {
            player.sendMessage(AdventureUtil.parse(
                    messages.getString("error.message.no-permission")));
            player.showTitle(Title.title(
                    AdventureUtil.parse(
                            "error.title.no-permission"),
                    AdventureUtil.parse(
                            "error.subtitle.no-permission"),
                    Title.Times.of(
                            Duration.ofMillis(messages.getInt("fly.times.fadeIn")),
                            Duration.ofMillis(messages.getInt("fly.times.stay")),
                            Duration.ofMillis(messages.getInt("fly.times.fadeOut"))
                    )
            ));
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            player.sendMessage("error.message.null-player");
            return true;
        }
        flyMethod(target);
        return  true;
    }

    private void flyMethod(Player player){
        if (player.hasPermission("adriianhessentials.fly"))
            return;

        deactivatedSound = messages.getString("sounds.sound.deactivated-id");
        activatedSound = messages.getString("sounds.sound.deactivated-id");
        volume = messages.getInt("sounds.volume.activated");
        dvolume = messages.getInt("sounds.volume.deactivated");
        pitch = messages.getInt("sounds.pitch.activated");
        dpitch = messages.getInt("sounds.pitch.deactivated");


        if (player.getAllowFlight() && player.isFlying()) {
            player.setAllowFlight(false);
            player.sendMessage(messages.getString("fly.messages.deactivated"));
            player.showTitle(Title.title(
                    Component.text(messages.getString("fly.title.deactivated")),
                    Component.text(messages.getString("fly.subtitle.deactivated")),
                    Title.Times.of(
                            Duration.ofMillis(messages.getInt("fly.times.fadeIn")),
                            Duration.ofMillis(messages.getInt("fly.times.stay")),
                            Duration.ofMillis(messages.getInt("fly.times.fadeOut"))
                    )
            ));
            SoundUtil.playSound(
                    deactivatedSound,
                    player,
                    dvolume,
                    dpitch);
            return;
        }
        player.setAllowFlight(true);
        player.sendMessage(messages.getString("fly.messages.activated"));
        player.showTitle(Title.title(
                Component.text(messages.getString("fly.title.activated")),
                Component.text(messages.getString("fly.subtitle.activated")),
                Title.Times.of(
                        Duration.ofMillis(messages.getInt("fly.times.fadeIn")),
                        Duration.ofMillis(messages.getInt("fly.times.stay")),
                        Duration.ofMillis(messages.getInt("fly.times.fadeOut"))
                )
        ));
        SoundUtil.playSound(
                activatedSound,
                player,
                volume,
                pitch);
    }
}
