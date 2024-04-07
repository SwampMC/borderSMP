package dev.louis.worldborder.Listeners.XPListeners;

import dev.louis.worldborder.Worldborder;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Arrays;
import java.util.List;

public class BlockPlaceListeners implements Listener {

    private final Worldborder plugin;

    public BlockPlaceListeners(Worldborder plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player player = e.getPlayer();
        Block block = e.getBlock();
        Material blockType = block.getType();

        if(!(player.getWorld().getName().equals(player.getUniqueId().toString()))) {
            e.setCancelled(true);
        }

        CoreProtectAPI api = plugin.getCoreProtect();
        if (api != null) {
            if(player.getGameMode().equals(GameMode.CREATIVE)) return;


            switch (blockType) {
                case DIRT:
                case GRASS_BLOCK:
                case COARSE_DIRT:
                case GRAVEL:
                case RED_SAND:
                case SAND:
                case PODZOL:
                case MYCELIUM:
                    plugin.getLevelManager().addXP(player, 1);
                    break;
                case STONE:
                case COBBLESTONE:
                case ANDESITE:
                case CLAY:
                case DIORITE:
                case SANDSTONE:
                case GRANITE:
                case CHISELED_SANDSTONE:
                case SMOOTH_SANDSTONE:
                case RED_SANDSTONE:
                case CHISELED_RED_SANDSTONE:
                case SMOOTH_RED_SANDSTONE:
                case TERRACOTTA:
                case WHITE_TERRACOTTA:
                case ORANGE_TERRACOTTA:
                case MAGENTA_TERRACOTTA:
                case YELLOW_TERRACOTTA:
                case LIGHT_BLUE_TERRACOTTA:
                case LIME_TERRACOTTA:
                case PINK_TERRACOTTA:
                case GRAY_TERRACOTTA:
                case LIGHT_GRAY_TERRACOTTA:
                case CYAN_TERRACOTTA:
                case PURPLE_TERRACOTTA:
                case BLUE_TERRACOTTA:
                case BROWN_TERRACOTTA:
                case GREEN_TERRACOTTA:
                case RED_TERRACOTTA:
                case BLACK_TERRACOTTA:
                case NETHERRACK:
                case SOUL_SAND:
                case PRISMARINE:
                case PRISMARINE_BRICKS:
                case DARK_PRISMARINE:
                case MOSSY_STONE_BRICK_SLAB:
                case MOSSY_COBBLESTONE_SLAB:
                case MOSSY_STONE_BRICK_STAIRS:
                case MOSSY_COBBLESTONE_STAIRS:
                case MOSSY_STONE_BRICK_WALL:
                case MOSSY_COBBLESTONE_WALL:
                case MOSSY_STONE_BRICKS:
                case MOSS_CARPET:
                case MOSS_BLOCK:
                case MOSSY_COBBLESTONE:
                case CHISELED_POLISHED_BLACKSTONE:
                case CRACKED_POLISHED_BLACKSTONE_BRICKS:
                case RED_NETHER_BRICKS:
                case NETHER_BRICKS:
                case CHISELED_NETHER_BRICKS:
                case CRACKED_NETHER_BRICKS:
                case GILDED_BLACKSTONE:
                case POLISHED_BLACKSTONE_BRICKS:
                case POLISHED_BLACKSTONE:
                case BLACKSTONE:
                case POLISHED_BASALT:
                case BASALT:
                case HONEYCOMB_BLOCK:
                case HONEY_BLOCK:
                case SPONGE:
                case WET_SPONGE:
                    plugin.getLevelManager().addXP(player, 2);
                    break;
                case COAL_ORE:
                    plugin.getLevelManager().addXP(player, 3);
                    break;
                case IRON_ORE:
                case REDSTONE_ORE:
                case LAPIS_ORE:
                case NETHER_QUARTZ_ORE:
                case GLOWSTONE:
                case END_STONE:
                case PURPUR_BLOCK:
                case PURPUR_PILLAR:
                case PURPUR_STAIRS:
                case PURPUR_SLAB:
                case SEA_LANTERN:
                    plugin.getLevelManager().addXP(player, 5);
                    break;
                case GOLD_ORE:
                    plugin.getLevelManager().addXP(player, 7);
                    break;
                case DIAMOND_ORE:
                case EMERALD_ORE:
                case OBSIDIAN:
                    plugin.getLevelManager().addXP(player, 10);
                    break;
                default:
                    break;
            }
        }
    }
}
