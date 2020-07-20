package Island;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Biome;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Chat.Color;
import Chat.sound;
import Player.ConditionPlayer;
import Player.player;
import mine.mine;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import way24.Learner;
import way24.way24;

public class createIs
{
	
	public static ArrayList<String> getTopList()
	{
		ArrayList<String> list = new ArrayList<String>();
		list.clear();
		for (String s : way24.Players.getKeys(false))
		{
			player p = new player(s);
			if (p.getIs() != -1 && new island(p.getIs()).getOwner().p.equals(p.p))
				list.add(p.getIs() + "");
		}
		Collections.sort(list, new Comparator<String>()
		{
	        public int compare(String o1, String o2) {
	        	if(new island(o1).getLvl() > new island(o2).getLvl())return -1;
	        	if(new island(o1).getLvl() < new island(o2).getLvl())return 1;
	        	return 0;
	        }
		});
		return list;
	}
	
	public static void removeIs(island is)
	{
		for (String s : is.getMembers())
		{
			is.removeMember(s);
			if (Bukkit.getOfflinePlayer(s).isOnline())
			{
				new player(s).pl.sendMessage("Вас выгнали с острова " + Color.col1 + is.getName());
				if (new player(s).pl.getWorld().equals(way24.worldi))
					way24.tp(Bukkit.getPlayer(s), way24.spawn);
			}
			new player(s).setIs(-1);			
		}
		is.getOwner().setIs(-1);
		int	x = is.getCenter().getBlockX();
		int	z = is.getCenter().getBlockZ();
		int	y = -1;
		while (y++ < 256)
		{
			int	j;
			int	i = -1;
			while (++i <= is.size() * 2)
			{
				j = -1;
				while (++j <= is.size() * 2)
					new Location(way24.worldi, x + i - is.size(), y, z + j - is.size()).getBlock().setType(Material.AIR);
			}
		}
		int	j;
		int	i = -1;
		while (++i <= is.size() * 2)
		{
			j = -1;
			while (++j <= is.size() * 2)
			{
				for (Entity e : new Location(way24.worldi, x + i - is.size(), 0, z + j - is.size()).getBlock().getChunk().getEntities())
					if (!(e instanceof Player))
						e.remove();
			}
		}
		is.free(false);
	}
	
	public static void setIs(Location loc)
	{
		int	x = loc.getBlockX();
		int	z = loc.getBlockZ();
		int	y = 42;
		
		int	j;
		int	k;
		int	i = -1;
		while (++i < 15)
		{
			j = -1;
			while (++j < 15)
			{
				k = -1;
				while (++k < 15)
				{
					Location loc1 = new Location(way24.worldi, x - 7 + i, y + j - 15, z - 7 + k);
					if (loc1.distance(loc) < 5)
						loc1.getBlock().setBlockData(Bukkit.createBlockData("minecraft:grass_block"));
				}
			}
		}
		loc.setX(loc.getX() + 1);
		loc.getWorld().generateTree(loc, TreeType.BIG_TREE);
	}
	
