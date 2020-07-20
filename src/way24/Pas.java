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
		p.pl.sendTitle("����� ���������� �� ������", Chat.Color.col1 + "Cas" + Chat.Color.col0 + "tro");
		if (p.getpas() == null)
		{
			p.pl.sendMessage("�� �� �����������������");
			p.pl.sendMessage("��� ����������� " + Chat.Color.col1 + "/reg <����� ������> <���������� ������>");			
			p.setip(p.pl.getAddress().getHostString());
			p.setOp(false);
			return ;
		}
		p.pl.sendMessage("�� �� �������������");
		p.pl.sendMessage("��� ����������� " + Chat.Color.col1 + "/l <������>");
	
		BukkitTask rn = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (!p.login())
            		p.pl.kickPlayer("����� ����� �����, ���������� ������\n\n" + Chat.Color.col1 + "Cas" + Chat.Color.col0 + "tro");
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), 300);
        
	}
	public static void setPas(Player pl, String[] args)
	{
		player p = new player(pl);
		if (p.login())
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("�� ��e �������������");
			return ;
		}
		if (p.getpas() != null)
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("�� ��� �����������������");
			return;
		}			
		if (args.length != 2)
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("��� ����������� " + Chat.Color.col1 + "/reg <����� ������> <���������� ������>");
			return ;
		}
		if (!args[0].equals(args[1]))
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("������ �� ���������");
			return ;
		}
		if (args[0].length() < 6)
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("������ ������ ���� " + Chat.Color.col1 + "�������" + Chat.Color.jast + " �� " + Chat.Color.col1 + 7 + Chat.Color.jast + " ��������");
			return ;
		}
		p.setpas(args[0]);
		p.pl.sendMessage(Chat.Color.col0 + "�� ������� ������������������");
		ConditionPlayer.postReg(p);
	}
	
	public static void setLog(Player pl, String[] args)
	{
		player p = new player(pl);
		if (p.login())
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("�� ��e �������������");
			return ;
		}
		if (p.getpas() == null)
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("�� ��� �� �����������������");
			p.pl.sendMessage("��� ����������� " + Chat.Color.col1+ "/reg <����� ������> <���������� ������>");
			return;
		}			
		if (args.length != 1)
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("��� ����������� " + Chat.Color.col1 + "/l < ������>");
			return ;
		}
		if (!args[0].equals(p.getpas()))
		{
			sound.nosound(p.pl);
			p.pl.sendMessage("������ �� ������");
			return ;
		}
		p.lastlogin(new Date().getTime());
		p.pl.sendMessage(Chat.Color.col0 + "�� ������� ��������������");
		ConditionPlayer.login(p);
	}
}
