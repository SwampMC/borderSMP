package dev.louis.worldborder.Commands.subCommands;

import dev.louis.worldborder.Commands.subCommand;
import dev.louis.worldborder.Worldborder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Bukkit.getLogger;

public class SMPDeleteCommand extends subCommand {
    @Override
    public String getName() {
        return "delete";
    }
    @Override
    public String getDescription() {
        return "Delete your world.";
    }
    @Override
    public String getSyntax() {
        return "/bordersmp delete";
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
            if(args.length == 2){
               if(args[1].equals("confirm")) {
                   player.sendMessage(ChatColor.GREEN + "Deleting world...");

                   if(!(player.getWorld().getName().equals("world"))){
                       Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + player.getName() + " world");
                   }

                   Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv delete " + worldName);
                   Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv confirm");

                   new BukkitRunnable() {
                       @Override
                       public void run() {
                           player.sendMessage(ChatColor.GREEN + "Your world has been deleted!");
                       }
                   }.runTaskLaterAsynchronously(plugin, 100L);
               }
            } else {
                player.sendMessage(ChatColor.RED + "Do /bordersmp delete confirm to delete your world.");
            }
        }
    }
}
