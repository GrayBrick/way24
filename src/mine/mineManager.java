package mine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Chat.Color;
import Chat.sound;
import Island.createIs;
import Player.ConditionPlayer;
import Player.player;
import way24.Learner;
import way24.admin;
import way24.way24;

public class mineManager
{
	public static int				timeLive	= 1000 * 60 * 120;
	public static int				timerreload	= 1;
	public static int				blockstick	= 7000;
	
	public static void delMine(mine m)
	{
		m.open(false);
		m.inworld(false);
		for (String s : m.getMembers())
		{
			player p = new player(s);
			p.inmine(false);
			p.setMine(-1);						
			m.removeMember(p.p);
			way24.tp(p.pl, way24.spawn);		
		}
	}
	
	public static void startMine()
	{
		mine mn = createmine.create(0.65);
		mn.inworld(true);
		mn.open(false);
		mn.timeStart(new Date().getTime());
		BukkitTask rl = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	delMine(mn);
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), timeLive / 50);
	}
	
	public static void startMineAd()
	{
		mine mn = createmineAd.create(0.5);
		mn.inworld(true);
		mn.open(false);
		mn.timeStart(new Date().getTime());
		BukkitTask rl = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	delMine(mn);
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), timeLive / 50);
	}
	
	public static void startMineWater()
	{
		mine mn = createmineWater.create(0.5);
		mn.inworld(true);
		mn.open(false);
		mn.timeStart(new Date().getTime());
		BukkitTask rl = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	delMine(mn);
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), timeLive / 50);
	}
	
	public static void startMineMeca()
	{
		mine mn = createmineMeca.create(1);
		mn.inworld(true);
		mn.open(false);
		mn.timeStart(new Date().getTime());
		BukkitTask rl = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	delMine(mn);
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), timeLive / 50);
	}
	
	public static void startMineEnd()
	{
		mine mn = createmineEnd.create(1);
		mn.inworld(true);
		mn.open(false);
		mn.elit(null);
		mn.timeStart(new Date().getTime());
		BukkitTask rl = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	delMine(mn);
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), timeLive / 50);
	}
	
	public static boolean cmdMine(Player p, int id, String[] args)
	{
		if (admin.Do(new player(p), 5000))
			return true;
		if (!new player(p).inmine())
		{
			if (new player(p).learnStade() == 2)
				Learner.msend(new player(p), Color.c(6) + "Теперь выбери обычную пещеру");
			createInv(new player(p), name);
		}
		return true;
	}

	static String name = Color.c(9) + "Пещеры";
	static String PVE = Color.col1 + "PVE пещера";
	static String PVEad = Color.col1 + "адская PVE пещера";
	static String PVEwater = Color.col1 + "подводная PVE пещера";
	static String PVEend= Color.col1 + "PVE пещера края";
	static String PVEmeca= Color.col1 + "PVE пещера месы";
	
	public static void createInv(player p, String name)
	{
		Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, name);
		
		List<String> lore = new ArrayList<String>();
		
		lore.add(Color.jast + "обычная PVE пещера");
		lore.add(Color.jast + "Открыто " + getOpenType(0).size());
		lore.add(Color.jast + "У вас " + Color.col0 + "∞" + Color.jast + " проходов");
		
		if (getOpenType(0).size() > 0)
			inv.setItem(1, Item.Item.create(Material.STONE, PVE, 1, lore));
		else
			inv.setItem(1, Item.Item.create(Material.STONE, Color.c(12) + "Off " + PVE, 1, lore));
		
		lore.clear();
		
		lore.add(Color.jast + "адская PVE пещера");
		lore.add(Color.jast + "Открыто " + getOpenType(2).size());
		lore.add(Color.jast + "У вас " + Color.col0 + p.getDocMine(2) + Color.jast + " проходов");
		if (p.getDocMine(2) < 1)
			lore.add(Color.jast + "Вы можете приобрести проходы на " + Color.col0 + "/shop шахтер");
		
		if (getOpenType(2).size() > 0)
			inv.setItem(3, Item.Item.create(Material.NETHERRACK, PVEad, 1, lore));
		else
			inv.setItem(3, Item.Item.create(Material.NETHERRACK, Color.c(12) + "Off " + PVEad, 1, lore));
		
		lore.clear();
		
		lore.add(Color.jast + "подводная PVE пещера");
		lore.add(Color.jast + "Открыто " + getOpenType(4).size());
		lore.add(Color.jast + "У вас " + Color.col0 + p.getDocMine(4) + Color.jast + " проходов");
		if (p.getDocMine(4) < 1)
			lore.add(Color.jast + "Вы можете приобрести проходы на " + Color.col0 + "/shop шахтер");
		
		if (getOpenType(4).size() > 0)
			inv.setItem(2, Item.Item.create(Material.PRISMARINE, PVEwater, 1, lore));
		else
			inv.setItem(2, Item.Item.create(Material.PRISMARINE, Color.c(12) + "Off " + PVEwater, 1, lore));
		
		lore.clear();
		
		lore.add(Color.jast + "PVE пещера края");
		lore.add(Color.jast + "Открыто " + getOpenType(6).size());
		lore.add(Color.jast + "У вас " + Color.col0 + p.getDocMine(6) + Color.jast + " проходов");
		if (p.getDocMine(6) < 1)
			lore.add(Color.jast + "Вы можете приобрести проходы на " + Color.col0 + "/shop шахтер");
		
		if (getOpenType(6).size() > 0)
			inv.setItem(4, Item.Item.create(Material.END_STONE, PVEend, 1, lore));
		else
			inv.setItem(4, Item.Item.create(Material.END_STONE, Color.c(12) + "Off " + PVEend, 1, lore));
		
		lore.clear();
		
		lore.add(Color.jast + "PVE пещера месы");
		lore.add(Color.jast + "Открыто " + getOpenType(8).size());
		lore.add(Color.jast + "У вас " + Color.col0 + p.getDocMine(8) + Color.jast + " проходов");
		if (p.getDocMine(8) < 1)
			lore.add(Color.jast + "Вы можете приобрести проходы на " + Color.col0 + "/shop шахтер");
		
		if (getOpenType(8).size() > 0)
			inv.setItem(0, Item.Item.create(Material.TERRACOTTA, PVEmeca, 1, lore));
		else
			inv.setItem(0, Item.Item.create(Material.TERRACOTTA, Color.c(12) + "Off " + PVEmeca, 1, lore));

		p.pl.openInventory(inv);
	}
	
	public static void clickInv(InventoryClickEvent e, player p)
	{
		if (p.pl.getOpenInventory().getTitle().equals(name))
		{
			if (e.getClickedInventory().getType().equals(InventoryType.HOPPER))
				e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR))
				return ;
			String name = e.getCurrentItem().getItemMeta().getDisplayName();
			if (name.equals(PVE))
			{
				if (p.learnStade() == 2)
					Learner.msend(p, Color.c(6) + "Выбирай самую левую, там самая свежая пещера");
				createMineType(p, 0, PVE);
			}
			if (name.equals(PVEad))
			{
				if (p.getDocMine(2) < 1)
				{
					p.pl.sendMessage("У вас нет проходов, купите их на " + Color.col0 + "/shop шахтер");
					sound.nosound(p.pl);
					p.pl.closeInventory();
					return ;
				}
				createMineType(p, 2, PVEad);
			}
			if (name.equals(PVEwater))
			{
				if (p.getDocMine(4) < 1)
				{
					p.pl.sendMessage("У вас нет проходов, купите их на " + Color.col0 + "/shop шахтер");
					sound.nosound(p.pl);
					p.pl.closeInventory();
					return ;
				}
				createMineType(p, 4, PVEwater);
			}
			if (name.equals(PVEend))
			{
				if (p.getDocMine(6) < 1)
				{
					p.pl.sendMessage("У вас нет проходов, купите их на " + Color.col0 + "/shop шахтер");
					sound.nosound(p.pl);
					p.pl.closeInventory();
					return ;
				}
				createMineType(p, 6, PVEend);
			}
			if (name.equals(PVEmeca))
			{
				if (p.getDocMine(8) < 1)
				{
					p.pl.sendMessage("У вас нет проходов, купите их на " + Color.col0 + "/shop шахтер");
					sound.nosound(p.pl);
					p.pl.closeInventory();
					return ;
				}
				createMineType(p, 8, PVEmeca);
			}
		}
		
		if (p.pl.getOpenInventory().getTitle().equals(PVE))
		{
			if (e.getClickedInventory().getType().equals(InventoryType.HOPPER))
				e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR))
				return ;
			String name = e.getCurrentItem().getItemMeta().getDisplayName();
			if (name.equals(PVE) && e.getCurrentItem().getItemMeta().getLore().get(0).split(" ").length == 2)
			{
				mine mn = new mine(Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[1]));
				if (!p.inmine())
	    		{
					if (p.learnStade() == 2)
					{
						Learner.msend(p, Color.c(6) + "Отлично! При входе в любую пещеру вам дается " + Color.c(14) + " 6 секунд неуязвимости");
						Learner.msend(p, Color.c(6) + "Теперь добудь 3 булыжника");
					}
	    			Location loc = createmine.getSpawn(mn.id);
	    			if (loc == null)
	    				return ;
	    			way24.tp(p.pl, loc);
	    			p.pl.sendMessage("Вы попали в шахту номер " + Color.col1 + mn.id);
	    			p.pl.sendMessage("Эта шахта закроется через " + Color.col1 + Time.Time.time(( - new Date().getTime() + (mn.timeStart() + mineManager.timeLive)) / 1000));
	    			p.setMine(mn.id);
	    			p.inmine(true);
		    		mn.addMember(p.p);
		    		p.pl.closeInventory();
		    		return ;
	    		}
				p.pl.sendMessage("Шахта регенерируется");
			}
		}
		if (p.pl.getOpenInventory().getTitle().equals(PVEad))
		{
			if (e.getClickedInventory().getType().equals(InventoryType.HOPPER))
				e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR))
				return ;
			String name = e.getCurrentItem().getItemMeta().getDisplayName();
			if (name.equals(PVEad) && e.getCurrentItem().getItemMeta().getLore().get(0).split(" ").length == 2)
			{
				mine mn = new mine(Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[1]));
				if (!p.inmine())
	    		{
	    			Location loc = createmineAd.getSpawn(mn.id);
	    			if (loc == null)
	    				return ;
	    			p.setDocMine(2, p.getDocMine(2) - 1);
	    			way24.tp(p.pl, loc);
	    			p.pl.sendMessage("Вы попали в шахту номер " + Color.col1 + mn.id);
	    			p.pl.sendMessage("Эта шахта закроется через " + Color.col1 + Time.Time.time(( - new Date().getTime() + (mn.timeStart() + mineManager.timeLive)) / 1000));
	    			p.setMine(mn.id);
	    			p.inmine(true);
		    		mn.addMember(p.p);
		    		p.pl.closeInventory();
		    		return ;
	    		}
				p.pl.sendMessage("Шахта регенерируется");
			}
		}
		if (p.pl.getOpenInventory().getTitle().equals(PVEwater))
		{
			if (e.getClickedInventory().getType().equals(InventoryType.HOPPER))
				e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR))
				return ;
			String name = e.getCurrentItem().getItemMeta().getDisplayName();
			if (name.equals(PVEwater) && e.getCurrentItem().getItemMeta().getLore().get(0).split(" ").length == 2)
			{
				mine mn = new mine(Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[1]));
				if (!p.inmine())
	    		{
	    			Location loc = createmineWater.getSpawn(mn.id);
	    			if (loc == null)
	    				return ;
	    			p.setDocMine(4, p.getDocMine(4) - 1);
	    			way24.tp(p.pl, loc);
	    			p.pl.sendMessage("Вы попали в шахту номер " + Color.col1 + mn.id);
	    			p.pl.sendMessage("Эта шахта закроется через " + Color.col1 + Time.Time.time(( - new Date().getTime() + (mn.timeStart() + mineManager.timeLive)) / 1000));
	    			p.setMine(mn.id);
	    			p.inmine(true);
		    		mn.addMember(p.p);
		    		p.pl.closeInventory();
		    		return ;
	    		}
				p.pl.sendMessage("Шахта регенерируется");
			}
		}
		if (p.pl.getOpenInventory().getTitle().equals(PVEend))
		{
			if (e.getClickedInventory().getType().equals(InventoryType.HOPPER))
				e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR))
				return ;
			String name = e.getCurrentItem().getItemMeta().getDisplayName();
			if (name.equals(PVEend) && e.getCurrentItem().getItemMeta().getLore().get(0).split(" ").length == 2)
			{
				mine mn = new mine(Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[1]));
				if (!p.inmine())
	    		{
	    			Location loc = createmineEnd.getSpawn(mn.id);
	    			if (loc == null)
	    				return ;
	    			p.setDocMine(6, p.getDocMine(6) - 1);
	    			way24.tp(p.pl, loc);
	    			p.pl.sendMessage("Вы попали в шахту номер " + Color.col1 + mn.id);
	    			p.pl.sendMessage("Эта шахта закроется через " + Color.col1 + Time.Time.time(( - new Date().getTime() + (mn.timeStart() + mineManager.timeLive)) / 1000));
	    			p.setMine(mn.id);
	    			p.inmine(true);
		    		mn.addMember(p.p);
		    		p.pl.closeInventory();
		    		return ;
	    		}
				p.pl.sendMessage("Шахта регенерируется");
			}
		}
		if (p.pl.getOpenInventory().getTitle().equals(PVEmeca))
		{
			if (e.getClickedInventory().getType().equals(InventoryType.HOPPER))
				e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR))
				return ;
			String name = e.getCurrentItem().getItemMeta().getDisplayName();
			if (name.equals(PVEmeca) && e.getCurrentItem().getItemMeta().getLore().get(0).split(" ").length == 2)
			{
				p.pl.sendMessage(e.getCurrentItem().getItemMeta().getLore().get(0) + " " + p.pl.getOpenInventory().getTitle());
				mine mn = new mine(Integer.parseInt(e.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[1]));
				if (!p.inmine())
	    		{
					if (p.learnStade() == 6)
					{
						Learner.msend(p, Color.c(6) + "И это ты выполнил! Забирай награду");
						p.learnStade(7);
						
						int	time;
						
						time = 60;
				        
				        BukkitTask r0 = new BukkitRunnable()
						{
				            @Override
				            public void run()
				            {
				            	if (p.learnStade() == 7)
				            	{
				            		p.pl.sendMessage("Игрок " + Learner.name + Color.jast + " перевел вам " + Color.col1 + 1200 + Color.jast + " скитов");
				            		p.setMoney(p.getMoney() + 1200);
				            		Learner.Meca(p);
				            	}
				            	cancel();
				            }
				        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
					}
	    			Location loc = createmineMeca.getSpawn(mn.id);
	    			if (loc == null)
	    				return ;
	    			p.setDocMine(8, p.getDocMine(8) - 1);
	    			way24.tp(p.pl, loc);
	    			p.pl.sendMessage("Вы попали в шахту номер " + Color.col1 + mn.id);
	    			p.pl.sendMessage("Эта шахта закроется через " + Color.col1 + Time.Time.time(( - new Date().getTime() + (mn.timeStart() + mineManager.timeLive)) / 1000));
	    			p.setMine(mn.id);
	    			p.inmine(true);
		    		mn.addMember(p.p);
		    		p.pl.closeInventory();
		    		return ;
	    		}
				p.pl.sendMessage("Шахта регенерируется");
			}
		}
	}
	
	public static void createMineType(player p, int type, String name)
	{
		Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, name);
		
		List<String> lore = new ArrayList<String>();
		
		int	x = 0;
		
		for (mine mn : getOpenType(type))
		{
			if (x > 4)
				break ;
			lore.clear();
			
			lore.add(Color.jast + "номер " + mn.id);
			lore.add(Color.jast + "До закрытия пещеры " + Color.col1 + Time.Time.time((mn.timeStart() + timeLive - new Date().getTime())/ 1000));
		
			if (type == 0)
				inv.setItem(x++, Item.Item.create(Material.STONE, PVE, 1, lore));
			if (type == 2)
				inv.setItem(x++, Item.Item.create(Material.NETHERRACK, PVEad, 1, lore));
			if (type == 4)
				inv.setItem(x++, Item.Item.create(Material.PRISMARINE, PVEwater, 1, lore));
			if (type == 6)
				inv.setItem(x++, Item.Item.create(Material.END_STONE, PVEend, 1, lore));
			if (type == 8)
				inv.setItem(x++, Item.Item.create(Material.TERRACOTTA, PVEmeca, 1, lore));
		}
		
		p.pl.openInventory(inv);
	}
	
	public static mine getmaxtime(int type)
	{
		int	id = -1;
		for (mine mn : getOpenType(type))
		{
			if (id == -1)
				id = mn.id;
			else
				if (mn.timeStart() > new mine(id).timeStart())
					id = mn.id;
		}
		return (new mine(id));
	}
	
	public static ArrayList<mine> getOpenType(int type)
	{
		ArrayList<mine> list = new ArrayList<mine>();
		for (String idm : way24.Mine.getKeys(false))
		{
			mine mn = new mine(Integer.parseInt(idm));
			if (mn.getType() == type && mn.open())
				list.add(mn);
		}
		Collections.sort(list, new Comparator<mine>()
		{
	        public int compare(mine o1, mine o2) {
	        	if(o1.timeStart() > o2.timeStart())
	        		return -1;
	        	if(o1.timeStart() < o2.timeStart())
	        		return 1;
	        	else
	        		return 0;
	        }
		});
		return list;
		
	}
}
