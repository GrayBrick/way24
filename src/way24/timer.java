package way24;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.EulerAngle;

import Chat.Color;
import NameTags.NameTags;
import Player.player;
import Time.Time;
import Traders.SpawnVillagers;
import mine.SpawnMonsters;
import mine.mine;
import mine.mineManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

public class timer
{
	static BukkitTask r0;
	static BukkitTask time;
	
	public static void timer()
	{
		if (r0 != null)
			r0.cancel();
		if (time != null)
			time.cancel();
		time = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	way24.world.setFullTime(6013);
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("Hi"), 0, 3);
		r0 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	int min = new Date().getMinutes();
            	int	sec = 0;
            	if ((min + 5) % 60 == 0 && sec == 0)
            	{
            		way24.saveAll();
        	        ComponentBuilder m1 = new ComponentBuilder(Color.col1 + "[" + Color.col0 + "Последние Изменения" + Color.col1 + "]" + Color.jast + "<-- Жми  04.09.19 1:30");
        	        m1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/info"));
        	        m1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Нажми для просмотра").color(Color.c1(3)).create()));
            		for (Player p : Bukkit.getOnlinePlayers())
            			p.spigot().sendMessage(m1.create());
            		mineManager.startMine();
            	}
            	if (sec == 0)
            	{
            		SpawnVillagers.spawn();
            		player best = null;
            		for (String player : way24.Players.getKeys(false))
            		{
            			player p = new player(player);
            			if (best == null && p.parcTimeBest() != 0)
            				best = p;
            			else
            				if (p.parcTimeBest() < best.parcTimeBest() && p.parcTimeBest() != 0)
            					best = p;
            		}
            		String bestname = best.p;
        	    	for (String loc : way24.Players.getConfigurationSection(bestname + ".parclocB").getKeys(false))
        	    	{
        	    		String[] loc1 = way24.Players.getString(bestname + ".parclocB." + loc).split(":");
        	    		final Location locparc = new Location(way24.world, (double)Integer.parseInt(loc1[0]) / 1000, (double)Integer.parseInt(loc1[1]) / 1000, (double)Integer.parseInt(loc1[2]) / 1000);
    		    		BukkitTask r0 = new BukkitRunnable()
    		    		{
    		                @Override
    		                public void run()
    		                {
    		                	for (Player p : way24.world.getPlayers())
    		                		p.spawnParticle(Particle.NOTE, locparc, 1);
    		                }
    		            }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), Integer.parseInt(loc) / 50);
        	    	}
                	NameTags.reloadNameTags();
            		for (String id : way24.Mine.getKeys(false))
            		{
            			mine m = new mine(Integer.parseInt(id));
            			if (!m.open())
            				continue ;
            			if (new Date().getTime() - m.timeStart() >= mineManager.timeLive - 3000)
            			{
            				mineManager.delMine(m);
            				continue ;
            			}
            			for (String s : m.getMembers())
            			{
            				if (!Bukkit.getOfflinePlayer(s).isOnline())
            				{
            					m.removeMember(s);
            					continue ;
            				}
        					new player(s).pl.sendMessage(Color.jast + "До закрытия пещеры " + Color.col0 + Time.time(( - new Date().getTime() + (m.timeStart() + mineManager.timeLive)) / 1000));
        					if ((- new Date().getTime() + (m.timeStart() + mineManager.timeLive)) / 1000 < 90)
        					{
        						BukkitTask r0 = new BukkitRunnable()
        			    		{
        			                @Override
        			                public void run()
        			                {
        			                	new player(s).pl.sendMessage(Color.jast + "До закрытия пещеры " + Color.col0 + Time.time(( - new Date().getTime() + (m.timeStart() + mineManager.timeLive)) / 1000));
        			                }
        			            }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), ((- new Date().getTime() + (m.timeStart() + mineManager.timeLive)) / 50) - 30 * 20);
        			            BukkitTask r1 = new BukkitRunnable()
        			    		{
        			                @Override
        			                public void run()
        			                {
        			                	new player(s).pl.sendMessage(Color.jast + "До закрытия пещеры " + Color.col0 + Time.time(( - new Date().getTime() + (m.timeStart() + mineManager.timeLive)) / 1000));
        			                }
        			            }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), ((- new Date().getTime() + (m.timeStart() + mineManager.timeLive)) / 50) - 10 * 20);
        					}
            			}
            		}
            		
            		ArmorStand ar = SpawnVillagers.getWar();
            		ArmorStand ar1 = SpawnVillagers.getWar1();
            		
            		double	x1;
        			
        			x1 = 0;
        			while ((x1 += 0.01) < 100)
        			{
        				final double x11 = x1;
        				BukkitTask r1 = new BukkitRunnable()
        				{
        		            @Override
        		            public void run()
        		            {
        		            	for (Entity e : ar.getNearbyEntities(1, 1, 1))
        		            		if (e instanceof Player)
        		            			if (((LivingEntity) e).getHealth() > 1)
        		            			{
        		            				e.teleport(new Location(way24.world, e.getLocation().getX() + Math.random() * 1 - 0.5, e.getLocation().getY(), e.getLocation().getZ() + Math.random() * 1 - 0.5, e.getLocation().getYaw(), e.getLocation().getPitch()));
        		            				way24.world.spawnParticle(Particle.CRIT_MAGIC, new Location(way24.world, e.getLocation().getX() + Math.random() * 1 - 0.5, e.getLocation().getY() + 1, e.getLocation().getZ() + Math.random() * 1 - 0.5, e.getLocation().getYaw(), e.getLocation().getPitch()), 1);
        		            				way24.world.playSound(e.getLocation(), Sound.values()[270], 1, 1);
        		            				((LivingEntity) e).setHealth(((LivingEntity) e).getHealth() - 1);
        		            			}   		            				
        		            	ar.setRightArmPose(new EulerAngle((x11 * x11 * x11) / 10000, (x11 * x11) / 100, x11));
        		            	ar1.setRightArmPose(new EulerAngle((x11 * x11 * x11) / 10000, x11, (x11 * x11) / 100));
        		            	cancel();
        		            }
        		        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), (long) (x1 * 5));
        			}
        			
        			double m1 = x1;
        			
        			while ((x1 -= 0.01) > 0)
        			{
        				final double x11 = x1;
        				BukkitTask r1 = new BukkitRunnable()
        				{
        		            @Override
        		            public void run()
        		            {
        		            	for (Entity e : ar.getNearbyEntities(1, 1, 1))
        		            		if (e instanceof Player)
        		            			if (((LivingEntity) e).getHealth() > 1)
        		            			{
        		            				e.teleport(new Location(way24.world, e.getLocation().getX() + Math.random() * 1 - 0.5, e.getLocation().getY(), e.getLocation().getZ() + Math.random() * 1 - 0.5, e.getLocation().getYaw(), e.getLocation().getPitch()));
        		            				way24.world.spawnParticle(Particle.CRIT_MAGIC, new Location(way24.world, e.getLocation().getX() + Math.random() * 1 - 0.5, e.getLocation().getY(), e.getLocation().getZ() + Math.random() * 1 - 0.5, e.getLocation().getYaw(), e.getLocation().getPitch()), 1);
        		            				way24.world.playSound(e.getLocation(), Sound.values()[270], 1, 1);
        		            				((LivingEntity) e).setHealth(((LivingEntity) e).getHealth() - 1);
        		            			} 
        		            	ar.setRightArmPose(new EulerAngle((x11 * x11 * x11) / 10000, (x11 * x11) / 100, x11));
        		            	ar1.setRightArmPose(new EulerAngle((x11 * x11 * x11) / 10000, x11, (x11 * x11) / 100));
        		            	cancel();
        		            }
        		        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), (long) (m1 * 5 + (long) ((m1 - x1) * 5)));
        			}
                	SpawnMonsters.spawnM();
            	}
            	if (min % 59 == 0 && sec == 0)
            	{
            		way24.reloadLvl();
            		timer();
            		cancel();
            	}
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("Hi"), 1200, 1200);
	}
}
