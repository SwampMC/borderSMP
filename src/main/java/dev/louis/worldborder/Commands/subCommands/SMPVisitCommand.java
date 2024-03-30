package dev.louis.worldborder.Commands.subCommands;

import dev.louis.worldborder.Commands.subCommand;
import dev.louis.worldborder.Worldborder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SMPVisitCommand extends subCommand {
    @Override
    public String getName() {
        return "visit";
    }
    @Override
    public String getDescription() {
        return "Visit a smp";
    }
    @Override
    public String getSyntax() {
        return "/bordersmp visit <player>";
    }
    @Override
    public Boolean IsOwnerOnly() {
        return false;
    }

    @Override
    public void perform(Worldborder plugin, Player player, String[] args) {
        Player target = plugin.getServer().getPlayer(args[1]);
        if(target != null){
            player.teleport(target);

            if(target.getWorld().getName().equals(target.getUniqueId().toString())){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + player.getName() + " " + target.getUniqueId().toString());
            } else if (target.getWorld().getName().equals(player.getUniqueId().toString()) || player.getWorld().getName().equals(target.getUniqueId().toString())){
                player.sendMessage(ChatColor.GREEN + "You are in the same world as the player!");
            } else {
                player.sendMessage(ChatColor.RED + "The player is not in a world yet!");
            }
        } else {
            player.sendMessage(ChatColor.RED + "Player not found!");
        }
    }
}
