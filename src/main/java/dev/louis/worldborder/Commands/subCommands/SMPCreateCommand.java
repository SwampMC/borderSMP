package dev.louis.worldborder.Commands.subCommands;

import dev.louis.worldborder.Commands.subCommand;
import dev.louis.worldborder.Worldborder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Bukkit.getLogger;

public class SMPCreateCommand extends subCommand {
    @Override
    public String getName() {
        return "create";
    }
    @Override
    public String getDescription() {
        return "Create a borderSMP world.";
    }
    @Override
    public String getSyntax() {
        return "/bordersmp create";
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
            player.sendMessage(ChatColor.GREEN + "Creating world...");

            String seed = "844424930132196";
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv create " + worldName + " NORMAL -s " + seed);

            plugin.getWorldborderManager().setup(player);

            plugin.getLevelManager().setupPlayer(player);

            player.sendMessage(ChatColor.GREEN + "Your world has been created!");
            player.sendMessage(ChatColor.GREEN + "Run /bordersmp home or click on the NPC to teleport to your world.");

        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + player.getName() + " " + player.getUniqueId().toString());
        }
    }
}
