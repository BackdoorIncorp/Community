package net.backdoorinc.community.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Spy {
    public HashMap<Player, Player> specificPlayerSpy = new HashMap<>();

    public ArrayList<Player> allEnabled = new ArrayList<>();

    public boolean isListeningPlayer(Player p) {
        if (specificPlayerSpy.containsKey(p))
            return true;
        return false;
    }

    public boolean isListeningAll(Player p) {
        if (allEnabled.contains(p))
            return true;
        return false;
    }

    public Player getPlayerSingle(Player p) {
        if (isListeningPlayer(p))
            return specificPlayerSpy.get(p);
        return null;
    }

    public void addAll(Player p) {
        if (!isListeningAll(p))
            allEnabled.add(p);
    }

    public void removeAll(Player p) {
        if (allEnabled.contains(p))
            allEnabled.remove(p);
    }

    public void addSpecific(Player p, Player target) {
        if (!isListeningPlayer(p))
            specificPlayerSpy.put(p, target);
    }

    public void removeSpecific(Player p) {
        if (isListeningPlayer(p))
            specificPlayerSpy.remove(p);
    }

    public void sendAll(Player p, String msg) {
        if (specificPlayerSpy.containsValue(p)) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                if (specificPlayerSpy.get(all) != null)
                    all.sendMessage(msg);
            }
            for (Player all : allEnabled)
                all.sendMessage(msg);
        }
    }
}

