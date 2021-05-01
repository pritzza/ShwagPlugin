package me.pritzza.swagplugin.Commands;

import me.pritzza.swagplugin.Items.ItemManager;
import me.pritzza.swagplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.pritzza.swagplugin.Commands.Command.rejectCommand;

public class GunCommand implements CommandExecutor {

    private final Main main;
    public GunCommand(final Main m) { main = m; }

    private void giveGun(final Player player)
    {
        ItemStack gun = ItemManager.getGun();
        player.getInventory().addItem(gun);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            if (player.isOp() || player.hasPermission("gun"))
                giveGun(player);
            else
                rejectCommand(player);
        }
        else
            main.getLogger().info("Gotta be ingame for that one");

        return true;
    }
}
