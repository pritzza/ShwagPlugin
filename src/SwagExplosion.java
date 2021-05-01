package me.pritzza.swagplugin;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class SwagExplosion implements Listener {

    static final Random rng = new Random();

    @EventHandler
    public void onPlayerBlockBreak(BlockBreakEvent event)
    {
        if (rng.nextInt(100) == 0) {
            Block blockBroken = event.getBlock();
            blockBroken.getWorld().createExplosion(blockBroken.getLocation(), 5.f, true, true);
        }
    }
}
