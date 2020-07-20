package Traders;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Chat.Color;
import Player.player;
import way24.admin;

public class Skits
{
	public static String	name	= Color.c(3) + "�����";
	public static Location	loc		= new Location(way24.way24.world, -1.5, 8, 118.5, 180, 0);
	public static String	diamS	= Color.c(17) + " " + Color.c(11) + "�����";
	public static String	emS		= Color.c(17) + " " + Color.c(10) + "�������";
	public static String	ironS	= Color.c(17) + " " + Color.c(7) + "������";
	public static String	goldS	= Color.c(17) + " " + Color.c(6) + "������";
	public static String	redsS	= Color.c(17) + " " + Color.c(4) + "��������";
	public static String	lapS	= Color.c(17) + " " + Color.c(9) + "�������";
	public static String	coalS	= Color.c(17) + " " + Color.c(8) + "�����";
	
	public static void clickSkits(PlayerInteractEntityEvent e, player p)
	{
		Entity skits = e.getRightClicked();
		if (!skits.isCustomNameVisible())
			return ;
		if (skits.getCustomName().equals(name))
		{
			if (!admin.Do(p, 5000))
				createInv(p, 9, name);
			e.setCancelled(true);
		}
	}
	
	public static void createInv(player p, int size, String name)
	{
		Inventory inv = Bukkit.createInventory(null, size, name);
		
		List<String> lore = new ArrayList<String>();
		
		lore.add(Color.c(15) + "��� : �������� 1 ����� �� 100 ������");
		lore.add(Color.c(15) + "��� : �������� 10 ������� �� 1000 ������");
		lore.add(Color.c(15) + "SHIFT + ��� : �������� ��� ������ �� �����");
		
		inv.setItem(4, Item.Item.create(Material.DIAMOND, diamS, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "��� : �������� 1 ������� �� 80 ������");
		lore.add(Color.c(15) + "��� : �������� 10 ��������� �� 800 ������");
		lore.add(Color.c(15) + "SHIFT + ��� : �������� ��� �������� �� �����");
		
		inv.setItem(3, Item.Item.create(Material.EMERALD, emS, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "��� : �������� 1 ������ �� 10 ������");
		lore.add(Color.c(15) + "��� : �������� 10 ������ �� 100 ������");
		lore.add(Color.c(15) + "SHIFT + ��� : �������� ��� ������ �� �����");
		
		inv.setItem(5, Item.Item.create(Material.IRON_INGOT, ironS, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "��� : �������� 1 ������ �� 38 ������");
		lore.add(Color.c(15) + "��� : �������� 10 ������ �� 380 ������");
		lore.add(Color.c(15) + "SHIFT + ��� : �������� ��� ������ �� �����");
		
		inv.setItem(2, Item.Item.create(Material.GOLD_INGOT, goldS, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "��� : �������� 1 �������� �� 3 ������");
		lore.add(Color.c(15) + "��� : �������� 10 ��������� �� 30 ������");
		lore.add(Color.c(15) + "SHIFT + ��� : �������� ���� �������� �� �����");
		
		inv.setItem(6, Item.Item.create(Material.REDSTONE, redsS, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "��� : �������� 1 ������� �� 5 ������");
		lore.add(Color.c(15) + "��� : �������� 10 �������� �� 50 ������");
		lore.add(Color.c(15) + "SHIFT + ��� : �������� ���� ������� �� �����");
		
		inv.setItem(1, Item.Item.create(Material.LAPIS_LAZULI, lapS, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "��� : �������� 1 ����� �� 4 ������");
		lore.add(Color.c(15) + "��� : �������� 10 ���� �� 40 ������");
		lore.add(Color.c(15) + "SHIFT + ��� : �������� ���� ����� �� �����");
		
		inv.setItem(7, Item.Item.create(Material.COAL, coalS, 1, lore));
		
		p.pl.openInventory(inv);
	}
	
	public static void UniShop(player p, InventoryAction a, Material m, int coef, String name, String singlname, String multiname)
	{
		if (a.equals(InventoryAction.PICKUP_ALL))
		{
			for (ItemStack item : p.pl.getInventory().getContents())
			{
				if (item == null)
					continue ;
				if (item.getType().equals(m))
				{
					animGive(item, p.pl);
					p.pl.sendMessage("�� �������� " + Color.c(11) + 1 + Color.c(3) + singlname + Color.c(15) + "�� " + Color.c(11) + coef + Color.c(3) + " ����(�/��)");
					item.setAmount(item.getAmount() - 1);
					p.setMoney(p.getMoney() + coef);
					return ;
				}						
			}
			p.pl.sendMessage("� ��� ���" + Color.c(3) + multiname);
			return ;
		}
		if (a.equals(InventoryAction.PICKUP_HALF))
		{
			int am = 0;
			for (ItemStack item : p.pl.getInventory().getContents())
			{
				if (item == null)
					continue ;
				if (item.getType().equals(m))
					am += item.getAmount();
			}
			if (am < 10)
			{
				p.pl.sendMessage("� ��� ��� " + Color.c(11) + 10 + Color.c(3) + multiname);
				return ;
			}
			am = 10;
			for (ItemStack item : p.pl.getInventory().getContents())
			{
				if (item == null)
					continue ;
				if (item.getType().equals(m))
				{
					if (item.getAmount() >= am)
					{
						item.setAmount(item.getAmount() - am);
						break ;
					}
					am -= item.getAmount();
					item.setAmount(0);
				}
			}
			animGive(Item.Item.create(m, 1), p.pl);
			p.pl.sendMessage("�� �������� " + Color.c(11) + 10 + Color.c(3) + multiname + Color.c(15) + "�� " + Color.c(11) + 10 * coef + Color.c(3) + " ����(�/��)");
			p.setMoney(p.getMoney() + 10 * coef);
			return ;
		}
		if (a.equals(InventoryAction.MOVE_TO_OTHER_INVENTORY))
		{
			int am = 0;
			int	finisham;
			for (ItemStack item : p.pl.getInventory().getContents())
			{
				if (item == null)
					continue ;
				if (item.getType().equals(m))
					am += item.getAmount();
			}
			if (am == 0)
			{
				p.pl.sendMessage("� ��� ���" + Color.c(3) + multiname);
				return ;
			}
			finisham = am;
			for (ItemStack item : p.pl.getInventory().getContents())
			{
				if (item == null)
					continue ;
				if (item.getType().equals(m))
				{
					if (item.getAmount() >= am)
					{
						item.setAmount(item.getAmount() - am);
						break ;
					}
					am -= item.getAmount();
					item.setAmount(0);
				}
			}
			animGive(Item.Item.create(m, 1), p.pl);
			p.pl.sendMessage("�� �������� " + Color.c(11) + finisham + Color.c(3) + name + Color.c(15) + "�� " + Color.c(11) + finisham * coef + Color.c(3) + " ����(�/��)");
			p.setMoney(p.getMoney() + finisham * coef);
			return ;
		}
	}
	
	public static void clickInv(InventoryClickEvent e, player p)
	{
		if (p.pl.getOpenInventory().getTitle().equals(name))
		{
			if (e.getClickedInventory().getSize() == 9)
				e.setCancelled(true);
			if (e.getCurrentItem() == null)
				return ;
			if (admin.Do(p, 1000))
				return ;
			String name = e.getCurrentItem().getItemMeta().getDisplayName();
			if (name.equals(diamS))
			{
				UniShop(p, e.getAction(), Material.DIAMOND, 100, " �����(�/��) ", " ����� ", " ������� ");
				return ;
			}
			if (name.equals(emS))
			{
				UniShop(p, e.getAction(), Material.EMERALD, 80, " �������(�/��) ", " ������� ", " ��������� ");
				return ;
			}
			if (name.equals(ironS))
			{
				UniShop(p, e.getAction(), Material.IRON_INGOT, 10, " ������(�) ", " ������ ", " ������ ");
				return ;
			}
			if (name.equals(goldS))
			{
				UniShop(p, e.getAction(), Material.GOLD_INGOT, 38, " ������(�) ", " ������ ", " ������ ");
				return ;
			}
			if (name.equals(redsS))
			{
				UniShop(p, e.getAction(), Material.REDSTONE, 3, " ��������(�) ", " �������� ", " ��������� ");
				return ;
			}
			if (name.equals(lapS))
			{
				UniShop(p, e.getAction(), Material.LAPIS_LAZULI, 5, " �������(�) ", " ������� ", " �������� ");
				return ;
			}
			if (name.equals(coalS))
			{
				UniShop(p, e.getAction(), Material.COAL, 4, " ����(�/�) ", " ���� ", " ���� ");
				return ;
			}
			e.setCancelled(true);
		}
	}
	
	public static void animGive(ItemStack item, Player p)
	{
		WanderingTrader miner = SpawnVillagers.getSkits();
		
		Location loc = miner.getLocation();
		
		if (!((miner.getEyeLocation().getYaw() >= 175 || miner.getEyeLocation().getYaw() <= 182) && (miner.getEyeLocation().getPitch() <= 4 || miner.getEyeLocation().getPitch() <= -4)))
			return ;
	
		Location locp = p.getLocation();
		locp.setY(locp.getY() + 1);
		
		ArmorStand ar = way24.way24.setMiniBlockPix(locp, 2, 0, -3, item.getType());
		
		Location loc1 = ar.getLocation().clone();
		
		double k0 = (miner.getLocation().getX() - ar.getLocation().getX()) / 30;
		double k1 = ((miner.getLocation().getY() + 0.3) - ar.getLocation().getY()) / 30;
		double k2 = ((miner.getLocation().getZ() - 1) - ar.getLocation().getZ()) / 30;
		
		int	i = -1;
		while (++i < 30)
		{				
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	loc1.setX(loc1.getX() + k0);
					loc1.setY(loc1.getY() + k1);
					loc1.setZ(loc1.getZ() + k2);
	            	ar.teleport(loc1);
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), i / 4);	            					
		}
		BukkitTask r1 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	ar.remove();
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), 10);
		
        int	time = 15;
        
		int	x;
		
		x = -1;
		while (++x < 152)
		{
			final int x1 = x;
			BukkitTask r0 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	miner.setRotation(180 - x1, (int)(x1 * 0.1697)); //28, 25.7
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 9);
		}
		
		time += x / 9;
		
		x = -1;
		while (++x < 32)
		{
			final int x1 = x;
			BukkitTask r0 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (x1 == 30)
	            	{
	            		new Location(way24.way24.world, -3, 8, 119).getBlock().setBlockData(Bukkit.createBlockData("minecraft:spruce_trapdoor[facing=north,half=bottom,open=false]"));
	            		way24.way24.world.playSound(new Location(way24.way24.world, -3, 8, 119), Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, 1, 1);
	            	}
	            	miner.setRotation(28 + x1, 28 + (int)(x1 * 0.8218)); //60, 52
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 15);
		}
		
		time += x / 15;
		
		x = -1;
		while (++x < 32)
		{
			final int x1 = x;
			BukkitTask r0 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	miner.setRotation(60 - x1, 52 - (int)(x1 * 0.8218)); //28, 25.7
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 15);
		}
		
