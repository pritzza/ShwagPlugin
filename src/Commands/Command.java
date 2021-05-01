package me.pritzza.swagplugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Command {

    public static void rejectCommand(final Player player)
    {
        player.sendMessage(ChatColor.DARK_AQUA + "You suck");
    }
}
