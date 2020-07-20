package Chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Color
{
	public static ChatColor col0 = c(11);
	public static ChatColor col1 = c(3);
	public static ChatColor jast = c(15);
	
	public static net.md_5.bungee.api.ChatColor c1(int x) {
		return net.md_5.bungee.api.ChatColor.values()[x];
	}
	public static ChatColor c(int x) {
		return ChatColor.values()[x];
	}
	public static void color(Player p, String s) {
		for(ChatColor color : ChatColor.values()) {
			p.sendMessage(color + "��� ����� " + color.ordinal() + " " + s + " " + color.name());
		}
	}
	public static String colorP(int x){
		if(x<10)return c(12)+""+x+"%";
		if(x>=10 && x<30)return c(6)+""+x+"%";
		if(x>=30 && x<60)return c(10)+""+x+"%";
		if(x>=60 && x<100)return c(2)+""+x+"%";
		if(x>=100 && x<150)return c(11)+""+x+"%";
		return c(5)+""+x+"%";
	}
	public static String OC(String s) {
		String name = s;
		String name1 = "";
		for(int x = 0;x<name.length();x++) {
			if(!(x==0 || x==1)) {
				name1=name1+name.split("")[x];
			}
		}
		return name1;
	}
}
