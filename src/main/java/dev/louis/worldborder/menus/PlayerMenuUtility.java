package dev.louis.worldborder.menus;

import org.bukkit.entity.Player;

public class PlayerMenuUtility {

    private Player owner;
    private Player playerToPunish;

    public PlayerMenuUtility(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }

    public Player getPlayerToPunish() {
        return playerToPunish;
    }

    public void setPlayerToPunish(Player playerToKill) {
        this.playerToPunish = playerToKill;
    }

}
