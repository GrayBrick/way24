package NameTags;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Chat.Color;
import Island.createIs;
import Island.island;
import Player.player;
import Traders.Skits;
import way24.Parcur;

public class NameTags
{
	static ArrayList<Location>	NameTagsLoc = new ArrayList<Location>();
	static ArrayList<Double>	NameTagsHeal = new ArrayList<Double>();
	
	public static ArmorStand NameTag(int i)
	{
		for (Entity e : way24.way24.world.getNearbyEntities(NameTagsLoc.get(i), 0.1, 0.1, 0.1))
		{
			if (e instanceof ArmorStand && ((ArmorStand) e).getMaxHealth() == NameTagsHeal.get(i))
				return (ArmorStand) e;
		}
		return (ArmorStand) createNameTags.create(NameTagsLoc.get(i), NameTagsHeal.get(i));
	}
	
	public static void reloadNameTags()
	{	
		//parc
		
		NameTagsLoc.add(0, new Location(way24.way24.world, 12.5, 7.25, 21.5));
		NameTagsHeal.add(0, (double) 500);
		
		NameTagsLoc.add(1, new Location(way24.way24.world, 12.5, 7, 21.5));
		NameTagsHeal.add(1, (double) 508);

		NameTagsLoc.add(2, new Location(way24.way24.world, 12.5, 6.75, 21.5));
		NameTagsHeal.add(2, (double) 501);
		
		NameTagsLoc.add(3, new Location(way24.way24.world, 12.5, 6.50, 21.5));
		NameTagsHeal.add(3, (double) 502);
		
		NameTagsLoc.add(4, new Location(way24.way24.world, 12.5, 6.25, 21.5));
		NameTagsHeal.add(4, (double) 503);
		
		NameTagsLoc.add(5, new Location(way24.way24.world, 12.5, 6, 21.5));
		NameTagsHeal.add(5, (double) 504);
		
		NameTagsLoc.add(6, new Location(way24.way24.world, 12.5, 5.75, 21.5));
		NameTagsHeal.add(6, (double) 505);
		
		NameTagsLoc.add(7, new Location(way24.way24.world, 12.5, 5.50, 21.5));
		NameTagsHeal.add(7, (double) 506);
		
		NameTagsLoc.add(8, new Location(way24.way24.world, 12.5, 5.25, 21.5));
		NameTagsHeal.add(8, (double) 507);
		
		//topIs
		
		NameTagsLoc.add(9, new Location(way24.way24.world, 4.5, 7.25, 21.5));
		NameTagsHeal.add(9, (double) 509);

		NameTagsLoc.add(10, new Location(way24.way24.world, 4.5, 7, 21.5));
		NameTagsHeal.add(10, (double) 517);
		
		NameTagsLoc.add(11, new Location(way24.way24.world, 4.5, 6.75, 21.5));
		NameTagsHeal.add(11, (double) 510);
		
		NameTagsLoc.add(12, new Location(way24.way24.world, 4.5, 6.5, 21.5));
		NameTagsHeal.add(12, (double) 511);
		
		NameTagsLoc.add(13, new Location(way24.way24.world, 4.5, 6.25, 21.5));
		NameTagsHeal.add(13, (double) 512);
		
		NameTagsLoc.add(14, new Location(way24.way24.world, 4.5, 6, 21.5));
		NameTagsHeal.add(14, (double) 513);
		
		NameTagsLoc.add(15, new Location(way24.way24.world, 4.5, 5.75, 21.5));
		NameTagsHeal.add(15, (double) 514);
		
		NameTagsLoc.add(16, new Location(way24.way24.world, 4.5, 5.5, 21.5));
		NameTagsHeal.add(16, (double) 515);
		
		NameTagsLoc.add(17, new Location(way24.way24.world, 4.5, 5.25, 21.5));
		NameTagsHeal.add(17, (double) 516);
		
		NameTagsLoc.add(18, new Location(way24.way24.world, 4.5, 7, 21.5));
		NameTagsHeal.add(18, (double) 517);
		
		NameTag(9).setCustomName("Лучшие острова");
		moveCastro(NameTag(0));
		animMoonWind();
	}
	
