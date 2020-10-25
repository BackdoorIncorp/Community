package net.backdoorinc.community.data;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Fly {
    private ArrayList<Player> fly = new ArrayList<>();

    public void addFly(Player p) {
        if (!isFly(p))
            fly.add(p);
    }

    public boolean isFly(Player p) {
        if (fly.contains(p))
            return true;
        return false;
    }

    public void removeFly(Player p) {
        if (isFly(p))
            fly.remove(p);
    }
}

