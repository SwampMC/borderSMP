package dev.louis.worldborder.Managers;

import dev.louis.worldborder.Data.Role;
import dev.louis.worldborder.Worldborder;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class borderSMPManager {
    private final Worldborder plugin;
    private YamlConfiguration SMPConfig;
    private File SMPFile;

    public borderSMPManager(Worldborder plugin){
        this.plugin = plugin;
    }
    public void loadSMPFile(){
        plugin.getDataFolder().mkdir();
        SMPFile = new File(plugin.getDataFolder(), "members-data.yml");
        if(!SMPFile.exists()){
            try{
                SMPFile.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        SMPConfig = YamlConfiguration.loadConfiguration(SMPFile);
    }
    public void addMemberPlayer(Player player, Player member, Role role){
        if(!SMPConfig.contains(player.getUniqueId().toString() + "-members")) {
            SMPConfig.set(player.getUniqueId() + ".smp", player.getUniqueId());
            SMPConfig.set(player.getUniqueId() + ".member", player.getUniqueId());
            SMPConfig.set(player.getUniqueId() + ".role", role);
        }
        try{
            SMPConfig.save(SMPFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void removeMemberPlayer(Player player, Player member){
        if(SMPConfig.contains(player.getUniqueId().toString() + "-members")) {
            SMPConfig.set(player.getUniqueId() + ".smp", null);
            SMPConfig.set(player.getUniqueId() + ".member", null);
            SMPConfig.set(player.getUniqueId() + ".role", null);
        }
        try{
            SMPConfig.save(SMPFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void setRole(Player player, Role role){
        if(SMPConfig.contains(player.getUniqueId().toString() + "-members")) {
            SMPConfig.set(player.getUniqueId() + ".role", role);
        }
        try{
            SMPConfig.save(SMPFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public Role getRole(Player player){
        if(SMPConfig.contains(player.getUniqueId().toString() + "-members")) {
            return (Role) SMPConfig.get(player.getUniqueId() + ".role");
        }
        return null;
    }
    public List<Player> getMembers(Player player){
        List<Player> members = new ArrayList<>();
        if(SMPConfig.contains(player.getUniqueId().toString() + "-members")) {
            for(String member : SMPConfig.getStringList(player.getUniqueId() + "-members")){
                members.add(plugin.getServer().getPlayer(member));
            }
        }
        return members;
    }
    public boolean isMember(Player player, Player member){
        if(SMPConfig.contains(player.getUniqueId().toString() + "-members")) {
            return SMPConfig.getStringList(player.getUniqueId() + "-members").contains(member.getUniqueId().toString());
        }
        return false;
    }
    public boolean isRole(Player player, Role role){
        if(SMPConfig.contains(player.getUniqueId().toString() + "-members")) {
            return SMPConfig.get(player.getUniqueId() + ".role").equals(role);
        }
        return false;
    }
}
