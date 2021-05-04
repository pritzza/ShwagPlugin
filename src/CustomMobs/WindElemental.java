package me.pritzza.swagplugin.CustomMobs;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

public class WindElemental extends EntityGhast {

    public WindElemental(Location loc)
    {
        super(EntityTypes.GHAST, ((CraftWorld) loc.getWorld()).getHandle());

        this.setPosition(loc.getX(), loc.getY(), loc.getZ());

        this.setCustomName(new ChatComponentText(ChatColor.BLUE + "Wind Elemental"));
        this.setCustomNameVisible(true);

        this.goalSelector.a(0, new PathfinderGoalLookAtPlayer(this, EntityPlayer.class, 80.0f));
        //this.goalSelector.a(1, new PathfinderGoalRandomFly(this));
        this.goalSelector.a(1, new PathfinderGoalRandomLookaround(this));
        this.goalSelector.a(2, new PathfinderGoalFloat(this));

    }
}
