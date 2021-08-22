package me.adriianhdev.adriianhessentials.utils;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;

public class SoundUtil {
    public static void playSound(String sound,
                                 Audience audience,
                                 float volume,
                                 float pitch)
    {
        audience.playSound(
                Sound.sound(Key.key(sound),
                        Sound.Source.NEUTRAL,
                        volume,
                        pitch));
    }

}
