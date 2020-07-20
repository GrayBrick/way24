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

public class Plants
{
	public static String	name		= Color.c(10) + "Огородник";
	public static Location	loc			= new Location(way24.way24.world, -6.5, 8, 95.5, -90, 0);
	
	public static String	pay			= Color.c(17) + " " + Color.c(10) + "Купить растение";
	public static String	payDoc		= Color.c(17) + " " + Color.c(11) + "Купить разрешение";
	
	public static String	seed		= Color.c(17) + " " + Color.c(10) + "Семена пшеницы";
	public static String	seedDoc		= Color.c(17) + " " + Color.c(10) + "Разрешение на поадку семян пшеницы";
	
	public static String	seedP		= Color.c(17) + " " + Color.c(6) + "Картошка";
	public static String	seedPDoc	= Color.c(17) + " " + Color.c(6) + "Разрешение на поадку картошки";
	
	public static String	seedC		= Color.c(17) + " " + Color.c(6) + "Морковь";
	public static String	seedCDoc	= Color.c(17) + " " + Color.c(6) + "Разрешение на поадку моркови";
	
	public static String	seedPum		= Color.c(17) + " " + Color.c(6) + "Семена тыквы";
	public static String	seedPumDoc	= Color.c(17) + " " + Color.c(6) + "Разрешение на поадку семян тыквы";
	
	public static String	seedMel		= Color.c(17) + " " + Color.c(2) + "Семена арбуза";
	public static String	seedMelDoc	= Color.c(17) + " " + Color.c(2) + "Разрешение на поадку семян арбуза";
	
	public static String	seedBeet	= Color.c(17) + " " + Color.c(12) + "Семена свеклы";
	public static String	seedBeetDoc	= Color.c(17) + " " + Color.c(12) + "Разрешение на поадку семян свеклы";
	
	public static String	seedCoc		= Color.c(17) + " " + Color.c(6) + "Какао-бобы";
	public static String	seedCocDoc	= Color.c(17) + " " + Color.c(6) + "Разрешение на поадку какао-бобов";
	
	public static String	seedBam		= Color.c(17) + " " + Color.c(10) + "бамбук";
	public static String	seedBamDoc	= Color.c(17) + " " + Color.c(10) + "Разрешение на поадку бамбука";
	
	public static String	seedSug		= Color.c(17) + " " + Color.c(10) + "тростник";
	public static String	seedSugDoc	= Color.c(17) + " " + Color.c(10) + "Разрешение на поадку тростника";
	
	public static String	seedBer		= Color.c(17) + " " + Color.c(4) + "ягодный куст";
	public static String	seedBerDoc	= Color.c(17) + " " + Color.c(4) + "Разрешение на поадку ягодного куста";
	
	public static void clickPlants(PlayerInteractEntityEvent e, player p)
	{
		Entity plants = e.getRightClicked();
		if (!plants.isCustomNameVisible())
			return ;
		if (plants.getCustomName().equals(name))
		{
			if (!admin.Do(p, 5000))
				createInv(p, name);
			e.setCancelled(true);
		}
	}
	
	public static void createInv(player p, String name)
	{
		Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, name);
		
		List<String> lore = new ArrayList<String>();
		
		lore.add(Color.c(15) + "Вы можете купить растение");
		lore.add(Color.c(15) + "И посадить его у вас на острове");
		
		inv.setItem(1, Item.Item.create(Material.WHEAT_SEEDS, pay, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "Вы можете купить разрешение на посадку растений");
		lore.add(Color.c(15) + "Без определенного разрешения вы не сможете посадить определенное растение");
		
		inv.setItem(3, Item.Item.create(Material.LILY_PAD, payDoc, 1, lore));
		
		p.pl.openInventory(inv);
	}
	
