package way24;

import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Difficulty;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Biome;
import org.bukkit.block.Chest;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Cod;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Villager.Type;
import org.bukkit.event.Listener;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.EulerAngle;

import Chat.Color;
import Chat.message;
import Chat.sound;
import Island.createIs;
import Island.island;
import Money.money;
import NameTags.NameTags;
import NameTags.createNameTags;
import Player.ConditionPlayer;
import Player.Friend;
import Player.Stat;
import Player.Tp;
import Player.player;
import Serv.serv;
import Time.Time;
import Traders.Skits;
import Traders.SpawnVillagers;
import mine.SpawnMonsters;
import mine.createmine;
import mine.createmineAd;
import mine.createmineEnd;
import mine.createmineMeca;
import mine.createmineWater;
import mine.mine;
import mine.mineManager;

public class way24 extends JavaPlugin implements Listener {
	
	public static FileConfiguration		Players;
	public static FileConfiguration		Mine;
	public static FileConfiguration		islands;
	public static FileConfiguration		Server;
	public static World					world;
	public static World					worldmine;
	public static World					worldad;
	public static World					worldwater;
	public static World					worldi;
	public static World					worldend;
	public static World					worldmeca;
	public static ArrayList<World>		worldsPveMine	= new ArrayList<World>();
	public static Location				spawn 			= new Location(Bukkit.getWorld("world"), 8.5, 7, 8.5);
	public static Location				mineLoc 		= new Location(Bukkit.getWorld("worldmine"), 0, 0, 0);
	public static Location				shop			= new Location(Bukkit.getWorld("world"), -1.5, 8, 114.5);
	public static Location				shopPlants		= new Location(Bukkit.getWorld("world"), -3.5, 8, 95.5, 90, 0);
	
	public static Location				portal1 		= new Location(Bukkit.getWorld("world"), -4.5, 8, 102.5, -90, 0);
	public static Location				portal0 		= new Location(Bukkit.getWorld("world"), 24.5, 7, 8.5, 90, 0);
	
	public static ArrayList<Villager>	villagersList	= new ArrayList<Villager>();
	
	public static Location				loc0;
	public static Location				loc1;
	
	public void onEnable()
	{
		Bukkit.getServer().getPluginManager().registerEvents(new RegisterEvents(), this);
		File fileplayer = new File("plugins/Players.yml");
		File filemine = new File("plugins/mine.yml");
		File fileislands = new File("plugins/islands.yml");
		File fileserver = new File("plugins/server.yml");
		try
		{
			fileserver.createNewFile();
			fileislands.createNewFile();
			fileplayer.createNewFile();
			filemine.createNewFile();
		} catch (Exception localException) {}
		Players = player.getconPlayer();
		Mine = mine.getconMine();
		islands = island.getconIs();
		Server = serv.getconServer();
		for (Player p : Bukkit.getOnlinePlayers())
			message.reloadheader(p);
		
		worldmine = Bukkit.createWorld(new WorldCreator("worldmine"));
		worldad = Bukkit.createWorld(new WorldCreator("worldadpve"));
		worldwater = Bukkit.createWorld(new WorldCreator("worldWater"));
		world = Bukkit.createWorld(new WorldCreator("world"));
		worldi = Bukkit.createWorld(new WorldCreator("worli"));
		worldend = Bukkit.createWorld(new WorldCreator("worldend"));
		worldmeca = Bukkit.createWorld(new WorldCreator("worldMeca"));
		
		world.setDifficulty(Difficulty.HARD);
		worldmine.setDifficulty(Difficulty.HARD);
		worldmine.setMonsterSpawnLimit(7);
		worldad.setDifficulty(Difficulty.HARD);
		worldad.setMonsterSpawnLimit(0);
		worldwater.setDifficulty(Difficulty.HARD);
		worldwater.setMonsterSpawnLimit(0);
		worldend.setDifficulty(Difficulty.HARD);
		worldend.setMonsterSpawnLimit(0);
		worldi.setDifficulty(Difficulty.HARD);
		worldi.setMonsterSpawnLimit(15);
		
		worldsPveMine.add(worldmine);
		worldsPveMine.add(worldad);
		worldsPveMine.add(worldwater);
		worldsPveMine.add(worldend);
		worldsPveMine.add(worldmeca);
		
		timer.timer();
	}
	
