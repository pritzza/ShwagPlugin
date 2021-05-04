package me.pritzza.swagplugin;

import me.pritzza.swagplugin.Commands.FartCommand;
import me.pritzza.swagplugin.Commands.GunCommand;
import me.pritzza.swagplugin.Commands.JewishSpaceLaserCommand;
import me.pritzza.swagplugin.CustomMobs.CustomMobManager;
import me.pritzza.swagplugin.Items.ItemEvents;
import me.pritzza.swagplugin.Items.ItemManager;
import me.pritzza.swagplugin.BababooeyCounter.PlayerTracker;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new CustomDeathMessages(), this);

        getServer().getPluginManager().registerEvents(new CustomMobManager(), this);

        getServer().getPluginCommand("fireJewishSpaceLaser").setExecutor(new JewishSpaceLaserCommand(this));

        ItemManager.init(this);

        getServer().getPluginManager().registerEvents(new PlayerTracker(), this);
        getServer().getPluginManager().registerEvents(new ItemEvents(), this);
        getServer().getPluginCommand("gun").setExecutor(new GunCommand(this));
        getServer().getPluginCommand("fart").setExecutor(new FartCommand(this));
        getServer().getPluginManager().registerEvents(new MobEnhancers(), this);
        getServer().getPluginManager().registerEvents(new FoodBuffs(), this);
        getServer().getPluginManager().registerEvents(new SwagExplosion(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
