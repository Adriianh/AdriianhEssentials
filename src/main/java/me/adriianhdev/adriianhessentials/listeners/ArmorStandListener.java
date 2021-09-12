package me.adriianhdev.adriianhessentials.listeners;

import me.adriianhdev.adriianhessentials.AdriianhEssentials;
import me.adriianhdev.adriianhessentials.PluginCore;
import me.adriianhdev.adriianhessentials.managers.FileManager;
import me.adriianhdev.adriianhessentials.utils.SoundUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ArmorStandListener implements Listener {

    //Default valor of Sounds
    String sound = "ENTITY.EXPERIENCE_ORB.PICKUP";
    float volume = 1f;
    float pitch = 1f;

    public ArmorStandListener(PluginCore pluginCore) {
        FileManager messages = pluginCore.getFilesLoader().getMessages();
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();

        //Menu list
        final String asGui = "<gray>Armor Stand - GUI";

        if(e.getView().getTitle().equalsIgnoreCase(asGui)){
            e.setCancelled(true);

            switch(e.getCurrentItem().getType()){
                case ARMOR_STAND:
                    player.sendMessage("Opening the Armor Stand Creator-GUI ");
                    SoundUtil.playSound(
                            sound,
                            player,
                            volume,
                            pitch);
                    player.closeInventory();
                    break;
                case BARRIER:
                    player.sendMessage("Armor Stand GUI has been closed.");
                    SoundUtil.playSound(
                            sound,
                            player,
                            volume,
                            pitch);
                    player.closeInventory();
                    break;
                default:
                    break;
            }
        }

    }

}
