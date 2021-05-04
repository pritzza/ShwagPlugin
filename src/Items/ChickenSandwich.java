package me.pritzza.swagplugin.Items;

import me.pritzza.swagplugin.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class ChickenSandwich {

    private ItemStack item = null;

    public ChickenSandwich() { makeChickenSandwich(); }
    public final ItemStack getItem() { return this.item; }

    private void makeChickenSandwich()
    {
        this.item = new ItemStack(Material.COOKED_CHICKEN);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "Chicken Sandwich");

        item.setItemMeta(meta);
    }

    public void addChickenSandwichRecipe(final Main m)
    {
        NamespacedKey key = new NamespacedKey(m, "chicken_sandwich");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" B ",
                " C ",
                " B ");
        recipe.setIngredient('B', Material.BREAD);
        recipe.setIngredient('C', Material.COOKED_CHICKEN);

        Bukkit.getServer().addRecipe(recipe);
    }
}
