package net.backdoorinc.community.youtuber;

import net.backdoorinc.community.Community;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;

public class Box {
    private ArrayList<Player> queque = new ArrayList<>();

    File file = new File("plugins/Community", "box.yml");

    FileConfiguration box = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);

    File file2 = new File("plugins/Community", "spawn.yml");

    FileConfiguration spawn = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file2);

    private Player youtuber;

    private Player spieler;

    private Community plugin;

    private static Box boxclass;

    public void enter(Player p, String boxname) {
        if (p.hasPermission("community.youtuber"))
            if (this.youtuber == null) {
                double x = this.box.getDouble(boxname.toLowerCase() + ".x");
                double y = this.box.getDouble(boxname.toLowerCase() + ".y");
                double z = this.box.getDouble(boxname.toLowerCase() + ".z");
                World w = Bukkit.getServer().getWorld(this.box.getString(boxname.toLowerCase() + ".world"));
                float yaw = (float)this.box.getLong(boxname.toLowerCase() + ".yaw");
                float pitch = (float)this.box.getLong(boxname.toLowerCase() + ".pitch");
                p.teleport(new Location(w, x, y, z, yaw, pitch));
                p.sendMessage("Spain der Box ;)");
                setyoutuber(p);
            } else {
                p.sendMessage("Box ist bereits in Verwendung.");
            }
    }

    public void leave(Player p, String Boxname) {
        if (p.hasPermission("community.youtuber")) {
            this.youtuber = null;
            World w = Bukkit.getServer().getWorld(this.spawn.getString("spawn.world"));
            double x = this.spawn.getDouble("spawn.x");
            double y = this.spawn.getDouble("spawn.y");
            double z = this.spawn.getDouble("spawn.z");
            p.teleport(new Location(w, x, y, z));
            p.sendMessage("erfolgreich verlassen.");
            for (Player all : this.queque)
                all.sendMessage(p.getDisplayName() + "hat die Box verlassen!");
            this.queque.clear();
        }
    }

    public void JoinPlayer(Player p) {
        if (this.queque.contains(p)) {
            p.sendMessage("bist bereits in der Warteschlange!");
        } else {
            this.queque.add(p);
            p.sendMessage("wurdest zur Warteschlange hinzugef");
        }
    }

    public ArrayList<Player> getQueque() {
        return this.queque;
    }

    public Player getyoutuber() {
        return this.youtuber;
    }

    public Player getspieler() {
        return this.spieler;
    }

    public void setyoutuber(Player p) {
        this.youtuber = p;
    }

    public void setspieler(final Player p) {
        this.spieler = p;
        p.teleport((Entity)this.youtuber);
        final float xp = p.getExp();
        final int lvl = p.getLevel();
        p.setLevel(0);
        p.setExp(0.0F);
        (new BukkitRunnable() {
            int counter = 10;

            public void run() {
                p.setLevel(this.counter);
                if (this.counter <= 0) {
                    Box.this.spieler = null;
                    p.setLevel(lvl);
                    p.setExp(xp);
                    World w = Bukkit.getServer().getWorld(Box.this.spawn.getString("spawn.world"));
                    double x = Box.this.spawn.getDouble("spawn.x");
                    double y = Box.this.spawn.getDouble("spawn.y");
                    double z = Box.this.spawn.getDouble("spawn.z");
                    p.teleport(new Location(w, x, y, z));
                    if (Box.this.queque.size() > 0) {
                        Player p2 = Box.this.queque.get(0);
                        Box.this.setspieler(p2);
                        Box.this.queque.remove(p2);
                    }
                    cancel();
                }
                this.counter--;
            }
        }).runTaskTimer(Community.instance, 20L, 20L);
    }
}

