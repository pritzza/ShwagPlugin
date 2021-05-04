package me.pritzza.swagplugin.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;

public class Gun {

    private ItemStack item;

    public Gun() { makeGun(); }
    public final ItemStack getItem() { return this.item; }

    private final void makeGun()
    {
        this.item = new ItemStack(Material.STICK);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "Gun");

        //meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier("GENERIC_ATTACK_SPEED", 13,
        //        AttributeModifier.Operation.ADD_NUMBER));

        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.RESET + "" + ChatColor.DARK_PURPLE + "swag");
        meta.setLore(lore);

        item.setItemMeta(meta);
    }

    public static void useGun(PlayerInteractEvent event)
    {
        Random rng = new Random();
        Player player = event.getPlayer();

        ArrayList<Arrow> projectiles = new ArrayList<Arrow>();

        final boolean lClick= event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK);

        int numProj;

        if (lClick) {
            final int minNumProjectiles = 7;
            final int numRangeProjectiles = 3;

            numProj = minNumProjectiles + rng.nextInt(numRangeProjectiles);
        }
        else
            numProj = 1;

        for(int i = 0; i < numProj; ++i)
            projectiles.add(player.launchProjectile(Arrow.class));
        //projectiles.add(player.getWorld().spawnEntity(player.getLocation(), EntityType.COD));

        Vector vel = new Vector();
        final int power = 7;

        Vector offset = new Vector();
        final float spread = 0.3f;

        for (Arrow proj : projectiles)
        {
            proj.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);

            if (lClick) {
                offset.setX((rng.nextFloat() - 0.5f) * spread);
                offset.setY((rng.nextFloat() - 0.5f) * spread);
                offset.setZ((rng.nextFloat() - 0.5f) * spread);
            }
            vel = player.getLocation().getDirection();

            vel.add(offset);
            vel.multiply(5);

            proj.setVelocity(vel);
        }
    }

}
