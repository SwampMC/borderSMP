package dev.louis.worldborder.Managers;

import dev.louis.worldborder.Worldborder;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class placerholderAPIManager extends PlaceholderExpansion{
    private Worldborder plugin;
    public placerholderAPIManager(Worldborder plugin){
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "swampmc";
    }
    @Override
    public @NotNull String getAuthor() {
        return "swampMCDevs";
    }
    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }
    @Override
    public String onPlaceholderRequest(Player player, String identifier){
        if(player == null){
            return "";
        }

        if(identifier.equals("bordersmp_xp")){
            if(plugin.getLevelManager().getRequiredXp(player) == 0){
                return "No World!";
            }

            return String.valueOf(plugin.getLevelManager().getXp(player));
        }

        if(identifier.equals("bordersmp_level")){

            if(plugin.getLevelManager().getRequiredXp(player) == 0){
                return "No World!";
            }

            return String.valueOf(plugin.getLevelManager().getLevel(player));
        }

        if(identifier.equals("bordersmp_requiredxp")){

            if(plugin.getLevelManager().getRequiredXp(player) == 0){
                return "No World!";
            }

            return String.valueOf(plugin.getLevelManager().getRequiredXp(player));
        }

        if(identifier.equals("bordersmp_pname")){
            return player.getName();
        }

        return null;
    }
}
