package me.adriianhdev.adriianhessentials.loaders;

import me.adriianhdev.adriianhessentials.PluginCore;
import me.adriianhdev.adriianhessentials.api.Loader;
import me.adriianhdev.adriianhessentials.commands.*;
import me.adriianhdev.adriianhessentials.commands.builder.ExecutorBuilder;
import org.bukkit.Bukkit;

public class CommandsLoader implements Loader {

    private final PluginCore pluginCore;

    public CommandsLoader(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {
        registerCommands(
                new ExecutorBuilder("adriianhessentials", new MainCommand(pluginCore)),
                new ExecutorBuilder("fly", new FlyCommand(pluginCore)),
                new ExecutorBuilder("gamemode", new GamemodeCommand(pluginCore)),
                new ExecutorBuilder("god", new GodModeCommand(pluginCore)),
                new ExecutorBuilder("kill", new KillCommand(pluginCore)),
                new ExecutorBuilder("armorstand", new ArmorStandCommand(pluginCore))
        );
    }

    public void registerCommands(ExecutorBuilder... executorBuilders){

        for (ExecutorBuilder executorBuilder : executorBuilders){
            Bukkit.getPluginCommand(executorBuilder.getCommandName()).setExecutor(executorBuilder.getCommandExecutor());
        }
    }
}
