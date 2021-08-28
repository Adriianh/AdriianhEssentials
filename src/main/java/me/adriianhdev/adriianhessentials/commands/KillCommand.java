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

public class KillCommand implements CommandExecutor {

    private final FileConfiguration config;

    public KillCommand(AdriianhEssentials plugin) {
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
            killMethod(player);
            return true;
        }

        if (!(player.hasPermission("adriianhessentials.command.kill.others"))) {
            player.sendMessage("&aYou don't have the required permission. &f(adriianhessentials.kill.others)");
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
        killMethod(target);
        return true;
    }

    public void killMethod(Player player){
        if(player.hasPermission("adriianhessentials.command.kill"))
            return;

        deactivatedSound = config.getString("sounds.sound.deactivated-id");
        activatedSound = config.getString("sounds.sound.deactivated-id");
        volume = config.getInt("sounds.volume.activated");
        dvolume = config.getInt("sounds.volume.deactivated");
        pitch = config.getInt("sounds.pitch.activated");
        dpitch = config.getInt("sounds.pitch.deactivated");

        if(player.isInvulnerable()){
            player.sendMessage("You can't death. Please deactivate the god mode.");
            SoundUtil.playSound(
                    activatedSound,
                    player,
                    volume,
                    pitch);
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 29);

            return;
        }
        player.sendMessage("You have just killed yourself.");
        player.setHealth(0);
        player.showTitle(Title.title(
                Component.text("&C&lKILLED"),
                Component.text("&7You just got killed."),
                Title.Times.of(
                        Duration.ofMillis(500),
                        Duration.ofMillis(3000),
                        Duration.ofMillis(1000)
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
