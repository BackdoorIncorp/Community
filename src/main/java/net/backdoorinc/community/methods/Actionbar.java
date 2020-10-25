package net.backdoorinc.community.methods;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Actionbar {
    public void sendActionBar(Player p, String message) {
        IChatBaseComponent c = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat pac = new PacketPlayOutChat(c, (byte)2);
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket((Packet)pac);
    }
}
