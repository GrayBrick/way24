package way24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import Player.player;

public class Parcur
{
	public static void setRecord(player p)
	{
		String name = p.p;
		way24.Players.set(name + ".parclocB", null);
    	for (String loc : way24.Players.getConfigurationSection(name + ".parcloc").getKeys(false))
    		way24.Players.set(name + ".parclocB." + loc, way24.Players.getString(name + ".parcloc." + loc));
	}
	
	public static ArrayList<player> getTopList()
	{
		ArrayList<player> list = new ArrayList<player>();
		for (String s : way24.Players.getKeys(false))
		{
			player p = new player(s);
			if (p.parcTimeBest() != 0)
				list.add(p);
		}
		Collections.sort(list, new Comparator<player>()
		{
	        public int compare(player o1, player o2) {
	        	if(o1.parcTimeBest() > o2.parcTimeBest())return 1;
	        	if(o1.parcTimeBest() < o2.parcTimeBest())return -1;
	        	return 0;
	        }
		});
		return list;
	}
}
