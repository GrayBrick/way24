package mine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import Player.player;

public class mine
{
	public int	id;
	
	public mine(int id)
	{
		this.id = id;
	}
	
	public void open(boolean b)
	{
		way24.way24.Mine.set(id + ".open", b);
	}
	
	public boolean open()
	{
		
		return way24.way24.Mine.getBoolean(id + ".open");
	}
	
	public void inworld(boolean b)
	{
		way24.way24.Mine.set(id + ".inworld", b);
	}
	
	public boolean inworld()
	{
		return way24.way24.Mine.getBoolean(id + ".inworld");
	}
	
	public void timegen(int time)
	{
		way24.way24.Mine.set(id + ".timegen", time);
	}
	
	public int timegen()
	{
		return way24.way24.Mine.getInt(id + ".timegen");
	}
	public void timeStart(long time)
	{
		way24.way24.Mine.set(id + ".timeStart", time);
	}
	
	public long timeStart()
	{
		return way24.way24.Mine.getLong(id + ".timeStart");
	}
	
	public void setMembers(List<String> list)
	{
		way24.way24.Mine.set(id + ".участники", list);
	}
	
	public List<String> getMembers()
	{
		return (way24.way24.Mine.getStringList(id + ".участники"));
	}
	
	public void addMember(String reg)
	{
		List list = getMembers();
		list.add(reg);
		setMembers(list);
	}
	
	public void removeMember(String reg)
	{
		List list = getMembers();
		list.remove(reg);
		setMembers(list);
	}
	
	public void setType(int x)
	{
		way24.way24.Mine.set(id + ".type", x);
	}
	
	public int getType()
	{
		return way24.way24.Mine.getInt(id + ".type");
	}
	
	public void setBlock(int x, int y, int z, String block)
	{
		way24.way24.Mine.set(id + ".block." + x + "." + y + "." + z, block);
	}
	
	public Location elit()
	{
		if (way24.way24.Mine.getString(id + ".elit") == null)
			return null;
		return new Location(Bukkit.getWorld(way24.way24.Mine.getString(id +".elit.world")),way24.way24.Mine.getDouble(id +".elit.x"),way24.way24.Mine.getDouble(id +".elit.y"),way24.way24.Mine.getDouble(id +".elit.z"));
	}
	public void elit(Location loc) {
		if (loc == null)
		{
			way24.way24.Mine.set(id +".elit", null);
			return ;
		}
		way24.way24.Mine.set(id +".elit.world", loc.getWorld().getName());
		way24.way24.Mine.set(id +".elit.x", loc.getX());
		way24.way24.Mine.set(id +".elit.y", loc.getY());
		way24.way24.Mine.set(id +".elit.z", loc.getZ());
	}
	
	public String getBlock(int x, int y, int z)
	{
		return way24.way24.Mine.getString(id + ".block." + x + "." + y + "." + z);
	}
	
	public  synchronized static FileConfiguration getconMine()
	{
		File file = new File("plugins/mine.yml");
		return YamlConfiguration.loadConfiguration(file);
	}
	
	public static void saveconMine()
	{
		File file = new File("plugins/mine.yml");
		try {
			way24.way24.Mine.save(file);
		} catch (Exception e) {}
	}
}
