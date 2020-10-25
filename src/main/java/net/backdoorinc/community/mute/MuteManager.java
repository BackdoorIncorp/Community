package net.backdoorinc.community.mute;

import net.backdoorinc.community.Community;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MuteManager {

    private Community community;

    public MuteManager(Community community) {
        this.community = community;
    }

    public void mute(String UUID, String Playername, String Reason, long seconds, String Dauer, String operator) {
        long end = 0L;
        Playername = Playername.toLowerCase();
        String Player = Bukkit.getOfflinePlayer(Playername).getName();
        if (seconds == -1L) {
            end = -1L;
        } else {
            long currentTime = System.currentTimeMillis();
            long millis = seconds * 1000L;
            end = currentTime + millis;
        }
        this.community.mySQL.update("INSERT INTO Mutes (Spielername, UUID, Ende, Grund, Dauer, Muter) VALUES ('" + Player + "','" + UUID + "','" + end + "','" + Reason + "','" + Dauer + "','" + operator + "')");
    }

    public void unmute(String UUID) {
        this.community.mySQL.update("DELETE FROM Mutes WHERE UUID='" + UUID + "'");
    }

    public boolean isMuted(String UUID) {
        ResultSet rs = this.community.mySQL.getResult("SELECT Ende FROM Mutes WHERE UUID='" + UUID + "'");
        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getReason(String UUID) {
        ResultSet rs = this.community.mySQL.getResult("SELECT * FROM Mutes WHERE UUID='" + UUID + "'");
        try {
            if (rs.next())
                return rs.getString("Grund");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getDuration(String UUID) {
        ResultSet rs = this.community.mySQL.getResult("SELECT Dauer FROM Mutes WHERE UUID='" + UUID + "'");
        try {
            if (rs.next())
                return rs.getString("Dauer");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public long getEnd(String UUID) {
        ResultSet rs = this.community.mySQL.getResult("SELECT * FROM Mutes WHERE UUID='" + UUID + "'");
        try {
            if (rs.next())
                return rs.getLong("Ende");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public List<String> getMutedPlayers() {
        List<String> list = new ArrayList<>();
        ResultSet rs = this.community.mySQL.getResult("SELECT * FROM Mutes");
        try {
            while (rs.next())
                list.add(rs.getString("Spielername"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getMuter(String UUID) {
        ResultSet rs = this.community.mySQL.getResult("SELECT * FROM Mutes WHERE UUID='" + UUID + "'");
        try {
            if (rs.next())
                return rs.getString("Muter");
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(this.community.PREFIX + "beim Lesen der Datenbank");
        }
        return "null";
    }

    public String getRemainingTime(String UUID) {
        long current = System.currentTimeMillis();
        long end = getEnd(UUID);
        if (end == -1L)
            return "";
        long millies = end - current;
        long seconds = 0L;
        long minutes = 0L;
        long hours = 0L;
        long days = 0L;
        long weeks = 0L;
        while (millies > 1000L) {
            millies -= 1000L;
            seconds++;
        }
        while (seconds > 60L) {
            seconds -= 60L;
            minutes++;
        }
        while (minutes > 60L) {
            minutes -= 60L;
            hours++;
        }
        while (hours > 24L) {
            hours -= 24L;
            days++;
        }
        while (days > 7L) {
            days -= 7L;
            weeks++;
        }
        return + weeks + " Woche(n) " + days + " Tag(e) " + hours + " Stunde(n) " + minutes + " Minute(n) " + seconds + " Sekunde(n) ";
    }

    public String getUUID(String Playername) {
        return Bukkit.getOfflinePlayer(Playername).getUniqueId().toString();
    }
}
