package me.pritzza.swagplugin.Items;

import me.pritzza.swagplugin.Main;

import org.bukkit.*;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeBow {

    private static HashMap<Player, ArrayList<Arrow>> usingTimeBow = new HashMap<Player, ArrayList<Arrow>>();

    private ItemStack item = null;

    public TimeBow() { makeTimeBow(); }
    public final ItemStack getItem() { return this.item; }

    private void makeTimeBow()
    {
        this.item = new ItemStack(Material.BOW);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "" + ChatColor.RED + "Time Bow");

        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.RESET + "An ancient bow who's power transcends that of time and space.");
        meta.setLore(lore);

        item.setItemMeta(meta);
    }

    public void addTimeBowRecipe(final Main m)
    {
        NamespacedKey key = new NamespacedKey(m, "time_bow");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("EEE",
                "EBE",
                "EEE");
        recipe.setIngredient('E', Material.ENDER_EYE);
        recipe.setIngredient('B', Material.BOW);

        Bukkit.getServer().addRecipe(recipe);
    }

    public static void addUser(Player player) { usingTimeBow.put(player, new ArrayList<Arrow>()); }
    public static void removeUser(Player player) { usingTimeBow.remove(player); }

    public static void useTimeBow(Player player, Arrow arrow)
    {
        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
        arrow.setGravity(false);
        usingTimeBow.get(player).add(arrow);
    }

    public static void hit(ProjectileHitEvent event)
    {
        // for every player and the arrow's they've fired from the timebow
        usingTimeBow.forEach((k, v) ->
        {
            if(v.contains(event.getEntity())) {

                final Location arrowLoc = event.getEntity().getLocation();

                arrowLoc.setYaw( k.getLocation().getYaw() );
                arrowLoc.setPitch( k.getLocation().getPitch() );

                k.teleportAsync(arrowLoc);

                k.getWorld().playSound(k.getLocation(), Sound.AMBIENT_CAVE, 2.f, 9.f);

                k.getWorld().spawnParticle(Particle.CRIT_MAGIC, k.getLocation(), 120);
                k.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, k.getLocation(), 60);
                k.getWorld().spawnParticle(Particle.FLAME, k.getLocation(), 30);
                k.getWorld().spawnParticle(Particle.END_ROD, k.getLocation(), 15);
                k.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, k.getLocation(), 7);
            }
        });
    }


}
