package me.pritzza.swagplugin.Items;

import me.pritzza.swagplugin.Main;

import org.bukkit.inventory.ItemStack;

import java.sql.Time;

public class ItemManager {

    public static final char FORMATTING_CHAR = '\u00A7';

    private static Gun gun = null;
    private static ChickenSandwich chickenSandwich = null;
    private static TimeBow timeBow = null;

    public static void init(final Main m)
    {
        gun = new Gun();
        chickenSandwich = new ChickenSandwich();
        timeBow = new TimeBow();

        chickenSandwich.addChickenSandwichRecipe(m);
        timeBow.addTimeBowRecipe(m);
    }

    public static ItemStack getGun() { return gun.getItem(); }
    public static ItemStack getChickenSandwich() { return chickenSandwich.getItem(); }
    public static ItemStack getTimeBow() { return timeBow.getItem(); }

}
