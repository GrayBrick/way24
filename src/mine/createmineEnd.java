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
import org.bukkit.block.Block;
import org.bukkit.block.ShulkerBox;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Chat.Color;
import Player.player;
import way24.way24;

public class createmineEnd
{
	public static int		size 		= 80;
	public static Double	coef;
	public static World		world		= way24.worldend;
	public static int		interval	= 256;
	public static int		type		= 6;
	
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
			Bukkit.getPlayer("_GreyBrick_").sendMessage("end" + "\n" + mn.id);
		mn.setType(type);
		Location loc = new Location(world, mn.id * interval, 0, 0);
		int	i;
		int	j;
		
		x = 0;
		while (++x < size)
			setPlase(mn, x, x * 1);		
		
		time += x * 1;
		
		time = setType(mn.id, time, mn, 2);	                    
        
		x = 0;
		while (++x < (int) (Math.random() * (size * 1.5 / 10)) + 5)
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
	            	setWorm(mn.id, (int) (Math.random() * 2 + 7), (int) (Math.random() * 3), mn);
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x * 2);			
		}
		time += x * 2;
        
        BukkitTask r11 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	Material mat = Material.OBSIDIAN;
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Установка башни " + mat);
        		}
            	int x = -1;
				while (++x < coef * ((size * size * size / 100) * 0.1466) / 21)
				{
					int	x1 = (int) (Math.random() * (size - 5 * 2) + 5);
					int	y1 = (int) (Math.random() * (size - 5 * 2) + 5);
					int	z1 = (int) (Math.random() * (size - 5 * 2) + 5);
					
					setTower(new Location(world, mn.id * interval + x1, y1, z1), mat, mn);
				}
				cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + 5);
        
        time += 5;
		
        time = setBedr(mn, time, type);
        
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
        			count += 3;
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
    	    	            		mineManager.startMineMeca();
    	    	            		cancel();
    	    	            	}
    	    	            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
    	    	        		{
    	    	        			Player p = Bukkit.getPlayer("_GreyBrick_");
    	    	        			if (new player(p).root(type))
    	    	        				p.sendMessage("Установка слоя ");
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
    	    	            	int	i1 = -1;
    	    	            	while (++i1 < size)
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
    	    	    	            		while (++j < size)
    	    	    	            		{    	    	            			
    	    	    	            			int	k = -1;
    	    	    	            			while (++k < size)
    	    	    	            			{
    	    	    	            				Location loc = new Location(world, mn.id * interval + i, j, k);
    	    	    	            				if (!(world.getChunkAt(loc.getBlock()).isLoaded()))
    	    	    	            					world.getChunkAt(loc.getBlock()).load();
    	    	    	            				Location loc1 = loc.clone();
    	    	    	            				loc1.setY(loc1.getBlockY() + 1);
    	    	    	            				if (loc.getBlock().getType().equals(Material.END_STONE) && loc1.getBlock().getType().equals(Material.AIR))
    	    	    	            				{
    	    	    	            					int rand = (int)(Math.random() * 100);
    	        	    	            				if (rand <= 5)
    	        	    	            				{
    	        	    	            					int	x;
    	        	    	            					
    	        	    	            					int weight = (int)(Math.random() * 4);
    	        	    	            					x = -1;
    	        	    	            					loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:chorus_plant[down=true]"));
    	        	    	            					while (++x < weight)
    	        	    	            					{
    	        	    	            						loc1.setY(loc1.getY() + 1);
    	        	    	            						if (loc1.getBlock().getType().equals(Material.AIR))
    	        	    	            							loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:chorus_plant[down=true]"));
    	        	    	            						else
    	        	    	            							break ;
    	        	    	            					}
    	        	    	            					loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:chorus_flower"));
    	        	    	            				}
    	    	    	            				}
    	    	    	            				if (!loc.getBlock().isEmpty() && loc1.getBlock().getType().equals(Material.AIR))
    	    	    	            				{
    	    	    	            					int rand = (int)(Math.random() * 10000);
    	    	    	            					if (rand < 400)
    	        	    	            				{
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:gray_shulker_box[facing=up]"));
    	    	    	            						ShulkerBox bar = (ShulkerBox) loc1.getBlock().getState();
    	    	    	            						if (rand < 1)
    	    	    	            						{
    	    	    	            							if (mn.elit() == null)
    	    	    	            							{
    	    	    	            								if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
            	    	    	        	    	        		{
            	    	    	        	    	        			Player p = Bukkit.getPlayer("_GreyBrick_");
            	    	    	        	    	        			p.sendMessage(Color.c(6) + "+1 " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ());
            	    	    	        	    	        		}
    	    	    	            								Location loc12 = loc.clone();
    	    	    	            								loc12.setY(loc12.getY() + 1);
    	    	    	            								mn.elit(loc12);
    	    	    	            							}	
    	    	    	            							bar.getInventory().setItem(13, Item.Item.create(Material.ELYTRA, Color.col0 + "Вау"));
    	    	    	            						}
    	    	    	            						bar.getInventory().setItem(13, Item.Item.create(Material.ACACIA_BOAT, Color.col1 + "В следующий раз найдешь элитры"));
                	    	            				}
    	    	    	            					else if (rand <= 1000)
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
    	        	    	            					loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:end_stone_brick_slab[type=bottom]"));
    	        	    	            				}
    	        	    	            				else if (rand <= 2000)
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
    	        	    	            					loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:end_stone_brick_stairs[half=bottom]"));
    	        	    	            				}
    	        	    	            				else if (rand <= 2050)
    	        	    	            				{
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:end_stone_brick_wall"));
    	    	    	            						while (loc1.getY() < size)
    	    	    	            						{
    	    	    	            							loc1.setY(loc1.getY() + 1);
    	    	    	            							if (!loc1.getBlock().isEmpty())
    	    	    	            								break ;
    	    	    	            							loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:" + ((int)(Math.random() * 5) == 0 ? "end_stone_brick_wall" : "end_rod")));
    	    	    	            						}
    	    	    	            						loc1.setY(loc1.getY() - 1);
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:end_stone_brick_wall"));
    	        	    	            				}
    	    	    	            				}
    	    	    	            				loc1 = loc.clone();
    	    	    	            				loc1.setX(loc1.getX() + 1);
    	    	    	            				if (!loc.getBlock().isEmpty() && loc1.getBlock().getType().equals(Material.AIR))
    	    	    	            				{
    	    	    	            					int rand = (int)(Math.random() * 1000);
    	        	    	            				if (rand <= 5)
    	        	    	            				{
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:end_stone"));
    	    	    	            						while (loc1.getX() < size + interval * mn.id)
    	    	    	            						{
    	    	    	            							loc1.setX(loc1.getX() + 1);
    	    	    	            							if (!loc1.getBlock().isEmpty())
    	    	    	            								break ;
    	    	    	            							loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:" + ((int)(Math.random() * 5) == 0 ? "end_stone" : "end_rod[facing=west]")));
    	    	    	            						}
    	    	    	            						loc1.setY(loc1.getX() - 1);
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:end_stone"));
    	        	    	            				}
    	    	    	            				}
    	    	    	            				loc1 = loc.clone();
    	    	    	            				loc1.setZ(loc1.getZ() - 1);
    	    	    	            				if (!loc.getBlock().isEmpty() && loc1.getBlock().getType().equals(Material.AIR))
    	    	    	            				{
    	    	    	            					int rand = (int)(Math.random() * 1000);
    	        	    	            				if (rand <= 5)
    	        	    	            				{
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:end_stone"));
    	    	    	            						int	x = -1;
    	    	    	            						while (++x < size)
    	    	    	            						{
    	    	    	            							loc1.setZ(loc1.getZ() - 1);
    	    	    	            							if (!loc1.getBlock().isEmpty())
    	    	    	            								break ;
    	    	    	            							loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:" + ((int)(Math.random() * 5) == 0 ? "end_stone" : "end_rod[facing=north]")));
    	    	    	            						}
    	    	    	            						loc1.setZ(loc1.getZ() + 1);
    	    	    	            						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:end_stone"));
    	        	    	            				}
    	    	    	            				}
    	    	    	            				loc.getBlock().getState().update();
    	    	    	            				if (j >= size - 1 && i == size - 1 && k == size - 1)
    	    	    	            				{
    	    	    	            					for (Entity en : loc.getChunk().getEntities())
    	    	    	            						en.remove();
    	    	    	            					if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
    	    	            	    	        		{
    	    	            	    	        			Player p = Bukkit.getPlayer("_GreyBrick_");
    	    	            	    	        			if (new player(p).root(type))
    	    	            	    	        				p.sendMessage("Открытие пещеры Ad");
    	    	            	    	        		}
    	    	            	    	            	mn.open(true);
    	    	    	            				}
    	    	    	            			}
    	    	    	            		}
    	        	    	            }
    	        	    			}.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), i1 * 5 + 120);
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
        				mn.setBlock(mn.id * interval + x, i, j, "minecraft:end_stone");
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
        
        BukkitTask r7 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	Material mat = Material.PURPUR_BLOCK;
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Установка ресурса " + mat);
        		}
            	int x = -1;
				while (++x < coef * ((size * size * size / 100) * 2.2466) / 21)
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
        
        BukkitTask r8 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	Material mat = Material.OBSIDIAN;
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Установка ресурса " + mat);
        		}
            	int x = -1;
				while (++x < coef * ((size * size * size / 100) * 2.2466) / 21)
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
        
        BukkitTask r9 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	Material mat = Material.PURPUR_PILLAR;
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Установка ресурса " + mat);
        		}
            	int x = -1;
				while (++x < coef * ((size * size * size / 100) * 2.2466) / 21)
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
        
        BukkitTask r10 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	Material mat = Material.END_STONE_BRICKS;
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Установка ресурса " + mat);
        		}
            	int x = -1;
				while (++x < coef * ((size * size * size / 100) * 2.2466) / 21)
				{
					int	x1 = (int) (Math.random() * (size - r * 2) + r);
					int	y1 = (int) (Math.random() * (size - r * 2) + r);
					int	z1 = (int) (Math.random() * (size - r * 2) + r);
					
					setOre(new Location(world, id * interval + x1, y1, z1), mat, (int) (Math.random() * 5 + 5), mn);
				}
				cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + otst);
        
        return time + otst;
	}
	
	public static void 	setTower(Location loc, Material m, mine mn)
	{
		int	r = (int) (Math.random() * 5);
		String		mat = Bukkit.createBlockData(m).getAsString();
		int	y = -1;
		while (++y < size)
		{
			int	x = -1;
			while (++x < r * 2)
			{
				int	z = -1;
				while (++z < r * 2)
				{
					loc.setY(y);
					if (new Location(loc.getWorld(), loc.getX() - r + x, y, loc.getZ() - r + z).distance(loc) <= r)
						mn.setBlock(loc.getBlockX() - r + x, y, loc.getBlockZ() - r + z, mat);						
				}
			}
		}
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
				
				if (new Location(world, id * interval + x1, y1, z1).getBlock().isEmpty()
						&& new Location(world, id * interval + x1, y1 + 1, z1).getBlock().isEmpty()
						&& !(new Location(world, id * interval + x1, y1 - 1, z1).getBlock().isLiquid())
						&& !(new Location(world, id * interval + x1, y1 - 1, z1).getBlock().isEmpty()))
				return 	new Location(world, id * interval + x1 + 0.5, y1, z1 + 0.5);
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
}
