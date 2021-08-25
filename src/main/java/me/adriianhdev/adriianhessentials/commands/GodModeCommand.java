package me.adriianhdev.adriianhessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GodModeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(player.hasPermission("adriianhessentials.command.godmode")){
                if(args.length == 0){
                    player.setInvulnerable(true);
                    player.sendMessage("God mode activated");
                }else{
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target instanceof Player){
                        target.sendMessage("You just got god mode");
                        target.setInvulnerable(true);
                        player.sendMessage("The player now has god mode.");
                    }
                }
            }
        }




        return true;
    }
}
