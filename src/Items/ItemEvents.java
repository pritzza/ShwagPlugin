package me.pritzza.swagplugin.Items;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class ItemEvents implements Listener {

    @EventHandler
    public void onUse(PlayerInteractEvent event)
    {
        ItemStack item = event.getItem();

        if (item == null)
            return;
        else if (item.getItemMeta().equals( ItemManager.getGun().getItemMeta() ))
            Gun.useGun(event);
    }

    // for timebow
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event)
    {
        TimeBow.addUser(event.getPlayer());
    }

    // for timebow
    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event)
    {
        TimeBow.removeUser(event.getPlayer());
    }

    // for timebow
    @EventHandler
    public void onShoot(EntityShootBowEvent event)
    {
        ItemStack item = event.getBow();

        if (item == null || !(event.getEntity() instanceof Player) )
            return;
        else if (item.getItemMeta().equals( ItemManager.getTimeBow().getItemMeta() ))
            TimeBow.useTimeBow( (Player) event.getEntity(), (Arrow) event.getProjectile());
    }

    // for timebow
    @EventHandler
    public void onArrowHit(ProjectileHitEvent event)
    {
        TimeBow.hit(event);
    }
}
