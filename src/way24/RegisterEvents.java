package way24;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.block.Biome;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftItem;
import org.bukkit.entity.Animals;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Trident;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPortalExitEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Chat.Color;
import Chat.message;
import Chat.sound;
import Island.ActionIsland;
import Island.createIs;
import Island.island;
import Player.ConditionPlayer;
import Player.Friend;
import Player.Stat;
import Player.player;
import Traders.Miner;
import Traders.Plants;
import Traders.Skits;
import mine.mine;
import mine.mineManager;

public class RegisterEvents implements Listener
{
	@EventHandler
	public void join(PlayerJoinEvent e)
	{
		e.setJoinMessage(Color.c(10) + e.getPlayer().getName());
        BukkitTask r5 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
        		player p = new player(e.getPlayer());
        		p.pl.setInvulnerable(false);
        		Pas.join(p);
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), 1);
	}
	
	@EventHandler
	public void move(PlayerMoveEvent e)
	{
		player p = new player(e.getPlayer());
		if (!p.login())
		{
			e.setCancelled(true);
			return ;
		}
		Location loc = e.getPlayer().getLocation();		
		if (loc.getWorld().equals(way24.world))
		{
			int	x = loc.getBlockX();
			int	y = loc.getBlockY();
			int	z = loc.getBlockZ();
			
			double	xd = loc.getX();
			double	yd = loc.getY();
			double	zd = loc.getZ();
			
			if (p.parc())
			{	
				if (p.pl.getPotionEffect(PotionEffectType.SPEED) != null || p.pl.getPotionEffect(PotionEffectType.JUMP) != null || p.pl.getPotionEffect(PotionEffectType.SLOW_FALLING) != null || yd < 7.5)
				{
					BukkitTask r = new BukkitRunnable()
					{
						@Override
						public void run()
						{
							if (!p.parc())
							for (Player pl : Bukkit.getOnlinePlayers())
								p.pl.showPlayer(pl);
						}
					}.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), 30);
					p.pl.teleport(new Location(way24.world, 6, 7, 19, -94, 0));
					p.pl.setSprinting(true);
					p.parc(false);
					sound.nosound(p.pl);
					return ;
				}
				else
					if (loc.getBlock().getType().equals(Material.REDSTONE_WIRE))
					{
						if (loc.getBlock().getBlockPower() == 0)// || e.getPlayer().getName().equals("_GreyBrick_"))
						{
							sound.psound(128, p.pl);
							final int[]	count;
							
							count = new int[1];
							count[0] = 15;
							BukkitTask r = new BukkitRunnable()
							{
								@Override
								public void run()
								{
									loc.getBlock().setBlockData(Bukkit.createBlockData("minecraft:redstone_wire[power=" + count[0] + "]"));
									if (count[0]-- == 0)
										cancel();
								}
							}.runTaskTimer(Bukkit.getPluginManager().getPlugin("Hi"), 0, 4);
						}
					}
			}
			if (x == -6 && y > 7 && y < 12 && z > 100 && z < 104)
			{
				for (Player pl : way24.world.getPlayers())
					pl.spawnParticle(Particle.SMOKE_NORMAL, loc, 10);
				p.pl.teleport(way24.portal0);
				return ;
			}
			if (x == 26 && y > 6 && y < 10 && z < 10 && z > 6)
			{
				for (Player pl : way24.world.getPlayers())
					pl.spawnParticle(Particle.SMOKE_NORMAL, loc, 10);
				p.pl.teleport(way24.portal1);
				return ;
			}
			if (x > 6 && x < 10 && y > 6 && y < 11 && z == 39)
			{
				for (Player pl : way24.world.getPlayers())
					pl.spawnParticle(Particle.SMOKE_NORMAL, loc, 10);
				p.pl.chat("/is home");
				return ;
			}
			if (x == 12 && y == 8 && z == 18)
			{
				if (p.pl.getFoodLevel() < 20)
					p.pl.setFoodLevel(p.pl.getFoodLevel() + 1);
				sound.yessound(p.pl);
				for (Player pl : Bukkit.getOnlinePlayers())
						p.pl.hidePlayer(pl);
				way24.Players.set(p.p + ".parcloc", null);
				p.parcTime(new Date().getTime());
				if (!p.parc())
				{
					final BossBar b = Bukkit.createBossBar("", BarColor.WHITE, BarStyle.SOLID, BarFlag.CREATE_FOG);
					final long time = p.parcTime();
					final double Btime = (double)(p.parcTimeBest() / 1000);
					p.parcTry(p.parcTry() + 1);
					b.addPlayer(p.pl);
					int	tick = 0;
					BukkitTask r0 = new BukkitRunnable()
					{
			            @Override
			            public void run()
			            {
			            	Location loc = p.pl.getLocation();
			            	for (Player pl : way24.world.getPlayers())
			            		pl.spawnParticle(Particle.VILLAGER_HAPPY, loc, 1);			            	
			            	double timep = (double)((double)(new Date().getTime() - time) / 1000);
			            	p.setparcloc(new Date().getTime() - time, loc);
			            	if ((double) (timep / Btime) < 0.125)
			            		b.setTitle(Color.col0 + "Время : " + Color.c(5) + "" + Color.c(17) + "" +timep);
			            	else if ((double) (timep / Btime) < 0.125 * 2)
			            		b.setTitle(Color.col0 + "Время : " + Color.c(13) + "" + Color.c(17) + "" +timep);
			            	else if ((double) (timep / Btime) < 0.125 * 3)
			            		b.setTitle(Color.col0 + "Время : " + Color.col0 + "" + Color.c(17) + "" +timep);
			            	else if ((double) (timep / Btime) < 0.125 * 4)
			            		b.setTitle(Color.col0 + "Время : " + Color.c(10) + "" + Color.c(17) + "" +timep);
			            	else if ((double) (timep / Btime) < 0.125 * 5)
			            		b.setTitle(Color.col0 + "Время : " + Color.c(14) + "" + Color.c(17) + "" +timep);
			            	else if ((double) (timep / Btime) < 0.125 * 6)
			            		b.setTitle(Color.col0 + "Время : " + Color.c(6) + "" + Color.c(17) + "" +timep);
			            	else if ((double) (timep / Btime) < 0.125 * 7)
			            		b.setTitle(Color.col0 + "Время : " + Color.c(12) + "" + Color.c(17) + "" +timep);
			            	else if ((double) (timep / Btime) < 0.125 * 8)
			            		b.setTitle(Color.col0 + "Время : " + Color.c(4) + "" + Color.c(17) + "" +timep);
			            	else
			            		b.setTitle(Color.col0 + "Время : " + Color.col1 + "" + Color.c(17) + "" +timep);
			            	if ((double)(timep / Btime) > 1)
			            		b.setColor(BarColor.RED);
			            	else
			            		b.setProgress((double)(timep / Btime));
			            	if (!p.parc() || !p.pl.getWorld().equals(way24.world))
			            	{
			            		b.removeAll();
			            		cancel();
			            	}
			            }
			        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("Hi"), 0, 1);
				}
		        p.parc(true);
				return ;
			}
			if (x == 8 && y == 13 && z == 8)
			{
				if (!p.parc())
					return ;
				if (p.parcTimeBest() > new Date().getTime() - p.parcTime() || p.parcTimeBest() == 0)
				{
					p.parcTimeBest(new Date().getTime() - p.parcTime());
					Parcur.setRecord(p);
					p.pl.sendMessage("Это ваше лучшее время");
				}
				else
					p.pl.sendMessage("Выше лучшее время " + Color.c(13) + (double)p.parcTimeBest() / 1000 + Color.jast + "сек.");
				for (Player pl : Bukkit.getOnlinePlayers())
				{
					pl.sendMessage(Color.c(13) + p.p + Color.jast + " прошел паркур за " + Color.col0 + (double)(new Date().getTime() -  p.parcTime()) / 1000 + Color.jast + " секунд\nи получает " + Color.col0 + "10.000" + Color.jast + " скитов");
				}
				p.setMoney(p.getMoney() + 10000);
				p.pl.teleport(way24.spawn);
				p.parc(false);
				return ;
			}
			if (y < 0)
			{
				p.pl.setFallDistance(0);
				int	j = y;
				Location loc1;
				while (++j < 60)
				{
					int i = -5;
					while (++i < 5)
					{
						int	k = -5;
						while (++k < 5)
						{
							loc1 = new Location(way24.world, x + i + 0.5, j, z + k + 0.5);
							if (loc1.getBlock().isEmpty() && !(new Location(way24.world, x + i, j - 1, z + k).getBlock().isPassable())
									&& new Location(way24.world, x + i, j + 1, z + k).getBlock().isEmpty() && new Location(way24.world, x + i, j + 2, z + k).getBlock().isEmpty()
									 && new Location(way24.world, x + i, j + 3, z + k).getBlock().isEmpty()
									 && new Location(way24.world, x + i, j + 4, z + k).getBlock().isEmpty()
									 && new Location(way24.world, x + i, j + 5, z + k).getBlock().isEmpty()
									 && new Location(way24.world, x + i, j + 6, z + k).getBlock().isEmpty())
							{
								p.pl.teleport(loc1);
								return ;
							}
						}
					}
				}
				p.pl.teleport(way24.spawn);
				return ;
			}
		}
	}		
	
	@EventHandler
	public void quit(PlayerQuitEvent e)
	{
		e.setQuitMessage(Color.c(12) + e.getPlayer().getName());
		ConditionPlayer.quit(new player(e.getPlayer()));
	}
	
	@EventHandler
	public void Chat(PlayerChatEvent e)
	{
		if (!admin.Do(new player(e.getPlayer()), 1500))
			message.sendM(new player(e.getPlayer()), e.getMessage());
		e.setCancelled(true);
	}

	@EventHandler
	public void BlockBreak(BlockBreakEvent e)
	{
		player p = new player(e.getPlayer());
		ActionIsland.breack(e, p);
		
		if (p.learnStade() == 2 && (e.getBlock().getType().equals(Material.STONE) || e.getBlock().getType().equals(Material.COBBLESTONE)))
		{
			int	x;
			
			x = 0;
			for (ItemStack i : p.pl.getInventory())
			{
				if (i != null && i.getType().equals(Material.COBBLESTONE))
					x += i.getAmount();
			}
			if (x >= 3)
			{
				p.learnStade(3);
				int	time;
				
				time = 60;
		        
				BukkitTask r0 = new BukkitRunnable()
				{
		            @Override
		            public void run()
		            {
		            	if (p.learnStade() == 3)
		            		Learner.msend(p, Color.c(6) + "Отлично! Ты выполнил задание!");
		            	cancel();
		            }
		        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
		        
		        time += 60;
		        
		        BukkitTask r1 = new BukkitRunnable()
				{
		            @Override
		            public void run()
		            {
		            	if (p.learnStade() == 3)
		            	{
		            		p.pl.sendMessage("Игрок " + Learner.name + Color.jast + " перевел вам " + Color.col1 + 1000 + Color.jast + " скитов");
		            		p.setMoney(p.getMoney() + 1000);
		            		Learner.GiveCobbl(p);
		            	}
		            	cancel();
		            }
		        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
				return ;
			}
			Learner.msend(p, Color.c(6) + "Выкапывай булыжник пока у тебя их не будет " + Color.c(14) + 3 + Color.c(6) + " штуки");
		}
	}
	
	@EventHandler
	public void BlockPlace(BlockPlaceEvent e)
	{
		player p = new player(e.getPlayer());
		ActionIsland.place(e, p);
	}
	
	@EventHandler
	public void EntityDamage(PlayerArmorStandManipulateEvent e)
	{
		if (e.getPlayer() instanceof Player)
		{
			player p = new player((Player) e.getPlayer());
			ActionIsland.Armor(e, p);
		}
	}
	
	@EventHandler
	public void EntityDamage(EntityDamageByEntityEvent e)
	{
		if (e.getDamager() instanceof Arrow || e.getDamager() instanceof Trident)
		{
			Projectile en = (Projectile) e.getDamager();
			if (en.getShooter() instanceof Player)
			{
				player p = new player((Player) en.getShooter());
				ActionIsland.Damage(e, p);
			}
		}
		if (e.getDamager() instanceof Player)
		{
			player p = new player((Player) e.getDamager());
			ActionIsland.Damage(e, p);
		}
	}
	
	@EventHandler
	public void BucketEmpty(PlayerBucketEmptyEvent e)
	{
		player p = new player(e.getPlayer());
		ActionIsland.BucketEmpty(e, p);
	}
	
	@EventHandler
	public void Interact(PlayerInteractEvent e)
	{
		player p = new player(e.getPlayer());
		if (p.pl.getDisplayName().equals("0"))
			return ;
		p.pl.setDisplayName("0");
		BukkitTask r = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	p.pl.setDisplayName("1");
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), 1);
		if (p.pl.isSneaking() && p.pl.isOp())
		{
				if (p.getTick() == 0 && p.pl.getInventory().getHeldItemSlot() == 0)
					p.setTick(1);
				else
				if (p.getTick() == 1 && p.pl.getInventory().getHeldItemSlot() == 2)
					p.setTick(2);
				else
				if (p.getTick() == 2 && p.pl.getInventory().getHeldItemSlot() == 1)
				{
					p.setTick(0);
					if (p.pl.getGameMode() == GameMode.CREATIVE)
						p.pl.setGameMode(GameMode.SURVIVAL);
					else
						p.pl.setGameMode(GameMode.CREATIVE);
				}
				else
					p.setTick(0);
		}	
		if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getBlockData().equals(Bukkit.createBlockData("minecraft:farmland[moisture=7]")))
			e.setCancelled(true);
		if (p.pl.getWorld().equals(way24.worldend) && p.getMine() != -1)
		{
			
			mine mn = new mine(p.getMine());
			if (e.getClickedBlock() != null && mn.elit() != null && e.getClickedBlock().getLocation().equals(mn.elit()))
			{
				p.pl.getWorld().dropItem(p.pl.getLocation(), new ItemStack(Material.ELYTRA));
				mn.elit(null);
				e.setCancelled(true);
				sound.yessound(p.pl);
		    	Firework fw = (Firework) p.pl.getLocation().getWorld().spawnEntity(p.pl.getLocation(), EntityType.FIREWORK);
		    	
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
		    	fwmeta.setPower(0);
		    	
		    	fw.setFireworkMeta(fwmeta);
				for (Player pl : Bukkit.getOnlinePlayers())
					pl.sendMessage(Color.col0 + "Игрок " + Color.c(13) + p.p + Color.col0 + " нашел Элитры!");
			}
		}
		ActionIsland.interact(e, p);
	}
	
	@EventHandler
	public void BucketEmpty(PlayerInteractEntityEvent e)
	{
		player p = new player(e.getPlayer());
		if (p.pl.getDisplayName().equals("0"))
			return ;
		p.pl.setDisplayName("0");
		BukkitTask r = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	p.pl.setDisplayName("1");
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), 1);
		ActionIsland.EntityEvent(e, p);
		Skits.clickSkits(e, p);
		Plants.clickPlants(e, p);
		Miner.clickMiner(e, p);
		
		if (e.getRightClicked() instanceof Player)
			Friend.cmdFr(p, new String[]{"+", e.getRightClicked().getName()});
