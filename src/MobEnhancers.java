package me.pritzza.swagplugin;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;

public class MobEnhancers implements Listener {

    private void enhanceZombie(Zombie zombie)
    {
        final float ZOMBIE_HEALTH_MODIFIER = 1.5f;
        final int ZOMBIE_VANILLA_HEALTH = (int) zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        final int ZOMBIE_NEW_HEALTH = (int) (ZOMBIE_VANILLA_HEALTH * ZOMBIE_HEALTH_MODIFIER);

        zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(ZOMBIE_NEW_HEALTH);
        zombie.setHealth(ZOMBIE_NEW_HEALTH);

        zombie.setGlowing(true);

        zombie.setInvisible(true);

        zombie.setCanBreakDoors(true);


        ItemStack item = new ItemStack(Material.PUFFERFISH);

        zombie.getEquipment().setItemInOffHand(item);
    }

    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent event)
    {
        switch (event.getEntityType())
        {
            case ZOMBIE:
                enhanceZombie( (Zombie) event.getEntity() );
            break;

            case SKELETON:
                Skeleton skele = (Skeleton) event.getEntity();

                //skele.getEquipment().setItemInMainHand(item);
                break;
        }
    }
}
