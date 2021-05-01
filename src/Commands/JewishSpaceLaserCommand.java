package me.pritzza.swagplugin.Commands;

import me.pritzza.swagplugin.Main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import static me.pritzza.swagplugin.Commands.Command.rejectCommand;

public class JewishSpaceLaserCommand implements CommandExecutor {

    static Main main;
    public JewishSpaceLaserCommand(final Main m) { main = m; }

    private void startJewishSpaceLaser(final Player player)
    {
        final int JEWISH_SPACE_LASER_COUNT = 4;
        final int JEWISH_SPACE_LASER_DELAY = 30;

        final World w = player.getWorld();
        final Location l = player.getTargetBlock(64).getLocation();

        BukkitTask task = new JewishLaserTask( main, w, l, JEWISH_SPACE_LASER_COUNT )
                .runTaskTimer(main, JEWISH_SPACE_LASER_COUNT, JEWISH_SPACE_LASER_DELAY);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            if (player.isOp() || player.hasPermission("fireJewishSpaceLaser"))
                startJewishSpaceLaser(player);
            else
                rejectCommand(player);
        }
        else
            main.getLogger().info("Gotta be ingame for that one");

        return true;
    }

}

class JewishLaserTask extends BukkitRunnable
{
    private final Main main;
    private final World world;
    private final Location location;
    private int counter = 0;
    private final int STRIKES = 100;

    public JewishLaserTask(final Main m, final World w, final Location l, final int c) {
        this.main = m;
        this.world = w;
        this.location = l;
        this.counter = c;
    }

    @Override
    public void run() {

        switch (counter)
        {
            case 4: main.getServer().broadcastMessage(ChatColor.RED + "FIRING JEWISH SPACE LASER!!!");  break;
            case 3: main.getServer().broadcastMessage(ChatColor.RED + "IN 3");                          break;
            case 2: main.getServer().broadcastMessage(ChatColor.RED + "IN 2");                          break;
            case 1: main.getServer().broadcastMessage(ChatColor.RED + "IN 1");                          break;
            case 0:
                for (int i = 0; i < STRIKES; ++i)
                    world.strikeLightning(location);
                break;

            default:
                //main.getServer().broadcastMessage(ChatColor.RED + "TURNING JEWISH SPACE LASER OFF");
                this.cancel();
        }

        this.counter--;
    }
}