package dev.louis.worldborder.Managers;

import dev.louis.worldborder.Worldborder;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class LevelManager {
    private final Worldborder plugin;
    private YamlConfiguration levelConfig;
    private File levelFile;
    private List<String> top;
    Economy ecomomy = Worldborder.getEconomy();

    public LevelManager(Worldborder plugin){
        this.plugin = plugin;

        top = new ArrayList<>();
    }
    public void loadlevelFile(){
        plugin.getDataFolder().mkdir();
        levelFile = new File(plugin.getDataFolder(), "level-data.yml");
        if(!levelFile.exists()){
            try{
                levelFile.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        levelConfig = YamlConfiguration.loadConfiguration(levelFile);
    }
    public void setupPlayer(Player player) {
        Economy ecomomy = Worldborder.getEconomy();

        if(!levelConfig.contains(player.getUniqueId().toString())) {
            levelConfig.set(player.getUniqueId() + ".level", 0);
            levelConfig.set(player.getUniqueId() + ".xp", 0);
            levelConfig.set(player.getUniqueId() + ".requird-xp", 500);
        }
        levelConfig.set(player.getUniqueId() +".name", player.getName());
        try{
            levelConfig.save(levelFile);
        } catch (IOException e){
            e.printStackTrace();
        }

        if(ecomomy.getBalance(player) == 0){
            ecomomy.depositPlayer(player, 2000);
        }
    }
    public void addXP(Player player, int amount) {
        levelConfig.set(player.getUniqueId() + ".xp", getXp(player) + amount);
        try{
            levelConfig.save(levelFile);
        } catch (IOException e){
            e.printStackTrace();
        }

        if(getXp(player) >= getRequiredXp(player)){
            levelUp(player);
        }
    }
    public void levelUp(Player player){
        levelConfig.set(player.getUniqueId() + ".exp", 0);
        levelConfig.set(player.getUniqueId() + ".level", getLevel(player) + 1);

        if (levelConfig.getInt(player.getUniqueId() + ".level") >= 50 && levelConfig.getInt(player.getUniqueId() + ".level") < 90){
            levelConfig.set(player.getUniqueId() + ".requird-xp", getRequiredXp(player) + 750);
        } else if (levelConfig.getInt(player.getUniqueId() + ".level") >= 90) {
            levelConfig.set(player.getUniqueId() + ".requird-xp", getRequiredXp(player) + 1000);
        } else {
            levelConfig.set(player.getUniqueId() + ".requird-xp", getRequiredXp(player) + 300);
        }

        try{
            levelConfig.save(levelFile);
        } catch (IOException e){
            e.printStackTrace();
        }

        switch (getLevel(player)){
            case 1:
            case 5:
                player.sendMessage(ChatColor.GREEN + "You have reached level " + getLevel(player) + "." );
                plugin.getWorldborderManager().addBlocks(player, 15);

                EconomyResponse eco = ecomomy.depositPlayer(player, 1000);
                if(eco.transactionSuccess()){
                    player.sendMessage(ChatColor.GREEN + "You have been rewarded with 1000$ and 15 blocks of border expansion");
                } else {
                    player.sendMessage(ChatColor.RED + "An error occured: " + eco.errorMessage);
                }
                break;
            case 2:
            case 3:
            case 4:
                player.sendMessage(ChatColor.GREEN + "You have reached level " + getLevel(player) + "." );
                plugin.getWorldborderManager().addBlocks(player, 10);

                EconomyResponse eco1 = ecomomy.depositPlayer(player, 1000);
                if(eco1.transactionSuccess()){
                    player.sendMessage(ChatColor.GREEN + "You have been rewarded with 1000$ and 15 blocks of border expansion");
                } else {
                    player.sendMessage(ChatColor.RED + "An error occured: " + eco1.errorMessage);
                }
                break;
            case 7:
            case 6:
            case 8:
            case 9:
            case 11:
            case 12:
                plugin.getWorldborderManager().addBlocks(player, 15);
                player.sendMessage(ChatColor.GREEN + "Border has been expanded by 15 blocks");
                EconomyResponse eco2 = ecomomy.depositPlayer(player, 1000);
                if(eco2.transactionSuccess()){
                    player.sendMessage(ChatColor.GREEN + "You have been rewarded with 1000$ and 15 blocks of border expansion");
                } else {
                    player.sendMessage(ChatColor.RED + "An error occured: " + eco2.errorMessage);
                }

                break;
            case 10:
                plugin.getWorldborderManager().addBlocks(player, 25);
                player.sendMessage(ChatColor.GREEN + "Border has been expanded by 25 blocks");
                EconomyResponse eco3 = ecomomy.depositPlayer(player, 1000);
                if(eco3.transactionSuccess()){
                    player.sendMessage(ChatColor.GREEN + "You have been rewarded with 1000$ and 15 blocks of border expansion");
                } else {
                    player.sendMessage(ChatColor.RED + "An error occured: " + eco3.errorMessage);
                }

                break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                plugin.getWorldborderManager().addBlocks(player, 20);
                player.sendMessage(ChatColor.GREEN + "Border has been expanded by 20 blocks");
                EconomyResponse eco4 = ecomomy.depositPlayer(player, 1000);
                if(eco4.transactionSuccess()){
                    player.sendMessage(ChatColor.GREEN + "You have been rewarded with 1000$ and 15 blocks of border expansion");
                } else {
                    player.sendMessage(ChatColor.RED + "An error occured: " + eco4.errorMessage);
                }

                break;
            case 20:
                plugin.getWorldborderManager().addBlocks(player, 30);
                player.sendMessage(ChatColor.GREEN + "Border has been expanded by 20 blocks");

                EconomyResponse eco5 = ecomomy.depositPlayer(player, 1000);
                if(eco5.transactionSuccess()){
                    player.sendMessage(ChatColor.GREEN + "You have been rewarded with 1000$ and 15 blocks of border expansion");
                } else {
                    player.sendMessage(ChatColor.RED + "An error occured: " + eco5.errorMessage);
                }

                break;




            default: break;
        }

    }
    public List<String> loadTop(){
        Map<String, Integer> data = new HashMap<>();
        for (String key : levelConfig.getKeys(false)){
            data.put(key, levelConfig.getInt(key + ".level"));
        }

        top.clear();
        top = data.entrySet().stream().sorted(
                Collections.reverseOrder(
                        Map.Entry.comparingByValue()
                ))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return top;
    }
    public int getLevel(Player player){
        return levelConfig.getInt(player.getUniqueId() + ".level");
    }
    public int getXp(Player player){
        return levelConfig.getInt(player.getUniqueId() + ".xp");
    }
    public int getRequiredXp(Player player){
        return levelConfig.getInt(player.getUniqueId() + ".requird-xp");
    }
    public String getName(Player player){
        return player.getName();
    }
}
