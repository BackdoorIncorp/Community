package net.backdoorinc.community;

import net.backdoorinc.community.files.FileManager;
import net.backdoorinc.community.mysql.History;
import net.backdoorinc.community.mysql.Mute;
import net.backdoorinc.community.mysql.MySQL;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.ws.Holder;
import java.util.ArrayList;
import java.util.List;

public class Community extends JavaPlugin {


    public static List<String> werbung = new ArrayList<>();

    public static ArrayList<Player> hider_teamshow = new ArrayList<>();

    public static ArrayList<Player> hider_noneshow = new ArrayList<>();

    public static List<String> bw = new ArrayList<>();
    public static Community instance;
    public MySQL mySQL = new MySQL();
    public History history = new History();
    public Mute mute = new Mute();
    public FileManager fileManager = new FileManager(this);
    public final String PREFIX = "";

    @Override
    public void onEnable() {
        mySQL.connect();
        mySQL.createTable();




    }

    @Override
    public void onDisable() {
        mySQL.disconnect();




    }
}
