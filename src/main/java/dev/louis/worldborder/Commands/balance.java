package dev.louis.worldborder.Commands;

import dev.louis.worldborder.Worldborder;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class balance implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            Economy ecomomy = Worldborder.getEconomy();

            player.sendMessage(ChatColor.GREEN + "Your balance is: " +  ecomomy.getBalance(player));
        }


        return false;
    }
}
