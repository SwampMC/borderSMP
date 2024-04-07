package dev.louis.worldborder.Commands;

import dev.louis.worldborder.Worldborder;
import org.bukkit.entity.Player;

public abstract class subCommand {
    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract Boolean IsOwnerOnly();

    public abstract void perform(Worldborder plugin, Player player, String args[]);

}