	public static void newIs(player p, int x)
	{
		if (new Date().getTime() - p.timeIs() < 1000 * 60 * 60 && !p.pl.isOp())
		{
			p.pl.sendMessage("Создавать новый остров можно только " + Color.col0 + "1" + Color.jast + " раз в час");
			return ;
		}
		if (x == 0 && p.getIs() != -1 && new island(p.getIs()).getOwner().p.equals(p.p))
		{
			p.pl.sendMessage("Вы точно хотите создать новый остров? Старй " + Color.col0 + "удалится\n\n ");
			ComponentBuilder m1 = new ComponentBuilder("ПОДТВЕРДИТЬ");
	        m1.color(Color.c1(11));
	        m1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/is create y"));
	        m1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Подтвердить ").color(Color.c1(3)).append(Color.jast + "удаление острова").create()));
	        p.pl.spigot().sendMessage(new ComponentBuilder("Нажмите ").append(m1.create()).append(new ComponentBuilder("").event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, null)).color(Color.c1(15)).create()).append(" для удаления текущего острова и создания нового").create());
			ComponentBuilder m = new ComponentBuilder("ПОДТВЕРДИТЬ");
	        m.color(Color.c1(11));
	        m.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/is create y"));
	        m.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Подтвердить ").color(Color.c1(3)).append(Color.jast + "удаление острова").create()));
	        p.pl.spigot().sendMessage(new ComponentBuilder("||-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-||\n\n||--------->").append(m.create()).append(new ComponentBuilder("<---------||\n\n||_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_||").event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, null)).color(Color.c1(15)).create()).create());
			return ;
		}
		if (p.getIs() != -1)
		{
			island is = new island(p.getIs());
			if (is.getOwner().p.equals(p.p))
				removeIs(is);			
			else
			{
				p.setIs(-1);
				is.removeMember(p.p);
			}
		}
		island is = new island();
		is.first(new Date().getTime());
		is.setOwner(p);
		is.setName(p.p);
		is.setCenter(new Location(way24.worldi, 300 * is.id, 42, 0));
		setIs(new Location(way24.worldi, 300 * is.id, 42, 0));
		is.size(15);
		is.free(true);
		p.setIs(is.id);
		p.setTimeIs();
		is.setMembers(null);
		p.setHome(new Location(way24.worldi, 300 * is.id - 3, 43, 0));
		is.setBiom(Biome.PLAINS);
		way24.tp(p.pl, new Location(way24.worldi, 300 * is.id - 3, 43, 0));
		p.pl.sendMessage("Теперь это твой новый " + Color.col1 + " остров");
		is.setLvlblock(0);
		if (p.learnStade() == 0)
		{
			p.learnStade(1);
			int	time;
			
			time = 60;
			
			BukkitTask r0 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (p.learnStade() == 1)
	            		Learner.msend(p, Color.c(6) + "Отлично!!! Ты создал свой остров!");
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
	        
	        time += 60;
			
			BukkitTask r1 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (p.learnStade() == 1)
	            	{
	            		p.pl.sendMessage("Игрок " + Learner.name + Color.jast + " перевел вам " + Color.col1 + 500 + Color.jast + " скитов");
	            		p.setMoney(p.getMoney() + 500);
	            		Learner.Iscreate(p);
	            	}
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
		}		
	}
	
	public static void joinIs(player p, int id)
	{
		if (!new island(id).free())
		{
			p.pl.sendMessage("Остров уже не существует");
			return ;
		}
		if (p.getIs() == id)
		{
			p.pl.sendMessage("Вы уже являетесь участником острова " + Color.col1 + new island(id).getName());
			return ;
		}
		if (p.getIs() != -1)
		{
			island i = new island(p.getIs());
			if (i.getOwner().p.equals(p.p))
				removeIs(i);			
			else
			{
				p.setIs(-1);
				i.removeMember(p.p);
			}
		}
		if (Bukkit.getOfflinePlayer(new island(id).getOwner().p).isOnline())
			new island(id).getOwner().pl.sendMessage("К вашему острову присоединился " + Color.col1 + p.p);
		for (String s : new island(id).getMembers())
			if (Bukkit.getOfflinePlayer(s).isOnline())
				Bukkit.getPlayer(s).sendMessage("К вашему острову присоединился " + Color.col1 + p.p);
		p.setIs(id);
		new island(id).addMember(p.p);
		p.setHome(new island(id).getOwner().getHome());
		cmdHome(p);
	}
	
	public static boolean isValidHome(Location loc)
	{
		Location loc1 = loc.clone();
		Location loc2 = loc.clone();
		loc1.setY(loc1.getBlockY() - 1);
		loc2.setY(loc2.getBlockY() + 1);
		if (loc.getBlock().isEmpty()
				&& loc2.getBlock().isEmpty()
				&& !(loc1.getBlock().isLiquid())
				&& !(loc1.getBlock().isEmpty()))
			return true;
		return false;
	}
	
	public static void cmdHome(player p)
	{
		if (p.getIs() == -1)
		{
			p.pl.sendMessage("У вас нет острова, чтобы создать " + Color.col1 + "/is create");
			return ;
		}
		island is = new island(p.getIs());
		if (p.getHome() == null || !isValidHome(p.getHome()))
		{
			int	m = 100;
			while (m-- > 0)
			{
				int	x1 = (int) (Math.random() * is.size() * 2);
				int	y1 = 256;
				int	z1 = (int) (Math.random() * is.size() * 2);
				while (y1 > 0)
				{						
					if (isValidHome(new Location(way24.worldi, is.getCenter().getX() + x1 - is.size(), y1, is.getCenter().getZ() + z1 - is.size())))
						p.setHome(new Location(way24.worldi, is.getCenter().getX() + x1 - is.size() + 0.5, y1, is.getCenter().getZ() + z1 - is.size() + 0.5));
					y1--;
				}
			}
		}
		is.lastlogin(new Date().getTime());
		way24.tp(p.pl, p.getHome());
		if (p.learnStade() == 3)
			Learner.msend(p, Color.c(6) + "Хорошо, теперь поставь новую точку спавна " + Color.c(14) + "/is sethome");
	}
	
	public static void cmdsetHome(player p)
	{
		if (p.getIs() == -1)
		{
			p.pl.sendMessage("У вас нет острова, чтобы создать " + Color.col1 + "/is create");
			return ;
		}
		island is = new island(p.getIs());
		if (!is.inIsland(p.pl.getLocation()))
		{
			p.pl.sendMessage("Вы должны быть на своем острове, " + Color.col1 + "/is home");
			return ;
		}
		if (!isValidHome(p.pl.getLocation()))
		{
			p.pl.sendMessage("Вы не можете поставить тут home");
			return ;
		}
		p.setHome();
		p.pl.sendMessage(Color.col0 + "Вы установили точку телепорта на свой остров");
		if (p.learnStade() == 3)
		{
			p.learnStade(4);
			Learner.msend(p, Color.c(6) + "Отлично!!! вот твоя награда!");
			
			int	time;
			
			time = 60;
	        
	        BukkitTask r0 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (p.learnStade() == 4)
	            	{
	            		p.pl.sendMessage("Игрок " + Learner.name + Color.jast + " перевел вам " + Color.col1 + 300 + Color.jast + " скитов");
	            		p.setMoney(p.getMoney() + 300);
	            		Learner.sethome(p);
	            	}
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
		}			
	}
	
	public static void cmdAdd(player p, String[] args)
	{
		if (p.getIs() == -1)
		{
			p.pl.sendMessage("У вас нет острова, чтобы создать " + Color.col1 + "/is create");
			return ;
		}
		island is = new island(p.getIs());
		if (!is.getOwner().p.equals(p.p) && !p.pl.isOp())
		{
			p.pl.sendMessage("Вы не глава тут");
			return ;
		}
		for (Player pl : Bukkit.getOnlinePlayers())
		{
			if (pl.getName().equals(args[1]))
			{
				if (p.p.equals(args[1]) && !p.pl.isOp())
				{
					p.pl.sendMessage("А ты хакер я смотрю");
					return ;
				}
				for (String s : is.getMembers())
				{
					if (s.equals(args[1]))
					{
						p.pl.sendMessage(Color.col1 + args[1] + Color.jast + " участник вашего острова");
						return ;
					}
				}
				player p0 = new player(args[1]);
				p0.pl.sendMessage(Color.col1 + p.p + Color.jast + " предлагает вам играть на острове вместе " + Color.col1 + new island(p.getIs()).getName());
				if (p0.getIs() != -1)
				{
					if (new island(p0.getIs()).getOwner().p.equals(p0.p))
						p0.pl.sendMessage("Если вы согласитесь, то " + Color.col0 + " ваш" + Color.jast + " остров" + Color.col0 + " удалится!");
					else
						p0.pl.sendMessage("Если вы согласитесь, то " + Color.col0 + " вы" + Color.jast + " покинете остров игрока" + Color.col0 + new island(p0.getIs()).getOwner().p);
				}
				ComponentBuilder m = new ComponentBuilder("ПОДТВЕРДИТЬ");
		        m.color(Color.c1(11));
		        m.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/is 98421342 " + p.getIs()));
		        m.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Подтвердить ").color(Color.c1(3)).append(Color.jast + " переход на другой остров").create()));
		        p0.pl.spigot().sendMessage(new ComponentBuilder("||-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-||\n\n||--------->").append(m.create()).append(new ComponentBuilder("<---------||\n\n||_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_||").event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, null)).color(Color.c1(15)).create()).create());
				return ;
			}
		}
		p.pl.sendMessage("Игрок " + Color.col1 + args[1] + Color.jast + " не в игре");
	}
	
	public static void cmdAddI(player p, String[] args, int id)
	{
		island is = new island(id);
		if (!is.getOwner().p.equals(p.p) && !p.pl.isOp())
		{
			p.pl.sendMessage("Вы не глава тут");
			return ;
		}
		for (Player pl : Bukkit.getOnlinePlayers())
		{
			if (pl.getName().equals(args[1]))
			{
				if (p.p.equals(args[1]) && !p.pl.isOp())
				{
					p.pl.sendMessage("А ты хакер я смотрю");
					return ;
				}
				for (String s : is.getMembers())
				{
					if (s.equals(args[1]))
					{
						p.pl.sendMessage(Color.col1 + args[1] + Color.jast + " участник вашего острова");
						return ;
					}
				}
				player p0 = new player(args[1]);
				p0.pl.sendMessage(Color.col1 + p.p + Color.jast + " предлагает вам играть на острове вместе " + Color.col1 + new island(id).getName());
				if (p0.getIs() != -1)
				{
					if (new island(p0.getIs()).getOwner().p.equals(p0.p))
						p0.pl.sendMessage("Если вы согласитесь, то " + Color.col0 + " ваш" + Color.jast + " остров" + Color.col0 + " удалится!");
					else
						p0.pl.sendMessage("Если вы согласитесь, то " + Color.col0 + " вы" + Color.jast + " покинете остров игрока" + Color.col0 + new island(p0.getIs()).getOwner().p);
				}
				ComponentBuilder m = new ComponentBuilder("ПОДТВЕРДИТЬ");
		        m.color(Color.c1(11));
		        m.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/is 98421342 " + id));
		        m.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Подтвердить ").color(Color.c1(3)).append(Color.jast + " переход на другой остров").create()));
		        p0.pl.spigot().sendMessage(new ComponentBuilder("||-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-||\n\n||--------->").append(m.create()).append(new ComponentBuilder("<---------||\n\n||_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_||").event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, null)).color(Color.c1(15)).create()).create());
				return ;
			}
		}
		p.pl.sendMessage("Игрок " + Color.col1 + args[1] + Color.jast + " не в игре");
	}
	
	public static void cmdRem(player p, String[] args)
	{
		if (p.getIs() == -1)
		{
			p.pl.sendMessage("У вас нет острова, чтобы создать " + Color.col1 + "/is create");
			return ;
		}
		island is = new island(p.getIs());
		if (!is.getOwner().pl.equals(p.pl) && !p.pl.isOp())
		{
			p.pl.sendMessage("Вы не глава тут");
			return ;
		}
		for (String s : is.getMembers())
		{
			if (s.equals(args[1]))
			{
				player pl = new player(args[1]);
				if (pl.pl.isOnline())
				{
					if (is.inIsland(pl.pl.getLocation()))
						way24.tp(pl.pl, way24.spawn);
					pl.pl.sendMessage("Вас выгнали с острова " + Color.col1 + is.getName());
				}
				pl.setIs(-1);
				is.removeMember(args[1]);
				p.pl.sendMessage("Вы выгнали игрока " + Color.col1 + args[1] + Color.jast + " с острова " + Color.col1 + is.getName());
				return ;
			}
		}
		p.pl.sendMessage("Игрока " + Color.col1 + args[1] + Color.jast + " нет в участниках региона " + Color.col1 + is.getName());
	}
	
	public static void cmdRemI(player p, String[] args, int id)
	{
		island is = new island(id);
		if (!is.getOwner().p.equals(p.p) && !p.pl.isOp())
		{
			p.pl.sendMessage("Вы не глава тут");
			return ;
		}
		for (String s : is.getMembers())
		{
			if (s.equals(args[1]))
			{
				player pl = new player(args[1]);
				if (pl.pl.isOnline())
				{
					if (is.inIsland(pl.pl.getLocation()))
						way24.tp(p.pl, way24.spawn);
					pl.pl.sendMessage("Вас выгнали с острова " + Color.col1 + is.getName());
				}
				pl.setIs(-1);
				is.removeMember(args[1]);
				p.pl.sendMessage("Вы выгнали игрока " + Color.col1 + args[1] + Color.jast + " с острова " + Color.col1 + is.getName());
				return ;
			}
		}
		p.pl.sendMessage("Игрока " + Color.col1 + args[1] + Color.jast + " нет в участниках региона " + Color.col1 + is.getName());
	}
	
	public static void cmdInfo(player p)
	{
		for (String s : way24.islands.getKeys(false))
		{
			island is = new island(s);
			if (!is.free())
				continue ;
			if (is.inIsland(p.pl.getLocation()))
			{ 
				int	len = 10;
				p.pl.sendMessage(getstr("Имя :", Color.col0 + is.getName(), len));
				p.pl.sendMessage(getstr("Глава :", Color.col1 + is.getOwner().p, len));
				p.pl.sendMessage(getstr("Размер :", Color.col1 + "" + (is.size() * 2 + 1) * (is.size() * 2 + 1) + " (" + Color.col0 + (is.size() * 2 + 1) + Color.col1 + " x " + Color.col0 + (is.size() * 2 + 1) + Color.col1 + ")", len));
				p.pl.sendMessage(getstr("Биом :", Color.col1 + is.getBiome(), len));
				p.pl.sendMessage(getstr("Центр :", Color.col0 + "x: " + Color.col1 + is.getCenter().getBlockX() + " " + Color.col0 + "z: " + Color.col1 + is.getCenter().getBlockZ(), len));
				if (is.getMembers().size() > 0)
				{
					p.pl.sendMessage("Участники :");
					for (String name : is.getMembers())
						p.pl.sendMessage("   " + Color.col1 + name);
				}
				p.pl.sendMessage(getstr("Id :", Color.col1 + "" + is.id, len));
			    SimpleDateFormat formatForDateNow = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
				p.pl.sendMessage(getstr("Создан :", Color.col1 + "" + formatForDateNow.format(new Date(is.first())), len));
				p.pl.sendMessage(getstr("Последний вход :", Color.col1 + "" + formatForDateNow.format(new Date(is.lastlogin())), len));
				p.pl.sendMessage(getstr("Уровень :", Color.col1 + "" + new DecimalFormat("###.##").format(Math.sqrt(is.getLvl())), len));
				return ;
			}
		}
		p.pl.sendMessage("Вы не на острове");
	}
	
	public static String getstr(String s1, String s2, int len)
	{
		int	x;
		
		x = -1;
		s1 += "\n";
		while (++x < 3)
			s1 += " ";
		s1 += s2;
		return s1;
	}
	
	public static void cmdBiome(player p, String[] args)
	{
		if (p.getIs() == -1)
		{
			p.pl.sendMessage("У вас нет острова, чтобы создать " + Color.col1 + "/is create");
			return ;
		}
		island is = new island(p.getIs());
		if (!is.getOwner().p.equals(p.p) && !p.pl.isOp())
		{
			p.pl.sendMessage("Вы не глава тут");
			return ;
		}
		Biome b = null;
		try
		{
			b = Biome.valueOf(args[1]);
		} catch(Exception ex)
		{
			String str = "";
			p.pl.sendMessage("Такого биома нет");
			for (Biome s : Biome.values())
			{
				str += s + "" + Color.c(7) + ", " + Color.jast;
			}
			p.pl.sendMessage(str);
			return ;
		}		
		is.setBiom(b);
		p.pl.sendMessage("Вы поставили биом " + Color.col1 + b.name() + Color.jast + ", для отображения биома перезайдите на сервер");
	}
	
	public static void cmdUp(player p)
	{
		
		island is = new island(p.getIs());
		if (is.size() == 128)
		{
			p.pl.sendMessage("У вас макимальный размер острова");
			return ;
		}
		int	x = (is.size() - 15) * 88 + 50;
		if (p.getMoney() < x)
		{
			p.pl.sendMessage("Для увеличения острова с " + Color.col1 + (is.size() * 2 + 1) * (is.size() * 2 + 1) + Color.jast + " блоков² до " +  Color.col1 + ((is.size() + 1) * 2 + 1) * ((is.size() + 1) * 2 + 1) + Color.jast + " блоков² требуется " + Color.col0 + x + Color.col1 + " Скитов");
			p.pl.sendMessage("Баланс : " + Color.col1 + p.getMoney() + Color.jast + " скитов");
			p.pl.sendMessage(Color.col1 + "/shop " + Color.jast + "получить скиты");
			return ;
		}
		p.setMoney(p.getMoney() - x);		
		p.pl.sendMessage("Вы увеличили остров с " + Color.col1 + (is.size() * 2 + 1) * (is.size() * 2 + 1) + Color.jast + " блоков² до " +  Color.col1 + ((is.size() + 1) * 2 + 1) * ((is.size() + 1) * 2 + 1) + Color.jast + " блоков²");
		is.size(is.size() + 1);
	}
	
	public static void cmdRename(player p, String[] args)
	{
		if (p.getIs() == -1)
		{
			p.pl.sendMessage("У вас нет острова, чтобы создать " + Color.col1 + "/is create");
			sound.nosound(p.pl);
			return ;
		}
		island is = new island(p.getIs());
		if (!is.getOwner().p.equals(p.p))
		{
			p.pl.sendMessage(Color.jast + "Вы не владелец региона " + Color.col1 + is.getName());
			sound.nosound(p.pl);
			return ;
		}
		if (args.length < 2)
		{
			p.pl.sendMessage(Color.jast + "Введите новое имя региону " + Color.col1 + "/is rename " + Color.col0 + " <новое имя>");
			sound.nosound(p.pl);
			return ;
		}
		byte[] b = args[1].getBytes(StandardCharsets.UTF_8);
		if (!args[1].equals(new String(b)))
		{
			p.pl.sendMessage("В новом имени запрещенные символы");
			sound.nosound(p.pl);
			return ;
		}
		char	f;
		
		f = 0;
		for (String s : way24.islands.getKeys(false))
		{
			island nis = new island(s);
			if (nis.getName().equalsIgnoreCase(args[1]))
				f = 1;
		}
		if (f != 0)
		{
			p.pl.sendMessage("Такое имя занято");
			sound.nosound(p.pl);
			return ;
		}
		new island(p.getIs()).setName(args[1]);
		for (String s : is.getMembers())
		{
			if (Bukkit.getOfflinePlayer(s).isOnline())
			{
				Bukkit.getPlayer(s).sendMessage(Color.jast + "Ваш остров теперь называется " + Color.col0 + args[1]);
				sound.yessound(Bukkit.getPlayer(s));
			}
		}
		p.pl.sendMessage(Color.jast + "Ваш остров теперь называется " + Color.col0 + args[1]);
		sound.yessound(p.pl);
	}
	
	public static void cmdIs(Player p, String[] args)
	{
		player pl = new player(p);
		if (args.length == 0)
		{
			p.sendMessage(Color.col1 + "/is create " + Color.jast + "создать остров");
			p.sendMessage(Color.col1 + "/is home " + Color.jast + "тп на остров(даже если вы не писали /is sethome)");
			p.sendMessage(Color.col1 + "/is sethome " + Color.jast + "установить точку телепортации на остров");
			p.sendMessage(Color.col1 + "/is add <ник> " + Color.jast + "добавить игрока на остров");
			p.sendMessage(Color.col1 + "/is rem <ник> " + Color.jast + "выгнать игрока с острова");
			p.sendMessage(Color.col1 + "/is info " + Color.jast + "информация об острове");
			p.sendMessage(Color.col1 + "/is up " + Color.jast + "Увеличить остров в каждую сторону на 1 блок");
			p.sendMessage(Color.col1 + "/is rename <новое имя> " + Color.jast + "Переименовывает остров");
			return ;
		}
		if (args.length == 1)
		{
			if (args[0].equalsIgnoreCase("create"))
				newIs(pl, 0);
			if (args[0].equalsIgnoreCase("home"))
				cmdHome(pl);
			if (args[0].equalsIgnoreCase("sethome"))
				cmdsetHome(pl);	
			if (args[0].equalsIgnoreCase("info"))
				cmdInfo(pl);
			if (args[0].equalsIgnoreCase("up"))
				cmdUp(pl);
		}
		if (args[0].equalsIgnoreCase("rename"))
			cmdRename(pl, args);
		if (args.length == 2)
		{
			if (args[0].equalsIgnoreCase("create") && args[1].equals("y"))
				newIs(pl, 1);
			if (args[0].equalsIgnoreCase("add"))
				cmdAdd(pl, args);
			if (args[0].equalsIgnoreCase("rem"))
				cmdRem(pl, args);
			if (args[0].equalsIgnoreCase("biome"))
				cmdBiome(pl, args);
			if (args[0].equals("98421342"))
				joinIs(pl, Integer.parseInt(args[1]));	
			if (args[0].equalsIgnoreCase("home"))
				if (new player(p).doIs())
				{
					try
					{
						way24.tp(p, new player(args[1]).getHome());
					} catch(Exception ex) 
					{
						island is = new island(Integer.parseInt(args[1]));
						way24.tp(p, is.getOwner().getHome());
					}
				}			
		}
		if (args.length == 3)
		{
			if (p.isOp())
			{
				if (args[0].equalsIgnoreCase("add"))
					cmdAddI(pl, args, Integer.parseInt(args[2]));
				if (args[0].equalsIgnoreCase("rem"))
					cmdRemI(pl, args, Integer.parseInt(args[2]));
			}
		}
	}
}
