package net.backdoorinc.community.files;

import net.backdoorinc.community.Community;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private Community community;

    public FileManager(Community community) {
        this.community = community;
    }


    public  File warps = new File("plugins/Community", "warps.yml");

    public  FileConfiguration warpconfigFile = (FileConfiguration)YamlConfiguration.loadConfiguration(warps);

    public  File mutes = new File("plugins/Community", "mutes.yml");

    public  YamlConfiguration muteconfigurationfile = YamlConfiguration.loadConfiguration(mutes);

    public  File spawn = new File("plugins/Community", "spawn.yml");

    public  FileConfiguration spawnconfigfile = (FileConfiguration)YamlConfiguration.loadConfiguration(spawn);

    public  File userlist = new File("plugins/Community", "userlist.yml");

    public  FileConfiguration userlistconfig = (FileConfiguration)YamlConfiguration.loadConfiguration(userlist);

    public  File boxen = new File("plugins/Community", "boxen.yml");

    public  FileConfiguration boxenconfig = (FileConfiguration)YamlConfiguration.loadConfiguration(boxen);

    public  File banFile = new File("plugins/Community", "bans.yml");

    public  YamlConfiguration bans = YamlConfiguration.loadConfiguration(banFile);

    public  File CommunitySettingsFile = new File("plugins/Community", "communitysettings.yml");

    public  YamlConfiguration CommunitySettings = YamlConfiguration.loadConfiguration(CommunitySettingsFile);

    public  File SparksFile = new File("plugins/Community", "sparks.yml");

    public  YamlConfiguration Sparks = YamlConfiguration.loadConfiguration(SparksFile);

    public  File SparkButtonsFile = new File("plugins/Community", "sparkbuttons.yml");

    public  YamlConfiguration SparkButtons = YamlConfiguration.loadConfiguration(SparkButtonsFile);

    public  File RankFile = new File("plugins/Community", "userranks.yml");

    public  YamlConfiguration Ranks = YamlConfiguration.loadConfiguration(RankFile);

    public  File ButtonsFile = new File("plugins/Community", "boxenjoinbuttons.yml");

    public  YamlConfiguration JoinButtons = YamlConfiguration.loadConfiguration(ButtonsFile);

    public  File getConfigFile() {
        return new File("plugins/Community", "configBans.yml");
    }

    public  FileConfiguration getConfigFileConfiguration() {
        return (FileConfiguration)YamlConfiguration.loadConfiguration(getConfigFile());
    }

    public  void setDefaults() {
        FileConfiguration config = getConfigFileConfiguration();
        config.options().copyDefaults(true);
        config.addDefault("MySQL.username", "root");
        config.addDefault("MySQL.password", "!");
        config.addDefault("MySQL.database", "localhost");
        config.addDefault("MySQL.host", "localhost");
        config.addDefault("MySQL.port", "port");
        try {
            config.save(getConfigFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readConfig() {
        FileConfiguration config = getConfigFileConfiguration();
        this.community.mySQL.username = config.getString("MySQL.username");
        this.community.mySQL.password = config.getString("MySQL.password");
        this.community.mySQL.database = config.getString("MySQL.database");
        this.community.mySQL.host = config.getString("MySQL.host");
        this.community.mySQL.port = config.getString("MySQL.port");
        this.community.mute.username = config.getString("MySQL.username");
        this.community.mute.password = config.getString("MySQL.password");
        this.community.mute.database = config.getString("MySQL.database");
        this.community.mute.host = config.getString("MySQL.host");
        this.community.mute.port = config.getString("MySQL.port");
        this.community.history.username = config.getString("MySQL.username");
        this.community.history.password = config.getString("MySQL.password");
        this.community.history.database = config.getString("MySQL.database");
        this.community.history.host = config.getString("MySQL.host");
        this.community.history.port = config.getString("MySQL.port");
    }

    public  void loadFile() {
        if (!CommunitySettingsFile.exists()) {
            CommunitySettings.addDefault("Config.PvP", "");
            CommunitySettings.addDefault("Config.TnT", "");
            CommunitySettings.addDefault("Config.Mob", "");
            CommunitySettings.addDefault("Config.Whitelist", "");
            CommunitySettings.set("Config.PvP", "false");
            CommunitySettings.set("Config.TnT", "false");
            CommunitySettings.set("Config.Mob", "false");
            CommunitySettings.set("Config.Whitelist", "false");
            try {
                CommunitySettings.save(CommunitySettingsFile);
                CommunitySettings.load(CommunitySettingsFile);
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("[Community] Fehler beim schreiben der Community-Settings");
                e.printStackTrace();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    public  void check() {
        if (!mutes.exists())
            muteconfigurationfile.set("admin", "o");
        try {
            muteconfigurationfile.save(mutes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!spawn.exists())
            try {
                spawnconfigfile.save(spawn);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Config konnte nicht erstellt werden!");
            }
        if (!warps.exists())
            try {
                warpconfigFile.save(warps);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("beim erstellen der Warps-Datei");
            }
        if (!RankFile.exists())
            try {
                Ranks.save(RankFile);
            } catch (IOException e) {
                e.printStackTrace();
                Bukkit.getConsoleSender().sendMessage("beim erstellen der Rang-Datei!");
            }
        if (!ButtonsFile.exists())
            try {
                JoinButtons.save(ButtonsFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        try {
            JoinButtons.load(ButtonsFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        if (!SparksFile.exists())
            try {
                Sparks.save(SparksFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        if (!SparkButtonsFile.exists())
            try {
                SparkButtons.save(SparkButtonsFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
