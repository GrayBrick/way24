package Player;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Chat.Color;
import mine.mine;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import way24.admin;
import way24.way24;

public class Tp
{
	public static void tptimer(Player p, Player to)
	{		
		new player(p).tploc(p.getLocation());		
		BukkitTask rn = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	Location loc = new player(p).tploc();
            	Location loc1 = p.getLocation();
    			if (loc.getX() == loc1.getX() && loc.getY() == loc1.getY() && loc.getZ() == loc1.getZ())
    			{
    				if (to.getWorld().equals(way24.worldmine))
    				{
    					p.sendMessage(Color.c(3) + to.getName() + Color.c(15) + "в пещерах");
    					return ;
    				}
    				way24.tp(p, to.getLocation());
    			}
    			else
    				p.sendMessage(Color.c(12) + "Телепорт отменен");
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), 20);
	}
	
	public static boolean to(Player p, String[] args)
	{
		if (admin.Do(new player(p), 3000))
			return true;
		if (args.length == 11)
    	{
    		for (Player pl : Bukkit.getOnlinePlayers())
	    	{
	    		if (pl.getName().equalsIgnoreCase(args[10]))
	    		{
	    			if (args[0].equals("2"))
	    			{
	    				if (new player(pl).tp())
	    					pl.sendMessage(Color.c(15) + "Ваш телепорт отменили");
	    				new player(pl).tp(false);
	    				return true;
	    			}
	    			if (!new player(pl).tp() || new Date().getTime() - new player(pl).tptime() > 10000)
	    			{
	    				new player(pl).tptime(new Date().getTime());
	    				p.sendMessage(Color.c(15) + "Он больше не хочет к вам телепортироваться");
	    				return true;
	    			}
	    			pl.sendMessage(Color.c(15) + "Не двигайтесь " + Color.c(3) + "1" + Color.c(15) + " секунду для телепортации");
	    			p.sendMessage(Color.c(15) + "Вы подтвердили запрос игрока " + Color.c(3) + pl.getName());
	    			tptimer(pl, p);
	    			new player(pl).tp(false);
	    			return true;
	    		}
	    	}
    		p.sendMessage(Color.c(7) + "Игрок не в сети");
    		return true;
    	}
    	if (args.length != 1)
    	{
    		p.sendMessage(Color.c(15) + "/to <ник игрока>");
    		return true;
		}	    		
    	for (Player pl : Bukkit.getOnlinePlayers())
    	{
    		if (pl.getName().equalsIgnoreCase(args[0]))
    		{
    			if (new player(pl).parc())
    			{
    				p.sendMessage("Он проходит " + Color.c(3) + "паркур");
    				return true;
    			}
    			p.sendMessage(Color.c(15) + "Вы отправили запрос игроку " + Color.c(3) + pl.getName() + Color.c(15) + "\nждите подтверждения...");
    			new player(p).tp(true);
    			new player(p).tptime(new Date().getTime());
    			ComponentBuilder m = new ComponentBuilder("Да");    	        
    	        m.color(Color.c1(10));
    	        m.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/to 1 1 1 1 1 1 1 1 1 1 " + p.getName()));
    	        m.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Нажми для подтверждения").color(Color.c1(10)).create()));
    	        ComponentBuilder m1 = new ComponentBuilder(" Нет");
    	        m1.color(Color.c1(12));
    	        m1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/to 2 1 1 1 1 1 1 1 1 1 " + p.getName()));
    	        m1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Нажми для отказа").color(Color.c1(12)).create()));
    	        pl.spigot().sendMessage(new ComponentBuilder(Color.c(15)+"Принять игрока " + Color.c(3) + p.getName() + Color.c(15) + " ? ").append(m.create()).append(m1.create()).append(Color.c(15) + " ← жми").create());
    			return true;
    		}
    	}
    	p.sendMessage(Color.c(7) + "Игрок не в сети");
    	return true;
	}
}
