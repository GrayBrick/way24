package Island;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import Player.player;
import way24.way24;

public class island
{
	public static int	id;
	
	public island(int id)
	{
		this.id = id;
	}
	
	public island(String id)
	{
		this.id = Integer.parseInt(id);
	}
	
	public island()
	{
		int	x = 1;
		while (true)
		{
			if (!new island(x).free())
			{
				id = x;
				break;
			}
			x++;
		}
	}
	
	public void setName(String arg)
	{
		way24.islands.set(id + ".имя", arg);
	}
	
	public long first() {
		return way24.islands.getLong(id+".first");
	}
	public void first(long b) {
		way24.islands.set(id+".first", b);
	}
	
	public int getLvl() {
		return getLvlblock();
	}
	
	public int getLvlblock() {
		return way24.islands.getInt(id + ".lvl.block");
	}
	public void setLvlblock(int b) {
		way24.islands.set(id + ".lvl.block", b);
	}
	
	public long lastlogin() {
		return way24.Players.getLong(id+".lastlogin");
	}
	public void lastlogin(long b) {
		way24.Players.set(id+".lastlogin", b);
	}
	
	public String getName()
	{
		if (way24.islands.getString(id + ".имя") == null)
			setName(getOwner().p);
		return way24.islands.getString(id + ".имя");
	}
	
	public void setOwner(player p)
	{
		way24.islands.set(id + ".глава", p.p);
	}
	
	public static player getOwner()
	{
		return (new player(way24.islands.getString(id + ".глава")));
	}
	
	public void setMembers(List<String> list)
	{
		way24.islands.set(id + ".участники", list);
	}
	
	public static List<String> getMembers()
	{
		return (way24.islands.getStringList(id + ".участники"));
	}
	
	public boolean free()
	{
		return (way24.islands.getBoolean(id + ".free"));
	}
	
	public void free(boolean b)
	{
		way24.islands.set(id + ".free", b);
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
	
	public void size(int size)
	{
		way24.islands.set(id + ".размер", size);
	}
	
	public int size()
	{
		return way24.islands.getInt(id + ".размер");
	}
	
	public Location getCenter() {
		if (!way24.islands.contains(id + ".center"))
			return null;
		return new Location(Bukkit.getWorld(way24.islands.getString(id+".center.world")),way24.islands.getDouble(id+".center.x"),way24.islands.getDouble(id+".center.y"),way24.islands.getDouble(id+".center.z"));
	}
	public void setCenter(Location loc) {
		way24.islands.set(id+".center.world", loc.getWorld().getName());
		way24.islands.set(id+".center.x", loc.getX());
		way24.islands.set(id+".center.y", loc.getY());
		way24.islands.set(id+".center.z", loc.getZ());
	}
	
	public static island getIsland(Location loc)
	{
		for (String s : way24.islands.getKeys(false))
		{
			island is = new island(s);
			if (!is.free())
				continue ;
			if (is.inIsland(loc))
				return is;
		}
		return null;
	}
	
	public void setBiom(Biome b)
	{
		Location loc = getCenter();
		
		int	x = loc.getBlockX();
		int	z = loc.getBlockZ();
		int	y = 42;
		
		int i = -1;
		while (++i < 257)
		{
			int k = -1;
			while (++k < 257)
			{
				new Location(way24.worldi, x - 128 + i, 42, z - 128 + k).getBlock().setBiome(b);
			}
		}
		way24.islands.set(id + ".biome", b.name());
	}
	
	public String getBiome()
	{
		return way24.islands.getString(id + ".biome");
	}
	
	public boolean inIsland(Location loc)
	{
		if (!loc.getWorld().equals(way24.worldi))
			return false;
		Location cen = getCenter();
		
		int	x = cen.getBlockX();
		int	z = cen.getBlockZ();
		
		int	size = size();
		
		int	x1 = loc.getBlockX();
		int	z1 = loc.getBlockZ();

		if (x1 < x - size)
			return false;
		if (x1 > x + size)
			return false;
		if (z1 < z - size)
			return false;
		if (z1 > z + size)
			return false;
		return true;
	}
	
	public  synchronized static FileConfiguration getconIs()
	{
		File file = new File("plugins/islands.yml");
		return YamlConfiguration.loadConfiguration(file);
	}
	
	public static void saveconIs()
	{
		File file = new File("plugins/islands.yml");
		try {
			way24.islands.save(file);
		} catch (Exception e) {}
	}
}
