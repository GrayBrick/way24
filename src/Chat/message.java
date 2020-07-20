package Chat;

import java.util.ArrayList;
import java.util.Date;

import net.minecraft.server.v1_16_R1.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Player.player;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import way24.Learner;
import way24.admin;

public class message
{	
	public static void messageg(player p, Player pl, String em, String place)
	{
		ComponentBuilder m1 = new ComponentBuilder(p.p);
        m1.color(Color.c1(7));
        m1.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,"/m " + p.p + " "));
        m1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Отправить сообщение ").color(Color.c1(7)).append(Color.jast + p.p).create()));
        pl.spigot().sendMessage(new ComponentBuilder(place).append(m1.create()).append(new ComponentBuilder(" : ").event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, null)).color(Color.c1(7)).create()).append(em).create());
	}
	
	public static void messagel(player p, Player pl, String em)
	{
		ComponentBuilder m1 = new ComponentBuilder(p.p);
        m1.color(Color.c1(7));
        m1.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,"/m " + p.p + " "));
        m1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Отправить сообщение ").color(Color.c1(7)).append(Color.jast + p.p).create()));
        pl.spigot().sendMessage(new ComponentBuilder("").append(m1.create()).append(new ComponentBuilder(" : ").event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, null)).color(Color.c1(15)).create()).append(em).create());
	}
	
	public static void privatmessage(Player sender, Player recipient, String em)
	{
		sound.clocksound(recipient);
		ComponentBuilder m1 = new ComponentBuilder(sender.getName() + Color.col1 + " - " + Color.c(7) + recipient.getName());
        m1.color(Color.c1(7));
        m1.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,"/m " + sender.getName() + " "));
        m1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Отправить сообщение ").color(Color.c1(7)).append(Color.jast + sender.getName()).create()));
        recipient.spigot().sendMessage(new ComponentBuilder(Color.col1 + "˻" + Color.col0 + "M" + Color.col1 + "˺ ").append(m1.create()).append(new ComponentBuilder(" : ").event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, null)).event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, null)).color(Color.c1(15)).create()).append(em).create());
        sender.spigot().sendMessage(new ComponentBuilder(Color.col1 + "˻" + Color.col0 + "M" + Color.col1 + "˺ ").append(m1.create()).append(new ComponentBuilder(" : ").event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, null)).event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, null)).color(Color.c1(15)).create()).append(em).create());
	}
	
	public static void mmes(Player p, String[] args)
	{
		if (admin.Do(new player(p), 3000))
			return ;
		if (args.length < 2)
		{
			p.sendMessage("Используй " + Color.col1 + "/m <ник> <сообщение>");
			sound.nosound(p);
			return ;
		}
		if (args[0].equalsIgnoreCase("Дмитрий"))
		{
			player pl = new player(p);
			if (pl.learnStade() == 7)
			{
				if (args.length != 2)
				{
					Learner.msend(pl, Color.c(6) + "Я не вижу твоего баланса");
					return ;
				}
				try
				{
					Integer.parseInt(args[1]);
				} catch(Exception ex)
				{
					Learner.msend(pl, Color.c(6) + "Я не вижу тут числа");
					return ;
				}
				if (Integer.parseInt(args[1]) != pl.getMoney())
				{
					Learner.msend(pl, Color.c(6) + "Проверь баланс еще разок");
					return ;
				}
				pl.learnStade(8);
				Learner.msend(pl, Color.c(6) + "Теперь ты знаешь почти все");
				
				int	time;
				
				time = 60;
		        
		        BukkitTask r0 = new BukkitRunnable()
				{
		            @Override
		            public void run()
		            {
		            	if (pl.learnStade() == 8)
		            	{
		            		pl.pl.sendMessage("Игрок " + Learner.name + Color.jast + " перевел вам " + Color.col1 + 100 + Color.jast + " скитов");
		            		pl.setMoney(pl.getMoney() + 100);
		            	}
		            	cancel();
		            }
		        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
		        
		        time += 60;
		        
		        BukkitTask r1 = new BukkitRunnable()
				{
		            @Override
		            public void run()
		            {
		            	if (pl.learnStade() == 8)
		            		Learner.msend(pl, Color.c(6) + "Как только добавится что-то новое, я тебя об этом оповещу");
		            	cancel();
		            }
		        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
		        
		        time += 60;
		        
		        BukkitTask r2 = new BukkitRunnable()
				{
		            @Override
		            public void run()
		            {
		            	if (pl.learnStade() == 8)
		            		Learner.msend(pl, Color.c(6) + "До встречи, и знай, мы еще увидимся!");
		            	cancel();
		            }
		        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
		        
		        time += 60;
		        
		        BukkitTask r3 = new BukkitRunnable()
				{
		            @Override
		            public void run()
		            {
		            	if (pl.learnStade() == 8)
		            		Learner.msend(pl, Color.c(6) + "Приятной игры!");
		            	cancel();
		            }
		        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
			}
			else
				Learner.msend(pl, Color.c(6) + "Давай потом, я сейчас занят другим новичком");
			return ;
		}
		for (Player pl : Bukkit.getOnlinePlayers())
		{
			if (pl.getName().equals(args[0]))
			{
				String	msg = "";
				int		x = 0;
				while (++x < args.length)
				{
					msg += args[x];
					msg += " ";
				}
				if (new player(args[0]).pl.equals(p))
					return ;
				privatmessage(p, pl, msg);
				return ;
			}
		}
		p.sendMessage("Игрок " + Color.col1 + args[0] + Color.jast + " не в игре");
		sound.nosound(p);
	}
	
	public static void sendM(player player, String em)
	{	
		if (!player.login())
			return ;
		if (em.getBytes()[0] == 33 && em.length() != 1)
		{
			String mes;
			char[] m = em.toCharArray();
			int	x = 0;
			mes = "";
			for (char ch : m)
				if (x++ != 0)
					mes += ch;
			String place = "";
			if (player.pl.getWorld().equals(way24.way24.world))
				place = Color.c(15) + "˻" + Color.c(7) + "S" + Color.c(15) + "˺ ";
			if (way24.way24.worldsPveMine.contains(player.pl.getWorld()))
				place = Color.c(15) + "˻" + Color.c(7) + "M" + Color.c(15) + "˺ ";
			if (player.pl.getWorld().equals(way24.way24.worldi))
				place = Color.c(15) + "˻" + Color.c(7) + "I" + Color.c(15) + "˺ ";
			for (Player pl : Bukkit.getOnlinePlayers())
				messageg(player, pl, mes, place);
			return ;
		}
		Location loc1  = player.pl.getLocation();
		for (Player pl : Bukkit.getOnlinePlayers())
		{
			Location loc  = pl.getLocation();
			if (loc.getWorld().equals(loc1.getWorld()) && loc1.distance(loc) < 200)
				messagel(player, pl, em);
		}	
	}
	
	public static void reloadheader(Player p)
	{
		EntityPlayer ep = ((CraftPlayer) p).getHandle();
		p.setPlayerListFooter("играют : " + Color.col0 + Bukkit.getOnlinePlayers().size() + Color.jast + "  пинг : " + Color.col0 + ep.ping);
		int	x = 2;
		int	add = x;
		int	col = (int) (Math.random() * 15);
		ArrayList<String> way24 = new ArrayList<String>();
		way24.add("﴿  ⧼  C  A  S  T  R  O  ⧽  ﴾");
		way24.add(Color.c(col) + "﴿" + Color.jast + "  ⧼  C  A  S  T  R  O  ⧽  ﴾");
		way24.add("﴿" + Color.c(col) + "  ⧼"  + Color.jast +  "  C  A  S  T  R  O  ⧽  ﴾");
		way24.add("﴿  ⧼" + Color.c(col) + "  C"  + Color.jast +  "  A  S  T  R  O  ⧽  ﴾");
		way24.add("﴿  ⧼  C" + Color.c(col) + "  A"  + Color.jast +  "  S  T  R  O  ⧽  ﴾");
		way24.add("﴿  ⧼  C  A" + Color.c(col) + "  S"  + Color.jast +  "  T  R  O  ⧽  ﴾");
		way24.add("﴿  ⧼  C  A  S" + Color.c(col) + "  T"  + Color.jast +  "  R  O  ⧽  ﴾");
		way24.add("﴿  ⧼  C  A  S  T" + Color.c(col) + "  R"  + Color.jast +  "  O  ⧽  ﴾");
		way24.add("﴿  ⧼  C  A  S  T  R" + Color.c(col) + "  O"  + Color.jast +  "  ⧽  ﴾");
		way24.add("﴿  ⧼  C  A  S  T  R  O" + Color.c(col) + "  ⧽"  + Color.jast +  "  ﴾");
		way24.add("﴿  ⧼  C  A  S  T  R  O  ⧽" + Color.c(col) + "  ﴾");
		way24.add("﴿  ⧼  C  A  S T  R  O  ⧽  ﴾");
		way24.add("﴿  ⧼  C  A S T R  O  ⧽  ﴾");
		way24.add("﴿  ⧼  C A S T R O  ⧽  ﴾");
		way24.add("﴿  ⧼ C A S T R O ⧽  ﴾");
		way24.add("﴿ ⧼ C A S T R O ⧽ ﴾");
		way24.add("﴿ ⧼ C A ST R O ⧽ ﴾");
		way24.add("﴿ ⧼ C ASTR O ⧽ ﴾");
		way24.add("﴿ ⧼ CASTRO ⧽ ﴾");
		way24.add("﴿ ⧼CASTRO⧽ ﴾");
		way24.add("﴿⧼CASTRO⧽﴾");
		for (String s : way24)
		{
			BukkitTask rn = new BukkitRunnable()
			{
				@Override
				public void run()
				{
					p.setPlayerListHeader(s);
					cancel();
				}
			}.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), x);
			x += add;
		}
		int	g = x;
		BukkitTask rn = new BukkitRunnable()
		{
			@Override
			public void run()
			{
				if (p.isOnline())
					reloadheader(p);
				cancel();
			}
		}.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), 600);
		for (String s : way24)
		{
			BukkitTask r = new BukkitRunnable()
			{
				@Override
				public void run()
				{
					p.setPlayerListHeader(s);
					cancel();
				}
			}.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), g + x);
			x -= add;
		}
	}
}