	public static void createInv(player p, int size, String name)
	{
		Inventory inv = Bukkit.createInventory(null, size, name);
		
		List<String> lore = new ArrayList<String>();
		
		lore.add(Color.c(15) + "Купить семена пшеницы за " + Color.c(3) + 10 + Color.c(15) + " скитов");
		
		inv.setItem(0, Item.Item.create(Material.WHEAT_SEEDS, seed, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "Купить картошку за " + Color.c(3) + 100 + Color.c(15) + " скитов");
		
		inv.setItem(1, Item.Item.create(Material.POTATO, seedP, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "Купить морковь за " + Color.c(3) + 100 + Color.c(15) + " скитов");
		
		inv.setItem(2, Item.Item.create(Material.CARROT, seedC, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "Купить семена тыквы за " + Color.c(3) + 1000 + Color.c(15) + " скитов");
		
		inv.setItem(3, Item.Item.create(Material.PUMPKIN_SEEDS, seedPum, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "Купить семена арбуза за " + Color.c(3) + 1000 + Color.c(15) + " скитов");
		
		inv.setItem(4, Item.Item.create(Material.MELON_SEEDS, seedMel, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "Купить какао-боб за " + Color.c(3) + 2000 + Color.c(15) + " скитов");
		
		inv.setItem(6, Item.Item.create(Material.COCOA_BEANS, seedCoc, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "Купить семена свеклы за " + Color.c(3) + 500 + Color.c(15) + " скитов");
		
		inv.setItem(5, Item.Item.create(Material.BEETROOT_SEEDS, seedBeet, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "Купить бамбук за " + Color.c(3) + 5000 + Color.c(15) + " скитов");
		
		inv.setItem(7, Item.Item.create(Material.BAMBOO, seedBam, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "Купить тростник за " + Color.c(3) + 5000 + Color.c(15) + " скитов");
		
		inv.setItem(8, Item.Item.create(Material.SUGAR_CANE, seedSug, 1, lore));
		
		lore.clear();
		
		lore.add(Color.c(15) + "Купить ягодный куст за " + Color.c(3) + 1000 + Color.c(15) + " скитов");
		
		inv.setItem(13, Item.Item.create(Material.SWEET_BERRIES, seedBer, 1, lore));
		
		p.pl.openInventory(inv);
	}
	
	public static void createInvDoc(player p, int size, String name)
	{
		Inventory inv = Bukkit.createInventory(null, size, name);
		
		List<String> lore = new ArrayList<String>();
		
		if (!p.seedDoc(0))
			lore.add(Color.c(15) + "Купить разрешение на посадку семян пшеницы за " + Color.c(3) + 1000 + Color.c(15) + " скитов");
		else
			lore.add(Color.c(15) + "У вас есть это разрешение");
		
		inv.setItem(0, Item.Item.create(Material.WHEAT_SEEDS, seedDoc, 1, lore));
		
		lore.clear();

		if (!p.seedDoc(1))
			lore.add(Color.c(15) + "Купить разрешение на посадку картошки за " + Color.c(3) + 5000 + Color.c(15) + " скитов");
		else
			lore.add(Color.c(15) + "У вас есть это разрешение");
		
		inv.setItem(1, Item.Item.create(Material.POTATO, seedPDoc, 1, lore));
		
		lore.clear();

		if (!p.seedDoc(2))
			lore.add(Color.c(15) + "Купить разрешение на посадку моркови за " + Color.c(3) + 6000 + Color.c(15) + " скитов");
		else
			lore.add(Color.c(15) + "У вас есть это разрешение");
		
		inv.setItem(2, Item.Item.create(Material.CARROT, seedCDoc, 1, lore));
		
		lore.clear();

		if (!p.seedDoc(3))
			lore.add(Color.c(15) + "Купить разрешение на посадку семян тыквы за " + Color.c(3) + 10000 + Color.c(15) + " скитов");
		else
			lore.add(Color.c(15) + "У вас есть это разрешение");
		
		inv.setItem(3, Item.Item.create(Material.PUMPKIN_SEEDS, seedPumDoc, 1, lore));
		
		lore.clear();
		
		if (!p.seedDoc(4))
			lore.add(Color.c(15) + "Купить разрешение на посадку семян арбуза за " + Color.c(3) + 10000 + Color.c(15) + " скитов");
		else
			lore.add(Color.c(15) + "У вас есть это разрешение");
		
		inv.setItem(4, Item.Item.create(Material.MELON_SEEDS, seedMelDoc, 1, lore));
		
		lore.clear();

		if (!p.seedDoc(5))
			lore.add(Color.c(15) + "Купить разрешение на посадку семян свеклы за " + Color.c(3) + 5000 + Color.c(15) + " скитов");
		else
			lore.add(Color.c(15) + "У вас есть это разрешение");
		
		inv.setItem(5, Item.Item.create(Material.BEETROOT_SEEDS, seedBeetDoc, 1, lore));
		
		lore.clear();
		
		if (!p.seedDoc(6))
			lore.add(Color.c(15) + "Купить разрешение на посадку какао-бобов за " + Color.c(3) + 10000 + Color.c(15) + " скитов");
		else
			lore.add(Color.c(15) + "У вас есть это разрешение");
		
		inv.setItem(6, Item.Item.create(Material.COCOA_BEANS, seedCocDoc, 1, lore));
		
		lore.clear();
		
		if (!p.seedDoc(7))
			lore.add(Color.c(15) + "Купить разрешение на посадку бамбука за " + Color.c(3) + 10000 + Color.c(15) + " скитов");
		else
			lore.add(Color.c(15) + "У вас есть это разрешение");
		
		inv.setItem(7, Item.Item.create(Material.BAMBOO, seedBamDoc, 1, lore));
		
		lore.clear();
		
		if (!p.seedDoc(8))
			lore.add(Color.c(15) + "Купить разрешение на посадку тростника за " + Color.c(3) + 10000 + Color.c(15) + " скитов");
		else
			lore.add(Color.c(15) + "У вас есть это разрешение");
		
		inv.setItem(8, Item.Item.create(Material.SUGAR_CANE, seedSugDoc, 1, lore));
		
		lore.clear();
		
		if (!p.seedDoc(9))
			lore.add(Color.c(15) + "Купить разрешение на посадку ягодного куста за " + Color.c(3) + 5000 + Color.c(15) + " скитов");
		else
			lore.add(Color.c(15) + "У вас есть это разрешение");
		
		inv.setItem(13, Item.Item.create(Material.SWEET_BERRIES, seedBerDoc, 1, lore));	
		
		p.pl.openInventory(inv);
	}
	
	public static void UniShop(player p, InventoryAction a, Material m, int price, String name)
	{
		if (a.equals(InventoryAction.PICKUP_ALL))
		{
			if (p.getMoney() < price)
			{
				p.pl.sendMessage("У вас не достаточно денег");
				return ;
			}
			animGive(Item.Item.create(m, 1), p.pl);
			p.pl.getInventory().addItem(Item.Item.create(m, 1));
			p.pl.sendMessage("Вы купили " + Color.c(3) + name);
			p.setMoney(p.getMoney() - price);
		}
	}
	
	public static void UniShopDoc(player p, InventoryAction a, int price, String name, int id)
	{
		if (a.equals(InventoryAction.PICKUP_ALL))
		{
			if (p.seedDoc(id))
			{
				p.pl.sendMessage("У вас уже есть это разрешение");
				return ;
			}
			if (p.getMoney() < price)
			{
				p.pl.sendMessage("У вас не достаточно денег");
				return ;
			}
			animGive(Item.Item.create(Material.PAPER, 1), p.pl);
			p.seedDoc(id, true);
			p.pl.sendMessage("Вы купили " + Color.c(3) + name);
			p.setMoney(p.getMoney() - price);
		}
	}
	
	public static void clickInv(InventoryClickEvent e, player p)
	{
		if (p.pl.getOpenInventory().getTitle().equals(name))
		{
			if (e.getClickedInventory().getType().equals(InventoryType.HOPPER))
				e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR))
				return ;
			if (admin.Do(p, 1000))
				return ;
			String name = e.getCurrentItem().getItemMeta().getDisplayName();
			if (name.equals(pay))
			{
				createInv(p, 18, pay);
				return ;
			}
			if (name.equals(payDoc))
			{
				createInvDoc(p, 18, payDoc);
				return ;
			}
			e.setCancelled(true);
		}
		
		if (p.pl.getOpenInventory().getTitle().equals(pay))
		{
			if (e.getClickedInventory().getSize() == 18)
				e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR))
				return ;
			String name = e.getCurrentItem().getItemMeta().getDisplayName();
			if (name.equals(seed))
			{
				UniShop(p, e.getAction(), Material.WHEAT_SEEDS, 10, "семена пшеницы");
				return ;
			}
			if (name.equals(seedP))
			{
				UniShop(p, e.getAction(), Material.POTATO, 100, "картошку");
				return ;
			}
			if (name.equals(seedC))
			{
				UniShop(p, e.getAction(), Material.CARROT, 100, "морковь");
				return ;
			}
			if (name.equals(seedPum))
			{
				UniShop(p, e.getAction(), Material.PUMPKIN_SEEDS, 1000, "семена тыквы");
				return ;
			}
			if (name.equals(seedMel))
			{
				UniShop(p, e.getAction(), Material.MELON_SEEDS, 1000, "семена арбуза");
				return ;
			}
			if (name.equals(seedBeet))
			{
				UniShop(p, e.getAction(), Material.BEETROOT_SEEDS, 500, "семена свеклы");
				return ;
			}
			if (name.equals(seedCoc))
			{
				UniShop(p, e.getAction(), Material.COCOA_BEANS, 2000, "какао-боб");
				return ;
			}
			if (name.equals(seedBam))
			{
				UniShop(p, e.getAction(), Material.BAMBOO, 5000, "бамбук");
				return ;
			}
			if (name.equals(seedSug))
			{
				UniShop(p, e.getAction(), Material.SUGAR_CANE, 5000, "тростник");
				return ;
			}
			if (name.equals(seedBer))
			{
				UniShop(p, e.getAction(), Material.SWEET_BERRIES, 1000, "ягодный куст");
				return ;
			}
			e.setCancelled(true);
		}
		
		if (p.pl.getOpenInventory().getTitle().equals(payDoc))
		{
			if (e.getClickedInventory().getSize() == 18)
				e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR))
				return ;
			String name = e.getCurrentItem().getItemMeta().getDisplayName();
			if (name.equals(seedDoc))
			{
				UniShopDoc(p, e.getAction(), 1000, "разрешение на посадку семян пшеницы", 0);
				return ;
			}			
			if (name.equals(seedPDoc))
			{
				UniShopDoc(p, e.getAction(), 5000, "разрешение на посадку картошки", 1);
				return ;
			}
			if (name.equals(seedCDoc))
			{
				UniShopDoc(p, e.getAction(), 6000, "разрешение на посадку моркови", 2);
				return ;
			}
			if (name.equals(seedPumDoc))
			{
				UniShopDoc(p, e.getAction(), 10000, "разрешение на посадку семян тыквы", 3);
				return ;
			}
			if (name.equals(seedMelDoc))
			{
				UniShopDoc(p, e.getAction(), 10000, "разрешение на посадку семян арбуза", 4);
				return ;
			}
			if (name.equals(seedBeetDoc))
			{
				UniShopDoc(p, e.getAction(), 5000, "разрешение на посадку семян свеклы", 5);
				return ;
			}
			if (name.equals(seedCocDoc))
			{
				UniShopDoc(p, e.getAction(), 10000, "разрешение на посадку какао-бобов", 6);
				return ;
			}
			if (name.equals(seedBamDoc))
			{
				UniShopDoc(p, e.getAction(), 10000, "разрешение на посадку бамбука", 7);
				return ;
			}
			if (name.equals(seedSugDoc))
			{
				UniShopDoc(p, e.getAction(), 10000, "разрешение на посадку тростника", 8);
				return ;
			}
			if (name.equals(seedBerDoc))
			{
				UniShopDoc(p, e.getAction(), 5000, "разрешение на посадку ягодного куста", 9);
				return ;
			}
			e.setCancelled(true);
		}
	}
	public static void animGive(ItemStack item, Player p)
	{
		Villager miner = SpawnVillagers.getPlants();
		
		if (!(miner.getEyeLocation().getYaw() == -90 && miner.getEyeLocation().getPitch() == 0))
			return ;
		
		Location loc = miner.getLocation();
		
		int	x;
		int	time;
		
		time = 0;
		x = -1;
		while (++x < 120)
		{
			final int x1 = x;
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	miner.setRotation(x1 - 90, 0);
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), x / 9);
		}
		
		time += x / 9;
		
		Location loc1 = loc.clone();
		
		loc1.setYaw(30);
		
		double k0 = (miner.getLocation().getX() + 7.5) / 30;
		double k1 = (miner.getLocation().getY() - 8) / 30;
		double k2 = ((miner.getLocation().getZ() - 96.5)) / 30;
		
		x = -1;
		
		while (++x < 30)
		{				
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	loc1.setX(loc1.getX() - k0);
					loc1.setY(loc1.getY() - k1);
					loc1.setZ(loc1.getZ() - k2);
	            	miner.teleport(loc1);
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 4);	            					
		}
		
