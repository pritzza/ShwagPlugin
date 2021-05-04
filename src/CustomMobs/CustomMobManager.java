package me.pritzza.swagplugin.CustomMobs;

import me.pritzza.swagplugin.Items.ItemManager;

import net.minecraft.server.v1_16_R3.WorldServer;

import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomMobManager implements Listener {

    @EventHandler
    public void onChickenSandwichClick(PlayerInteractEvent event)
    {
        ItemMeta meta = event.getItem().getItemMeta();

        if (meta.equals( ItemManager.getChickenSandwich().getItemMeta() ) &&
            event.getAction().equals(Action.LEFT_CLICK_AIR))
        {
            WindElemental we = new WindElemental(event.getPlayer().getLocation());
            WorldServer world = ((CraftWorld) event.getPlayer().getWorld()).getHandle();

            world.addEntity(we);
        }
    }
}
