package net.backdoorinc.community.methods.objects;

import org.bukkit.Location;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Squid;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Guard {
    private Guardian g;

    private Squid s;

    Location loc;

    public Guard(Location loc) {
        this.loc = loc;
    }

    public void summon() {
        this.g = (Guardian)this.loc.getWorld().spawn(this.loc, Guardian.class);
        this.s = (Squid)this.loc.getWorld().spawn(new Location(this.loc.getWorld(), this.loc
                .getX() + 15.0D, this.loc.getY(), this.loc.getZ() + 15.0D), Squid.class);
        this.g.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999999, 999999));
        this.s.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999999, 999999));
        this.g.setTarget((LivingEntity)this.s);
    }

    public void kill() {
        if (this.loc.getWorld().getLivingEntities().contains(this.g))
            this.g.remove();
        if (this.loc.getWorld().getLivingEntities().contains(this.s))
            this.s.remove();
    }
}

