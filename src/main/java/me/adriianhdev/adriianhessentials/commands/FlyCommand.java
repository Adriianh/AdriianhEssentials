package me.adriianhdev.adriianhessentials.commands;

import me.adriianhdev.adriianhessentials.PluginCore;
import me.adriianhdev.adriianhessentials.managers.FileManager;
import me.adriianhdev.adriianhessentials.utils.SoundUtil;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class FlyCommand implements CommandExecutor {

    private final PluginCore pluginCore;
    private final FileManager messages;

    public FlyCommand(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
        this.messages = pluginCore.getFilesLoader().getMessages();
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
            sender.sendMessage(messages.getComponent("error.messages.non-player"));
            return true;
        }

        Player player = (Player) sender;

        if (!(args.length > 0)) {
            flyMethod(player);
            return true;
        }

        if (!(player.hasPermission("adriianhessentials.fly.others"))) {
            player.sendMessage(messages.getComponent("error.messages.no-permission"));
            player.showTitle(Title.title(
                    messages.getComponent("error.title.no-permission"),
                    messages.getComponent("error.subtitle.no-permission"),
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
            player.sendMessage(messages.getComponent("error.messages.null-player"));
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
            player.sendMessage(messages.getComponent("fly.messagess.deactivated"));
            player.showTitle(Title.title(
                    messages.getComponent("fly.title.deactivated"),
                    messages.getComponent("fly.subtitle.deactivated"),
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
        player.sendMessage(messages.getComponent("fly.messages.activated"));
        player.showTitle(Title.title(
                messages.getComponent("fly.title.activated"),
                messages.getComponent("fly.subtitle.activated"),
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
