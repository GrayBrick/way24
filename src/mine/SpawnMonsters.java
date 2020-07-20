package mine;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Player.player;

public class SpawnMonsters
{
	public static void spawnM()
	{
		int	time = 0;
		int	nterval = 3;
		
    	BukkitTask r0 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	for (Player p : way24.way24.worldad.getPlayers())
        		{
        			player pl = new player(p);
        			Location dis = p.getLocation();
        			if (!pl.inmine())
        				continue ;
        			int	x = -1;
        			while (++x < 10)
        			{
        				Location loc = createmineAd.getSpawn(pl.getMine());
        				if (loc.distance(dis) > 25)
        				{
        					x--;
        					continue ;
        				}
        				int l = 0;
        				for (Entity e : loc.getWorld().getNearbyEntities(loc, 8, 8, 8))
        				{
        					if (e instanceof Monster || e instanceof Player)
        						l++;
        				}
        				if (l > 1)
        					continue ;
        				int	type = (int) (Math.random() * 100);
        				if (type < 30)
        					loc.getWorld().spawnEntity(loc, EntityType.ZOMBIFIED_PIGLIN);
        				else
        				if (type < 60)
        					loc.getWorld().spawnEntity(loc, EntityType.MAGMA_CUBE);
        				else
        				if (type < 90)
        					loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
        				else
        					loc.getWorld().spawnEntity(loc, EntityType.WITHER_SKELETON);
        			}
        		}
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + 3 * 15 * 20);
        
        time += 3 * 15 * 20;
        
        BukkitTask r1 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	for (Player p : way24.way24. worldwater.getPlayers())
        		{
        			player pl = new player(p);
        			Location dis = p.getLocation();
        			if (!pl.inmine())
        				continue ;
        			int	x = -1;
        			while (++x < 10)
        			{
        				Location loc = createmineWater.getSpawn(pl.getMine());
        				if (loc.distance(dis) > 25)
        				{
        					x--;
        					continue ;
        				}
        				int l = 0;
        				for (Entity e : loc.getWorld().getNearbyEntities(loc, 8, 8, 8))
        						l++;
        				if (l > 1)
        					continue ;
        				int	type = (int) (Math.random() * 90);
        					if (type < 10)
        						loc.getWorld().spawnEntity(loc, EntityType.SQUID);
        				else
        					if (type < 15)
        						loc.getWorld().spawnEntity(loc, EntityType.GUARDIAN);
        				else
        					if (type < 30)
        						loc.getWorld().spawnEntity(loc, EntityType.DROWNED);
        				else
        					if (type < 40)
        						loc.getWorld().spawnEntity(loc, EntityType.COD);
        				else
        					if (type < 50)
        						loc.getWorld().spawnEntity(loc, EntityType.SALMON);
        				else
        					if (type < 60)
        						loc.getWorld().spawnEntity(loc, EntityType.TROPICAL_FISH);
        				else
        					if (type < 70)
        						loc.getWorld().spawnEntity(loc, EntityType.PUFFERFISH);
        				else
        					if (type < 80)
        						loc.getWorld().spawnEntity(loc, EntityType.TURTLE);
        				else
        					if (type < 90)
        						loc.getWorld().spawnEntity(loc, EntityType.DOLPHIN);
        			}
        		}
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + 3 * 15 * 20);
        
        time += 3 * 15 * 20;
        
        BukkitTask r2 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	for (Player p : way24.way24.worldend.getPlayers())
        		{
        			player pl = new player(p);
        			Location dis = p.getLocation();
        			if (!pl.inmine())
        				continue ;
        			int	x = -1;
        			while (++x < 10)
        			{
        				Location loc = createmineEnd.getSpawn(pl.getMine());
        				if (loc.distance(dis) > 25)
        				{
        					x--;
        					continue ;
        				}
        				int l = 0;
        				for (Entity e : loc.getWorld().getNearbyEntities(loc, 8, 8, 8))
        						l++;
        				if (l > 1)
        					continue ;
        				int	type = (int) (Math.random() * 100);
        					if (type < 25)
        						loc.getWorld().spawnEntity(loc, EntityType.ENDERMAN);
        				else
        					if (type < 50)
        						loc.getWorld().spawnEntity(loc, EntityType.ENDERMITE);
        				else
        					if (type < 75)
        						loc.getWorld().spawnEntity(loc, EntityType.SHULKER);
        				else
            				if (type < 100)
            					loc.getWorld().spawnEntity(loc, EntityType.PHANTOM);
        			}
        		}
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + 3 * 15 * 20);
        
        time += 3 * 15 * 20;
        
        BukkitTask r3 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	for (Player p : way24.way24.worldmeca.getPlayers())
        		{
        			player pl = new player(p);
        			Location dis = p.getLocation();
        			if (!pl.inmine())
        				continue ;
        			int	x = -1;
        			while (++x < 10)
        			{
        				Location loc = createmineMeca.getSpawn(pl.getMine());
        				if (loc.getBlock().getLightLevel() > 7)
        					continue ;
        				if (loc.distance(dis) > 25)
        				{
        					x--;
        					continue ;
        				}
        				int l = 0;
        				for (Entity e : loc.getWorld().getNearbyEntities(loc, 8, 8, 8))
        						l++;
        				if (l > 1)
        					continue ;
        				int	type = (int) (Math.random() * 100);
        					if (type < 20)
        						loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
        				else
        					if (type < 40)
        						loc.getWorld().spawnEntity(loc, EntityType.HUSK);
        				else
        					if (type < 60)
        						loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
        				else
        					if (type < 80)
        						loc.getWorld().spawnEntity(loc, EntityType.OCELOT);
        				else
        					if (type < 100)
        						loc.getWorld().spawnEntity(loc, EntityType.PILLAGER);
        			}
        		}
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + 3 * 15 * 20);
        
        time += 3 * 15 * 20;
        
        BukkitTask r4 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	for (Player p : way24.way24.worldmine.getPlayers())
        		{
        			player pl = new player(p);
        			Location dis = p.getLocation();
        			if (!pl.inmine())
        				continue ;
        			int	x = -1;
        			while (++x < 10)
        			{
        				Location loc = createmine.getSpawn(pl.getMine());
        				if (loc.getBlock().getLightLevel() > 7)
        					continue ;
        				if (loc.distance(dis) > 25)
        				{
        					x--;
        					continue ;
        				}
        				int l = 0;
        				for (Entity e : loc.getWorld().getNearbyEntities(loc, 8, 8, 8))
        						l++;
        				if (l > 1)
        					continue ;
        				int	type = (int) (Math.random() * 100);
        					if (type < 20)
        						loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
        				else
        					if (type < 40)
        						loc.getWorld().spawnEntity(loc, EntityType.SPIDER);
        				else
        					if (type < 55)
        						loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
        				else
        					if (type < 70)
        						loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
        				else
        					if (type < 90)
        						loc.getWorld().spawnEntity(loc, EntityType.ENDERMAN);
        				else
        					if (type < 95)
        						loc.getWorld().spawnEntity(loc, EntityType.WITCH);
        				else
            				if (type < 100)
            					loc.getWorld().spawnEntity(loc, EntityType.SLIME);
        			}
        		}
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + 3 * 15 * 20);        
	}
}
