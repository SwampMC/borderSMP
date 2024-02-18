package dev.louis.worldborder.Commands.subCommands;

import dev.louis.worldborder.Commands.subCommand;
import dev.louis.worldborder.Data.Role;
import dev.louis.worldborder.Worldborder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MemberAddCommand extends subCommand {
    @Override
    public String getName() {
        return "addmember";
    }

    @Override
    public String getDescription() {
        return "Add a member to your smp";
    }

    @Override
    public String getSyntax() {
        return "/bordersmp addmember <player> <role>";
    }

    @Override
    public Boolean IsOwnerOnly() {
        return false;
    }

    @Override
    public void perform(Worldborder plugin, Player player, String[] args) {

    }

}