	public void onDisable()
	{
		player best = null;
		for (String player : Players.getKeys(false))
		{
			player p = new player(player);
			if (best == null && p.parcTimeBest() != 0)
				best = p;
			else
				if (p.parcTimeBest() < best.parcTimeBest() && p.parcTimeBest() != 0)
					best = p;
		}
		for (String player : Players.getKeys(false))
		{
			player p = new player(player);
			Players.set(p.p + ".parcloc", null);
			if (!p.p.equals(best.p))
				Players.set(p.p + ".parclocB", null);
		}
		for (Player p : way24.world.getPlayers())
		{
			player pl = new player(p);
			if (pl.parc())
			{
				pl.parc(false);
				pl.pl.teleport(new Location(way24.world, 6, 7, 19, -94, 0));
			}
		}
		for (String s : Mine.getKeys(false))
		{
			mine m = new mine(Integer.parseInt(s));
			Mine.set(s + ".block", null);
			if (!m.open())
				m.inworld(false);
		}
		timer.r0.cancel();
		saveAll();
	}
	
	public boolean onCommand(CommandSender sender, Command c, String s, String[] args)
	{
		Player p = (Player)sender;
		if ((!new player(p).move()) && (!c.getName().equalsIgnoreCase("reg")) && (!c.getName().equalsIgnoreCase("r")) && (!c.getName().equalsIgnoreCase("register")) && (!c.getName().equalsIgnoreCase("l")) && (!c.getName().equalsIgnoreCase("log")) && (!c.getName().equalsIgnoreCase("login")))
	    	return true;
		player pl = new player(p);
	    if (c.getName().equalsIgnoreCase("reg"))
	    	Pas.setPas(p, args);
	    if (c.getName().equalsIgnoreCase("l"))
	    	Pas.setLog(p, args);
	    if (c.getName().equalsIgnoreCase("color"))
	    	Color.color(p, args[0]);
	    if (c.getName().equalsIgnoreCase("reloads"))
	    {
	    	if (!p.isOp())
	    		return true;
	    	reloadserver();
	    }
	    if (c.getName().equalsIgnoreCase("miniblock"))
	    {
	    	if (!pl.doSpawn())
	    		return true;
	    	if (args.length == 1)
	    		setMiniBlock(p.getLocation(), Integer.parseInt(args[0]), p.getItemInHand().getType());
	    	if (args.length == 2)
	    	{
	    		if (args[0].equalsIgnoreCase("cir"))
	    		{
	    			setMiniBlockCir(p.getLocation(), Integer.parseInt(args[1]), p.getItemInHand().getType());
	    			return true ;
	    		}
	    		setMiniBlockNear(p.getLocation(), Integer.parseInt(args[0]), 0, Integer.parseInt(args[1]), p.getItemInHand().getType());
	    	}	
	    	if (args.length == 3)
	    		setMiniBlockNear(p.getLocation(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), p.getItemInHand().getType());
	    	if (args.length == 4)
	    	{
	    		if (args[0].equalsIgnoreCase("cub"))
	    		{
	    			setMiniBlockCub(p.getLocation(), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), p.getItemInHand().getType());
	    			return true ;
	    		}
	    		if (args[0].equalsIgnoreCase("pix"))
	    		{
	    			setMiniBlockPix(p.getLocation(), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), p.getItemInHand().getType());
	    			return true ;
	    		}
	    	}
	    }
	    if (c.getName().equalsIgnoreCase("stat"))
	    {
	    	if (admin.Do(new player(p), 3000))
				return true;
	    	if (args.length == 0)
	    		Stat.getState(p, pl);
	    	if (args.length == 1)
	    	{
	    		Stat.getState(p, new player(args[0]));
	    	}
	    }
	    if (c.getName().equalsIgnoreCase("pass"))
	    {
	    	if (admin.Do(new player(p), 10000))
				return true;
	    	if (args.length != 3)
	    	{
	    		p.sendMessage(Color.col0 + "/pass <старый пароль> <новый пароль> <повтор нового пароля> " + Color.jast + " - Смена пароля");
	    		return true;
	    	}
	    	if (!args[0].equalsIgnoreCase(pl.getpas()))
	    	{
	    		p.sendMessage(Color.jast + "Старый пароль не верный");
	    		return true;
	    	}
	    	if (!args[1].equalsIgnoreCase(args[2]))
	    	{
	    		p.sendMessage(Color.jast + "Новые пароли не совпадают");
	    		return true;
	    	}
	    	if (args[1].length() < 6)
			{
				sound.nosound(p);
				p.sendMessage("Пароль должен быть " + Chat.Color.col1 + "минимум" + Chat.Color.jast + " из " + Chat.Color.col1 + 7 + Chat.Color.jast + " символов");
				return true;
			}
			pl.setpas(args[1]);
			p.sendMessage(Chat.Color.col0 + "Вы успешно зарегистрировались");
			sound.yessound(p);
			return true;
	    }
	    if (c.getName().equalsIgnoreCase("test"))
	    {
	    	if (!p.isOp())
	    		return true;
	    	Location loc = p.getLocation();
	    	
	    	loc.setY(loc.getY() + 1.7);
	    	
	    	Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
	    	
	    	FireworkMeta fwmeta = fw.getFireworkMeta();
	    	
	    	Builder ef = FireworkEffect.builder();
	    	ef.flicker(true);
	    	ef.trail(true);
	    	ef.with(FireworkEffect.Type.BALL_LARGE);
	    	int	x = -1;
	    	while (++x < Math.random() * 50)
	    	{
	    		ef.withColor(org.bukkit.Color.fromBGR((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)));
	    	}
	    	x = -1;
	    	while (++x < Math.random() * 50)
	    	{
	    		ef.withFade(org.bukkit.Color.fromBGR((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)));
	    	}
	    	FireworkEffect efk = ef.build();	
	    	
	    	fwmeta.addEffect(efk);
	    	fwmeta.setPower(1);
	    	
	    	fw.setFireworkMeta(fwmeta);
	    	
	    	CraftPlayer e = (CraftPlayer) p;
	    }
	    if (c.getName().equalsIgnoreCase("setpose"))
	    {
	    	if (args[0].equals("0"))
	    		loc0 = p.getLocation().clone();
	    	if (args[0].equals("1"))
	    		loc1 = p.getLocation().clone();
	    	if (args[0].equals("2"))
	    	{
	    		Location loc3 = p.getLocation();
	    		int	m = 0;
	    		int	x1 = -1;
	    		while (++x1 <= Math.abs(loc0.getBlockX() - loc1.getBlockX()))
	    		{		
	    			int	y1 = -1;
	    			while (++y1 <= Math.abs(loc0.getBlockY() - loc1.getBlockY()))
		    		{
		    			int	z1 = -1;
		    			while (++z1 <= Math.abs(loc0.getBlockZ() - loc1.getBlockZ()))
			    		{
		    				Location loc = new Location(loc0.getWorld(),
			    					Math.min(loc0.getBlockX(), loc1.getBlockX()) + x1,
			    					Math.min(loc0.getBlockY(), loc1.getBlockY()) + y1,
			    					Math.min(loc0.getBlockZ(), loc1.getBlockZ()) + z1);
			    			if (loc.getBlock().getType().equals(Material.GRASS))
			    				continue ;
			    			if (loc.getBlock().getType().equals(Material.TALL_GRASS))
			    				continue ;
			    			if (loc.getBlock().getType().equals(Material.SEAGRASS))
			    				continue ;
			    			if (loc.getBlock().getType().equals(Material.TALL_SEAGRASS))
			    				continue ;
			    			if (loc.getBlock().getType().equals(Material.LANTERN))
			    				continue ;
			    			if (loc.getBlock().getType().equals(Material.LADDER))
			    				continue ;
			    			if (loc.getBlock().getType().equals(Material.CAMPFIRE))
			    				continue ;
			    			if (loc.getBlock().getType().equals(Material.AIR))
			    				continue ;
		    				final int x = x1;
		    				final int y = y1;
		    				final int z = z1;
		    				m++;
		    				BukkitTask r1 = new BukkitRunnable()
		    				{
		    		            @Override
		    		            public void run()
		    		            {
					    			Location loc = new Location(loc0.getWorld(),
					    					Math.min(loc0.getBlockX(), loc1.getBlockX()) + x,
					    					Math.min(loc0.getBlockY(), loc1.getBlockY()) + y,
					    					Math.min(loc0.getBlockZ(), loc1.getBlockZ()) + z);
					    			if (loc.getBlock().getType().equals(Material.GRASS))
					    				return ;
					    			if (loc.getBlock().getType().equals(Material.TALL_GRASS))
					    				return ;
					    			if (loc.getBlock().getType().equals(Material.SEAGRASS))
					    				return ;
					    			if (loc.getBlock().getType().equals(Material.TALL_SEAGRASS))
					    				return ;
					    			if (loc.getBlock().getType().equals(Material.LANTERN))
					    				return ;
					    			if (loc.getBlock().getType().equals(Material.LADDER))
					    				return ;
					    			if (loc.getBlock().getType().equals(Material.CAMPFIRE))
					    				return ;
					    			if (!loc.getBlock().getType().equals(Material.AIR))
					    			{
					    				setMiniBlockNear(loc3.clone(), x, y, z, loc.getBlock().getType());
					    			}
		    		            	cancel();
		    		            }
		    		        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), m);
			    		}
		    		}
	    		}
	    	}
	    }
	    if (c.getName().equalsIgnoreCase("mineregen"))
	    {
	    	if (!p.isOp())
	    		return true;
	    	if (args.length == 0)
	    	{
	    		p.sendMessage("0 - обычная\n1 - ад\n2 - край\n3 - подводная\n4 - меса");
	    		return true;
	    	}
	    	if (args[0].equalsIgnoreCase("0"))
	    		mineManager.startMine();
	    	if (args[0].equalsIgnoreCase("1"))
	    		mineManager.startMineAd();
	    	if (args[0].equalsIgnoreCase("2"))
	    		mineManager.startMineEnd();
	    	if (args[0].equalsIgnoreCase("3"))
	    		mineManager.startMineWater();
	    	if (args[0].equalsIgnoreCase("4"))
	    		mineManager.startMineMeca();
	    }
	    if (c.getName().equalsIgnoreCase("fr"))
	    	Friend.cmdFr(pl, args);
	    if (c.getName().equalsIgnoreCase("m"))
	    	message.mmes(p, args);
	    if (c.getName().equalsIgnoreCase("mine"))
	    	mineManager.cmdMine(p, 0, args);
	    if (c.getName().equalsIgnoreCase("tell"))
	    	message.mmes(p, args);
	    if (c.getName().equalsIgnoreCase("spawn"))
	    	if (!admin.Do(new player(p), 2000))
				tp(p, spawn);
	    if (c.getName().equalsIgnoreCase("is"))
	    	createIs.cmdIs(p, args);
	    if (c.getName().equalsIgnoreCase("setup"))
	    	admin.cmdSetup(p, args);
	    if (c.getName().equalsIgnoreCase("to"))
	    	Tp.to(p, args);
	    if (c.getName().equalsIgnoreCase("biome"))
	    {
	    	if (args.length == 1)
	    		p.getLocation().getBlock().setBiome(Biome.valueOf(args[0]));
	    	p.sendMessage(p.getLocation().getBlock().getBiome() + "");
	    }
	    if (c.getName().equals("money"))
	    	money.cmdMoney(p, args);
	    if (c.getName().equalsIgnoreCase("shop"))
	    {
	    	if (admin.Do(new player(p), 2000))
				return true;
	    	if (args.length == 0)
	    		tp(p, shop);
	    	if (args.length == 1)
	    	{
	    		if (args[0].equalsIgnoreCase("огородник"))
		    		tp(p, shopPlants);
	    		if (args[0].equalsIgnoreCase("скитс"))
	    			tp(p, shop);
	    		if (args[0].equalsIgnoreCase("шахтер") || args[0].equalsIgnoreCase("шахтёр"))
	    			tp(p, new Location(way24.world, 9.5, 1, 109.5, 90, 0));
	    	}
	    }
	    if (c.getName().equalsIgnoreCase("info"))
	    {
	    	if (admin.Do(new player(p), 3000))
				return true;
	    	p.sendMessage(Color.col0 + "/is " + Color.jast + "- информации об островах");
	    	p.sendMessage(Color.col0 + "/to <ник> " + Color.jast + "- телепортация к игрокам");
	    	p.sendMessage(Color.col0 + "/mine " + Color.jast + "- перемещение в шахту");
	    	p.sendMessage(Color.col0 + "/shop " + Color.jast + "- покупка скитов");
	    	p.sendMessage(Color.col0 + "/spawn " + Color.jast + "- перемещение на спавн, там же и паркур с призом 10000 скитов");
	    	p.sendMessage(Color.c(7) + "---" + Color.col0 + "нововведения" + Color.c(7) + "---");
	    	p.sendMessage(Color.col0 + "1. " + Color.jast + "- подводная шахта стала намного интереснее");
	    }
		return true;
	}
	
	public static void tp(Player p, Location to)
	{
		try
		{
			World be = p.getWorld();
			Location loc = p.getLocation();
			player pl = new player(p);
			p.teleport(to);
			if (to.getWorld().equals(worldi))
			{
				if (island.getIsland(to).id == island.getIsland(loc).id)
					return ;
				p.sendMessage(Color.jast + "Вы попали на остров " + Color.col0 + island.getIsland(to).getName());
				if (pl.getIs() == -1)
					p.setGameMode(GameMode.ADVENTURE);
				else
					if (!new island(pl.getIs()).inIsland(to))
						p.setGameMode(GameMode.ADVENTURE);
					else
						p.setGameMode(GameMode.SURVIVAL);
			}
			if (!be.equals(to.getWorld()))
			{
				if (to.getWorld().equals(worldi))
					ConditionPlayer.island(pl);
				if (to.getWorld().equals(worldmine) || to.getWorld().equals(worldad) || to.getWorld().equals(worldwater) || to.getWorld().equals(worldend) || to.getWorld().equals(worldmeca))
					ConditionPlayer.mine(pl);
				if (to.getWorld().equals(world))
					ConditionPlayer.spawn(pl);
			}
		} catch(Exception ex){}
	}
	
	public static void saveAll()
	{
		for (Player p : Bukkit.getOnlinePlayers())
		{
			if (new player(p).login())
			{
				new player(p).savei();
				new player(p).setOp(p.isOp());
			}
		}
		serv.saveconServer();
		mine.saveconMine();
		island.saveconIs();
		player.saveconPlayer();
//		Players = null;
//		Mine = null;
//		islands = null;
//		Server = null;
//		System.gc();
	}
	
	public static void setMiniBlock(Location loc, int type, Material m)
	{
    	loc.setX(loc.getBlockX() + 0.145);
    	loc.setY(loc.getBlockY() - 0.659);
    	loc.setZ(loc.getBlockZ() + 0.241);
    	
    	loc.setYaw(-45);
    	loc.setPitch(0);
    	
    	if (type == 1)
    		loc.setX(loc.getX() + 0.625);
    	else if (type == 2)
    	{
    		loc.setY(loc.getY() + 0.625);
    	}
    	else if (type == 3)
    	{
    		loc.setX(loc.getX() + 0.625);
    		loc.setY(loc.getY() + 0.625);
    	} 
    	else if (type == 4)
    	{
    		loc.setZ(loc.getZ() - 0.625);
    	} 
    	else if (type == 5)
    	{
    		loc.setX(loc.getX() + 0.625);
    		loc.setZ(loc.getZ() - 0.625);
    	}
    	else if (type == 6)
    	{
    		loc.setZ(loc.getZ() - 0.625);
    		loc.setY(loc.getY() + 0.625);
    	}
    	else if (type == 7)
    	{
    		loc.setX(loc.getX() + 0.625);
    		loc.setZ(loc.getZ() - 0.625);
    		loc.setY(loc.getY() + 0.625);
    	}	
    	
    	ArmorStand ar = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
    	ar.setVisible(false);
		ar.setGravity(false);
		ar.setItemInHand(Item.Item.create(m, 1));
    	ar.setRightArmPose(new EulerAngle(6.02, 0, 0));	
	}
	
	public static void setMiniBlockNear(Location loc, int i, int j, int k, Material m)
	{
    	loc.setX(loc.getBlockX() + 0.145);
    	loc.setY(loc.getBlockY() - 0.659);
    	loc.setZ(loc.getBlockZ() + 0.241);
    	
    	loc.setYaw(-45);
    	loc.setPitch(0);
    	
    	loc.setX(loc.getX() + 0.375 * i);
    	loc.setY(loc.getY() + 0.375 * j);
    	loc.setZ(loc.getZ() + 0.375 * k);
    	
    	ArmorStand ar = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
    	ar.setVisible(false);
		ar.setGravity(false);
		ar.setItemInHand(Item.Item.create(m, 1));
    	ar.setRightArmPose(new EulerAngle(6.02, 0, 0));	
	}
	
	public static ArmorStand setMiniBlockPix(Location loc, int i, int j, int k, Material m)
	{
    	loc.setX(loc.getBlockX() + 0.145);
    	loc.setY(loc.getBlockY() - 0.659);
    	loc.setZ(loc.getBlockZ() + 0.241);
    	
    	loc.setYaw(-45);
    	loc.setPitch(0);
    	
    	loc.setX(loc.getX() + 0.0625 * i);
    	loc.setY(loc.getY() + 0.0625 * j);
    	loc.setZ(loc.getZ() + 0.0625 * k);
    	
    	ArmorStand ar = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
    	ar.setVisible(false);
		ar.setGravity(false);
		ar.setItemInHand(Item.Item.create(m, 1));
    	ar.setRightArmPose(new EulerAngle(6.02, 0, 0));
    	return ar;
	}
	
	public static void setMiniBlockCub(Location loc, int i, int j, int k, Material m)
	{
    	int	x = -1;
    	while (++x < i)
    	{
    		int	y = -1;
    		while (++y < j)
    		{
    			int	z = -1;
    			while (++z < k)
    				if (x == 0 || x == i - 1 || y == 0 || y == j - 1 || z == 0 || z == k - 1)
    					setMiniBlockNear(loc.clone(), x, y, z, m);
    		}
    	}
	}
	
	public static void setMiniBlockCir(Location loc, int i, Material m)
	{
    	int	x = -1;
    	while (++x < i * 2)
    	{
    		int	y = -1;
    		while (++y < i * 2)
    		{
    			int	z = -1;
    			while (++z < i * 2)
    				if (new Location(loc.getWorld(), loc.getX() + x - i, loc.getY() + y - i, loc.getZ() + z - i).distance(loc) <= i - 1 && new Location(loc.getWorld(), loc.getX() + x - i, loc.getY() + y - i, loc.getZ() + z - i).distance(loc) > i - 2)
    					setMiniBlockNear(loc.clone(), x - i, y - i, z - i, m);
    		}
    	}
	}
	
	public static void reloadserver()
	{
		final ArrayList<Integer> count = new ArrayList<Integer>();
		count.add(0);
		File start = new File("run.bat");
		BukkitTask r0 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (count.get(0) == 60)
            	{
            		for (Player p : Bukkit.getOnlinePlayers())
            			p.kickPlayer("Перезагрузка сервера");
            		Bukkit.getServer().spigot().restart();
            		cancel();
            	}
            	if (count.get(0) == 0)
            		for (Player p : Bukkit.getOnlinePlayers())
            			p.sendMessage(Color.jast + "Перезагрузка сервера через : " + Color.col0 + 60 + Color.jast + " секунд");
            	if (count.get(0) == 30)
            		for (Player p : Bukkit.getOnlinePlayers())
            			p.sendMessage(Color.jast + "Перезагрузка сервера через : " + Color.col0 + 30 + Color.jast + " секунд");
            	if (count.get(0) >= 50)
            		for (Player p : Bukkit.getOnlinePlayers())
            			p.sendMessage(Color.jast + "Перезагрузка сервера через : " + Color.col0 + (60 - count.get(0)));
            	if (count.get(0) == 39)
            	try
        		{
        			Runtime.getRuntime().exec("cmd /c" + start);
        		} catch(Exception ex) {}
            	count.set(0, count.get(0) + 1);
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("Hi"), 0, 20);
	}
	
	public static void reloadLvl()
	{
		int		x;
		int		i;
		player	p;
		
		x = -1;
		for (String pl : Players.getKeys(false))
		{
			p = null;
			p = new player(pl);
        	if (p.getIs() == -1 || !new island(p.getIs()).getOwner().p.equals(p.p))
				continue ;
        	i = (int) Math.pow((new island(p.getIs()).size() * 2) / 16, 2) + 10;
        	x += i;
			BukkitTask r = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	player p = new player(pl);
	            	if (p.getIs() == -1 || !new island(p.getIs()).getOwner().p.equals(p.p))
	    				return ;
	    			lvl_calculation(p.getIs());
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), x - i);
		}
	}
	
	public static int	lvl_calculation(int is)
	{
		ArrayList<Chunk>	chunk_list;
		int					center_x;
		int					center_z;
		int					x;
		int					z;
		
		center_x = (int) new island(is).getCenter().getX();
		center_z = (int) new island(is).getCenter().getZ();
		chunk_list	= new ArrayList<Chunk>();
		x = -1;
		while (++x <= new island(is).size() * 2)
		{
			z = -1;
			while (++z <= new island(is).size() * 2)
			{
				Chunk ch = new Location(way24.worldi, center_x + x - new island(is).size(), 0, center_z + z - new island(is).size()).getChunk();
				if (!chunk_list.contains(ch))
					chunk_list.add(ch);
			}
		}
		x = -1;
		for (Chunk ch : chunk_list)
		{
			x++;
			new island(is).setLvlblock(0);
			BukkitTask r = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	int	x;
	            	int	y;
	            	int	z;
	            	
	            	y = -1;
	            	while (++y < 256)
	            	{
	            		x = -1;
	            		while (++x < 16)
	            		{
	            			z = -1;
	            			while (++z < 16)
	            				if (!ch.getBlock(x, y, z).isEmpty())
	            					new island(is).setLvlblock(new island(is).getLvlblock() + 1);
	            		}
	            	}
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), x * 1);
		}	
		return x;
	}
}
