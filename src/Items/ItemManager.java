package me.pritzza.swagplugin.Items;

import me.pritzza.swagplugin.Main;

import org.bukkit.inventory.ItemStack;

public class ItemManager {

    public static final char FORMATTING_CHAR = '\u00A7';

    private static Gun gun = null;
    private static ChickenSandwich chickenSandwich = null;

    public static void init(final Main m)
    {
        gun = new Gun();
        chickenSandwich = new ChickenSandwich();

        chickenSandwich.addChickenSandwichRecipe(m);
    }

    public static ItemStack getGun() { return gun.getItem(); }
    public static ItemStack getChickenSandwich() { return chickenSandwich.getItem(); }

}