	public static String code(int[] l)
	{
		String	s;
		int		x;
		int		i;
		int		i1;
		boolean	f;
		
		if (l.length != 0)
			s = "";
		else
			return " ";
		x = -1;
		i = 0;
		f = true;
		while (++x < l.length)
		{
			if (f && l[x] == 8 && x <= l.length / 2)
				i++;
			else
				f = false;
		}
		f = true;
		i1 = 0;
		x = l.length;
		while (x-- > 0)
		{
			if (f && l[x] == 8 && x >= l.length / 2)
				i1++;
			else
				f = false;
		}
		i = Math.min(i, i1);
		x = -1;
		while (++x < l.length - i * 2)
			s += Color.c(l[x + i]) + "⬛";
		if (s.length() == 0)
			return " ";
		return s;
	}
	
	public static void animMoonWind()
	{
		ArrayList<String>	list;
		final int[]			count;
		int					x;
		int 				i;
		
		count = new int[1];
		list = new ArrayList<String>();
		
		list.add(code(new int[]{})); // 
		list.add(code(new int[]{}));
		list.add(code(new int[]{}));
		list.add(code(new int[]{}));
		list.add(code(new int[]{}));
		list.add(code(new int[]{}));
		list.add(code(new int[]{}));
		list.add(code(new int[]{}));
		
		list.add(code(new int[]{8, 03, 03, 03, 11, 03, 03, 03, 11})); // m
		list.add(code(new int[]{8, 03, 11, 03, 11, 03, 11, 03, 11}));
		list.add(code(new int[]{8, 03, 03, 11, 03, 11, 03, 11, 03, 03, 11}));
		list.add(code(new int[]{8, 03, 03, 11, 8, 03, 11, 8, 03, 03, 11}));
		list.add(code(new int[]{8, 03, 03, 11, 8, 8, 8, 8, 03, 03, 11}));
		list.add(code(new int[]{8, 03, 03, 11, 8, 8, 8, 8, 03, 03, 11}));
		list.add(code(new int[]{}));
		list.add(code(new int[]{}));
		
		list.add(code(new int[]{8, 03, 03, 03, 03, 03, 11})); // o
		list.add(code(new int[]{8, 03, 03, 11, 8, 8, 03, 03, 11}));
		list.add(code(new int[]{8, 03, 11, 8, 8, 8, 8, 03, 11}));
		list.add(code(new int[]{8, 03, 11, 8, 8, 8, 8, 03, 11}));
		list.add(code(new int[]{8, 03, 11, 8, 8, 8, 8, 03, 11}));
		list.add(code(new int[]{8, 03, 03, 11, 8, 8, 03, 03, 11}));
		list.add(code(new int[]{8, 03, 03, 03, 03, 03, 11}));
		list.add(code(new int[]{}));
		
		list.add(code(new int[]{8, 03, 03, 03, 03, 03, 11})); // o
		list.add(code(new int[]{8, 03, 03, 11, 8, 8, 03, 03, 11}));
		list.add(code(new int[]{8, 03, 11, 8, 8, 8, 8, 03, 11}));
		list.add(code(new int[]{8, 03, 11, 8, 8, 8, 8, 03, 11}));
		list.add(code(new int[]{8, 03, 11, 8, 8, 8, 8, 03, 11}));
		list.add(code(new int[]{8, 8, 03, 03, 11, 8, 8, 03, 03, 11, 8}));
		list.add(code(new int[]{8, 8, 8, 03, 03, 03, 03, 03, 11, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		
		list.add(code(new int[]{8, 8, 03, 03, 11, 8, 8, 03, 11, 8, 8})); // N
		list.add(code(new int[]{8, 8, 03, 03, 03, 11, 8, 03, 11, 8, 8}));
		list.add(code(new int[]{8, 8, 03, 11, 03, 11, 8, 03, 11, 8, 8}));
		list.add(code(new int[]{8, 8, 03, 11, 03, 03, 11, 03, 11, 8, 8}));
		list.add(code(new int[]{8, 8, 03, 11, 8, 03, 11, 03, 11, 8, 8}));
		list.add(code(new int[]{8, 8, 03, 11, 8, 03, 03, 03, 11, 8, 8}));
		list.add(code(new int[]{8, 8, 03, 11, 8, 8, 03, 03, 11, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8})); // 
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
			
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8})); // W
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		list.add(code(new int[]{8, 06, 06, 14, 8, 8, 8, 8, 06, 06, 14}));
		list.add(code(new int[]{8, 06, 06, 14, 8, 8, 8, 8, 06, 06, 14}));
		list.add(code(new int[]{8, 06, 06, 14, 8, 06, 14, 8, 06, 06, 14}));
		list.add(code(new int[]{8, 06, 06, 14, 06, 14, 06, 14, 06, 06, 14}));
		list.add(code(new int[]{8, 8, 06, 14, 06, 14, 06, 14, 06, 14, 8}));
		list.add(code(new int[]{8, 8, 06, 06, 06, 14, 06, 06, 06, 14, 8}));
		
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		
		list.add(code(new int[]{8, 8, 8, 06, 06, 06, 06, 14, 8, 8, 8})); // I 
		list.add(code(new int[]{8, 8, 8, 8, 06, 06, 14, 8, 8, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 06, 06, 14, 8, 8, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 06, 06, 14, 8, 8, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 06, 06, 14, 8, 8, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 06, 06, 14, 8, 8, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 06, 06, 06, 06, 14, 8, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		
		list.add(code(new int[]{8, 8, 06, 06, 14, 8, 8, 06, 14, 8, 8})); // N
		list.add(code(new int[]{8, 8, 06, 06, 06, 14, 8, 06, 14, 8, 8}));
		list.add(code(new int[]{8, 8, 06, 14, 06, 14, 8, 06, 14, 8, 8}));
		list.add(code(new int[]{8, 8, 06, 14, 06, 06, 14, 06, 14, 8, 8}));
		list.add(code(new int[]{8, 8, 06, 14, 8, 06, 14, 06, 14, 8, 8}));
		list.add(code(new int[]{8, 8, 06, 14, 8, 06, 06, 06, 14, 8, 8}));
		list.add(code(new int[]{8, 8, 06, 14, 8, 8, 06, 06, 14, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		
		list.add(code(new int[]{8, 06, 06, 06, 06, 06, 06, 06, 14, 8, 8})); // D
		list.add(code(new int[]{8, 06, 06, 14, 8, 8, 8, 06, 06, 14, 8}));
		list.add(code(new int[]{8, 06, 06, 14, 8, 8, 8, 8, 06, 14, 8}));
		list.add(code(new int[]{8, 06, 06, 14, 8, 8, 8, 8, 06, 14, 8}));
		list.add(code(new int[]{8, 06, 06, 14, 8, 8, 8, 8, 06, 14, 8}));
		list.add(code(new int[]{8, 06, 06, 14, 8, 8, 8, 06, 06, 14, 8}));
		list.add(code(new int[]{8, 06, 06, 06, 06, 06, 06, 06, 14, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8})); // 
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		list.add(code(new int[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}));
		
		x = -1;
		while (++x < 8)
		{
			i = x + 1;
			NameTag(i).setCustomName(list.get(0));
		}
		count[0] = 0;
		BukkitTask r = new BukkitRunnable()
		{
			@Override
			public void run()
			{
				int	x;
				int	i;
				
				x = -1;
				if (count[0]++ >= list.size() - 11)
					cancel();
				while (++x < 8)
				{
					i = x + 1;
					NameTag(i).setCustomName(list.get(i + count[0]));
					NameTag(i + 9).setCustomName(list.get(i + count[0]));
				}
			}
		}.runTaskTimer(Bukkit.getPluginManager().getPlugin("Hi"), 0, 2);
		
		BukkitTask r1 = new BukkitRunnable()
		{
			@Override
			public void run()
			{
				setTopParc();
				setTopIs();
			}
		}.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), 2 * (list.size() - 9));
	}
	
	public static void setTopParc()
	{
		ArrayList<player> toplist = Parcur.getTopList();
		
		NameTag(1).setCustomName("Приз " + Color.c(13) + "" + Color.c(17) + "10.000" + Color.jast + " скитов");
		if (toplist.size() >= 1)
			NameTag(2).setCustomName(Color.c(14) + "" + Color.c(17) + toplist.get(0).p + Color.jast + " время " + Color.col0 + (double) toplist.get(0).parcTimeBest() / 1000 + Color.jast + " сек.");
		else
			NameTag(2).setCustomName(Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_");
		if (toplist.size() >= 2)
			NameTag(3).setCustomName(Color.c(15) + "" + Color.c(17) + toplist.get(1).p + Color.jast + " время " + Color.col0 + (double) toplist.get(1).parcTimeBest() / 1000 + Color.jast + " сек.");
		else
			NameTag(3).setCustomName(Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_");
		if (toplist.size() >= 3)
			NameTag(4).setCustomName(Color.c(6) + "" + Color.c(17) + toplist.get(2).p + Color.jast + " время " + Color.col0 + (double) toplist.get(2).parcTimeBest() / 1000 + Color.jast + " сек.");
		else
			NameTag(4).setCustomName(Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_");
		
		int x = 4;
		while (++x < 9)
		{
			if (toplist.size() >= x)
				NameTag(x).setCustomName(Color.c(7) + toplist.get(x - 1).p + Color.jast + " время " + Color.col0 + (double) toplist.get(x - 1).parcTimeBest() / 1000 + Color.jast + " сек.");
			else
				NameTag(x).setCustomName(Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_");
		}
	}
	
	public static void setTopIs()
	{
		ArrayList<String> toplist;
		
		toplist = null;
		toplist = createIs.getTopList();
		if (toplist.size() >= 1)
			NameTag(10).setCustomName(Color.c(14) + "" + Color.c(17) + new island(toplist.get(0)).getName() + Color.jast + " уровень " + Color.col0 + new DecimalFormat("###.##").format(Math.sqrt(new island(toplist.get(0)).getLvl())) + Color.jast + " глава " + Color.c(11) + new island(toplist.get(0)).getOwner().p);
		else
			NameTag(10).setCustomName(Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_");
		if (toplist.size() >= 2)
			NameTag(11).setCustomName(Color.c(15) + "" + Color.c(17) + new island(toplist.get(1)).getName() + Color.jast + " уровень " + Color.col0 + new DecimalFormat("###.##").format(Math.sqrt(new island(toplist.get(1)).getLvl())) + Color.jast + " глава " + Color.c(11) + new island(toplist.get(1)).getOwner().p);
		else
			NameTag(11).setCustomName(Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_");
		if (toplist.size() >= 3)
			NameTag(12).setCustomName(Color.c(6) + "" + Color.c(17) + new island(toplist.get(2)).getName() + Color.jast + " уровень " + Color.col0 + new DecimalFormat("###.##").format(Math.sqrt(new island(toplist.get(2)).getLvl())) + Color.jast + " глава " + Color.c(11) + new island(toplist.get(2)).getOwner().p);
		else
			NameTag(12).setCustomName(Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_");
		
		int x = 12;
		while (++x < 18)
		{
			if (toplist.size() >= x)
				NameTag(x).setCustomName(Color.c(7) + new island(toplist.get(x - 10)).getName() + Color.jast + " уровень " + Color.col0 + new DecimalFormat("###.##").format(Math.sqrt(new island(toplist.get(x - 10)).getLvl())) + Color.jast + " глава " + Color.c(11) + new island(toplist.get(x - 10)).getOwner().p);
			else
				NameTag(x).setCustomName(Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_" + Color.c(7) + "-" + Color.c(8) + "_");
		}
	}
	
	public static void moveCastro(ArmorStand ParcNameTag)
	{
		int	x = 2;
		int	add = x;
		int	col = (int) (Math.random() * 15);
		ArrayList<String> list = new ArrayList<String>();
		list.add("Таблица лидеров");
		list.add(Color.c(col) + "Т" + Color.jast + "аблица лидеров");
		list.add("Т" + Color.c(col) + "а"  + Color.jast + "блица лидеров");
		list.add("Та" + Color.c(col) + "б" + Color.jast + "лица лидеров");
		list.add("Табл" + Color.c(col) + "и" + Color.jast + "ца лидеров");
		list.add("Табли" + Color.c(col) + "ц" + Color.jast + "а лидеров");
		list.add("Таблиц" + Color.c(col) + "а" + Color.jast + " лидеров");
		list.add("Таблица" + Color.c(col) + " л" + Color.jast + "идеров");
		list.add("Таблица л" + Color.c(col) + "и" + Color.jast + "деров");
		list.add("Таблица ли" + Color.c(col) + "д" + Color.jast + "еров");
		list.add("Таблица лид" + Color.c(col) + "е" + Color.jast + "ров");
		list.add("Таблица лиде" + Color.c(col) + "р" + Color.jast + "ов");
		list.add("Таблица лидер" + Color.c(col) + "о" + Color.jast + "в");
		list.add("Таблица лидеро" + Color.c(col) + "в");
		list.add("Таблица лидеров");
		list.add(Color.c(col) + "Т" + Color.jast + "аблица лидеров");
		list.add("Таблица лидеров");
		list.add("аблица лидеров п");
		list.add("блица лидеров па");
		list.add("лица лидеров парк");
		list.add("ица лидеров парку");
		list.add("ца лидеров паркур");
		list.add("а лидеров паркура");
		list.add("лидеров паркура ");
		list.add("идеров паркура с");
		list.add("деров паркура се");
		list.add("еров паркура сер");
		list.add("ров паркура серв");
		list.add("ов паркура серве");
		list.add("в паркура сервер");
		list.add(" паркура сервера");
		list.add("паркура сервера ");
		list.add("аркура сервера C");
		list.add("ркура сервера Ca");
		list.add("кура сервера Cas");
		list.add("ура сервера Cast");
		list.add("ра сервера Castr");
		list.add("а сервера Castro");
		list.add(" сервера Castro");
		list.add("сервера Castro ");
		list.add("ервера Castro  ");
		list.add("рвера Castro   ");
		list.add("вера Castro    ");
		list.add("ера Castro     ");
		list.add("ра Castro      ");
		list.add("а Castro       ");
		list.add(" Castro        ");
		list.add("  Castro       ");
		list.add("   Castro      ");
		list.add("    Castro     ");
		list.add("     Castro    ");
		list.add("     CastrO    ");
		list.add("     CAstrO    ");
		list.add("     CAstRO    ");
		list.add("     CAStRO    ");
		list.add("     CASTRO    ");
		list.add("⧼    CASTRO   ⧽");
		list.add("﴿⧼   CASTRO  ⧽﴾");
		list.add("﴿⧼  CASTRO ⧽﴾");
		list.add("﴿⧼CASTRO⧽﴾");		
		list.add("﴿ ⧼CASTRO⧽ ﴾");
		list.add("﴿ ⧼ CASTRO ⧽ ﴾");
		list.add("﴿ ⧼ C ASTR O ⧽ ﴾");
		list.add("﴿ ⧼ C A ST R O ⧽ ﴾");
		list.add("﴿ ⧼ C A S T R O ⧽ ﴾");
		list.add("﴿  ⧼ C A S T R O ⧽  ﴾");
		list.add("﴿  ⧼  C A S T R O  ⧽  ﴾");
		list.add("﴿  ⧼  C  A S T R  O  ⧽  ﴾");
		list.add("﴿  ⧼  C  A  S T  R  O  ⧽  ﴾");	
		list.add("﴿  ⧼  C  A  S  T  R  O  ⧽  ﴾");
		list.add(Color.c(col) + "﴿" + Color.jast + "  ⧼  C  A  S  T  R  O  ⧽  ﴾");
		list.add("﴿" + Color.c(col) + "  ⧼"  + Color.jast +  "  C  A  S  T  R  O  ⧽  ﴾");
		list.add("﴿  ⧼" + Color.c(col) + "  C"  + Color.jast +  "  A  S  T  R  O  ⧽  ﴾");
		list.add("﴿  ⧼  C" + Color.c(col) + "  A"  + Color.jast +  "  S  T  R  O  ⧽  ﴾");
		list.add("﴿  ⧼  C  A" + Color.c(col) + "  S"  + Color.jast +  "  T  R  O  ⧽  ﴾");
		list.add("﴿  ⧼  C  A  S" + Color.c(col) + "  T"  + Color.jast +  "  R  O  ⧽  ﴾");
		list.add("﴿  ⧼  C  A  S  T" + Color.c(col) + "  R"  + Color.jast +  "  O  ⧽  ﴾");
		list.add("﴿  ⧼  C  A  S  T  R" + Color.c(col) + "  O"  + Color.jast +  "  ⧽  ﴾");
		list.add("﴿  ⧼  C  A  S  T  R  O" + Color.c(col) + "  ⧽"  + Color.jast +  "  ﴾");
		list.add("﴿  ⧼  C  A  S  T  R  O  ⧽" + Color.c(col) + "  ﴾");
		list.add("﴿  ⧼  C  A  S T  R  O  ⧽  ﴾");
		list.add("﴿  ⧼  C  A S T R  O  ⧽  ﴾");
		list.add("﴿  ⧼  C A S T R O  ⧽  ﴾");
		list.add("﴿  ⧼ C A S T R O ⧽  ﴾");
		list.add("﴿ ⧼ C A S T R O ⧽ ﴾");
		list.add("﴿ ⧼ C A ST R O ⧽ ﴾");
		list.add("﴿ ⧼ C ASTR O ⧽ ﴾");
		list.add("﴿ ⧼ CASTRO ⧽ ﴾");
		list.add("﴿ ⧼CASTRO⧽ ﴾");
		list.add("﴿⧼CASTRO⧽﴾");
		for (String s : list)
		{
			BukkitTask rn = new BukkitRunnable()
			{
				@Override
				public void run()
				{
					ParcNameTag.setCustomName(s);
					cancel();
				}
			}.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), x);
			x += add;
		}
		int	g = x;
		for (String s : list)
		{
			BukkitTask r = new BukkitRunnable()
			{
				@Override
				public void run()
				{
					ParcNameTag.setCustomName(s);
					cancel();
				}
			}.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), g + x);
			x -= add;
		}
	}
}
