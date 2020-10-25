package net.backdoorinc.community.methods.objects;

import net.backdoorinc.community.Community;
import net.backdoorinc.community.youtuber.Box;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import sun.plugin2.main.server.Plugin;

public class Armor {


    private ArmorStand head;

    private ArmorStand dname;

    private ArmorStand waiting;

    private ArmorStand time;

    private String name = null;

    private Location loc = null;

    private ItemStack is = null;

    private Box b;

    static int count;

    static int count2;

    public Armor(String name, Location loc, ItemStack is, Box b) {
        this.name = name;
        this.loc = new Location(loc.getWorld(), loc.getX(), loc.getY() + 2.0D, loc.getZ(), loc.getYaw(), loc.getPitch());
        this.is = is;
        this.b = b;
    }

    public void summon() {
        this.head = (ArmorStand)this.loc.getWorld().spawn(this.loc, ArmorStand.class);
        this.head.setVisible(false);
        this.head.setGravity(false);
        this.head.setHelmet(this.is);
        this.head.setTicksLived(2147483647);
        count = Bukkit.getScheduler().scheduleSyncRepeatingTask(Community.instance, new Runnable() {
                    @Override
                    public void run() {
                        if (Armor.this.head.getLocation().getYaw() != 360.0F) {
                            Armor.this.head.teleport(new Location(Armor.this.head.getLocation().getWorld(), Armor.this.head.getLocation().getX(), Armor.this.head.getLocation().getY(), Armor.this.head.getLocation().getZ(), Armor.this.head.getLocation().getYaw() + 2.0F, Armor.this.head.getLocation().getPitch()));
                        } else {
                            Armor.this.head.getLocation().setYaw(0.0F);
                        }
                    }
                },0L,0L);
                count2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Community.instance, new Runnable() {
                    public void run() {
                        Armor.this.time.setCustomName("YOUR NAME");
                    }
                }, 0L, 20L);
        this.dname = (ArmorStand)this.loc.getWorld().spawn(sub(this.loc, 1.2F), ArmorStand.class);
        this.dname.setVisible(false);
        this.dname.setGravity(false);
        this.dname.setCustomNameVisible(true);
        this.dname.setCustomName(this.name);
        this.dname.setTicksLived(2147483647);
        this.waiting = (ArmorStand)this.loc.getWorld().spawn(sub(this.loc, 1.5F), ArmorStand.class);
        this.waiting.setVisible(false);
        this.waiting.setGravity(false);
        this.waiting.setCustomNameVisible(true);
        this.waiting.setCustomName("Wartezeit:");
        this.waiting.setTicksLived(2147483647);
        this.time = (ArmorStand)this.loc.getWorld().spawn(sub(this.loc, 1.8F), ArmorStand.class);
        this.time.setVisible(false);
        this.time.setGravity(false);
        this.time.setCustomNameVisible(true);
        this.time.setCustomName("YOUR NAME");
        this.time.setTicksLived(2147483647);
    }

    public void delete() {
        this.loc = null;
        Bukkit.getScheduler().cancelTask(count);
        Bukkit.getScheduler().cancelTask(count2);
        this.head.setHealth(0.0D);
        this.head.remove();
        this.dname.setHealth(0.0D);
        this.dname.remove();
        this.waiting.setHealth(0.0D);
        this.waiting.remove();
        this.time.setHealth(0.0D);
        this.time.remove();
        this.head = null;
        this.dname = null;
        this.waiting = null;
        this.time = null;
        this.time = null;
        this.name = null;
        this.is = null;
    }

    private Location sub(Location loc, float value) {
        return new Location(loc.getWorld(), loc.getX(), loc.getY() - value, loc.getZ(), loc.getYaw(), loc.getPitch());
    }
}

