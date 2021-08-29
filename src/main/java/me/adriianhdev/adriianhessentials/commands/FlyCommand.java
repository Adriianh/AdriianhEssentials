package me.adriianhdev.adriianhessentials.commands;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
import me.adriianhdev.adriianhessentials.managers.FileManager;
import me.adriianhdev.adriianhessentials.utils.SoundUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class FlyCommand implements CommandExecutor {

    private final FileManager config;

    public FlyCommand(AdriianhEssentials plugin) {
        this.config = plugin.getFiles().getConfig();
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
            flyMethod(player);
            return true;
        }

        if (!(player.hasPermission("adriianhessentials.fly.others"))) {
            player.sendMessage("&aYou don't have the required permission. &f(adriianhessentials.fly.others)");
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
        flyMethod(target);
        return  true;
    }

    private void flyMethod(Player player){
        if (player.hasPermission("adriianhessentials.fly"))
            return;

        deactivatedSound = config.getString("sounds.sound.deactivated-id");
        activatedSound = config.getString("sounds.sound.deactivated-id");
        volume = config.getInt("sounds.volume.activated");
        dvolume = config.getInt("sounds.volume.deactivated");
        pitch = config.getInt("sounds.pitch.activated");
        dpitch = config.getInt("sounds.pitch.deactivated");


        if (player.getAllowFlight() && player.isFlying()) {
            player.setAllowFlight(false);
            player.sendMessage(config.getString("fly.messages.deactivated"));
            player.showTitle(Title.title(
                    Component.text(config.getString("fly.title.deactivated")),
                    Component.text(config.getString("fly.subtitle.deactivated")),
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
            return;
        }
        player.setAllowFlight(true);
        player.sendMessage(config.getString("fly.messages.activated"));
        player.showTitle(Title.title(
                Component.text(config.getString("fly.title.activated")),
                Component.text(config.getString("fly.subtitle.activated")),
                Title.Times.of(
                        Duration.ofMillis(config.getInt("fly.title.fadeIn")),
                        Duration.ofMillis(config.getInt("fly.title.stay")),
                        Duration.ofMillis(config.getInt("fly.title.fadeOut"))
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
