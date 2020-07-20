package way24;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Chat.message;
import Chat.sound;
import Player.ConditionPlayer;
import Player.player;

public class Pas
{
	
	public static void join(player p)
	{
		ConditionPlayer.joinServer(p);
		p.pl.sendTitle("Добро пожаловать на сервер", Chat.Color.col1 + "Cas" + Chat.Color.col0 + "tro");
		if (p.getpas() == null)
		{
			p.pl.sendMessage("Вы не зарегистрированны");
			p.pl.sendMessage("Для регистрации " + Chat.Color.col1 + "/reg <новый пароль> <повторение пароля>");			
			p.setip(p.pl.getAddress().getHostString());
			p.setOp(false);
			return ;
		}
		p.pl.sendMessage("Вы не автаризованны");
		p.pl.sendMessage("Для авторизации " + Chat.Color.col1 + "/l <пароль>");
	
		BukkitTask rn = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (!p.login())
            		p.pl.kickPlayer("Время ввода вышло, попробуйте бысрее\n\n" + Chat.Color.col1 + "Cas" + Chat.Color.col0 + "tro");
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), 300);
        
	}
	public static void setPas(Player pl, String[] args)
	{
		player p = new player(pl);
		if (p.login())
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("Вы ужe авторизованны");
			return ;
		}
		if (p.getpas() != null)
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("Вы уже зарегистрированны");
			return;
		}			
		if (args.length != 2)
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("Для регистрации " + Chat.Color.col1 + "/reg <новый пароль> <повторение пароля>");
			return ;
		}
		if (!args[0].equals(args[1]))
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("Пароли не совпадают");
			return ;
		}
		if (args[0].length() < 6)
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("Пароль должен быть " + Chat.Color.col1 + "минимум" + Chat.Color.jast + " из " + Chat.Color.col1 + 7 + Chat.Color.jast + " символов");
			return ;
		}
		p.setpas(args[0]);
		p.pl.sendMessage(Chat.Color.col0 + "Вы успешно зарегистрировались");
		ConditionPlayer.postReg(p);
	}
	
	public static void setLog(Player pl, String[] args)
	{
		player p = new player(pl);
		if (p.login())
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("Вы ужe авторизованны");
			return ;
		}
		if (p.getpas() == null)
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("Вы еще не зарегистрированны");
			p.pl.sendMessage("Для регистрации " + Chat.Color.col1+ "/reg <новый пароль> <повторение пароля>");
			return;
		}			
		if (args.length != 1)
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("Для авторизации " + Chat.Color.col1 + "/l < пароль>");
			return ;
		}
		if (!args[0].equals(p.getpas()))
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("Пароль не верный");
			return ;
		}
		p.lastlogin(new Date().getTime());
		p.pl.sendMessage(Chat.Color.col0 + "Вы успешно авторизовались");
		ConditionPlayer.login(p);
	}
}
