package net.backdoorinc.community.data;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Data {
    public HashMap<Player, BukkitRunnable> run = new HashMap<>();
    public HashMap<Player,Player> messages = new HashMap<>();
    public HashMap<Player,Player> msg = new HashMap<>();


}
