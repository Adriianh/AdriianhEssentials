package me.adriianhdev.adriianhessentials.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class AdventureUtil {

    public static Component parse(String text) {
        return MiniMessage.get().parse(text);
    }

}
