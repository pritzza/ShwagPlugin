package me.pritzza.swagplugin.Items;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;

public class ItemEvents implements Listener {

    static private Random rng = new Random();

    @EventHandler
    public void onUse(PlayerInteractEvent event)
    {
        if (event.getItem() != null &&
                event.getItem().getItemMeta().equals( ItemManager.getGun().getItemMeta() ))
        {
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

            for (Entity proj : projectiles)
            {
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
}
