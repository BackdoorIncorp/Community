package net.backdoorinc.community.methods;

import net.backdoorinc.community.Community;
import net.backdoorinc.community.files.FileManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class CommunitySettings {
    private Community community;

    public CommunitySettings(Community community) {
        this.community = community;
    }

    public File CommunitySettingsFile = this.community.fileManager.CommunitySettingsFile;

    public YamlConfiguration CommunitySettings = this.community.fileManager.CommunitySettingsFile;

    public boolean PvP;

    public boolean Tnt;

    public boolean Mob;

    public boolean Whitelist;

    public boolean NoBuild;

    public boolean GlobalMute;

    public boolean NoBed;

    public boolean LavaTP;

    public boolean DisableAchievments;

    public boolean DisableEXP;

    public void loadFile() {
        if (!CommunitySettingsFile.exists()) {
            CommunitySettings.set("Config.PvP", "false");
            CommunitySettings.set("Config.TnT", "false");
            CommunitySettings.set("Config.Mob", "false");
            CommunitySettings.set("Config.Whitelist", "false");
            CommunitySettings.set("Config.NoBuild", "false");
            CommunitySettings.set("Config.GlobalMute", "false");
            CommunitySettings.set("Config.DisableBed", "false");
            CommunitySettings.set("Config.LavaTP", "false");
            CommunitySettings.set("Config.DisableAchievments", "false");
            try {
                CommunitySettings.save(CommunitySettingsFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (CommunitySettings.get("Config.NoBuild") == null)
                CommunitySettings.set("Config.NoBuild", "false");
            if (CommunitySettings.get("Config.GlobalMute") == null)
                CommunitySettings.set("Config.GlobalMute", "false");
            if (CommunitySettings.get("Config.DisableBed") == null)
                CommunitySettings.set("Config.DisableBed", "false");
            if (CommunitySettings.get("Config.LavaTP") == null)
                CommunitySettings.set("Config.LavaTP", "false");
            if (CommunitySettings.get("Config.DisableAchievments") == null)
                CommunitySettings.set("Config.DisableAchievments", "false");
            if (CommunitySettings.get("Config.DisableEXP") == null)
                CommunitySettings.set("Config.DisableEXP", "false");
            try {
                CommunitySettings.save(CommunitySettingsFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 36, "Einstellungen");
        if (PvP) {
            inv.setItem(9, getEnabled("PVP"));
        } else {
            inv.setItem(9, getDiabled("PVP"));
        }
        if (Tnt) {
            inv.setItem(10, getEnabled("TNT"));
        } else {
            inv.setItem(10, getDiabled("TNT"));
        }
        if (Mob) {
            inv.setItem(11, getEnabled("Mob-Spawning"));
        } else {
            inv.setItem(11, getDiabled("Mob-Spawning"));
        }
        if (Whitelist) {
            inv.setItem(12, getEnabled("Whitelist"));
        } else {
            inv.setItem(12, getDiabled("Whitelist"));
        }
        if (NoBuild) {
            inv.setItem(13, getEnabled("NoBuild"));
        } else {
            inv.setItem(13, getDiabled("NoBuild"));
        }
        if (GlobalMute) {
            inv.setItem(14, getEnabled("GlobalMute"));
        } else {
            inv.setItem(14, getDiabled("GlobalMute"));
        }
        if (NoBed) {
            inv.setItem(15, getEnabled("DisableBed"));
        } else {
            inv.setItem(15, getDiabled("DisableBed"));
        }
        if (LavaTP) {
            inv.setItem(16, getEnabled("LavaTP"));
        } else {
            inv.setItem(16, getDiabled("LavaTP"));
        }
        if (DisableAchievments) {
            inv.setItem(17, getEnabled("DisableAchievments"));
        } else {
            inv.setItem(17, getDiabled("DisableAchievments"));
        }
        if (DisableEXP) {
            inv.setItem(27, getEnabled("DisableEXP"));
        } else {
            inv.setItem(27, getDiabled("DisableEXP"));
        }
        inv.setItem(0, getItem("PVP", Arrays.asList(new String[] { "PVP aktiviert ist k, "die Spieler schlagen" }, ), Material.IRON_SWORD));
                inv.setItem(1, getItem("TNT", Arrays.asList(new String[] { "TNT aktiviert ist werden", "durch Explosionen zerst}, ), Material.TNT));
                        inv.setItem(2, getItem("Mob-Spawning", Arrays.asList(new String[] { "Mob-Spawning aktiviert ist k, "spawnen / gespawnt werden" }, ), Material.MONSTER_EGG));
                                inv.setItem(3, getItem("Whitelist", Arrays.asList(new String[] { "die Whitelist aktiviert ist k, "Spieler mit der Permission community.join", "Server betreten" }, ), Material.PAPER));
                                        inv.setItem(4, getItem("NoBuild", Arrays.asList(new String[] { "NoBuild aktiviert ist kann", "nur im Build-Modus bauen" }, ), Material.IRON_AXE));
        inv.setItem(5, getItem("GlobalMute", Arrays.asList(new String[] { "GlobalMute aktiviert ist kann", "nicht mehr im Chat schreiben, au, "hat die Permission community.globalmute.ignore" }, ), Material.IRON_FENCE));
                inv.setItem(6, getItem("DisableBed", Arrays.asList(new String[] { "DisableBed aktiviert ist k, "nicht mehr betreten werden" }, ), Material.BED));
                        inv.setItem(7, getItem("LavaTP", Arrays.asList(new String[] { "LavaTP aktiviert ist werden", "bei Kontakt mit Lava zum Spawn", "und geheilt" }, ), Material.LAVA_BUCKET));
        inv.setItem(8, getItem("DisableAchievments", Arrays.asList(new String[] { "DisableAchievments aktiviert ist k, "keine Achievments bekommen" }, ), Material.PAINTING));
                inv.setItem(18, getItem("DisableEXP", Arrays.asList(new String[] { "DisableEXP aktiviert ist k, "keine EXP Punkte erhalten" }, ), Material.EXP_BOTTLE));
                        p.openInventory(inv);
  }

        public void loadConfig(Player p) {
            try {
                CommunitySettings.load(CommunitySettingsFile);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
            if (CommunitySettings.getString("Config.PvP").equals("true")) {
                PvP = true;
                String name = FileManager.spawnconfigfile.getString("spawn.world");
                Bukkit.getWorld(name).setPVP(true);
            } else if (CommunitySettings.getString("Config.PvP").equals("false")) {
                PvP = false;
                try {
                    String name = FileManager.spawnconfigfile.getString("spawn.world");
                    Bukkit.getWorld(name).setPVP(false);
                } catch (Exception e) {
                    if (p != null)
                        p.sendMessage("ist ein Fehler aufgetreten. Bitte setze zuerst den Spawnpoint! Die Einstellungen werden z.T. nur fdie jeweilige Welt );
                }
            }
            if (CommunitySettings.getString("Config.Mob").equals("true")) {
                Mob = true;
            } else if (CommunitySettings.getString("Config.Mob").equals("false")) {
                Mob = false;
            }
            if (CommunitySettings.getString("Config.TnT").equals("true")) {
                Tnt = true;
            } else if (CommunitySettings.getString("Config.TnT").equals("false")) {
                Tnt = false;
            }
            if (CommunitySettings.getString("Config.Whitelist").equals("true")) {
                Whitelist = true;
            } else if (CommunitySettings.getString("Config.Whitelist").equals("false")) {
                Whitelist = false;
            }
            if (CommunitySettings.getString("Config.NoBuild").equals("true")) {
                NoBuild = true;
            } else if (CommunitySettings.getString("Config.NoBuild").equals("false")) {
                NoBuild = false;
            }
            if (CommunitySettings.getString("Config.GlobalMute").equals("true")) {
                GlobalMute = true;
            } else if (CommunitySettings.getString("Config.GlobalMute").equals("false")) {
                GlobalMute = false;
            }
            if (CommunitySettings.getString("Config.DisableBed").equals("true")) {
                NoBed = true;
            } else if (CommunitySettings.getString("Config.DisableBed").equals("false")) {
                NoBed = false;
            }
            if (CommunitySettings.getString("Config.LavaTP").equals("true")) {
                LavaTP = true;
            } else if (CommunitySettings.getString("Config.LavaTP").equals("false")) {
                LavaTP = false;
            }
            if (CommunitySettings.getString("Config.DisableAchievments").equals("true")) {
                DisableAchievments = true;
            } else if (CommunitySettings.getString("Config.DisableAchievments").equals("false")) {
                DisableAchievments = false;
            }
            if (CommunitySettings.getString("Config.DisableEXP").equals("true")) {
                DisableEXP = true;
            } else if (CommunitySettings.getString("Config.DisableEXP").equals("false")) {
                DisableEXP = false;
            }
        }

        private ItemStack getDiabled(String name) {
            ItemStack disabled = (new Dye(DyeColor.GRAY)).toItemStack();
            ItemMeta disabledMeta = disabled.getItemMeta();
            disabled.setAmount(1);
            disabledMeta.setDisplayName("+ name + " Deaktiviert");
                    disabled.setItemMeta(disabledMeta);
            return disabled;
        }

        private ItemStack getEnabled(String name) {
            ItemStack disabled = (new Dye(DyeColor.LIME)).toItemStack();
            ItemMeta disabledMeta = disabled.getItemMeta();
            disabled.setAmount(1);
            disabledMeta.setDisplayName("+ name + " Aktiviert");
                    disabled.setItemMeta(disabledMeta);
            return disabled;
        }

        private ItemStack getItem(String name, List lore, Material material) {
            ItemStack itemStack = new ItemStack(material);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("+ name);
                    itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
    }
    }
    }
