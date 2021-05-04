package me.pritzza.swagplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Random;

public class FoodBuffs implements Listener {

    static final int TPS = 20;
    static final int BLOCK_HEIGHT_LIMIT = 255;
    static final Random rng = new Random();

    private PotionEffect makePotionEffect(final PotionEffectType type, final int minDur, final int durRange, final int level, final int odds)
    {
        if (rng.nextInt(100) < odds)
            return new PotionEffect(type, (minDur + rng.nextInt(durRange)) * TPS, level, false);

        return null;
    }

    private void dropAnvil(Player player)
    {
        Location pLoc = player.getLocation();

        boolean indoors = false;

        for (int i = BLOCK_HEIGHT_LIMIT; i > pLoc.getBlockY(); --i)
            if (player.getWorld().getBlockAt(pLoc.getBlockX(), i, pLoc.getBlockZ()).getType() != Material.AIR)
                indoors = true;

        if (!indoors) {

            final int dropHeight = 10;

            player.getWorld().getBlockAt( pLoc.getBlockX(), pLoc.getBlockY() + dropHeight, pLoc.getBlockZ() ).setType(Material.ANVIL, true);

            if (player.getWorld().getBlockAt(pLoc).getType() == Material.ANVIL)
                player.getWorld().getBlockAt( pLoc.getBlockX(), pLoc.getBlockY(), pLoc.getBlockZ() ).setType(Material.AIR, true);
        }
    }

    @EventHandler
    public void onPlayerEat(PlayerItemConsumeEvent event)
    {
        Player player = event.getPlayer();

        ArrayList<PotionEffect> foodEffects = new ArrayList<PotionEffect>();

        switch (player.getItemInHand().getType())
        {
            case COOKIE:
                foodEffects.add(makePotionEffect(PotionEffectType.SPEED, 4, 2, 2, 100));
                break;
            case BEEF:
                dropAnvil(player);
                foodEffects.add(makePotionEffect(PotionEffectType.POISON, 10, 5, 0, 25));
                break;
            case CARROT:
                foodEffects.add(makePotionEffect(PotionEffectType.NIGHT_VISION, 30, 5, 0, 100));
                break;
            case SPIDER_EYE:
                foodEffects.add(makePotionEffect(PotionEffectType.CONFUSION, 30, 5, -1, 100));
                break;
        }

        for (PotionEffect foodEffect : foodEffects)
            if (foodEffect != null)
                player.addPotionEffect(foodEffect);
    }
}