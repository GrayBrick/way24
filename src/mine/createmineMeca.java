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

public class createmineMeca
{
	public static int		size = 80;
	public static Double	coef;
	public static World		world = way24.worldmeca;
	public static int		interval	= 256;
	public static int		type		= 8;
	
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
			Bukkit.getPlayer("_GreyBrick_").sendMessage("meca" + "\n" + mn.id);
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
		while (++x < (int) (Math.random() * (size * 1.5 / 10)) + 10)
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
	            	setWorm(mn.id, (int) (Math.random() * 2 + 3), (int) (Math.random() * 3), mn);
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x * 2);			
		}
		time += x * 2;
		
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
    	    	            		mineManager.startMineWater();
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
    	    	    	            				if (loc.getBlock().getType().equals(Material.BARRIER))
    	    	    	            				{
    	    	    	            					loc.getBlock().setType(Material.AIR);
    	    	    	            					loc.setY(loc.getY() - 1);
    	    	    	            					while (loc.getBlock().getType().equals(Material.AIR) && loc.getY() > 0)
    	    	    	            						loc.setY(loc.getY() - 1);
    	    	    	            					if (!loc.getBlock().getType().equals(Material.RAIL))
    	    	    	            					{
    	    	    	            						loc.setY(loc.getY() + 1);
        	    	    	            					loc.getBlock().setType(Material.RAIL);
    	    	    	            					}				
    	    	    	            				}
    	    	    	            				if ((loc.getBlock().getType().equals(Material.SANDSTONE) || loc.getBlock().getType().equals(Material.CHISELED_SANDSTONE) || loc.getBlock().getType().equals(Material.CUT_SANDSTONE)) && loc1.getBlock().getType().equals(Material.AIR))
    	    	    	            				{
    	    	    	            					int rand = (int)(Math.random() * 100);
    	    	    	            					if (rand < 10)
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:sandstone_stairs[half=bottom]"));
    	    	    	            					else if (rand < 20)
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:sandstone_slab[type=bottom]"));
    	    	    	            				}
    	    	    	            				if ((loc.getBlock().getType().equals(Material.RED_SANDSTONE) || loc.getBlock().getType().equals(Material.CHISELED_RED_SANDSTONE) || loc.getBlock().getType().equals(Material.CUT_RED_SANDSTONE)) && loc1.getBlock().getType().equals(Material.AIR))
    	    	    	            				{
    	    	    	            					int rand = (int)(Math.random() * 100);
    	    	    	            					if (rand < 10)
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:red_sandstone_stairs[half=bottom]"));
    	    	    	            					else if (rand < 20)
    	    	    	            						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:red_sandstone_slab[type=bottom]"));
    	    	    	            				}
    	    	    	            				if (!loc.getBlock().getType().equals(Material.AIR) && loc1.getBlock().getType().equals(Material.AIR) && loc.getY() % 6 == 0)
    	    	    	            				{
    	    	    	            					loc.setY(loc.getY() - 2);
    	    	    	            					int rand = (int)(Math.random() * 100);
    	    	    	            					if (rand < 10 && loc.getY() > 5 && loc.getY() < size - 5 && loc.getZ() > 5 && loc.getZ() < size - 5 && loc.getZ() % 5 == 0)
    	    	    	            					{
    	    	    	            						int pl = (int)(Math.random() * (size / 2)) + 2;
    	    	    	            						int	x = -1;
    	    	    	            						while (++x < (int)(Math.random() * (size / 2 - 10) + 10))
    	    	    	            						{			
    	    	    	            							Location set = new Location(world, interval * mn.id + pl + x, loc.getY(), loc.getZ());
	    	    	            								if (set.getX() % 6 == 0)
	    	    	            								{
	    	    	            									Location setd = set.clone();
	    	    	            									setd.setY(setd.getY() - 1);
	    	    	            									while (setd.getBlock().getType().equals(Material.AIR))
	    	    	            									{
	    	    	            										setd.getBlock().setBlockData(Bukkit.createBlockData("minecraft:sandstone_wall"));
	    	    	            										setd.setY(setd.getY() - 1);
	    	    	            									}
	    	    	            									set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=double]"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=bottom]"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=top]"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=bottom]"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=double]"));
    	    	    	            								
    	    	    	            								setd = set.clone();
	    	    	            									setd.setY(setd.getY() - 1);
	    	    	            									while (setd.getBlock().getType().equals(Material.AIR))
	    	    	            									{
	    	    	            										setd.getBlock().setBlockData(Bukkit.createBlockData("minecraft:sandstone_wall"));
	    	    	            										setd.setY(setd.getY() - 1);
	    	    	            									}
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:sandstone_wall"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:rail"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:sandstone_wall"));
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_fence"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_fence"));
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:sandstone_wall"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:lantern[hanging=true]"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:sandstone_wall"));
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_log[axis=z]"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_log[axis=z]"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_log[axis=z]"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_log[axis=z]"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_log[axis=z]"));
	    	    	            								}
	    	    	            								else
	    	    	            								{
	    	    	            									set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=top]"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=bottom]"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=top]"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=bottom]"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=top]"));
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:rail"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setZ(set.getZ() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
	    	    	            								}	
    	    	    	            						}
    	    	    	            					}
    	    	    	            					else if (rand < 20 && loc.getY() > 5 && loc.getY() < size - 5 && loc.getX() > interval * mn.id + 5 && loc.getX() < interval * mn.id + size - 5  && loc.getX() % 5 == 0)
    	    	    	            					{
    	    	    	            						int pl = (int)(Math.random() * (size / 2)) + 2;
    	    	    	            						int	z = -1;
    	    	    	            						while (++z < (int)(Math.random() * (size / 2 - 10) + 10))
    	    	    	            						{			
    	    	    	            							Location set = new Location(world, loc.getX(), loc.getY(), z + pl);
	    	    	            								if (set.getZ() % 6 == 0)
	    	    	            								{
	    	    	            									Location setd = set.clone();
	    	    	            									setd.setY(setd.getY() - 1);
	    	    	            									while (setd.getBlock().getType().equals(Material.AIR))
	    	    	            									{
	    	    	            										setd.getBlock().setBlockData(Bukkit.createBlockData("minecraft:sandstone_wall"));
	    	    	            										setd.setY(setd.getY() - 1);
	    	    	            									}
	    	    	            									set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=double]"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=bottom]"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=top]"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=bottom]"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=double]"));
    	    	    	            								
    	    	    	            								setd = set.clone();
	    	    	            									setd.setY(setd.getY() - 1);
	    	    	            									while (setd.getBlock().getType().equals(Material.AIR))
	    	    	            									{
	    	    	            										setd.getBlock().setBlockData(Bukkit.createBlockData("minecraft:sandstone_wall"));
	    	    	            										setd.setY(setd.getY() - 1);
	    	    	            									}
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:sandstone_wall"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:rail"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:sandstone_wall"));
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_fence"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_fence"));
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:sandstone_wall"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:lantern[hanging=true]"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:sandstone_wall"));
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_log[axis=x]"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_log[axis=x]"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_log[axis=x]"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_log[axis=x]"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_log[axis=x]"));
	    	    	            								}
	    	    	            								else
	    	    	            								{
	    	    	            									set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=top]"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=bottom]"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=top]"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=bottom]"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:oak_slab[type=top]"));
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:rail"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:rail"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() - 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								
    	    	    	            								set.setY(set.getY() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
    	    	    	            								set.setX(set.getX() + 1);
    	    	    	            								set.getBlock().setBlockData(Bukkit.createBlockData("minecraft:air"));
	    	    	            								}	
    	    	    	            						}
    	    	    	            					}
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
    	    	            		}.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), i1 * 1 + 120);  
    	    	            	}
    	    	            }
    	    	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), y * mineManager.timerreload);
    	            	cancel();
    	            }
    	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), count + 5);
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        return time + (size + 10) * 3;
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
        				mn.setBlock(mn.id * interval + x, i, j, "minecraft:" + ((int)(Math.random() * 4) == 0 ? ((int)(Math.random() * 4) == 0 ? "cut_" : "chiseled_") : "" ) + ((int)(Math.random() * (size / i)) == 0 ? "red_" : "") + "sandstone");
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
			mn.setBlock(loc1.getBlockX() , loc1.getBlockY(), loc1.getBlockZ(), "minecraft:barrier");
			
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
        
        BukkitTask r9 = new BukkitRunnable()
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
				while (++x < coef * ((size * size * size / 100) * 1.2466) / 21)
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
            	if (Bukkit.getOfflinePlayer("_GreyBrick_").isOnline())
        		{
        			Player p = Bukkit.getPlayer("_GreyBrick_");
        			if (new player(p).root(type))
        				p.sendMessage("Установка ресурса глина");
        		}
            	int x = -1;
				while (++x < coef * ((size * size * size / 100) * 21.2466) / 21)
				{
					int	x1 = (int) (Math.random() * (size - r * 2) + r);
					int	y1 = (int) (Math.random() * (size - r * 2) + r);
					int	z1 = (int) (Math.random() * (size - r * 2) + r);
					
					setTerr(new Location(world, id * interval + x1, y1, z1), (int) (Math.random() * 8 + 5), mn);
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
					{						
						if (way24.Mine.getString(mn.id + ".block" + "." + x + "." + y + "." + z) == null || !way24.Mine.getString(mn.id + ".block" + "." + x + "." + y + "." + z).equals("minecraft:barrier"))
							mn.setBlock(x, y, z, mat);
					}
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
	
	public static void	setTerr(Location loc, int size, mine mn)
	{
		int			x;
		
		int			x1 = loc.getBlockX();
		int			y1 = loc.getBlockY();
		int			z1 = loc.getBlockZ();
		
		x = -1;
		while (++x < size)
		{
			mn.setBlock(x1, y1, z1, getTerracotta(y1));
			mn.setBlock(x1 + 1, y1, z1, getTerracotta(y1));
			mn.setBlock(x1, y1 + 1, z1, getTerracotta(y1 + 1));
			mn.setBlock(x1, y1, z1 + 1, getTerracotta(y1));
			x1 += Math.random() * 2 - 1;
			y1 += Math.random() * 2 - 1;
			z1 += Math.random() * 2 - 1;
		}
	}
	
	public static String getTerracotta(int i)
	{
		if (i <= 5)
			return ("minecraft:terracotta");
		else if (i <= 9)
			return ("minecraft:black_terracotta");
		else if (i <= 13)
			return ("minecraft:red_terracotta");
		else if (i <= 17)
			return ("minecraft:green_terracotta");
		else if (i <= 21)
			return ("minecraft:brown_terracotta");
		else if (i <= 25)
			return ("minecraft:blue_terracotta");
		else if (i <= 29)
			return ("minecraft:purple_terracotta");
		else if (i <= 33)
			return ("minecraft:cyan_terracotta");
		else if (i <= 37)
			return ("minecraft:light_gray_terracotta");
		else if (i <= 41)
			return ("minecraft:gray_terracotta");
		else if (i <= 45)
			return ("minecraft:pink_terracotta");
		else if (i <= 49)
			return ("minecraft:lime_terracotta");
		else if (i <= 53)
			return ("minecraft:yellow_terracotta");
		else if (i <= 57)
			return ("minecraft:light_blue_terracotta");
		else if (i <= 61)
			return ("minecraft:magenta_terracotta");
		else if (i <= 65)
			return ("minecraft:orange_terracotta");
		else if (i <= 70)
			return ("minecraft:white_terracotta");
		return null;
	}
}
