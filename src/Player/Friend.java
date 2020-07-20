package Player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import Chat.Color;
import Chat.sound;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import way24.admin;

public class Friend
{
	public static void invite(player p1, player p2)
	{
		p1.pl.sendMessage(Color.jast + "Игрок " + Color.col0 + p2.p + Color.jast + " отправил вам запрос в друзья");
		ComponentBuilder m = new ComponentBuilder("Да");    	        
        m.color(Color.c1(10));
        m.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/fr 1 1 1 1 1 1 1 1 1 1 " + p2.p));
        m.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Нажми для подтверждения").color(Color.c1(10)).create()));
        ComponentBuilder m1 = new ComponentBuilder(" Нет");
        m1.color(Color.c1(12));
        m1.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/fr 2 1 1 1 1 1 1 1 1 1 " + p2.p));
        m1.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Нажми для отказа").color(Color.c1(12)).create()));
        p1.pl.spigot().sendMessage(new ComponentBuilder(Color.c(15)+"Принять игрока " + Color.c(3) + p2.p + Color.c(15) + " ? ").append(m.create()).append(m1.create()).append(Color.c(15) + " ← жми").create());
	}
	
	public static void cmdFr(player p, String[] args)
	{
		if (admin.Do(p, 5000))
			return ;
		if (args.length == 0)
		{
			sound.yessound(p.pl);
			p.pl.sendMessage(Color.col1 + "/fr + <ник игрока> " + Color.jast + " отправить запрос в друзья игроку");
			p.pl.sendMessage(Color.col1 + "/fr - <ник игрока> " + Color.jast + " убрать игрока из друзей");
		}
		if (args.length == 2)
		{
			if (args[0].equals("+"))
			{
				for (String s : way24.way24.Players.getKeys(false))
				{
					if (s.equals(args[1]))
					{
						player to = new player(s);
						for (String name : to.getFriends())
							if (name.equals(p.p))
							{
								p.pl.sendMessage(Color.col0 + args[1] + " " + Color.jast + " у вас в друзьях");
								sound.nosound(p.pl);
								return ;
							}
						if (to.getI("friend block invite") == 1)
						{
							p.pl.sendMessage(Color.col0 + args[1] + " " + Color.jast + " он не достоин дружить с тобой");
							sound.nosound(p.pl);
							return ;
						}
						if (to.getI("friend block invite") == 2)
						{
							int	f;
							
							f = 0;
							for (String tof : to.getFriends())
							{
								for (String toff : new player(tof).getFriends())
									if (toff.equals(p.p))
									{
										f++;
										break ;
									}
								if (f == 1)
									break ;
							}
							if (f == 0)
							{
								p.pl.sendMessage(Color.col0 + args[1] + " " + Color.jast + " он не достоин дружить с тобой");
								sound.nosound(p.pl);
								return ;
							}
						}
						if (Bukkit.getOfflinePlayer(args[1]).isOnline())
						{
							invite(to, p);
						}
						p.pl.sendMessage(Color.jast + "Вы отправили запрос дружбы игроку " + Color.col0 + args[1]);
						sound.yessound(p.pl);
						return ;	
					}
				}
				p.pl.sendMessage(Color.jast + "Игрок " + Color.col0 + args[0] + " " + Color.jast + " не зарегистрирован");
				sound.nosound(p.pl);
				return ;
			}
		}
	}
}
