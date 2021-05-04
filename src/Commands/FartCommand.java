package me.pritzza.swagplugin.Commands;

import me.pritzza.swagplugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.pritzza.swagplugin.Commands.Command.rejectCommand;

public class FartCommand implements CommandExecutor{

    static Main main;
    public FartCommand(final Main m) { main = m; }

    private void fart(final Player player)
    {
        player.sendMessage(ChatColor.GREEN + "*fart*");

        player.setFoodLevel(0);
        player.setSaturation(0);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;

            if (player.isOp() || player.hasPermission("fart"))
                fart(player);
            else
                rejectCommand(player);
        }
        else
            main.getLogger().info("Gotta be ingame for that one");

        return true;
    }
}
