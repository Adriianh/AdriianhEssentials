package me.adriianhdev.adriianhessentials.commands;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
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

public class GodModeCommand implements CommandExecutor {

    private final FileConfiguration config;

    public GodModeCommand(AdriianhEssentials plugin) {
        this.config = plugin.getConfig();
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
            sender.sendMessage("You can''t use this command in console");
            return true;
        }

        Player player = (Player) sender;

        if (!(args.length > 0)) {
            godMethod(player);
            return true;
        }

        if (!(player.hasPermission("adriianhessentials.command.god.others"))) {
            player.sendMessage("&aYou don't have the required permission. &f(adriianhessentials.command.god.others)");
            player.showTitle(Title.title(
                    Component.text("&C&lERROR"),
                    Component.text("&7You can't do this."),
                    Title.Times.of(
                            Duration.ofMillis(500),
                            Duration.ofMillis(3000),
                            Duration.ofMillis(1000)
                    )
            ));
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            player.sendMessage("This player is null, please view");
            return true;
        }
        godMethod(target);
        return  true;
    }

    public void godMethod(Player player) {
        if (player.hasPermission("adriianhessentials.command.godmode"))
            return;

        deactivatedSound = config.getString("sounds.sound.deactivated-id");
        activatedSound = config.getString("sounds.sound.deactivated-id");
        volume = config.getInt("sounds.volume.activated");
        dvolume = config.getInt("sounds.volume.deactivated");
        pitch = config.getInt("sounds.pitch.activated");
        dpitch = config.getInt("sounds.pitch.deactivated");

        if(player.isInvulnerable()){
            player.setInvulnerable(false);
            player.sendMessage("God mode deactivated");
            player.showTitle(Title.title(
                    Component.text(config.getString("god.title.deactivated")),
                    Component.text(config.getString("god.subtitle.deactivated")),
                    Title.Times.of(
                            Duration.ofMillis(config.getInt("god.title.fadeIn")),
                            Duration.ofMillis(config.getInt("god.title.stay")),
                            Duration.ofMillis(config.getInt("god.title.fadeOut"))
                    )
            ));
            SoundUtil.playSound(
                    activatedSound,
                    player,
                    volume,
                    pitch);
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 29);
            return;
        }
        player.setInvulnerable(true);
        player.sendMessage("God mode activated");
        player.showTitle(Title.title(
                Component.text(config.getString("god.title.activated")),
                Component.text(config.getString("god.subtitle.activated")),
                Title.Times.of(
                        Duration.ofMillis(config.getInt("god.title.fadeIn")),
                        Duration.ofMillis(config.getInt("god.title.stay")),
                        Duration.ofMillis(config.getInt("god.title.fadeOut"))
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
