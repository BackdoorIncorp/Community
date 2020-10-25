package net.backdoorinc.community.methods;

import net.backdoorinc.community.Community;
import net.backdoorinc.community.data.Vanished;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

public class Hider {
    private static Vanished v;

    private static Community plugin;

    public static ItemStack youtuber() {
        ItemStack youtuberi = (new Dye(DyeColor.PURPLE)).toItemStack();
        youtuberi.setAmount(1);
        ItemMeta enabledMeta = youtuberi.getItemMeta();
        enabledMeta.setDisplayName("Youtuber und Teammitglieder anzeigen");
        youtuberi.setItemMeta(enabledMeta);
        return youtuberi;
    }

    public static ItemStack none() {
        ItemStack nonei = (new Dye(DyeColor.GRAY)).toItemStack();
        nonei.setAmount(1);
        ItemMeta noneMeta = nonei.getItemMeta();
        noneMeta.setDisplayName("Spieler anzeigen");
        nonei.setItemMeta(noneMeta);
        return nonei;
    }

    public static ItemStack all() {
        ItemStack alli = (new Dye(DyeColor.LIME)).toItemStack();
        alli.setAmount(1);
        ItemMeta allItemMeta = alli.getItemMeta();
        allItemMeta.setDisplayName("Spieler anzeigen");
        alli.setItemMeta(allItemMeta);
        return alli;
    }

    public static void setYoutuber(Player p) {
        if (!Community.hider_teamshow.contains(p))
            Community.hider_teamshow.add(p);
        if (Community.hider_noneshow.contains(p))
            Community.hider_noneshow.remove(p);
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.hasPermission("community.youtuber")) {
                p.showPlayer(all);
                continue;
            }
            p.hidePlayer(all);
            p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
        }
        v.hide(p);
        p.getInventory().clear(8);
    }

    public static void setAll(Player p) {
        if (Community.hider_teamshow.contains(p))
            Community.hider_teamshow.remove(p);
        if (Community.hider_noneshow.contains(p))
            Community.hider_noneshow.remove(p);
        for (Player players : Bukkit.getOnlinePlayers()) {
            p.showPlayer(players);
            p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
        }
        v.hide(p);
        p.getInventory().clear(8);
        p.getInventory().setItem(8, all());
    }

    public static void setNone(Player p) {
        if (Community.hider_teamshow.contains(p))
            Community.hider_teamshow.remove(p);
        if (!Community.hider_noneshow.contains(p))
            Community.hider_noneshow.add(p);
        for (Player all : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(all);
            p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
        }
        p.getInventory().clear(8);
        p.getInventory().setItem(8, none());
    }
}