		time += x / 15;
		
		BukkitTask r0 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	new Location(way24.way24.world, -3, 8, 120).getBlock().setBlockData(Bukkit.createBlockData("minecraft:barrel[facing=north,open=true]"));
            	way24.way24.world.playSound(new Location(way24.way24.world, -3, 8, 120), Sound.BLOCK_BARREL_OPEN, 1, 1);
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + 10);
        
        time += 30;
        
        BukkitTask r2 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	new Location(way24.way24.world, -3, 8, 120).getBlock().setBlockData(Bukkit.createBlockData("minecraft:barrel[facing=north,open=false]"));
            	way24.way24.world.playSound(new Location(way24.way24.world, -3, 8, 120), Sound.BLOCK_BARREL_CLOSE, 1, 1);
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + 10);
        
        time += 25;
		
		x = -1;
		while (++x < 32)
		{
			final int x1 = x;
			BukkitTask r3 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	miner.setRotation(28 + x1, 28 + (int)(x1 * 0.8218)); //60, 52
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 15);
		}
		
		time += x / 15;
		
		x = -1;
		while (++x < 32)
		{
			final int x1 = x;
			BukkitTask r3 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (x1 == 30)
	            	{
	            		new Location(way24.way24.world, -3, 8, 119).getBlock().setBlockData(Bukkit.createBlockData("minecraft:spruce_trapdoor[facing=north,half=bottom,open=true]"));
	            		way24.way24.world.playSound(new Location(way24.way24.world, -3, 8, 119), Sound.BLOCK_WOODEN_TRAPDOOR_CLOSE, 1, 1);
	            	}
	            	miner.setRotation(60 - x1, 52 - (int)(x1 * 0.8218)); //28, 25.7
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 15);
		}
		
		time += x / 15;
		
		x = -1;
		while (++x < 152)
		{
			final int x1 = x;
			BukkitTask r3 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	miner.setRotation(28 + x1, 26 - (int)(x1 * 0.1697)); //180, 0
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 9);
		}
		
		time += x / 9;
		
		BukkitTask r3 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	miner.setRotation(180, 0);
            	ArmorStand ar = way24.way24.setMiniBlockPix(new Location(loc.getWorld(), -2, 9, 117), 2, 0, -3, Material.MAP);
        		
        		Location loc1 = ar.getLocation().clone();
        		
        		double k0 = (p.getLocation().getX() - ar.getLocation().getX()) / 30;
        		double k1 = (p.getLocation().getY() - ar.getLocation().getY()) / 30;
        		double k2 = ((p.getLocation().getZ() - 0.5) - ar.getLocation().getZ()) / 30;
        		
        		int	m = -1;
        		
        		int	i = -1;
        		while (++i < 30)
        		{
        			m++;				
					BukkitTask r1 = new BukkitRunnable()
        			{
        	            @Override
        	            public void run()
        	            {
        	            	loc1.setX(loc1.getX() + k0);
        					loc1.setY(loc1.getY() + k1);
        					loc1.setZ(loc1.getZ() + k2);
        	            	ar.teleport(loc1);
        	            	cancel();
        	            }
        	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), m / 4);	            					
        		}
        		BukkitTask r1 = new BukkitRunnable()
    			{
    	            @Override
    	            public void run()
    	            {
    	            	ar.remove();
    	            	cancel();
    	            }
    	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), 10);
    	        cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
	}
}
