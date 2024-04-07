package dev.louis.worldborder.Commands.subCommands;

import dev.louis.worldborder.Commands.subCommand;
import dev.louis.worldborder.Worldborder;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class EconomyBalance extends subCommand {
    @Override
    public String getName() {
        return "balance";
    }
    @Override
    public String getDescription() {
        return "Check your balance";
    }
    @Override
    public String getSyntax() {
        return "/economy balance";
    }
    @Override
    public Boolean IsOwnerOnly() {
        return false;
    }

    @Override
    public void perform(Worldborder plugin, Player player, String[] args) {
        Economy ecomomy = Worldborder.getEconomy();

        player.sendMessage(ChatColor.GREEN + "Your balance is: " +  ecomomy.getBalance(player));
    }
}