		time += x / 4;
		
		x = -1;
		while (++x < 60)
		{
			final int x1 = x;
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (x1 == 59)
	            	{
	            		new Location(way24.way24.world, -10, 8, 96).getBlock().setBlockData(Bukkit.createBlockData("minecraft:barrel[facing=east,open=true]"));
	            		way24.way24.world.playSound(new Location(way24.way24.world, -10, 8, 96), Sound.BLOCK_BARREL_OPEN, 1, 1);
	            	}
	            	miner.setRotation(x1 + 30, (float) (x1 * 0.591));
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 7);
		}
		
		time += x / 7 + 10;
		
		x = -1;
		while (++x < 44)
		{
			final int x1 = x;
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (x1 == 0)
	            	{
	            		new Location(way24.way24.world, -10, 8, 96).getBlock().setBlockData(Bukkit.createBlockData("minecraft:barrel[facing=east,open=false]"));
	            		way24.way24.world.playSound(new Location(way24.way24.world, -10, 8, 96), Sound.BLOCK_BARREL_CLOSE, 1, 1);
	            	}
	            	if (x1 == 43)
	            	{	
	            		int	rand = (int)(Math.random() * 8);
	            		Material m = null; 
	            		if (rand < 1)
	            			m = Material.RED_MUSHROOM_BLOCK;
	            		else if (rand < 2)
	            			m = Material.BROWN_MUSHROOM_BLOCK;
	            		else if (rand < 3)
	            			m = Material.MUSHROOM_STEM;
	            		else if (rand < 4)
	            			m = Material.CACTUS;
	            		else if (rand < 5)
	            			m = Material.OAK_LEAVES;
	            		else if (rand < 6)
	            			m = Material.PUMPKIN;
	            		else if (rand < 7)
	            			m = Material.HAY_BLOCK;
	            		else if (rand < 8)
	            			m = Material.MELON;
	            		ArmorStand ar = way24.way24.setMiniBlockPix(new Location(loc.getWorld(), -9, 9, 95), 6, 0, -2, m);
	            		BukkitTask r1 = new BukkitRunnable()
	        			{
	        	            @Override
	        	            public void run()
	        	            {
	        	            	ar.remove();
	        	            	cancel();
	        	            }
	        	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), 40);
	            	}
	            	miner.setRotation(x1 + 90, 35 + (float) (- x1 * 0.227));
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 7);
		}
		
		time += x / 7 + 5;
		
		x = -1;
		while (++x < 44)
		{
			final int x1 = x;
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (x1 == 43)
	            	{
	            		new Location(way24.way24.world, -10, 9, 96).getBlock().setBlockData(Bukkit.createBlockData("minecraft:barrel[facing=east,open=true]"));
	            		way24.way24.world.playSound(new Location(way24.way24.world, -10, 9, 96), Sound.BLOCK_BARREL_OPEN, 1, 1);
	            	}
	            	miner.setRotation(134 - x1, 25 - (float) (x1 * 0.568));
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 7);
		}
		
		time += x / 7 + 10;
		
		x = -1;
		while (++x < 60)
		{
			final int x1 = x;
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (x1 == 0)
	            	{
	            		new Location(way24.way24.world, -10, 9, 96).getBlock().setBlockData(Bukkit.createBlockData("minecraft:barrel[facing=east,open=false]"));
	            		way24.way24.world.playSound(new Location(way24.way24.world, -10, 9, 96), Sound.BLOCK_BARREL_CLOSE, 1, 1);
	            	}
	            	if (x1 == 59)
	            	{	
	            		ArmorStand ar = way24.way24.setMiniBlockPix(new Location(loc.getWorld(), -9, 9, 95), 9, 0, -9, item.getType());
	            		BukkitTask r1 = new BukkitRunnable()
	        			{
	        	            @Override
	        	            public void run()
	        	            {
	        	            	ar.remove();
	        	            	cancel();
	        	            }
	        	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), 40);
	            	}
	            	miner.setRotation(x1 + 90, (float) (x1 * 0.366));
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 7);
		}
		
		time += x / 7;
		
		x = -1;
		while (++x < 16)
		{
			final int x1 = x;
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	miner.setRotation(150 - x1, 22 + (float) (x1 * 0.187));
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 7);
		}
		
		time += x / 7 + 10;
		
		x = -1;
		while (++x < 44)
		{
			final int x1 = x;
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (x1 == 43)
	            	{
	            		new Location(way24.way24.world, -10, 8, 96).getBlock().setBlockData(Bukkit.createBlockData("minecraft:barrel[facing=east,open=true]"));
	            		way24.way24.world.playSound(new Location(way24.way24.world, -10, 8, 96), Sound.BLOCK_BARREL_OPEN, 1, 1);
	            	}
	            	miner.setRotation(134 - x1, 25 + (float) (x1 * 0.25));
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 7);
		}
		
		time += x / 7 + 7;
		
		x = -1;
		while (++x < 60)
		{
			final int x1 = x;
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (x1 == 0)
	            	{
	            		new Location(way24.way24.world, -10, 8, 96).getBlock().setBlockData(Bukkit.createBlockData("minecraft:barrel[facing=east,open=false]"));
	            		way24.way24.world.playSound(new Location(way24.way24.world, -10, 8, 96), Sound.BLOCK_BARREL_CLOSE, 1, 1);
	            	}
	            	miner.setRotation(x1 + 90, 36 - (float) (x1 * 0.233));
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 7);
		}
		
		time += x / 7 + 7;
		
		x = -1;
		while (++x < 75)
		{
			final int x1 = x;
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (x1 == 0)
	            	{
	            		new Location(way24.way24.world, -10, 8, 96).getBlock().setBlockData(Bukkit.createBlockData("minecraft:barrel[facing=east,open=false]"));
	            		way24.way24.world.playSound(new Location(way24.way24.world, -10, 8, 96), Sound.BLOCK_BARREL_CLOSE, 1, 1);
	            	}
	            	miner.setRotation(x1 + (x1 + 150 <= 180 ? 150 : -210), 22 - (float) (x1 * 0.293));
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 7);
		}
		
		time += x / 7;
		
		double k01 = (-7.5 + 6.5) / 30;
		double k11 = (8 - 8) / 30;
		double k21 = ((96.5 - 95.5)) / 30;
		
		
		Location loc2 = new Location(way24.way24.world, -7.5, 8, 96.5);
		
		loc2.setYaw(-135);
		loc2.setPitch(0);
		
		x = -1;
		while (++x < 30)
		{				
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	loc2.setX(loc2.getX() - k01);
					loc2.setY(loc2.getY() - k11);
					loc2.setZ(loc2.getZ() - k21);
	            	miner.teleport(loc2);
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 3);	            					
		}
		
		time += x / 3;
		
		x = -1;
		while (++x <= 45)
		{
			final int x1 = x;
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	miner.setRotation(x1 - 135, 0);
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 7);
		}
		
		time += x / 4;
		
		BukkitTask r1 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	ArmorStand ar = way24.way24.setMiniBlockPix(new Location(loc.getWorld(), -6, 9, 95), 2, 0, -3, item.getType());
        		
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
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time + x / 7);
	}
}
