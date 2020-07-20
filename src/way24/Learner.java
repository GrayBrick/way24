package way24;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import Chat.Color;
import Chat.sound;
import Player.player;

public class Learner
{
	public static String	name = Color.c(10) + "˻" + Color.c(14) + "Помощник" + Color.c(10) + "˺ " + Color.c(10) + "Дмитрий";
	
	public static void msend(player p, String msg)
	{
		p.pl.sendMessage(name + Color.jast + " : " + msg);
		sound.clocksound(p.pl);
	}
	
	public static void LearnSrart(player p)
	{
		int	time;
		
		time = 60;
		
		BukkitTask r0 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 0)
            		msend(p, Color.c(6) + "Привет " + Color.c(10) + p.p + Color.c(6) + " , я твой личный помощник");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r1 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 0)
            		msend(p, Color.c(6) + "И я расскажу тебе что есть на этом сервере");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r2 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 0)
            		msend(p, Color.c(6) + "Начнем с создания твоего острова");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r3 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 0)
            		msend(p, Color.c(6) + "Напиши команду " + Color.c(14) + "/is create");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);       
        
        time += 60;
        
        BukkitTask r4 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 0)
            		msend(p, Color.c(6) + "Нажми английскую клавишу " + Color.c(14) + "'T'" + Color.c(6) + " или " + Color.c(14) + "'/'" + Color.c(6) + " для открытия чата");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);    
        
        time += 60;
        
        BukkitTask r5 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 0)
            		msend(p, Color.c(6) + "Твоя награда за выполнение будет " + Color.c(14) + "500 " + Color.c(6) + " скитов, это такая игровая валюта");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r6 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 0 && p.getIs() != -1)
            	{
            		p.learnStade(1);
            		msend(p, Color.c(6) + "Оу, у тебя уже есть остров, держи награду");
            		p.pl.sendMessage("Игрок " + name + Color.jast + " перевел вам " + Color.col1 + 500 + Color.jast + " скитов");
            		p.setMoney(p.getMoney() + 500);
            		Iscreate(p);
            	}
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
	}

	public static void Iscreate(player p)
	{
		if (p.learnStade() != 1)
			return ;
		p.learnStade(1);
		
		int	time = 60;
		
		BukkitTask r2 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 1)
            		msend(p, Color.c(6) + "Твоя следующая задача заключается в том, что ты должен создать " + Color.c(14) + "деревянную кирку");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;		
        
        BukkitTask r3 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 1)
            		msend(p, Color.c(6) + "Твоя награда за выполнение будет " + Color.c(14) + "500 " + Color.c(6) + " скитов");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r4 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 1)
            		msend(p, Color.c(6) + "Сруби немного дерева, сделай верстак, создай палки и кирку");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
	}

	public static void PickCreate(player p)
	{
		if (p.learnStade() != 2)
			return ;
		
		int	time;
		
		time = 60;
        
		BukkitTask r2 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 2)
            		msend(p, Color.c(6) + "Теперь самое время познакомиться с " + Color.c(14) + "пещерами!");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r3 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 2)
            		msend(p, Color.c(6) + "Пещеры тут бывают нескольких видов");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r4 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 2)
            		msend(p, Color.c(6) + "Тебе сейчас доступна только " + Color.c(14) + "обычная " + Color.c(6) + "пещера");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r5 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 2)
            		msend(p, Color.c(6) + "Для входа в нее напиши команду " + Color.c(14) + "/mine");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r6 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 2)
            		msend(p, Color.c(6) + "Выбери там обычную пещеру и зайди в любую доступную");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r7 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 2)
            		msend(p, Color.c(6) + "Твоя главная задача, это добыть " + Color.c(14) + "3 булыжника");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r8 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 2)
            		msend(p, Color.c(6) + "За выполнение ты получишь " + Color.c(14) + 1000 + Color.c(6) + " скитов");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
	}

	public static void GiveCobbl(player p)
	{
		if (p.learnStade() != 3)
			return ;
		
		int	time;
		
		time = 60;
        
		BukkitTask r0 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 3)
            		msend(p, Color.c(6) + "Теперь ты знаешь как входить в пещеры");
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
            		msend(p, Color.c(6) + "Теперь разберемся с островом");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r2 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 3)
            		msend(p, Color.c(6) + "Вернись на остров используя команду " + Color.c(14) + "/is home");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r3 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 3)
            		msend(p, Color.c(6) + "И оставь новую точку возраждения используя команду " + Color.c(14) + "/is sethome");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r4 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 3)
            		msend(p, Color.c(6) + "Награда за выполнение " + Color.c(14) + 300 + Color.c(6) + " скитов");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
	}

	public static void sethome(player p)
	{
		if (p.learnStade() != 4)
			return ;
		
		int	time;
		
		time = 60;
        
		BukkitTask r0 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 4)
            		msend(p, Color.c(6) + "Ты можешь добавлять друзей на остров,\nрасширять его,\nменять биом,\nи даже имя,\nвсе эти действия описанны в " + Color.c(14) + "/is");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r1 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 4)
            		msend(p, Color.c(6) + "Но сейчас ты узнаешь где заработать " + Color.c(14) + "скитов");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r2 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 4)
            		msend(p, Color.c(6) + "На что их тратить и как с ними работать");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r3 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 4)
            		msend(p, Color.c(6) + "Для проверки твоего баланса используй команду " + Color.c(14) + "/money");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r4 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 4)
            		msend(p, Color.c(6) + "Для передачи денег " + Color.c(14) + "/money give <ник> <сумма>");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r5 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 4)
            		msend(p, Color.c(6) + "Проверь баланс и получи награду " + Color.c(14) + 50 + Color.c(6) + " скитов");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
	}

	public static void money(player p)
	{
		if (p.learnStade() != 5)
			return ;
		
		int	time;
		
		time = 60;
        
		BukkitTask r0 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 5)
            		msend(p, Color.c(6) + "Теперь твоя задача посадить пшеницу");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r1 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 5)
            		msend(p, Color.c(6) + "Но ты не думай что это так легко");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r2 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 5)
            		msend(p, Color.c(6) + "Для мотивации скажу что твой приз будет " + Color.c(14) + 3000 + Color.c(6) + " скитов");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r3 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 5)
            		msend(p, Color.c(6) + "Для посадки пшеницы (и не только) тебе нужно купить разрешение на посадку");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r4 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 5)
            		msend(p, Color.c(6) + "Купить его можно в магазине " + Color.c(14) + "/shop огородник");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r5 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 5)
            		msend(p, Color.c(6) + "Зайди в раздел " + Color.c(14) + "'Купить разрешение'" + Color.c(6) + " и купи " + Color.c(14) + "'Разрешение на посадку семян пшеницы'");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r6 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 5)
            		msend(p, Color.c(6) + "Потом зайди в раздел " + Color.c(14) + "'Купить растение'" + Color.c(6) + " и купи " + Color.c(14) + "'семена пшеницы'");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r7 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 5)
            		msend(p, Color.c(6) + "Твоя конечная цель это посадить пшеницу");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
	}

	public static void setwheat(player p)
	{
		if (p.learnStade() != 6)
			return ;
		
		int	time;
		
		time = 60;
        
		BukkitTask r0 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 6)
            		msend(p, Color.c(6) + "Ух, ты уже знаешь почти все");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r1 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 6)
            		msend(p, Color.c(6) + "Но я знаю что тебе рассказать");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r2 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 6)
            		msend(p, Color.c(6) + "Тебе стоит побывать в пещере " + Color.c(14) + "Меса");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r3 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 6)
            		msend(p, Color.c(6) + "Купи у шахтера " + Color.c(14) + "/shop шахтер" + Color.c(6) + " проход в пещеру месы и зайди в нее");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);	
        
        time += 60;
        
        BukkitTask r4 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 6)
            		msend(p, Color.c(6) + "Я буду ждать, приз составит " + Color.c(14) + "1200" + Color.c(6) + " скитов");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);	
	}

	public static void Meca(player p)
	{
		if (p.learnStade() != 7)
			return ;
		
		int	time;
		
		time = 60;
        
		BukkitTask r0 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 7)
            		msend(p, Color.c(6) + "Ты меня замучил");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r1 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 7)
            		msend(p, Color.c(6) + "Вообщем, основная информация есть в " + Color.c(14) + "/info");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r2 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 7)
            		msend(p, Color.c(6) + "Напиши это команду и ты получишь ничего)))");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r3 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 7)
            		msend(p, Color.c(6) + "Но я забыл сказать тебе еще одну вещь");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r4 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 7)
            		msend(p, Color.c(6) + "Тут есть глобальный и локальный чаты");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r5 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 7)
            		msend(p, Color.c(6) + "Пиши в начале сообщения " + Color.c(14) + "'!'" + Color.c(6) + " и тебя услышат все игроки на сервере");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r6 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 7)
            		msend(p, Color.c(6) + "И последнее");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r7 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 7)
            		msend(p, Color.c(6) + "Отправь мне личное сообщение с твоим балансом и я дам тебе еще" + Color.c(14) + " 100 " + Color.c(6) + "скитов");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
        
        time += 60;
        
        BukkitTask r8 = new BukkitRunnable()
		{
            @Override
            public void run()
            {
            	if (p.learnStade() == 7)
            		msend(p, Color.c(14) + "/m <ник> " + Color.c(6) + "для отправки личных сообщений игроку");
            	cancel();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("Hi"), time);
	}
}
