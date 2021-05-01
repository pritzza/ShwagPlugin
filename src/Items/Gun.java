package me.pritzza.swagplugin.Items;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Gun {

    private ItemStack item;

    public Gun() { makeGun(); }
    public final ItemStack getItem() { return this.item; }

    private final void makeGun()
    {
        this.item = new ItemStack(Material.STICK, 1);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("\u00A7k" + "Gun");

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier("GENERIC_ATTACK_SPEED", 13,
                AttributeModifier.Operation.ADD_NUMBER));

        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ItemManager.FORMATTING_CHAR + "swag");
        meta.setLore(lore);

        item.setItemMeta(meta);
    }

}
