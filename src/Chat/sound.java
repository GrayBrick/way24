package Chat;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class sound
{
	public static void psound(int sound, Player p)
	{
		p.playSound(p.getLocation(), Sound.values()[sound], 1, 1);
	}
	
	public static void yessound(Player p)
	{
		psound(208, p);
	}
	public static void nosound(Player p)
	{
		psound(270, p);
	}
	public static void clocksound(Player p)
	{
		psound(298, p);
	}
	public static void joinsound(Player p)
	{
		psound(424, p);
	}
}
