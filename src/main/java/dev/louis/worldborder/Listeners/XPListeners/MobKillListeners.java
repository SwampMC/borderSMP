package dev.louis.worldborder.Listeners.XPListeners;

import dev.louis.worldborder.Worldborder;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobKillListeners implements Listener {
    private final Worldborder plugin;

    public MobKillListeners(Worldborder plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobKill(EntityDeathEvent e){
        Player player = e.getEntity().getKiller();
        EntityType entityType = e.getEntityType();

        switch (entityType) {
            case ZOMBIE:
            case CREEPER:
            case ENDERMAN:
            case DROWNED:
            case SPIDER:
                plugin.getLevelManager().addXP(player, 2);
                break;
            case SKELETON:
            case CAVE_SPIDER:
            case BLAZE:
                plugin.getLevelManager().addXP(player, 3);
                break;
            case WITCH:
                plugin.getLevelManager().addXP(player, 4);
                break;
            case WARDEN:
                plugin.getLevelManager().addXP(player, 500);
                break;
            default:
                plugin.getLevelManager().addXP(player, 1);
                break;
        }
    }
}
