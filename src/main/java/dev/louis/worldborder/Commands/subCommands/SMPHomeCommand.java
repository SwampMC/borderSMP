package dev.louis.worldborder.Commands.subCommands;

import dev.louis.worldborder.Commands.subCommand;
import dev.louis.worldborder.Worldborder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SMPHomeCommand extends subCommand{
    @Override
    public String getName() {
        return "home";
    }
    @Override
    public String getDescription() {
        return "Go to your home..";
    }
    @Override
    public String getSyntax() {
        return "/bordersmp home";
    }
    @Override
    public Boolean IsOwnerOnly() {
        return false;
    }

    @Override
    public void perform(Worldborder plugin, Player player, String[] args) {
        String worldName = player.getUniqueId().toString();
        World world = Bukkit.getWorld(worldName);

        if(world == null){
            player.sendMessage(ChatColor.RED + "You haven't a world!");
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + player.getName() + " " + worldName);
        }
    }
}
