package dev.louis.worldborder.Commands.subCommands;

import dev.louis.worldborder.Commands.subCommand;
import dev.louis.worldborder.Worldborder;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class EconomySetMoney extends subCommand {
    @Override
    public String getName() {
        return "set";
    }
    @Override
    public String getDescription() {
        return "Set your balance";
    }
    @Override
    public String getSyntax() {
        return "/economy set <player> <amount>";
    }
    @Override
    public Boolean IsOwnerOnly() {
        return true;
    }

    @Override
    public void perform(Worldborder plugin, Player player, String[] args) {
        Economy ecomomy = Worldborder.getEconomy();

        if(args.length == 3){
            Player target = plugin.getServer().getPlayer(args[1]);
            if(target != null){
                try {
                    double amount = Double.parseDouble(args[2]);
                    ecomomy.depositPlayer(target, amount);
                    player.sendMessage(ChatColor.GREEN + "You set " + target.getName() + " balance to: " +  ecomomy.getBalance(target));

                    target.sendMessage(ChatColor.GREEN + "Your balance is set to: " +  ecomomy.getBalance(target));
                } catch (NumberFormatException e){
                    player.sendMessage(ChatColor.RED + "Invalid amount");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Player not found");
            }
        } else {
            player.sendMessage(ChatColor.RED + "Invalid usage");
        }
    }
}
