package me.adriianhdev.adriianhessentials.inventories;

import me.adriianhdev.adriianhessentials.utils.AdventureUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ArmorStandGUI {

    public void openMenu(Player player){
        //Create Inventory
        Inventory gui = Bukkit.createInventory(player, 9, "<gray>Armor Stand - GUI");

        //Create item
        ItemStack create = new ItemStack(Material.ARMOR_STAND);
        ItemMeta createMeta = create.getItemMeta();

        ItemStack close = new ItemStack(Material.BARREL);
        ItemMeta closeMeta = close.getItemMeta();

        //Set item options
        createMeta.displayName(AdventureUtil.parse("<green>Create"));
        ArrayList<String> createLore = new ArrayList<>();
        createLore.add("Create a new armor stand.");
        createMeta.setLore(createLore);

        closeMeta.displayName(AdventureUtil.parse("<red>Close"));

        //Set item meta
        create.setItemMeta(createMeta);
        close.setItemMeta(closeMeta);

        //Set inventory items
        gui.setItem(4, create);
        gui.setItem(8, close);

        //Open inventory
        player.openInventory(gui);
    }

}
