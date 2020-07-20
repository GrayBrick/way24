package NameTags;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class createNameTags
{
	public static Entity create(Location loc, double heals)
	{
		ArmorStand nt = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
		nt.setMaxHealth(heals);
		nt.setVisible(false);
		nt.setCustomNameVisible(true);
		nt.setGravity(false);
		return nt;
	}
}
