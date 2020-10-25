package net.backdoorinc.community.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Notify {

    public ArrayList<Player> enabled = new ArrayList<>();

    static String pref = "";

    public boolean isEnabled(Player p) {
        if (enabled.contains(p))
            return true;
        return false;
    }

    public void addPlayer(Player p) {
        enabled.add(p);
    }

    public void removePlayer(Player p) {
        enabled.remove(p);
    }

    public void addAllPlayersReload() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            if ((all.hasPermission("community.sup") || all.hasPermission("community.command.ml"))) {
                enabled.add(all);
                all.sendMessage("Benachrichtigungen wurden aufgrund eines reloads" );
            }
        }
    }

    public void removeAllPlayers() {
        for (Player p : enabled)
            p.sendMessage(pref + "Benachrichtigungen wurden" );
                    enabled.clear();
    }

    public void notifyPlayer(String msg) {
        for (Player all : enabled)
            all.sendMessage(msg);
    }
}



