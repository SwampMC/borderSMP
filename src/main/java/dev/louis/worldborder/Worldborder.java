package dev.louis.worldborder;

import dev.louis.worldborder.Commands.balance;
import dev.louis.worldborder.Commands.commandManager;
import dev.louis.worldborder.Commands.hub;
import dev.louis.worldborder.Commands.spawn;
import dev.louis.worldborder.Listeners.Worldlisteners.WorldListeners;
import dev.louis.worldborder.Listeners.XPListeners.BlockBreakListerners;
import dev.louis.worldborder.Listeners.XPListeners.BlockPlaceListeners;
import dev.louis.worldborder.Listeners.XPListeners.MobKillListeners;
import dev.louis.worldborder.Managers.*;
import dev.louis.worldborder.utils.bungeeCordUtil;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;

public final class Worldborder extends JavaPlugin implements Listener {

    //VARS

    private LevelManager levelManager;

    private WorldborderManager worldborderManager;

    private borderSMPManager BorderSMPManager;

    private bungeeCordUtil bungeecordutil;

    private static Economy econ = null;

    //ENABLE AND DISABLE

    @Override
    public void onDisable() {
        Bukkit.getMessenger().unregisterOutgoingPluginChannel(this, "BungeeCord");
        loadMessages(false);
    }

    @Override
    public void onEnable() {
        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        loadManagers(this);
        loadCommands(this);
        loadEvents(this);

       loadMessages(true);
    }

    //FUNCTIONS

    private void loadCommands(Worldborder plugin){
        getCommand("hub").setExecutor(new hub(plugin));
        getCommand("lobby").setExecutor(new hub(plugin));

        getCommand("bordersmp").setExecutor(new commandManager(plugin));
        getCommand("bsmp").setExecutor(new commandManager(plugin));

        getCommand("spawn").setExecutor(new spawn());
        getCommand("balance").setExecutor(new balance());

    }

    private void loadEvents(Worldborder plugin){

        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new BlockBreakListerners(plugin), plugin);
        pm.registerEvents(new BlockPlaceListeners(plugin), plugin);
        pm.registerEvents(new MobKillListeners(plugin), plugin);

        pm.registerEvents(new WorldListeners(), plugin);
    }

    private void loadManagers(Worldborder plugin){
        levelManager = new LevelManager(plugin);
        worldborderManager = new WorldborderManager(plugin);
        levelManager.loadlevelFile();
        bungeecordutil = new bungeeCordUtil();
        BorderSMPManager = new borderSMPManager(plugin);

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new placerholderAPIManager(this).register();
        }

        CoreProtectAPI api = getCoreProtect();
        if (api != null){
            api.testAPI();
        } else {
            getLogger().info("CoreProtect not found!");
        }
    }

    private void loadMessages(Boolean state){
        if(state){
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[SwampMC] " + ChatColor.WHITE + "SwampMC BorderSMP is " + ChatColor.GREEN + "enabled");
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[SwampMC] " + ChatColor.WHITE + "SwampMC BorderSMP is " + ChatColor.RED + "disabled");
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    //GETTERS

    public static Economy getEconomy() {
        return econ;
    }

    public CoreProtectAPI getCoreProtect() {
        Plugin plugin = getServer().getPluginManager().getPlugin("CoreProtect");

        if (plugin == null || !(plugin instanceof CoreProtect)) {
            return null;
        }

        CoreProtectAPI CoreProtect = ((CoreProtect) plugin).getAPI();
        if (CoreProtect.isEnabled() == false) {
            return null;
        }

        if (CoreProtect.APIVersion() < 9) {
            return null;
        }

        return CoreProtect;
    }

    public LevelManager getLevelManager(){
        return levelManager;
    }

    public WorldborderManager getWorldborderManager() {
        return worldborderManager;
    }

    public bungeeCordUtil getBungeecordutil(){
        return bungeecordutil;
    }

    public borderSMPManager getBorderSMPManager(){
        return BorderSMPManager;
    }


}
