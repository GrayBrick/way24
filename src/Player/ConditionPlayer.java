package Player;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Team;

import Chat.Color;
import Chat.message;
import Chat.sound;
import mine.createmineWater;
import mine.mine;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import way24.Learner;
import way24.way24;

public class ConditionPlayer
{
	public static void joinServer(player p)
	{	
		byte[] b = p.p.getBytes(StandardCharsets.UTF_8);
		if (!p.p.equals(new String(b)))
		{
			p.pl.kickPlayer("В нике запрещенные символы");
			return ;
		}
		if (p.first() == 0)
			p.first(new Date().getTime());
		p.pl.setExp(0);
		p.pl.setLevel(0);
		p.login(false);
		p.pl.getInventory().clear();
		p.pl.setMaxHealth(20);
		p.pl.setHealth(20);
		p.pl.setFoodLevel(20);
		p.pl.setFireTicks(0);
		p.pl.setFallDistance(0);
		try
		{
			p.pl.teleport(way24.spawn);
		}catch(Exception ex) 
		{
			Bukkit.getServer().reload();
			p.pl.kickPlayer("перезайди");
			return ;
		}	
		p.move(false);
		p.pl.setOp(false);
		p.pl.setGameMode(GameMode.SPECTATOR);
		p.inmine(false);
		sound.joinsound(p.pl);
	}
	public static void login(player p)
	{
		p.login(true);
		p.pl.setOp(p.getOp());
		if (p.learnStade() == 0)
			Learner.LearnSrart(p);
		if (p.learnStade() == 1)
			Learner.Iscreate(p);
		if (p.learnStade() == 2)
			Learner.PickCreate(p);
		if (p.learnStade() == 3)
			Learner.GiveCobbl(p);
		if (p.learnStade() == 4)
			Learner.sethome(p);
		if (p.learnStade() == 5)
			Learner.money(p);
		if (p.learnStade() == 6)
			Learner.setwheat(p);
		if (p.learnStade() == 7)
			Learner.Meca(p);
		p.loadi();
		p.move(true);
		if (p.getLeave() == null)
			way24.tp(p.pl, way24.spawn);
		else
			way24.tp(p.pl, p.getLeave());
		sound.yessound(p.pl);
		message.reloadheader(p.pl);
	}
	public static void postReg(player p)
	{
		Learner.LearnSrart(p);
		way24.tp(p.pl, way24.spawn);
		p.pl.setGameMode(GameMode.ADVENTURE);
		p.login(true);
		p.move(true);
		p.pl.setHealth(20);
		sound.yessound(p.pl);
		message.reloadheader(p.pl);
	}
	public static void quit(player p)
	{
		if (p.login())
		{
			p.setTimeInGame(p.getTimeInGame() + (new Date().getTime() - p.lastlogin()));
			if (p.inmine())
			{
				mine m = new mine(p.getMine());
				m.removeMember(p.p);
				p.setLeave(way24.spawn);
			}
			else
				p.setLeave();
			p.savei();
			p.setOp(p.pl.isOp());
		}	
	}
	
	public static void mine(player p)
	{
		if (p.parc())
			p.parc(false);
		p.inmine(true);
		p.pl.setInvulnerable(true);
		p.pl.sendMessage(Color.c(2) + "Неуязвимость включена");
		
		Location loc = p.pl.getLocation();
		
		if (loc.getWorld().equals(way24.worldwater))
		{
			int	x;
			
			x = -1;
			while (++x < (int)((Math.random() * 10) + 2))
			{
				loc.setX(loc.getX() + (int)(Math.random() * 6) - 3);
				loc.setY(loc.getY() + (int)(Math.random() * 6) - 3);
				loc.setZ(loc.getZ() + (int)(Math.random() * 6) - 3);
				if (!(loc.getBlock().getType().equals(Material.WATER) || loc.getBlock().getType().equals(Material.SEA_PICKLE)
						|| loc.getBlock().getType().equals(Material.KELP) || loc.getBlock().getType().equals(Material.KELP_PLANT)
						|| loc.getBlock().getType().equals(Material.SEA_PICKLE) || loc.getBlock().getType().equals(Material.SEAGRASS)))
					continue ;
				int type = (int)(Math.random() * 100);
				if (type < 20)
					loc.getWorld().spawnEntity(loc, EntityType.COD);
				else
				if (type < 40)
					loc.getWorld().spawnEntity(loc, EntityType.SALMON);
				else
				if (type < 60)
					loc.getWorld().spawnEntity(loc, EntityType.TROPICAL_FISH);
				else
				if (type < 80)
					loc.getWorld().spawnEntity(loc, EntityType.PUFFERFISH);
				else
				if (type < 100)
					loc.getWorld().spawnEntity(loc, EntityType.TURTLE);
			}
		}
        BukkitTask rl = new BukkitRunnable()
		{
        	@Override
            public void run()
            {
        		p.pl.setInvulnerable(false);
        		p.pl.sendMessage(Color.c(4) + "Неуязвимость отключена");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), 6 * 20);
		for (Player pl : Bukkit.getOnlinePlayers())
		{
			if (!pl.getName().equals("_GreyBrick_"))
				p.pl.showPlayer(pl);
		}
		p.pl.setGameMode(GameMode.SURVIVAL);
	}
	
	public static void island(player p)
	{
		if (p.parc())
			p.parc(false);
		for (Player pl : Bukkit.getOnlinePlayers())
		{
			if (!pl.getName().equals("_GreyBrick_"))
				p.pl.showPlayer(pl);
		}
		if (p.inmine())
		{
			mine m = new mine(p.getMine());
	    	m.removeMember(p.p);
	    	p.setMine(-1);
	    	p.inmine(false);
		}
	}
	
	public static void spawn(player p)
	{
		if (p.parc())
			p.parc(false);
		if (p.inmine())
		{
			mine m = new mine(p.getMine());
	    	m.removeMember(p.p);	    	
	    	p.setMine(-1);
	    	p.inmine(false);
		}
		p.pl.setGameMode(GameMode.ADVENTURE);
	}
}
