package Traders;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Chat.Color;
import Chat.sound;
import Player.player;
import way24.admin;

public class Miner
{
	public static String	name	= Color.col1 + "Шахтер";
	public static Location	loc		= new Location(way24.way24.world, 6.5, 1, 109.5, -90, 0);
	static String PVE 				= Color.col1 + "PVE пещера";
	static String PVEad 			= Color.col1 + "адская PVE пещера";
	static String PVEwater 			= Color.col1 + "подводная PVE пещера";
	static String PVEend			= Color.col1 + "PVE пещера края";
	static String PVEmeca			= Color.col1 + "PVE пещера месы";
	
	public static void clickMiner(PlayerInteractEntityEvent e, player p)
	{
		Entity miner = e.getRightClicked();
		if (!miner.isCustomNameVisible())
			return ;
		if (miner.getCustomName().equals(name))
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
		
		lore.add(Color.jast + "Нажмите для покупки " + Color.col0 + 1 + Color.jast + " прохода в пещеру");
		lore.add(Color.jast + "Цена " + Color.col0 + 500 + Color.jast + " скитов");
		lore.add(Color.jast + "У вас " + Color.col0 + p.getDocMine(2) + Color.jast + " прохода в пещеру");
		
		inv.setItem(0, Item.Item.create(Material.NETHERRACK, PVEad, 1, lore));
		
		lore.clear();
		
		lore.add(Color.jast + "Нажмите для покупки " + Color.col0 + 1 + Color.jast + " прохода в пещеру");
		lore.add(Color.jast + "Цена " + Color.col0 + 700 + Color.jast + " скитов");
		lore.add(Color.jast + "У вас " + Color.col0 + p.getDocMine(4) + Color.jast + " прохода в пещеру");
		
		inv.setItem(1, Item.Item.create(Material.PRISMARINE, PVEwater, 1, lore));
		
		lore.clear();
		
		lore.add(Color.jast + "Нажмите для покупки " + Color.col0 + 1 + Color.jast + " прохода в пещеру");
		lore.add(Color.jast + "Цена " + Color.col0 + 1000 + Color.jast + " скитов");
		lore.add(Color.jast + "У вас " + Color.col0 + p.getDocMine(6) + Color.jast + " прохода в пещеру");
		
		inv.setItem(2, Item.Item.create(Material.END_STONE, PVEend, 1, lore));
		
		lore.clear();
		
		lore.add(Color.jast + "Нажмите для покупки " + Color.col0 + 1 + Color.jast + " прохода в пещеру");
		lore.add(Color.jast + "Цена " + Color.col0 + 1200 + Color.jast + " скитов");
		lore.add(Color.jast + "У вас " + Color.col0 + p.getDocMine(8) + Color.jast + " прохода в пещеру");
		
		inv.setItem(3, Item.Item.create(Material.TERRACOTTA, PVEmeca, 1, lore));
		
		p.pl.openInventory(inv);
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
			if (name.equals(PVEad))
			{
				if (p.getMoney() < 500)
				{
					p.pl.sendMessage("Вам не хватает " + Color.col0 + (500 - p.getMoney()) + Color.jast + " скитов");
					p.pl.closeInventory();
					sound.nosound(p.pl);
					return ;
				}
				animGive(Item.Item.create(Material.NETHERRACK, 1), p.pl);
				sound.yessound(p.pl);
				p.setDocMine(2, p.getDocMine(2) + 1);
				p.setMoney(p.getMoney() - 500);
				createInv(p, 9, Miner.name);
				return ;
			}
			if (name.equals(PVEwater))
			{
				if (p.getMoney() < 700)
				{
					p.pl.sendMessage("Вам не хватает " + Color.col0 + (700 - p.getMoney()) + Color.jast + " скитов");
					p.pl.closeInventory();
					sound.nosound(p.pl);
					return ;
				}
				animGive(Item.Item.create(Material.PRISMARINE, 1), p.pl);
				sound.yessound(p.pl);
				p.setDocMine(4, p.getDocMine(4) + 1);
				p.setMoney(p.getMoney() - 700);
				createInv(p, 9, Miner.name);
				return ;
			}
			if (name.equals(PVEend))
			{
				if (p.getMoney() < 1000)
				{
					p.pl.sendMessage("Вам не хватает " + Color.col0 + (1000 - p.getMoney()) + Color.jast + " скитов");
					p.pl.closeInventory();
					sound.nosound(p.pl);
					return ;
				}
				animGive(Item.Item.create(Material.END_STONE, 1), p.pl);
				sound.yessound(p.pl);
				p.setDocMine(6, p.getDocMine(6) + 1);
				p.setMoney(p.getMoney() - 1000);
				createInv(p, 9, Miner.name);
				return ;
			}
			if (name.equals(PVEmeca))
			{
				if (p.getMoney() < 1200)
				{
					p.pl.sendMessage("Вам не хватает " + Color.col0 + (1200 - p.getMoney()) + Color.jast + " скитов");
					p.pl.closeInventory();
					sound.nosound(p.pl);
					return ;
				}
				animGive(Item.Item.create(Material.TERRACOTTA, 1), p.pl);
				sound.yessound(p.pl);
				p.setDocMine(8, p.getDocMine(8) + 1);
				p.setMoney(p.getMoney() - 1200);
				createInv(p, 9, Miner.name);
				return ;
			}
			e.setCancelled(true);
		}
	}
	
	public static void animGive(ItemStack item, Player p)
	{
		Villager miner = SpawnVillagers.getMiner();
		
		Location loc = miner.getLocation();
		
		if (!(miner.getEyeLocation().getYaw() == -90 && miner.getEyeLocation().getPitch() == 0))
			return ;
		
		int	x;
		
		x = -1;
		while (++x < 110)
		{
			final int x1 = x;
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (x1 == 50)
	            	{
	            		new Location(way24.way24.world, 6, 1, 110).getBlock().setBlockData(Bukkit.createBlockData("minecraft:barrel[facing=up,open=true]"));
	            		way24.way24.world.playSound(new Location(way24.way24.world, 6, 1, 110), Sound.BLOCK_BARREL_OPEN, 1, 1);
	            	}
	            	miner.setRotation(x1 - 90, (int)(x1 * 0.48));
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), x / 9);
		}
		int	m;
		
		m = x;
		while (--x >= 0)
		{
			final int x1 = x;
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (x1 == 30)
	            	{
	            		new Location(way24.way24.world, 6, 1, 110).getBlock().setBlockData(Bukkit.createBlockData("minecraft:barrel[facing=up,open=false]"));
	            		way24.way24.world.playSound(new Location(way24.way24.world, 6, 1, 110), Sound.BLOCK_BARREL_CLOSE, 1, 1);
	            	}
	            	if (x1 == 0)
	            	{
	            		ArmorStand ar = way24.way24.setMiniBlockPix(new Location(loc.getWorld(), 7, 2, 109), 2, 0, -3, item.getType());
	            		
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
	            	}	            		
	            	miner.setRotation(x1 - 90, (int)(x1 * 0.48));
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), m / 9 + (m - x) / 9);
		}
	}
}
