package dev.louis.worldborder.Commands;

import dev.louis.worldborder.Commands.subCommands.*;
import dev.louis.worldborder.Worldborder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class commandManager implements TabExecutor {

    private ArrayList<subCommand> subcommands = new ArrayList<>();
    private final Worldborder plugin;
    public commandManager(Worldborder plugin){
        this.plugin = plugin;

        subcommands.add(new SMPHomeCommand());
        subcommands.add(new SMPCreateCommand());
        subcommands.add(new SMPDeleteCommand());
        subcommands.add(new SMPVisitCommand());
        subcommands.add(new EconomySetMoney());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player){
            Player p = (Player) commandSender;

            if(strings.length > 0) {
                for (int i = 0; i < getSubcommands().size(); i++){
                   if(strings[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
                       if(getSubcommands().get(i).IsOwnerOnly()){
                          if(p.hasPermission("bordersmp.owner") || p.isOp()){
                              getSubcommands().get(i).perform(plugin, p, strings);
                          } else {
                              p.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                          }
                       } else {
                            getSubcommands().get(i).perform(plugin, p, strings);
                       }

                   }
                }
            } else if (strings.length == 0) {
                String worldName = p.getUniqueId().toString();
                World world = Bukkit.getWorld(worldName);

                if(world == null){
                    p.sendMessage(ChatColor.GREEN + "-----------------------------------");
                    for(int i = 0; i < getSubcommands().size(); i++){
                         p.sendMessage(ChatColor.YELLOW + getSubcommands().get(i).getSyntax() + " - " + getSubcommands().get(i).getDescription());
                    }
                    p.sendMessage(ChatColor.GREEN + "-----------------------------------");
                } else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + p.getName() + " " + worldName);
                }
            }
        }
        return true;
    }
    public ArrayList<subCommand> getSubcommands(){
        return subcommands;
    }
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 1) {
            List<String> suggestions = new ArrayList<>();
            for (int i = 0; i < getSubcommands().size(); i++) {
                suggestions.add(getSubcommands().get(i).getName());
            }
            return suggestions;
        }
        return null;
    }
}
