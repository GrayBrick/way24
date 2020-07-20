package Money;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Chat.Color;
import Player.player;
import way24.Learner;
import way24.admin;

public class money
{
	public static void cmdMoney(Player p, String[] args)
	{
		if (admin.Do(new player(p), 1500))
			return ;
    	if (args.length == 3)
    	{
    		if (args[0].equalsIgnoreCase("set"))
    		{
    			if (!p.isOp())
    				return ;
    			new player(args[1]).setMoney(Integer.parseInt(args[2]));
    		}
    		if (args[0].equalsIgnoreCase("give"))
    		{
    			for (Player pl : Bukkit.getOnlinePlayers())
    			{
    				player p1 = new player(pl);
    				if (args[1].equals(pl.getName()))
    				{
    					if (p1.p.equals(p.getName()))
        				{
        					p.sendMessage("Ты серьезно?");
        					return ;
        				}
    					if (!p1.login())
    					{
    						p.sendMessage("Он не зарегистрировался");
    						return ;
    					}
    					int	money = 0;
    					try
    					{
    						money = Integer.parseInt(args[2]);
    					} catch(Exception ex)
    					{
    						p.sendMessage(Color.col0 + args[2] + Color.jast + " не число");
    						return ;
    					}
    					if (money > new player(p).getMoney())
    					{
    						p.sendMessage("У вас не достаточно денег");
    						return ;
    					}
    					p.sendMessage("Вы перевели " + Color.col0 + args[1] + Color.col1 + " " + args[2] + Color.jast + " скитов");
    					new player(p).setMoney(new player(p).getMoney() - money);
    					p1.setMoney(p1.getMoney() + money);
    					p1.pl.sendMessage("Игрок " + Color.col0 + p.getName() + Color.jast + " перевел вам " + Color.col1 + money + Color.jast + " скитов");
    					return ;
    				}
    			}
    			p.sendMessage("Игрок " + Color.col1 + args[1] + Color.jast + " не на сервере");
    			return ;
    		}
    	}
    	p.sendMessage("Баланс : " + Color.col1 + new player(p).getMoney() + Color.jast + " скитов");
    	if (new player(p).learnStade() == 4)
    	{
    		new player(p).learnStade(5);
    		
			Learner.msend(new player(p), Color.c(6) + "Не думаю что это было сложно");
			
			int	time;
			
			time = 60;
	        
	        BukkitTask r0 = new BukkitRunnable()
			{
	            @Override
	            public void run()
	            {
	            	if (new player(p).learnStade() == 5)
	            	{
	            		new player(p).pl.sendMessage("Игрок " + Learner.name + Color.jast + " перевел вам " + Color.col1 + 50 + Color.jast + " скитов");
	            		new player(p).setMoney(new player(p).getMoney() + 50);
	            		Learner.money(new player(p));
	            	}
	            	cancel();
	            }
	        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
    	}
	}
}
