package dev.louis.worldborder.Listeners.Worldlisteners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class WorldListeners implements Listener {

    @EventHandler
    public void on(FoodLevelChangeEvent e) {
        Player player = (Player) e.getEntity();

        if(!(player.getWorld().getName().equals(player.getUniqueId().toString()))) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            event.setCancelled(true);
        }
    }

}
