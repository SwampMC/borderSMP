package dev.louis.worldborder.Managers;

import dev.louis.worldborder.Worldborder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

public class WorldborderManager {
    private final Worldborder plugin;
    public WorldborderManager(Worldborder plugin){
        this.plugin = plugin;
    }

    public void setup(Player player){
        World world = Bukkit.getWorld(player.getUniqueId().toString());
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setSize(20);
        Location newspawn = new Location(world, -32, 65, 48);

        world.setSpawnLocation(newspawn.getBlockX(), newspawn.getBlockY(), newspawn.getBlockZ());

        worldBorder.setCenter(newspawn.getBlockX(), newspawn.getBlockZ());
    }
    public void addBlocks(Player player, int amount){
        WorldBorder worldBorder = Bukkit.getWorld(player.getUniqueId().toString()).getWorldBorder();
        double bordersize = worldBorder.getSize();

        worldBorder.setSize(bordersize + amount);
    }
    public void removeBlocks(Player player, int amount){
        WorldBorder worldBorder = Bukkit.getWorld(player.getUniqueId().toString()).getWorldBorder();
        double bordersize = worldBorder.getSize();

        worldBorder.setSize(bordersize - amount);
    }
}
