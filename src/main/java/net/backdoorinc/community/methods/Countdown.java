package net.backdoorinc.community.methods;

import net.backdoorinc.community.Community;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Countdown {
     private int countdown;

    private  Community plugin = Community.instance;

     private int count;


    public void Countdown(int value) {
        countdown = value;
        count = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            public void run() {
                if (Countdown.countdown > 0) {
                    for (Player all : Bukkit.getOnlinePlayers())
                        all.setLevel(Countdown.countdown);
                    if ((((Countdown.countdown == 60) ? 1 : 0) | ((Countdown.countdown == 50) ? 1 : 0) | ((Countdown.countdown == 40) ? 1 : 0) | ((Countdown.countdown == 30) ? 1 : 0) | ((Countdown.countdown == 20) ? 1 : 0) | ((Countdown.countdown == 10) ? 1 : 0) | ((Countdown.countdown == 5) ? 1 : 0) | ((Countdown.countdown == 4) ? 1 : 0) | ((Countdown.countdown == 3) ? 1 : 0) | ((Countdown.countdown == 2) ? 1 : 0) | ((Countdown.countdown == 1) ? 1 : 0)) != 0) {
                        Bukkit.broadcastMessage( +  Countdown.countdown + "Sekunden!");
                        for (Player all : Bukkit.getOnlinePlayers())
                            all.playSound(all.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
                    }
                    Countdown.countdown--;
                } else {
                    Countdown.finish();
                }
            }
        },0L, 20L);
    }

    public void finish() {
        Bukkit.getScheduler().cancelTask(count);
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.setLevel(0);
            all.playSound(all.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
        }
        de.monitorparty.community.cmd.Countdown.isCounting = false;
        Bukkit.broadcastMessage("beendet!");
    }

    public void werdasliestdasistnureintest(Player player) {}
}

