package net.backdoorinc.community.data;

import org.bukkit.entity.Player;

import java.util.List;

public class Vanished {
    private List<Player> vanished;

    public void addVanished(Player p) {
        if (!vanished.contains(p))
            vanished.add(p);
    }

    public boolean isVanished(Player p) {
        if (vanished.contains(p))
            return true;
        return false;
    }

    public void removeVanished(Player p) {
        if (vanished.contains(p))
            vanished.remove(p);
    }

    public List<Player> isVanish = vanished;
}

