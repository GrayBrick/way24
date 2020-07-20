package Serv;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;

public class serv
{
	public static ArrayList<Entity> nts = new ArrayList<Entity>();
	
	public  synchronized static FileConfiguration getconServer()
	{
		File file = new File("plugins/server.yml");
		return YamlConfiguration.loadConfiguration(file);
	}
	
//	public static void saveNts()
//	{
//		for (Entity)
//		way24.way24.Server.set("nts", nts);
//	}
	
//	public static void getNts()
//	{
//		for (String s : way24.way24.Server.getStringList("nts"))
//		{
//			byte[] b = s.getBytes();
//			UUID u = new UUID(b);
//			nts.add(new UUID(b));
//		}
//	}
	
	public static void saveconServer()
	{
		File file = new File("plugins/server.yml");
		try {
			way24.way24.Server.save(file);
		} catch (Exception e) {}
	}
}
