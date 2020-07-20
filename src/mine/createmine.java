package mine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Barrel;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Player.player;
import way24.way24;

public class createmine
{
	public static int		size = 80;
	public static Double	coef;
	public static World		world 		= way24.worldmine;
	public static int		interval	= 256;
	public static int		type		= 0;
	
	public static mine	create(double c)
	{
		coef = c;
		int		time;
		int		x = 1;
		mine	mn1;
		
		time = 0;
		while (true)
		{
			if (!way24.Mine.contains(x + ""))
			{
				mn1 = new mine(x);
				mn1.inworld(true);
				break ;
			}
			if (new mine(x).inworld())
				x++;
			else
			{
				mn1 = new mine(x);
				mn1.inworld(true);
				break ;
			}
		}
		final mine mn = new mine(x);
		if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
			Bukkit.getPlayer("_GreyBrick_").sendMessage("stone" + "\n" + mn.id);
		mn.setType(type);
		Location loc = new Location(world, mn.id * interval, 0, 0);
		int	i;
		int	j;
		
		x = 0;
		while (++x < size)
			setPlase(mn, x, x * 1);		
		
		time += x * 1;
		
		time = setType(mn.id, time, mn, 5);	                    
        
		x = 0;
		while (++x < (int) (Math.random() * (size * 1.5 / 10)) + 8)
		{
			final int x1 = x;
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
	        		{
	        			Player p = Bukkit.getPlayer("_GreyBrick_");
	        			if (new player(p).root(type))
	        				p.sendMessage("Образование пещер " + x1);
	        		}
	            	setWorm(mn.id, (int) (Math.random() * 2 + 2), (int) (Math.random() * 3), mn);
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x * 1);			
		}
		time += x * 1;
		
