package Player;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import Chat.Color;
import Chat.sound;
import Island.createIs;
import Island.island;
import Traders.Miner;

public class Stat
{
	public static void getState(Player to, player p)
	{
		int					x;
		Inventory			inv;
		List<String>		lore;
		List<String>		lore1;
		String				Head0;
		String				Head1;
		Material			m0;
		Material			m1;
		ChatColor			c0;
		ChatColor			c1;
		int					len;
		SimpleDateFormat	formatForDateNow;
		island				is;
		DecimalFormat		df;
		String				nameIs;
		
		inv = Bukkit.createInventory(null, 9 * 4, Color.c(0) + "Статистика игрока " + Color.c(21) + p.p);		
		lore = new ArrayList<String>();
		lore1 = new ArrayList<String>();
		lore.add(p.getStatDown(0));
		lore1.add(p.getStatDown(1));
		Head0 = p.getStatHead(0);
		Head1 = p.getStatHead(1);
		m0 = Material.values()[p.getStatItem(0)];
		m1 = Material.values()[p.getStatItem(1)];
		x = -1;
		while (++x < 9)
		{
			if (x == 0)
			{
				inv.setItem(9, Item.Item.create(m1, Head0, 1, lore1));
				inv.setItem(18, Item.Item.create(m0, Head1, 1, lore));
			}
			if (x == 8)
			{
				inv.setItem(9 + 8, Item.Item.create(m1, Head0, 1, lore1));
				inv.setItem(18 + 8, Item.Item.create(m0, Head1, 1, lore));
			}
			if (x % 2 == 0)
				inv.setItem(x, Item.Item.create(m0, Head1, 1, lore));
			else
				inv.setItem(x, Item.Item.create(m1, Head0, 1, lore1));
			
			if ((x + 3 * 9) % 2 == 0)
				inv.setItem(x + 3 * 9, Item.Item.create(m0, Head1, 1, lore));
			else
				inv.setItem(x + 3 * 9, Item.Item.create(m1, Head0, 1, lore1));
		}
		
		c0 = Color.c(p.getStatColor(0));
		c1 = Color.c(p.getStatColor(1));
		len = 10;
		formatForDateNow = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
		df = new DecimalFormat("###.##");
		lore.clear();
		
		lore.add(c0 + "Первый вход :");
		lore.add("    " + c1 + formatForDateNow.format(new Date(p.first())));
		lore.add(c0 + "Последний вход :");
		lore.add("    " + c1 + formatForDateNow.format(new Date(p.lastlogin())));
		lore.add(c0 + "Время в игре :");
		lore.add("    " + c1 + Time.Time.time((p.getTimeInGame() + new Date().getTime() - p.lastlogin()) / 1000));
		lore.add(c0 + "Время текущей сессии :");
		lore.add("    " + c1 + Time.Time.time((new Date().getTime() - p.lastlogin()) / 1000));
		
		inv.setItem(10, Item.Item.create(Material.CLOCK, c1 + "Время", 1, lore));
		
		lore.clear();
		nameIs = c1 + "Нет острова";
		is = null;
		if (p.getIs() != -1)
		{
			is = new island(p.getIs());
			nameIs = c1 + "Остров " + is.getName();
			lore.add(c0 + "Глава :");
			lore.add("    " + c1 + is.getOwner().p);
			lore.add(c0 + "Уровень :");
			lore.add("    " + c1 + df.format(Math.sqrt(is.getLvl())));
		}
		
		inv.setItem(13, Item.Item.create(Material.GRASS_BLOCK, nameIs, 1, lore));
		
		lore.clear();
		
		lore.add(c0 + "Лучшее время :");
		lore.add(c1 + "    " + ((double) p.parcTimeBest() / 1000) + Color.jast + " сек.");
		lore.add(c0 + "Количество попыток :");
		lore.add(c1 + "    " + p.parcTry());
		
		inv.setItem(16, Item.Item.create(Material.ORANGE_CONCRETE, c1 + "Паркур", 1, lore));
		
		to.openInventory(inv);
	}
	
	public static void clickStat(InventoryClickEvent e, player p)
	{
		if (p.pl.getOpenInventory().getTitle().split(" ")[0].equals(Color.c(0) + "Статистика"))
		{
			if (e.getClickedInventory().getSize() == 9)
				e.setCancelled(true);
			if (e.getCurrentItem() == null)
				return ;
			String name = e.getCurrentItem().getItemMeta().getDisplayName();
			e.setCancelled(true);
		}
	}
}
