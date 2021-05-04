package me.pritzza.swagplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class CustomDeathMessages implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event)
    {
        event.setDeathMessage("Shwag");
    }


}