		x = 0;
		while (++x < (int) (Math.random() * (size * 1.5 / 10)) + 40)
		{
			final int x1 = x;
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
	        		{
	        			Player p = Bukkit.getPlayer("_GreyBrick_");
	        			if (new player(p).root(type))
	        				p.sendMessage("Образование пещер " + x1);
	        		}
	            	setWorm(mn.id, (int) (Math.random() * 2 + 1), (int) (Math.random() * 3), mn);
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x * 1);			
		}
		time += x * 1;
		
        time = setBedr(mn, time, type);
        
		BukkitTask r1 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	int	y = 0;
            	while (++y < 7)
            	{
            		int	i = 0;
            		while (++i < size)
            		{
            			int j = 0;
            			while (++j < size)
            			{
            				Location loc = new Location(world, mn.id * interval + i, y, j);
            				if (mn.getBlock(mn.id * interval + i, y, j).equals("minecraft:air"))
            					mn.setBlock(mn.id * interval + i, y, j, "minecraft:lava");
            			}
            		}
            	}
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + 10); 
        
        time = timerset(world, mn, time, new ArrayList<String>());
        
        BukkitTask rl = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Отчистка кеша");
        		}
            	way24.Mine.set(mn.id + ".block", null);
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + 15);
        
        mn.timegen(time + 15 + 1440);
    	
		return mn;
	}
	
	public static int timerset(World world, mine mn, int time, ArrayList<String> list)
	{
		BukkitTask rl = new BukkitRunnable()
		{
            @Override
            public void run()
            {
        		int	count = 0;
        		for (String l0 : way24.Mine.getConfigurationSection(mn.id + ".block").getKeys(false))
            	{
        			count += 1;
        			BukkitTask rl = new BukkitRunnable()
        			{
        	            @Override
        	            public void run()
        	            {
        	            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        	        		{
        	        			Player p = Bukkit.getPlayer("_GreyBrick_");
        	        			if (new player(p).root(type))
        	        				p.sendMessage("Заполнение листа " + l0);
        	        		}
        	            	for (String l1 : way24.Mine.getConfigurationSection(mn.id + ".block" + "." + l0).getKeys(false))
                        		for (String l2 : way24.Mine.getConfigurationSection(mn.id + ".block" + "." + l0 + "." + l1).getKeys(false))
                        			list.add(l0 + " " + l1 + " " + l2 + " " + way24.Mine.getString(mn.id + ".block" + "." + l0 + "." + l1 + "." + l2));
        	            	cancel();
        	            }
        	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), count);
            	}
        		BukkitTask rl = new BukkitRunnable()
    			{
    	            @Override
    	            public void run()
    	            {
    	            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
    	        		{
    	        			Player p = Bukkit.getPlayer("_GreyBrick_");
    	        			if (new player(p).root(type))
    	        				p.sendMessage("Сортировка листа");
    	        		}
    	            	Collections.sort(list, new Comparator<String>()
    	        		{
    	        	        public int compare(String o1, String o2) {
    	        	        	if(Integer.parseInt(o1.split(" ")[1]) > Integer.parseInt(o2.split(" ")[1]))
    	        	        		return 1;
    	        	        	if(Integer.parseInt(o1.split(" ")[1]) < Integer.parseInt(o2.split(" ")[1]))
    	        	        		return -1;
    	        	        	else
    	        	        		return 0;
    	        	        }
    	        		});
    	            	int x = -1;
    	            	int	y = 1;
    	            	int	c = 0;
    	            	ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
    	            	res.add(new ArrayList<String>());
    	            	for (String str : list)
    	            	{
    	            		++x;
    	            		if (x < mineManager.blockstick * y)
    	            			res.get(c).add(str);
    	            		else
    	            		{
    	            			res.get(c).add(str);
    	            			res.add(new ArrayList<String>());
    	            			y++;
    	            			c++;
    	            		}
    	            	}
    	            	final ArrayList<Integer> count = new ArrayList<Integer>();
    	            	count.add(0);
    	            	final int m = c;
    	            	BukkitTask r0 = new BukkitRunnable()
    	    			{
    	    	            @Override
    	    	            public void run()
    	    	            {
    	    	            	if (count.get(0) >= m)
    	    	            	{
    	    	            		mineManager.startMineAd();
    	    	            		cancel();
    	    	            	}
    	    	            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
    	    	        		{
    	    	        			Player p = Bukkit.getPlayer("_GreyBrick_");
    	    	        			if (new player(p).root(type))
    	    	        				p.sendMessage("Установка слоя " + count.get(0) + "/" + m + " " + (int)(((double)count.get(0) / m) * 100) + " / 100");
    	    	        		}
    	    	            	for (String str : res.get(count.get(0)))
    	    	            	{
    	    	            		String[] s = str.split(" ");
    	    	            		new Location(world, Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])).getBlock().setBlockData(Bukkit.createBlockData(s[3]));
    	    	            	}
    	    	            	count.set(0, count.get(0) + 1);
    	    	            }
    	    	        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("Hi"), 60, mineManager.timerreload);
    	    	        BukkitTask r1 = new BukkitRunnable()
    	    			{
    	    	            @Override
    	    	            public void run()
    	    	            {
    	    	            	for (String str : list)
    	    	            	{
    	    	            		String[] s = str.split(" ");
    	    	            		if (s[3].equals("minecraft:water") || s[3].split("_").length == 3)
    	    	            			new Location(world, Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])).getBlock().setBlockData(Bukkit.createBlockData(s[3]));
    	    	            	}
    	    	            	int	i1 = -1;
    	    	            	while (++i1 <= size)
    	    	            	{
    	    	            		final int i = i1;
    	    	            		BukkitTask r1 = new BukkitRunnable()
    	        	    			{
    	        	    	            @Override
    	        	    	            public void run()
    	        	    	            {
    	        	    	            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
    	        	    	        		{
    	        	    	        			Player p = Bukkit.getPlayer("_GreyBrick_");
    	        	    	        			if (new player(p).root(type))
    	        	    	        				p.sendMessage("Заполнение деталями " + i + "/" + size);
    	        	    	        		}
    	        	    	            	int	j = -1;
    	    	    	            		while (++j <= size)
    	    	    	            		{
    	    	    	            			int	k = -1;
    	    	    	            			while (++k <= size)
    	    	    	            			{
    	    	    	            				Location loc = new Location(world, mn.id * interval + i, j, k);
    	    	    	            				if (!(world.getChunkAt(loc.getBlock()).isLoaded()))
    	    	    	            					world.getChunkAt(loc.getBlock()).load();
    	    	    	            				Location loc1 = loc.clone();
    	    	    	            				loc1.setY(loc1.getBlockY() + 1);
    	    	    	            				if (loc.getBlock().getType().equals(Material.DIRT) && loc1.getBlock().getType().equals(Material.AIR))
    	    	    	            				{
    	    	    	            					int rand = (int)(Math.random() * 100);
    	    	    	            					if (rand < 50)
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:grass"));
    	    	    	            					else if (rand < 60)
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData(Material.BROWN_MUSHROOM));
    	    	    	            					else if (rand < 70)
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData(Material.RED_MUSHROOM));
    	    	    	            				}
    	    	    	            				if ((loc.getBlock().getType().equals(Material.STONE) || loc.getBlock().getType().equals(Material.COBBLESTONE) || loc.getBlock().getType().equals(Material.MOSSY_COBBLESTONE)) && loc1.getBlock().getType().equals(Material.AIR))
    	    	    	            				{
    	    	    	            					int rand = (int)(Math.random() * 1000);
    	    	    	            					if (rand < 100)
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:cobblestone_slab[type=bottom]"));
    	    	    	            					else if (rand < 200)
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:cobblestone_stairs[facing=" + ((int)(Math.random() * 2) == 0 ? "north" : "south") + ",half=bottom]"));
    	    	    	            					else if (rand < 300)
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:stone_button[face=floor,powered=" + ((int)(Math.random() * 2) == 0 ? "true" : "false") + "]"));
    	    	    	            					else if (rand < 400)
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:stone_pressure_plate[powered=" + ((int)(Math.random() * 2) == 0 ? "true" : "false") + "]"));
    	    	    	            					else if (rand <= 405)
    	        	    	            				{        	    	            					
    	        	    	            					loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:barrel[facing="+ ((int)(Math.random() * 2) == 0 ? ((int)(Math.random() * 2) == 0 ? "west" : "north") : ((int)(Math.random() * 2) == 0 ? ((int)(Math.random() * 2) == 0 ? "east" : "up") : ((int)(Math.random() * 2) == 0 ? "down" : "south"))) +"]"));
    	        	    	            					Barrel bar = (Barrel) loc1.getBlock().getState();
    	        	    	            					int	x = -1;
    	        	    	            					while (++x < (int) (Math.random() * bar.getInventory().getSize()))
    	        	    	            					{
    	        	    	            						int	r = (int)(Math.random() * 10000);
    	        	    	            						if (r < 2000 && r > 1700)
    	        	    	            							bar.getInventory().setItem((int)(Math.random() * bar.getInventory().getSize()),Item.Item.create(Material.NAME_TAG, 1));
    	        	    	            						else if (r < 3000)
    	        	    	            							bar.getInventory().setItem((int)(Math.random() * bar.getInventory().getSize()),Item.Item.create(Material.GOLD_INGOT, 1));
    	        	    	            						else if (r < 4000)
    	        	    	            							bar.getInventory().setItem((int)(Math.random() * bar.getInventory().getSize()),Item.Item.create(Material.IRON_INGOT, 1));
    	        	    	            						else if (r < 4500)
    	        	    	            							bar.getInventory().setItem((int)(Math.random() * bar.getInventory().getSize()),Item.Item.create(Material.IRON_NUGGET, 1));
    	        	    	            						else if (r < 5300)
    	        	    	            							bar.getInventory().setItem((int)(Math.random() * bar.getInventory().getSize()),Item.Item.create(Material.DIAMOND, 1));
    	        	    	            						else if (r < 5600)
    	        	    	            							bar.getInventory().setItem((int)(Math.random() * bar.getInventory().getSize()),Item.Item.create(Material.EMERALD, 1));
    	        	    	            						else if (r < 5620)
    	        	    	            						{
    	        	    	            							ItemStack item = Item.Item.create(Material.ENCHANTED_BOOK, 1);
    	        	    	            							item.addUnsafeEnchantment(Enchantment.MENDING, 1);
    	        	    	            							bar.getInventory().setItem((int)(Math.random() * bar.getInventory().getSize()), item);
    	        	    	            						}
    	        	    	            					}
    	        	    	            				}
    	    	    	            					else if (rand < 505)
    	    	    	            					{
    	    	    	            						Location loc2 = loc.clone();
    	    	    	            						loc2.setX(loc2.getX() + 1);
    	    	    	            						if (loc2.getBlock().isEmpty())
    	    	    	            							continue ;
    	    	    	            						loc2.setX(loc2.getX() - 2);
    	    	    	            						if (loc2.getBlock().isEmpty())
    	    	    	            							continue ;
    	    	    	            						loc2.setX(loc2.getX() + 1);
    	    	    	            						loc2.setZ(loc2.getZ() + 1);
    	    	    	            						if (loc2.getBlock().isEmpty())
    	    	    	            							continue ;
    	    	    	            						loc2.setZ(loc2.getZ() - 2);
    	    	    	            						if (loc2.getBlock().isEmpty())
    	    	    	            							continue ;
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:" + ((int)(Math.random() * 2) == 0 ? "red" : "black") + "_stained_glass"));
    	    	    	            						loc.setY(loc.getY() - 1);
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:" + ((int)(Math.random() * 2) == 0 ? "redstone_lamp[lit=true]" : "glowstone")));
    	    	    	            					}
    	    	    	            				}
    	    	    	            				if ((loc1.getBlock().getType().equals(Material.STONE) || loc1.getBlock().getType().equals(Material.COBBLESTONE) || loc1.getBlock().getType().equals(Material.MOSSY_COBBLESTONE)) && loc.getBlock().getType().equals(Material.AIR))
    	    	    	            				{
    	    	    	            					int rand = (int)(Math.random() * 100);
    	    	    	            					if (rand < 10)
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:cobweb"));
    	    	    	            					else if (rand < 20)
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_leaves[persistent=true]"));
    	    	    	            					else if (rand < 25)
    	    	    	            					{
    	    	    	            						Location loc2 = loc.clone();
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:" + ((int)(Math.random() * 2) == 0 ? "mossy_" : "") + "cobblestone_wall"));
    	    	    	            						loc2.setY(loc2.getBlockY() - 1);
    	    	    	            						if (loc2.getBlock().getType().equals(Material.AIR))
    	    	    	            							loc2.getBlock().setBlockData(Bukkit.createBlockData("minecraft:iron_bars"));
    	    	    	            						loc2.setY(loc2.getBlockY() - 1);
    	    	    	            						if (loc2.getBlock().getType().equals(Material.AIR))
    	    	    	            							loc2.getBlock().setBlockData(Bukkit.createBlockData("minecraft:end_rod[facing=down]"));
    	    	    	            					}
    	    	    	            					else if (rand < 35)
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:cobblestone_slab[type=top]"));
    	    	    	            					else if (rand < 45)
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:cobblestone_stairs[facing=" + ((int)(Math.random() * 2) == 0 ? "north" : "south") + ",half=top]"));
    	    	    	            					else if (rand < 55)
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:stone_button[face=ceiling,powered=" + ((int)(Math.random() * 2) == 0 ? "true" : "false") + "]"));
    	    	    	            				}
    	    	    	            				if (loc.getBlock().getType().equals(Material.GRANITE) && loc1.getBlock().getType().equals(Material.AIR))
    	    	    	            				{
    	    	    	            					int rand = (int)(Math.random() * 100);
    	    	    	            					if (rand < 10)
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:granite_slab[type=bottom]"));
    	    	    	            					else if (rand < 20)
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:granite_stairs[facing=" + ((int)(Math.random() * 2) == 0 ? "north" : "south") + ",half=bottom]"));
    	    	    	            				}
    	    	    	            				if (loc1.getBlock().getType().equals(Material.GRANITE) && loc.getBlock().getType().equals(Material.AIR))
    	    	    	            				{
    	    	    	            					int rand = (int)(Math.random() * 100);
    	    	    	            					if (rand < 10)
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:granite_slab[type=top]"));
    	    	    	            					else if (rand < 20)
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:granite_stairs[facing=" + ((int)(Math.random() * 2) == 0 ? "north" : "south") + ",half=top]"));
    	    	    	            				}
    	    	    	            				if (loc.getBlock().getType().equals(Material.ANDESITE) && loc1.getBlock().getType().equals(Material.AIR))
    	    	    	            				{
    	    	    	            					int rand = (int)(Math.random() * 100);
    	    	    	            					if (rand < 10)
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:andesite_slab[type=bottom]"));
    	    	    	            					else if (rand < 20)
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:andesite_stairs[facing=" + ((int)(Math.random() * 2) == 0 ? "north" : "south") + ",half=bottom]"));
    	    	    	            				}
    	    	    	            				if (loc1.getBlock().getType().equals(Material.GRANITE) && loc.getBlock().getType().equals(Material.AIR))
    	    	    	            				{
    	    	    	            					int rand = (int)(Math.random() * 100);
    	    	    	            					if (rand < 10)
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:andesite_slab[type=top]"));
    	    	    	            					else if (rand < 20)
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:andesite_stairs[facing=" + ((int)(Math.random() * 2) == 0 ? "north" : "south") + ",half=top]"));
    	    	    	            				}
    	    	    	            				if (loc.getBlock().getType().equals(Material.DIORITE) && loc1.getBlock().getType().equals(Material.AIR))
    	    	    	            				{
    	    	    	            					int rand = (int)(Math.random() * 100);
    	    	    	            					if (rand < 10)
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:diorite_slab[type=bottom]"));
    	    	    	            					else if (rand < 20)
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:diorite_stairs[facing=" + ((int)(Math.random() * 2) == 0 ? "north" : "south") + ",half=bottom]"));
    	    	    	            				}
    	    	    	            				if (loc1.getBlock().getType().equals(Material.GRANITE) && loc.getBlock().getType().equals(Material.AIR))
    	    	    	            				{
    	    	    	            					int rand = (int)(Math.random() * 100);
    	    	    	            					if (rand < 10)
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:diorite_slab[type=top]"));
    	    	    	            					else if (rand < 20)
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:diorite_stairs[facing=" + ((int)(Math.random() * 2) == 0 ? "north" : "south") + ",half=top]"));
    	    	    	            				}
    	    	    	            				if (j > size - 1 && i == size - 1 && k == size - 1)
    	    	    	            				{
    	    	    	            					for (Entity en : loc.getChunk().getEntities())
    	    	    	            						en.remove();
    	    	    	            					if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
    	    	            	    	        		{
    	    	            	    	        			Player p = Bukkit.getPlayer("_GreyBrick_");
    	    	            	    	        			if (new player(p).root(type))
    	    	            	    	        				p.sendMessage("Открытие пещеры");
    	    	            	    	        		}	
    	    	            	    	            	mn.open(true);
    	    	    	            				}
    	    	    	            			}
    	    	    	            		}
    	        	    	            }
    	    	            		}.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), i1 * 3 + 120);  
    	    	            	}
    	    	            }
    	    	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), y * mineManager.timerreload);
    	            	cancel();
    	            }
    	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), count + 5);
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        return time + (size + 10) * 5;
	}
	
	public static void	setPlase(mine mn, int x, int time)
	{
		BukkitTask rn = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("ставим ряд " + x);
        		}
            	int	i = 0;
        		while (++i < size)
        		{
        			int j = 0;
        			while (++j < size)
        			{
        				for (Entity e : world.getNearbyEntities(new Location(world, mn.id * interval + x, i, j), 2, 2, 2))
        				{
        					if (!(e instanceof Player))
        						e.remove();
        				}
        				mn.setBlock(mn.id * interval + x, i, j, "minecraft:" + ((int)(Math.random() * 4) == 0 ? ((int)(Math.random() * 4) == 0 ? "cobblestone" : "mossy_cobblestone") : "stone"));
        			}
        		}
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
	}
	
	public static int setBedr(mine mn, int time, int type)
	{
		BukkitTask rn = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	int j;
        		int i = -1;
        		if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Установка бедрока");
        		}
        		while (++i < size)
        		{
        			j = -1;
        			while (++j < size)
        			{
        				mn.setBlock(mn.id * interval + i, 0, j, "minecraft:bedrock");
        				mn.setBlock(mn.id * interval + i, j, 0, "minecraft:bedrock");
        				mn.setBlock(mn.id * interval + i, size, j, "minecraft:bedrock");
        				mn.setBlock(mn.id * interval + i, j, size, "minecraft:bedrock");
        				mn.setBlock(mn.id * interval, j, i, "minecraft:bedrock");
        				mn.setBlock(mn.id * interval + size, j, i, "minecraft:bedrock");
        			}
        		}
        		i = -1;
        		while (++i < size + 10)
        		{
        			final int i1 = i;
        			BukkitTask rn = new BukkitRunnable()
        			{
        	            @Override
        	            public void run()
        	            {
        	            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        	        		{
        	        			Player p = Bukkit.getPlayer("_GreyBrick_");
        	        			if (new player(p).root(type))
        	        				p.sendMessage("Чистка "+ i1);
        	        		}
        	            	int j = -1;
        	    			while (++j < size + 10)
        	    			{
        	    				int k = -1;
        	    				while (++k < size + 10)
        	    				{
        	    					if (i1 < 5 || i1 > size + 5)
        	    						mn.setBlock(mn.id * interval + i1 - 5, j - 5, k - 5, "minecraft:air");
        	    					if (j < 5 || j > size + 5)
        	    						mn.setBlock(mn.id * interval + i1 - 5, j - 5, k - 5, "minecraft:air");
        	    					if (k < 5 || k > size + 5)
        	    						mn.setBlock(mn.id * interval + i1 - 5, j - 5, k - 5, "minecraft:air");
        	    				}
        	    			}
        	            	cancel();
        	            }
        	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), i * 1);
        		}
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        return time + (size + 10) * 1;
	}
	
	public static void	setWorm(int id, int r, int type, mine mn)
	{
		int	x1 = (int) (Math.random() * (size - r * 2) + r);
		int	y1 = (int) (Math.random() * (size - r * 2) + r);
		int	z1 = (int) (Math.random() * (size - r * 2) + r);
		
		int	x2 = (int) (Math.random() * (size - r * 2) + r);
		int	y2 = (int) (Math.random() * (size - r * 2) + r);
		int	z2 = (int) (Math.random() * (size - r * 2) + r);
		
		if (type == 0)
		{
			x1 = r + 2;
			x2 = size - r - 2;
		}
		if (type == 1)
		{
			y1 = r + 2;
			y2 = size - r - 2;
		}
		if (type == 2)
		{
			z1 = r + 2;
			z2 = size - r - 2;
		}
		
		Location loc1 = new Location(world, id * interval + x1, y1, z1);
		Location loc2 = new Location(world, id * interval + x2, y2, z2);
	
		int	Xdiff;
		int	Ydiff;
		int	Zdiff;
		int	Odiff;
		
		int	c = 1;
		
		while (c++ < 50)
		{
			setСircle(loc1, (int) (Math.random() * (r * 0.1) + r), 2, mn, "minecraft:air");			
			
			Xdiff = loc1.getBlockX() - loc2.getBlockX();
			Ydiff = loc1.getBlockY() - loc2.getBlockY();
			Zdiff = loc1.getBlockZ() - loc2.getBlockZ();
			
			Odiff = (int) loc1.distance(loc2);
			
			if (Xdiff > 0)
			{
				loc1.setX(loc1.getBlockX() - getRand(Odiff, r));
			}
			else
				loc1.setX(loc1.getBlockX() + getRand(Odiff, r));
			
			if (Ydiff > 0)
			{
				loc1.setY(loc1.getBlockY() - getRand(Odiff, r));
			}
			else
				loc1.setY(loc1.getBlockY() + getRand(Odiff, r));
			
			if (Zdiff > 0)
			{
				loc1.setZ(loc1.getBlockZ() - getRand(Odiff, r));
			}
			else
				loc1.setZ(loc1.getBlockZ() + getRand(Odiff, r));
			if (loc1.distance(loc2) < r * 0.5)
				break ;
		}
	}
	
	public static int	setType(int id, int m, mine mn, int r)
	{
		int	time = m;
		int	otst = 1;
	
		BukkitTask rl = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Установка ресурса WATER");
        		}
            	int x = -1;
				while (++x < size * size * size / 2100)
				{
					int	x1 = (int) (Math.random() * (size - r * 2) + r);
					int	y1 = (int) (Math.random() * (size - r * 2) + r);
					int	z1 = (int) (Math.random() * (size - r * 2) + r);
					
					mn.setBlock(id * interval + x1 + r, y1 + r, z1 + r, "minecraft:water");
				}
				cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + otst);
		
        time += otst;
        
        BukkitTask r1 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	int x = -1;
        		while (++x < size * size * size / 15000)
        		{
        			int	x1 = (int) (Math.random() * (size - r * 3) + r);
					int	y1 = (int) (Math.random() * (size - r * 3) + r);
					int	z1 = (int) (Math.random() * (size - r * 3) + r);
        			
        			setСircle(new Location(way24.worldmine, id * interval + x1 + r, y1 + r, z1 + r), 3, 2, mn, "minecraft:gravel");
        		}
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + otst);
		
        time += otst;
		
        BukkitTask r2 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	int x = -1;
        		while (++x < size * size * size / 5100)
        		{
        			int	x1 = (int) (Math.random() * (size - r * 2) + r);
					int	y1 = (int) (Math.random() * (size - r * 2) + r);
					int	z1 = (int) (Math.random() * (size - r * 2) + r);
        			
        			setСircle(new Location(way24.worldmine, id * interval + x1 + r, y1 + r, z1 + r), 3, 2, mn, "minecraft:sand");
        		}
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + otst);
		
        time += otst;
		
        BukkitTask r3 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	int x = -1;
        		while (++x < size * size * size / 5100)
        		{
        			int	x1 = (int) (Math.random() * (size - r * 2) + r);
					int	y1 = (int) (Math.random() * (size - r * 2) + r);
					int	z1 = (int) (Math.random() * (size - r * 2) + r);
        			
        			setСircle(new Location(way24.worldmine, id * interval + x1 + r, y1 + r, z1 + r), 3, 2, mn, "minecraft:andesite");
        		}
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + otst);
		
        time += otst;
		
        BukkitTask r4 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	int x = -1;
        		while (++x < size * size * size / 5100)
        		{
        			int	x1 = (int) (Math.random() * (size - r * 2) + r);
					int	y1 = (int) (Math.random() * (size - r * 2) + r);
					int	z1 = (int) (Math.random() * (size - r * 2) + r);
        			
        			setСircle(new Location(way24.worldmine, id * interval + x1 + r, y1 + r, z1 + r), 3, 2, mn, "minecraft:diorite");
        		}
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + otst);
		
        time += otst;
		
        BukkitTask r5 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	int x = -1;
        		while (++x < size * size * size / 5100)
        		{
        			int	x1 = (int) (Math.random() * (size - r * 2) + r);
					int	y1 = (int) (Math.random() * (size - r * 2) + r);
					int	z1 = (int) (Math.random() * (size - r * 2) + r);
        			
        			setСircle(new Location(way24.worldmine, id * interval + x1 + r, y1 + r, z1 + r), 3, 2, mn, "minecraft:granite");
        		}
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + otst);
		
        time += otst;
        
        BukkitTask r50 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	int x = -1;
        		while (++x < size * size * size / 5100)
        		{
        			int	x1 = (int) (Math.random() * (size - r * 2) + r);
					int	y1 = (int) (Math.random() * (size - r * 2) + r);
					int	z1 = (int) (Math.random() * (size - r * 2) + r);
        			
        			setСircle(new Location(way24.worldmine, id * interval + x1 + r, y1 + r, z1 + r), 3, 2, mn, "minecraft:dirt");
        		}
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + otst);
		
        time += otst;
        
        BukkitTask r6 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	Material mat = Material.COAL_ORE;
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Установка ресурса " + mat);
        		}
            	int x = -1;
        		while (++x < ((size * size * size / 100) * 1.2466) / 21)
        		{
        			int	x1 = (int) (Math.random() * (size - r * 2) + r);
					int	y1 = (int) (Math.random() * (size - r * 2) + r);
					int	z1 = (int) (Math.random() * (size - r * 2) + r);
        			
        			setOre(new Location(world, id * interval + x1, y1, z1), mat, (int) (Math.random() * 5 + 5), mn);
        		}
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + otst);
		
        time += otst;
        
        BukkitTask r7 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	Material mat = Material.IRON_ORE;
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Установка ресурса " + mat);
        		}
            	int x = -1;
				while (++x < coef * ((size * size * size / 100) * 0.7659) / 13.5)
				{
					int	x1 = (int) (Math.random() * (size - r * 2) + r);
					int	y1 = (int) (Math.random() * (size - r * 2) + r);
					int	z1 = (int) (Math.random() * (size - r * 2) + r);
					
					setOre(new Location(way24.worldmine, id * interval + x1, y1, z1), mat, (int) (Math.random() * 4 + 3), mn);
				}
				cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + otst);
        
        time += otst;
        
        BukkitTask r8 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	Material mat = Material.GOLD_ORE;
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Установка ресурса " + mat);
        		}
            	int x = -1;
				while (++x < coef * ((size * size * size / 100) * 0.0801) / 12)
				{
					int	x1 = (int) (Math.random() * (size - r * 2) + r);
					int	y1 = (int) (Math.random() * (size - r * 2) + r);
					int	z1 = (int) (Math.random() * (size - r * 2) + r);
					
					setOre(new Location(way24.worldmine, id * interval + x1, y1, z1), mat, (int) (Math.random() * 3 + 3), mn);
				}
				cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + otst);
        
        time += otst;
		
        BukkitTask r9 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	Material mat = Material.REDSTONE_ORE;
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Установка ресурса " + mat);
        		}
            	int x = -1;
				while (++x < coef * ((size * size * size / 100) * 0.2411) / 24)
				{
					int	x1 = (int) (Math.random() * (size - r * 2) + r);
					int	y1 = (int) (Math.random() * (size - r * 2) + r);
					int	z1 = (int) (Math.random() * (size - r * 2) + r);
					
					setOre(new Location(way24.worldmine, id * interval + x1, y1, z1), mat, (int) (Math.random() * 7 + 5), mn);
				}
				cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + otst);
        
        time += otst;
		
        BukkitTask r10 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	Material mat = Material.LAPIS_ORE;
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Установка ресурса " + mat);
        		}
		        int x = -1;
				while (++x < coef * ((size * size * size / 100) * 0.033) / 13.5)
				{
					int	x1 = (int) (Math.random() * (size - r * 2) + r);
					int	y1 = (int) (Math.random() * (size - r * 2) + r);
					int	z1 = (int) (Math.random() * (size - r * 2) + r);
					
					setOre(new Location(way24.worldmine, id * interval + x1, y1, z1), mat, (int) (Math.random() * 4 + 3), mn);
				}
				cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + otst);
        
        time += otst;
		
        BukkitTask r11 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	Material mat = Material.EMERALD_ORE;
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Установка ресурса " + mat);
        		}
		        int x = -1;
				while (++x < coef * ((size * size * size / 100) * 0.0306) / 4.5)
				{
					int	x1 = (int) (Math.random() * (size - r * 2) + r);
					int	y1 = (int) (Math.random() * (size - r * 2) + r);
					int	z1 = (int) (Math.random() * (size - r * 2) + r);
					
					setOre(new Location(way24.worldmine, id * interval + x1, y1, z1), mat, (int) (Math.random() * 2 + 1), mn);
				}
				cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + otst);
        
        time += otst;
		
        BukkitTask r12 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	Material mat = Material.DIAMOND_ORE;
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Установка ресурса " + mat);
        		}
		        int x = -1;
				while (++x < coef * ((size * size * size / 100) * 0.0306) / 4.5)
				{
					int	x1 = (int) (Math.random() * (size - r * 2) + r);
					int	y1 = (int) (Math.random() * (size - r * 2) + r);
					int	z1 = (int) (Math.random() * (size - r * 2) + r);
					
					setOre(new Location(way24.worldmine, id * interval + x1, y1, z1), mat, (int) (Math.random() * 2 + 1), mn);
				}
				cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + otst);
        
        return time + otst;
	}
	
	public static void	setOre(Location loc, Material m, int size, mine mn)
	{
		int			x;
		String		mat = Bukkit.createBlockData(m).getAsString();
		
		int			x1 = loc.getBlockX();
		int			y1 = loc.getBlockY();
		int			z1 = loc.getBlockZ();
		
		x = -1;
		while (++x < size)
		{
			mn.setBlock(x1, y1, z1, mat);
			mn.setBlock(x1 + 1, y1, z1, mat);
			mn.setBlock(x1, y1 + 1, z1, mat);
			mn.setBlock(x1, y1, z1 + 1, mat);
			x1 += Math.random() * 2 - 1;
			y1 += Math.random() * 2 - 1;
			z1 += Math.random() * 2 - 1;
		}
	}
	
	public static Location getSpawn(int id)
	{
		int	x = 200;
		int	x1;
		int	y1;
		int	z1;
		while (x-- > 0)
		{
			x1 = (int) (Math.random() * 70);
			y1 = 70 - 5;
			z1 = (int) (Math.random() * 70);
			while (y1 > 0)
			{	
				
				if (new Location(way24.worldmine, id * interval + x1, y1, z1).getBlock().isEmpty()
						&& new Location(way24.worldmine, id * interval + x1, y1 + 1, z1).getBlock().isEmpty()
						&& !(new Location(way24.worldmine, id * interval + x1, y1 - 1, z1).getBlock().isLiquid())
						&& !(new Location(way24.worldmine, id * interval + x1, y1 - 1, z1).getBlock().isEmpty()))
				return 	new Location(way24.worldmine, id * interval + x1 + 0.5, y1, z1 + 0.5);
				y1--;
			}
		}
		return null;
	}
	
	public static int	getRand(int diff, int r)
	{
		int	ret = (int) (Math.random() * r - r / 4);
		return ret;
	}
	
	public static void	setСircle(Location loc, int rad, int random, mine mn, String mat)
	{
		int	i = -1;
		int	j;
		int	k;
		while (++i < rad * 2)
		{
			j = -1;
			while (++j < rad * 2)
			{
				k = -1;
				while (++k < rad * 2)
				{
					int	x = loc.getBlockX() - rad + i;
					int	y = loc.getBlockY() - rad + j;
					int	z = loc.getBlockZ() - rad + k;
					Location del = new Location(world, loc.getBlockX() - rad + i, loc.getBlockY() - rad + j, loc.getBlockZ() - rad + k);
					if (del.distance(loc) <= (int) (Math.random() * random) + rad)
						mn.setBlock(x, y, z, mat);
				}
			}
		}
	}
}
