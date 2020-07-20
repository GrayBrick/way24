package Island;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Chat.Color;
import Chat.sound;
import Player.player;
import way24.Learner;

public class ActionIsland
{
	public static void breack(BlockBreakEvent e, player p)
	{
		Location loc = e.getBlock().getLocation();
		if (loc.getWorld().equals(way24.way24.worldi))
		{
			if (p.getIs() != -1 && new island(p.getIs()).inIsland(loc) || p.doIs())
				return ;
			e.setCancelled(true);
		}
		if (loc.getWorld().equals(way24.way24.world) && !p.doSpawn())
		{
			p.pl.sendMessage("?? ?? ?????? ??? ??????");
			sound.nosound(p.pl);
			e.setCancelled(true);
		}
	}
	
	public static void place(BlockPlaceEvent e, player p)
	{
		Location loc = e.getBlock().getLocation();
		if (loc.getWorld().equals(way24.way24.worldi))
		{
			if (p.getIs() != -1 && new island(p.getIs()).inIsland(loc) || p.doIs())
			{
				if (e.getBlock().getType().equals(Material.WHEAT) && !p.seedDoc(0))
				{
					p.pl.sendMessage("? ??? ??? ?????????? ?? ??????? " + Color.col1 + "????? ???????");
					p.pl.sendMessage("??? ????? ?????????? ?? " + Color.col1 + "/shop ?????????");
					e.setCancelled(true);
					return ;
				}
				else if (p.learnStade() == 5 && e.getBlock().getType().equals(Material.WHEAT ))
				{
					p.learnStade(6);
					Learner.msend(p, Color.c(6) + "???????!!! ??? ???? ???????!");
					
					int	time;
					
					time = 60;
			        
			        BukkitTask r0 = new BukkitRunnable()
					{
			            @Override
			            public void run()
			            {
			            	if (p.learnStade() == 6)
			            	{
			            		p.pl.sendMessage("????? " + Learner.name + Color.jast + " ??????? ??? " + Color.col1 + 3000 + Color.jast + " ??????");
			            		p.setMoney(p.getMoney() + 3000);
			            		Learner.setwheat(p);
			            	}
			            	cancel();
			            }
			        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
				}
				if (e.getBlock().getType().equals(Material.POTATOES) && !p.seedDoc(1))
				{
					p.pl.sendMessage("? ??? ??? ?????????? ?? ??????? " + Color.col1 + "????????");
					p.pl.sendMessage("??? ????? ?????????? ?? " + Color.col1 + "/shop ?????????");
					e.setCancelled(true);
					return ;
				}
				if (e.getBlock().getType().equals(Material.CARROTS) && !p.seedDoc(2))
				{
					p.pl.sendMessage("? ??? ??? ?????????? ?? ??????? " + Color.col1 + "???????");
					p.pl.sendMessage("??? ????? ?????????? ?? " + Color.col1 + "/shop ?????????");
					e.setCancelled(true);
					return ;
				}
				if (e.getBlock().getType().equals(Material.PUMPKIN_STEM) && !p.seedDoc(3))
				{
					p.pl.sendMessage("? ??? ??? ?????????? ?? ??????? " + Color.col1 + "????? ?????");
					p.pl.sendMessage("??? ????? ?????????? ?? " + Color.col1 + "/shop ?????????");
					e.setCancelled(true);
					return ;
				}
				if (e.getBlock().getType().equals(Material.MELON_STEM) && !p.seedDoc(4))
				{
					p.pl.sendMessage("? ??? ??? ?????????? ?? ??????? " + Color.col1 + "????? ??????");
					p.pl.sendMessage("??? ????? ?????????? ?? " + Color.col1 + "/shop ?????????");
					e.setCancelled(true);
					return ;
				}
				if (e.getBlock().getType().equals(Material.BEETROOTS) && !p.seedDoc(5))
				{
					p.pl.sendMessage("? ??? ??? ?????????? ?? ??????? " + Color.col1 + "????? ??????");
					p.pl.sendMessage("??? ????? ?????????? ?? " + Color.col1 + "/shop ?????????");
					e.setCancelled(true);
					return ;
				}
				if (e.getBlock().getType().equals(Material.COCOA) && !p.seedDoc(6))
				{
					p.pl.sendMessage("? ??? ??? ?????????? ?? ??????? " + Color.col1 + "?????-?????");
					p.pl.sendMessage("??? ????? ?????????? ?? " + Color.col1 + "/shop ?????????");
					e.setCancelled(true);
					return ;
				}
				if (e.getBlock().getType().equals(Material.BAMBOO_SAPLING) && !p.seedDoc(7))
				{
					p.pl.sendMessage("? ??? ??? ?????????? ?? ??????? " + Color.col1 + "???????");
					p.pl.sendMessage("??? ????? ?????????? ?? " + Color.col1 + "/shop ?????????");
					e.setCancelled(true);
					return ;
				}
				if (e.getBlock().getType().equals(Material.SUGAR_CANE) && !p.seedDoc(8))
				{
					p.pl.sendMessage("? ??? ??? ?????????? ?? ??????? " + Color.col1 + "?????????");
					p.pl.sendMessage("??? ????? ?????????? ?? " + Color.col1 + "/shop ?????????");
					e.setCancelled(true);
					return ;
				}
				if (e.getBlock().getType().equals(Material.SWEET_BERRY_BUSH) && !p.seedDoc(9))
				{
					p.pl.sendMessage("? ??? ??? ?????????? ?? ??????? " + Color.col1 + "???????? ?????");
					p.pl.sendMessage("??? ????? ?????????? ?? " + Color.col1 + "/shop ?????????");
					e.setCancelled(true);
					return ;
				}
				return ;
			}
			e.setCancelled(true);
		}
		if (loc.getWorld().equals(way24.way24.world) && !p.doSpawn())
		{
			p.pl.sendMessage("?? ?? ?????? ??? ??????? ?????");
			sound.nosound(p.pl);
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public static void Armor(PlayerArmorStandManipulateEvent e, player p)
	{
		Location loc = e.getRightClicked().getLocation();
		if (e.getRightClicked() instanceof Monster)
			return ;
		if ((loc.getWorld().equals(way24.way24.worldmine) || loc.getWorld().equals(way24.way24.worldad) || loc.getWorld().equals(way24.way24.worldwater) || loc.getWorld().equals(way24.way24.worldend)) && e.getRightClicked() instanceof Player)
			e.setCancelled(true);
		if (loc.getWorld().equals(way24.way24.worldi))
		{
			if ((!(e.getRightClicked() instanceof Player) && p.getIs() != -1 && new island(p.getIs()).inIsland(loc)) || p.doIs())
				return ;
			e.setCancelled(true);
		}
		if (loc.getWorld().equals(way24.way24.world) && !p.doSpawn())
		{
			p.pl.sendMessage("?? ?? ?????? ??????? ???");
			sound.nosound(p.pl);
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public static void Damage(EntityDamageByEntityEvent e, player p)
	{
		Location loc = e.getEntity().getLocation();
		if (e.getEntity() instanceof Monster)
			return ;
		if ((loc.getWorld().equals(way24.way24.worldmine) || loc.getWorld().equals(way24.way24.worldad) || loc.getWorld().equals(way24.way24.worldwater) || loc.getWorld().equals(way24.way24.worldend)) && e.getEntity() instanceof Player)
			e.setCancelled(true);
		if (loc.getWorld().equals(way24.way24.worldi))
		{
			if ((!(e.getEntity() instanceof Player) && p.getIs() != -1 && new island(p.getIs()).inIsland(loc)) || p.doIs())
				return ;
			e.setCancelled(true);
		}
		if (loc.getWorld().equals(way24.way24.world) && !p.doSpawn())
		{
			p.pl.sendMessage("?? ?? ?????? ??? ????");
			sound.nosound(p.pl);
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public static void BucketEmpty(PlayerBucketEmptyEvent e, player p)
	{
		Location loc = e.getBlockClicked().getLocation();
		if (loc.getWorld().equals(way24.way24.worldmine) && p.pl.getItemInHand().getType().equals(Material.LAVA_BUCKET))
		{
			e.setCancelled(true);
			return ;
		}
		if (loc.getWorld().equals(way24.way24.worldi))
		{
			if (p.getIs() != -1 && new island(p.getIs()).inIsland(loc) || p.doIs())
				return ;
			e.setCancelled(true);
		}
		if (loc.getWorld().equals(way24.way24.world) && !p.doSpawn())
		{
			p.pl.sendMessage("?? ?? ?????? ??? ????????? ????????");
			sound.nosound(p.pl);
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public static void EntityEvent(PlayerInteractEntityEvent e, player p)
	{
		Location loc = e.getRightClicked().getLocation();
		if (loc.getWorld().equals(way24.way24.worldi))
		{
			if (p.getIs() != -1 && new island(p.getIs()).inIsland(loc) || p.doIs())
				return ;
			e.setCancelled(true);
		}
		if (loc.getWorld().equals(way24.way24.world) && !p.doSpawn())
			e.setCancelled(true);
	}
	
	@EventHandler
	public static void interact(PlayerInteractEvent e, player p)
	{
		if (!p.doIs() && (p.pl.getItemInHand().getType().equals(Material.SPLASH_POTION) || p.pl.getItemInHand().getType().equals(Material.LINGERING_POTION)))
			e.setCancelled(true);
		try
		{
			Location loc = e.getClickedBlock().getLocation();
			if ((p.getPortal(0) == 1 || p.getPortal(1) == 1) && p.pl.getItemInHand().getType().equals(Material.AIR))
			{
				if (p.getPortal(0) == 1)
				{
					if (loc.getBlock().getBlockData().getAsString().equals("minecraft:nether_portal[axis=x]"))
						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:nether_portal[axis=z]"));
					else
						loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:nether_portal[axis=x]"));
					return ;
				}
				if (p.getPortal(1) == 1)
				{
					loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:end_portal"));
					return ;
				}
			}
			if (loc.getWorld().equals(way24.way24.worldi))
			{
				if (p.getIs() != -1 && new island(p.getIs()).inIsland(loc) || p.doIs())
					return ;
				e.setCancelled(true);
			}
			if (loc.getWorld().equals(way24.way24.world) && !p.doSpawn())
			{
				if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getBlockData().equals(Bukkit.createBlockData("minecraft:crafting_table")))
					return ;
				e.setCancelled(true);
			}
		}catch(Exception ex)
		{
			Location loc = p.pl.getLocation();
			if (loc.getWorld().equals(way24.way24.worldi))
			{
				if (p.getIs() != -1 && new island(p.getIs()).inIsland(loc) || p.doIs())
					return ;
				e.setCancelled(true);
			}
			if (loc.getWorld().equals(way24.way24.world) && !p.doSpawn())
			{
				if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getBlockData().equals(Bukkit.createBlockData("minecraft:crafting_table")))
					return ;
				e.setCancelled(true);
			}
		}			
	}
}
