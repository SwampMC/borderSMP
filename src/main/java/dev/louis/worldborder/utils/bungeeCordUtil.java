package dev.louis.worldborder.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import dev.louis.worldborder.Worldborder;
import org.bukkit.entity.Player;
public class bungeeCordUtil {
    public void connectToServer(Worldborder plugin, Player player, String servername){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(servername);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

}