//		else
//			if (p.p.equals("_GreyBrick_"))
//			{
//				if (e.getRightClicked().getLocation().distance(p.pl.getLocation()) > 1)
//				{
//					p.pl.setPassenger(e.getRightClicked());
//				}
//				else
//					e.getRightClicked().teleport(new Location(p.pl.getWorld(), p.pl.getLocation().getX(), p.pl.getLocation().getY() - 1, p.pl.getLocation().getZ()));					
//			}
	}
	
	@EventHandler
	public void Portal(PlayerPortalEvent e)
	{
		e.setCancelled(true);
	}
	
	@EventHandler
	public void entityEx(EntityExplodeEvent e)
	{
		if (e.getEntity().getWorld().equals(way24.worldmine) || e.getEntity().getWorld().equals(way24.worldad) || e.getEntity().getWorld().equals(way24.worldwater) || e.getEntity().getWorld().equals(way24.worldend))
			return ;
		e.blockList().clear();
	}
	
	@EventHandler
	public void join(BlockIgniteEvent e)
	{
		if (e.getPlayer() == null)
		{
			e.setCancelled(true);
			return ;
		}
		if (e.getPlayer().getWorld().equals(way24.worldmine))
			e.setCancelled(true);
	}
	
	@EventHandler
	public void BlockEx(BlockExplodeEvent e)
	{
		if (e.getBlock().getWorld().equals(way24.worldmine) || e.getBlock().getWorld().equals(way24.worldad) || e.getBlock().getWorld().equals(way24.worldwater) || e.getBlock().getWorld().equals(way24.worldend))
			return ;
		e.blockList().clear();
	}
	
	@EventHandler
	public void join(BlockBurnEvent e)
	{
		e.setCancelled(true);
	}
	
	@EventHandler
	public void join(PlayerRespawnEvent e)
	{
		player p = new player(e.getPlayer());
		if (p.getIs() != -1)
		{
			createIs.cmdHome(p);
			e.setRespawnLocation(p.getHome());
		}
		else
		{
			way24.tp(p.pl, way24.spawn);
			e.setRespawnLocation(way24.spawn);
		}
	}
	
	@EventHandler
	public void Damage(EntityDamageEvent e)
	{
		if (e.getEntity() instanceof Player)
		{
			Player p = (Player) e.getEntity();
			if (p.getWorld().equals(way24.world))
				e.setCancelled(true);
			if (p.getWorld().equals(way24.world) && p.getLocation().getY() < -30)
			{
				e.setCancelled(true);
				p.setFallDistance(0);
				p.teleport(way24.spawn);
			}
		}
	}
	
	@EventHandler
	public void Damage(EntitySpawnEvent e)
	{
		Entity en = e.getEntity();
		int	x = 0;
		int	l = 0;
		int	k = 0;
		if (en.getWorld().equals(way24.worldi) && (en instanceof Animals || en instanceof Monster || en instanceof Villager))
		{
			for (Entity ent : en.getWorld().getNearbyEntities(en.getLocation(), 32, 256, 32))
				if (ent.getType().equals(en.getType()))
					x++;
			for (Entity ent : en.getWorld().getNearbyEntities(en.getLocation(), 64, 256, 64))
				if (ent.getType().equals(en.getType()))
					l++;
			for (Entity ent : en.getWorld().getNearbyEntities(en.getLocation(), 128, 256, 128))
				if (ent.getType().equals(en.getType()))
					k++;
		}
		if (x > 5 || l > 20 || k > 80)
			e.setCancelled(true);
		if (e.getEntity().getWorld().equals(way24.worldmine) || e.getEntity().getWorld().equals(way24.worldad) || e.getEntity().getWorld().equals(way24.worldwater) || e.getEntity().getWorld().equals(way24.worldend))
		{
			if (en.getLocation().getY() > 70)
				e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void Damage(PlayerTeleportEvent e)
	{
		Player p = e.getPlayer();
		if (p.getWorld().equals(way24.world))
		{
			if (e.getCause() == TeleportCause.ENDER_PEARL || e.getCause() == TeleportCause.CHORUS_FRUIT)
				e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void Damage(InventoryClickEvent e)
	{
		player p = new player((Player) e.getWhoClicked());
		Skits.clickInv(e, p);
		Plants.clickInv(e, p);
		Miner.clickInv(e, p);
		
		Stat.clickStat(e, p);
		
		if (p.learnStade() == 1 && e.getCurrentItem() != null && e.getCurrentItem().getType().equals(Material.WOODEN_PICKAXE))
		{
			p.learnStade(2);
			int	time;
			
			time = 60;
			
			BukkitTask r0 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (p.learnStade() == 2)
	            		Learner.msend(p, Color.c(6) + "Отлично!!! Ты создал деревянную кирку!");
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
	        
	        time += 60;
	        
	        BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (p.learnStade() == 2)
	            	{
	            		p.pl.sendMessage("Игрок " + Learner.name + Color.jast + " перевел вам " + Color.col1 + 500 + Color.jast + " скитов");
	            		p.setMoney(p.getMoney() + 500);
	            		Learner.PickCreate(p);
	            	}
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
		}
		
		mineManager.clickInv(e, p);
	}
	
	@EventHandler
	public void ExitPortal(EntityPortalExitEvent e)
	{
		if (e.getEntity().getWorld().equals(way24.world))
			e.setCancelled(true);		
	}
	
	@EventHandler
	public void Drop(BlockDropItemEvent e)
	{
		if (way24.worldsPveMine.contains(e.getBlock().getWorld()))
		{
			for (Item i : e.getItems())
        	{
        		CraftItem ci = (CraftItem) i;
        		ci.setCustomName(10 + "");
        		ci.setCustomNameVisible(true);
        		ci.setRotation(10, 10);
        	}
        	BukkitTask r0 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	char	f;
	            	
	            	f = 0;
	            	for (Item i : e.getItems())
	            	{
	            		CraftItem ci = (CraftItem) i;
	            		ci.setCustomName(Integer.parseInt(ci.getCustomName()) - 1 + "");
	            		if (!i.isDead() && Integer.parseInt(ci.getCustomName()) == 0)
	            			i.remove();
	            		if (!i.isDead())
	            			f = 1;
	            	}
	            	if (f == 0)
	            		cancel();
	            }
	        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("Hi"), 0, 20);
		}
	}
	
	@EventHandler
	public void ChunkUnLoad(ChunkUnloadEvent e)
	{
		if (way24.worldsPveMine.contains(e.getWorld()))
		{
			char	f;
			
			f = 0;
			for (Entity en : e.getChunk().getEntities())
			{
				if (en.isCustomNameVisible())
					f = 1;
			}
			if (f == 1)
				e.getChunk().load();
		}
	}
	
	@EventHandler
	public void Drop(ItemSpawnEvent e)
	{
		if (way24.worldsPveMine.contains(e.getEntity().getWorld()))
		{
        	CraftItem ci = (CraftItem) e.getEntity();
        	ci.setCustomName(20 + "");
        	ci.setCustomNameVisible(true);
        	BukkitTask r0 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	char	f;
	            	
	            	f = 0;
	            	CraftItem ci = (CraftItem) e.getEntity();
	            	ci.setCustomName(Integer.parseInt(ci.getCustomName()) - 1 + "");
	            	if (!ci.isDead() && Integer.parseInt(ci.getCustomName()) == 0)
	            		ci.remove();
	            	if (!ci.isDead())
	            		f = 1;
	            	if (f == 0)
	            		cancel();
	            }
	        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("Hi"), 0, 20);
		}
	}
	
	public ArrayList<String> randomlist(int size)
	{
		ArrayList<String> strlist = new ArrayList<String>();
		String m = "abcdefghijklmnopqrstuvwxyz";
		char[] str = {'a','a','a','a','a','a','a'};
		int	x = -1;
    	while (++x < size)
    	{
    		int	i = -1;
    		while (++i < str.length)
    			str[i] = (char) m.getBytes()[(int)(Math.random() * m.length())];
    		strlist.add(new String(str));
    	}
	    return strlist;
	}
	
	@EventHandler
	public void TabCompl(TabCompleteEvent e)
	{
		player p = new player((Player) e.getSender());
		if (e.getBuffer().split(" ")[0].equalsIgnoreCase("/shop"))
		{
			List<String> list = new ArrayList<String>();
			list.add("огородник");
			list.add("скитс");
			list.add("шахтер");
			ArrayList<String> flist = new ArrayList<String>();
			if (e.getBuffer().split(" ").length > 1)
			{
				for (String s : list)
				{	
					int		x;
					char	f;
					
					x = -1;
					f = 0;
					for (byte b : e.getBuffer().split(" ")[1].getBytes())
					{
						x++;
						if (s.getBytes().length > x && b != s.getBytes()[x])
							f = 1;
					}
					if (f == 0)
						flist.add(s);
				}
				e.setCompletions(flist);
				return ;
			}	
			e.setCompletions(list);
			return ;
		}
		if (e.getBuffer().split(" ")[0].equalsIgnoreCase("/is") && e.getBuffer().split(" ").length >= 2 && e.getBuffer().split(" ")[1].equalsIgnoreCase("rem"))
		{
			ArrayList<String> list = new ArrayList<String>();
			ArrayList<String> flist = new ArrayList<String>();
			if (p.getIs() == -1)
				return ;
			island is = new island(p.getIs());
			for (String s : is.getMembers())
				list.add(s);
			if (e.getBuffer().split(" ").length > 2)
			{
				for (String s : list)
				{	
					int		x;
					char	f;
					
					x = -1;
					f = 0;
					for (byte b : e.getBuffer().split(" ")[2].getBytes())
					{
						x++;
						if (s.getBytes().length > x && b != s.getBytes()[x])
							f = 1;
					}
					if (f == 0)
						flist.add(s);
				}
				e.setCompletions(flist);
				return ;
			}	
			e.setCompletions(list);
			return ;
		}
		if (e.getBuffer().split(" ")[0].equalsIgnoreCase("/is") && e.getBuffer().split(" ").length >= 2 && e.getBuffer().split(" ")[1].equalsIgnoreCase("add"))
		{
			ArrayList<String> list = new ArrayList<String>();
			ArrayList<String> flist = new ArrayList<String>();
			if (p.getIs() == -1)
				return ;
			island is = new island(p.getIs());
			for (Player s : Bukkit.getOnlinePlayers())
				if (!s.getName().equals(p.p))
					list.add(s.getName());
			if (e.getBuffer().split(" ").length > 2)
			{
				for (String s : list)
				{	
					int		x;
					char	f;
					
					x = -1;
					f = 0;
					for (byte b : e.getBuffer().split(" ")[2].getBytes())
					{
						x++;
						if (s.getBytes().length > x && b != s.getBytes()[x])
							f = 1;
					}
					if (f == 0)
						flist.add(s);
				}
				e.setCompletions(flist);
				return ;
			}	
			e.setCompletions(list);
			return ;
		}
		if (e.getBuffer().split(" ")[0].equalsIgnoreCase("/is") && e.getBuffer().split(" ").length >= 2 && e.getBuffer().split(" ")[1].equalsIgnoreCase("biome"))
		{
			ArrayList<String> list = new ArrayList<String>();
			ArrayList<String> flist = new ArrayList<String>();
			if (p.getIs() == -1)
				return ;
			for (Biome s : Biome.values())
				if (!s.name().equals(p.p))
					list.add(s.name());
			if (e.getBuffer().split(" ").length > 2)
			{
				for (String s : list)
				{	
					int		x;
					char	f;
					
					x = -1;
					f = 0;
					for (byte b : e.getBuffer().split(" ")[2].getBytes())
					{
						x++;
						if (s.getBytes().length > x && b != s.getBytes()[x])
							f = 1;
					}
					if (f == 0)
						flist.add(s);
				}
				e.setCompletions(flist);
				return ;
			}	
			e.setCompletions(list);
			return ;
		}
		if (e.getBuffer().split(" ")[0].equalsIgnoreCase("/is") && e.getBuffer().split(" ").length >= 2 && e.getBuffer().split(" ")[1].equalsIgnoreCase("home"))
		{
			ArrayList<String> list = new ArrayList<String>();
			ArrayList<String> flist = new ArrayList<String>();
			if (!p.doIs())
				return ;
			for (String s : way24.islands.getKeys(false))
			{
				if (Bukkit.getOfflinePlayer((new island(s).getOwner().p)).isOnline())
					list.add(new island(s).getOwner().p);
			}
			for (String s : way24.islands.getKeys(false))
			{
				if (!Bukkit.getOfflinePlayer((new island(s).getOwner().p)).isOnline())
					list.add(new island(s).getOwner().p);
			}
			if (e.getBuffer().split(" ").length > 2)
			{
				for (String s : list)
				{	
					int		x;
					char	f;
					
					x = -1;
					f = 0;
					for (byte b : e.getBuffer().split(" ")[2].getBytes())
					{
						x++;
						if (s.getBytes().length > x && b != s.getBytes()[x])
							f = 1;
					}
					if (f == 0)
						flist.add(s);
				}
				e.setCompletions(flist);
				return ;
			}	
			e.setCompletions(list);
			return ;
		}
		if (e.getBuffer().split(" ")[0].equalsIgnoreCase("/is") && e.getBuffer().split(" ").length >= 2 && e.getBuffer().split(" ")[1].equalsIgnoreCase("rename"))
		{
			ArrayList<String> list = randomlist(1000);
			ArrayList<String> flist = new ArrayList<String>();
			if (!p.doIs())
				return ;
			if (e.getBuffer().split(" ").length > 2)
			{
				for (String s : list)
				{	
					int		x;
					char	f;

					x = -1;
					f = 0;
					for (byte b : e.getBuffer().split(" ")[2].getBytes())
					{
						x++;
						if (s.getBytes().length > x && b != s.getBytes()[x])
							f = 1;
					}
					if (f == 0)
						flist.add(s);
				}
				e.setCompletions(flist);
				return ;
			}	
			e.setCompletions(list);
			return ;
		}
		if (e.getBuffer().split(" ")[0].equalsIgnoreCase("/is"))
		{
			ArrayList<String> list = new ArrayList<String>();
			ArrayList<String> flist = new ArrayList<String>();
			list.add("create");
			if (p.getIs() != -1)
			{
				list.add("add");
				list.add("rem");
				list.add("biome");
				list.add("home");
				list.add("add");
				list.add("rem");
				list.add("sethome");
				list.add("info");
				list.add("up");
				list.add("rename");
			}
			if (e.getBuffer().split(" ").length > 1)
			{
				for (String s : list)
				{	
					int		x;
					char	f;
					
					x = -1;
					f = 0;
					for (byte b : e.getBuffer().split(" ")[1].getBytes())
					{
						x++;
						if (s.getBytes().length > x && b != s.getBytes()[x])
							f = 1;
					}
					if (f == 0)
						flist.add(s);
				}
				e.setCompletions(flist);
				return ;
			}	
			e.setCompletions(list);
			return ;
		}
		if (e.getBuffer().equalsIgnoreCase("/is biome "))
		{
			List<String> list = new ArrayList<String>();
			for (Biome s : Biome.values())
				list.add(s.name());
			e.setCompletions(list);
			return ;
		}
	}
}

