package dev.louis.worldborder.Commands;

import dev.louis.worldborder.Worldborder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class hub implements CommandExecutor {
    private final Worldborder plugin;
    public hub(Worldborder plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            plugin.getBungeecordutil().connectToServer(plugin, player, "lobby");

        }


        return false;
    }
}
