package Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import Chat.Color;
import way24.way24;

public class player
{
	public String p;
	public Player pl;
	
	public player(Player p)
	{
		this.p = p.getName();
		this.pl = p;
	}
	public player(String p)
	{
		this.p = p;
		this.pl = Bukkit.getPlayer(p);
	}
	
	
	public void setparcloc(long time, Location loc)
	{
		way24.Players.set(p + ".parcloc." + time, (int) (loc.getX() * 1000) + ":" + (int) (loc.getY() * 1000) + ":" + (int) (loc.getZ() * 1000));
	}
	
	public Boolean seedDoc(int id) {
		return way24.Players.getBoolean(p + ".seedDoc." + id);
	}
	public void seedDoc(int id, Boolean arg1)
	{
		way24.Players.set(p + ".seedDoc." + id, arg1);
	}
	
	
	public boolean getOp()
	{
		return way24.Players.getBoolean(p + ".Op");
	}
	public void setOp(Boolean arg)
	{
		way24.Players.set(p + ".Op", arg);
	}	
	public boolean move() {
		return way24.Players.getBoolean(p + ".движение");
	}
	public void move(boolean b) {
		way24.Players.set(p + ".движение", b);
	}
	
	public boolean root(int x) {
		return way24.Players.getBoolean(p + ".отладка");
	}
	public void root(int x, boolean b) {
		way24.Players.set(p + ".отладка", b);
	}

	public int	getDocMine(int x)
	{
		return way24.Players.getInt(p + ".mineDoc." + x);
	}
	public void	setDocMine(int x, int m)
	{
		way24.Players.set(p + ".mineDoc." + x, m);
	}
	
	public int	getMoney()
	{
		return way24.Players.getInt(p + ".money");
	}
	public void	setMoney(int x)
	{
		way24.Players.set(p + ".money", x);
	}
	public boolean tp() {
		return way24.Players.getBoolean(p + ".tp");
	}
	public void tp(boolean b) {
		way24.Players.set(p + ".tp", b);
	}
	public boolean parc() {
		return way24.Players.getBoolean(p + ".parc");
	}
	public void parc(boolean b) {
		way24.Players.set(p + ".parc", b);
	}
	public long parcTime() {
		return way24.Players.getLong(p + ".parcTime");
	}
	public void parcTime(long b) {
		way24.Players.set(p + ".parcTime", b);
	}
	public long parcTry() {
		return way24.Players.getLong(p + ".parcTry");
	}
	public void parcTry(long b) {
		way24.Players.set(p + ".parcTry", b);
	}
	public long parcTimeBest() {
		return way24.Players.getLong(p + ".parcTimeBest");
	}
	public void parcTimeBest(long b) {
		way24.Players.set(p + ".parcTimeBest", b);
	}
	public long tptime() {
		return way24.Players.getLong(p + ".tptime");
	}
	public void tptime(long b) {
		way24.Players.set(p + ".tptime", b);
	}
	
	public long learnStade() {
		return way24.Players.getLong(p + ".learn");
	}
	public void learnStade(int b) {
		way24.Players.set(p + ".learn", b);
	}
	
	public long first() {
		return way24.Players.getLong(p + ".first");
	}
	public void first(long b) {
		way24.Players.set(p + ".first", b);
	}
	
	public long lastlogin() {
		return way24.Players.getLong(p + ".lastlogin");
	}
	public void lastlogin(long b) {
		way24.Players.set(p + ".lastlogin", b);
	}

	public double timeIs()
	{
		return way24.Players.getDouble(p + ".timeIs");
	}
	public void setTimeIs()
	{
		way24.Players.set(p + ".timeIs", new Date().getTime());
	}
	public boolean doSpawn()
	{
		return way24.Players.getBoolean(p + ".dospawn");
	}
	public void doSpawn(Boolean b)
	{
		way24.Players.set(p + ".dospawn", b);
	}
	public boolean doIs()
	{
		return way24.Players.getBoolean(p + ".dois");
	}
	public void doIs(Boolean b)
	{
		way24.Players.set(p + ".dois", b);
	}
	public int	getIs()
	{
		if (way24.Players.getInt(p + ".is") == 0)
			return -1;
		return way24.Players.getInt(p + ".is");
	}
	public void	setIs(int x)
	{
		way24.Players.set(p + ".is", x);
	}
	public int	getMine()
	{
		if (way24.Players.getInt(p + ".mineid") == 0)
			return -1;
		return way24.Players.getInt(p + ".mineid");
	}
	public void	setMine(int x)
	{
		way24.Players.set(p + ".mineid", x);
	}
	public String getip() {
		return way24.Players.getString(p + ".ip");
	}
	public void setip(String arg1) {
		way24.Players.set(p + ".ip", arg1);
	}
	public String getpas() {
		return way24.Players.getString(p + ".pas");
	}
	public void setpas(String arg1) {
		way24.Players.set(p + ".pas", arg1);
	}
	public Boolean login() {
		return way24.Players.getBoolean(p + ".login");
	}
	public void login(Boolean arg1)
	{
		way24.Players.set(p + ".login", arg1);
	}
	public Boolean inmine() {
		return way24.Players.getBoolean(p + ".mine");
	}
	public void inmine(Boolean arg1)
	{
		way24.Players.set(p + ".mine", arg1);
	}
	public void saveExp()
	{
		way24.Players.set(p + ".инвентарь.опытF", pl.getExp());
		way24.Players.set(p + ".инвентарь.опыт", pl.getLevel());
	}
	public void loadExp()
	{
		pl.setExp((float) way24.Players.getDouble(p + ".инвентарь.опытF"));
		pl.setLevel(way24.Players.getInt(p + ".инвентарь.опыт"));
	}
	public void saveExp(int n)
	{
		way24.Players.set(p + ".инвентарь" + n + ".опытF", pl.getExp());
		way24.Players.set(p + ".инвентарь" + n + ".опыт", pl.getLevel());
	}
	public void loadExp(int n)
	{
		pl.setExp((float) way24.Players.getDouble(p + ".инвентарь" + n + ".опытF"));
		pl.setLevel(way24.Players.getInt(p + ".инвентарь" + n + ".опыт"));
	}
	public Location tploc()
	{
		if (way24.Players.getString(p + ".tploc") == null)
			return null;
		return new Location(Bukkit.getWorld(way24.Players.getString(p + ".tploc.world")),way24.Players.getDouble(p + ".tploc.x"),way24.Players.getDouble(p + ".tploc.y"),way24.Players.getDouble(p + ".tploc.z"));
	}
	public void tploc(Location loc) {
		if (loc == null)
		{
			way24.Players.set(p + ".tploc", null);
			return ;
		}
		way24.Players.set(p + ".tploc.world", loc.getWorld().getName());
		way24.Players.set(p + ".tploc.x", loc.getX());
		way24.Players.set(p + ".tploc.y", loc.getY());
		way24.Players.set(p + ".tploc.z", loc.getZ());
	}
	public void savei() {
			int x = 0;
			for(ItemStack i : pl.getInventory()) {
				
				try {
					way24.Players.set(p + ".инвентарь"+"."+x, i);
					x++;
				}catch(Exception ex) {}
			}
			saveExp();
			way24.Players.set(p + ".GM", pl.getGameMode().getValue());
			if(pl.getFoodLevel()<2) {
				way24.Players.set(p + ".инвентарь.голод", 2);
			} else way24.Players.set(p + ".инвентарь.голод", pl.getFoodLevel());
			if(pl.getHealth()<0.5) {
				way24.Players.set(p + ".инвентарь.жизни", 0.5);
			} else way24.Players.set(p + ".инвентарь.жизни", pl.getHealth());
	  }
	public void loadi() {
		  try {
			  loadExp();
			  pl.setOp(getOp());
			  pl.setGameMode(GameMode.getByValue(way24.Players.getInt(p + ".GM")));
			  pl.setFoodLevel(way24.Players.getInt(p + ".инвентарь.голод"));
			  pl.setHealth(way24.Players.getDouble(p + ".инвентарь.жизни"));
			  for(String s : way24.Players.getConfigurationSection(p + ".инвентарь").getKeys(true)) {
				  try {
					  pl.getInventory().setItem(Integer.parseInt(s), way24.Players.getItemStack(p + ".инвентарь"+"."+Integer.parseInt(s)));
				  }catch(Exception ex) {}
			  }
		  }catch(Exception ex) {}
	  }
	public void savei(int n) {
		int x = 0;
		for(ItemStack i : pl.getInventory()) {
			
			try {
				way24.Players.set(p + ".инвентарь" + n + "."+x, i);
				x++;
			}catch(Exception ex) {}
		}
		saveExp(n);
		way24.Players.set(p + ".GM" + n, pl.getGameMode().getValue());
		if(pl.getFoodLevel()<2) {
			way24.Players.set(p + ".инвентарь" + n + ".голод", 2);
		} else way24.Players.set(p + ".инвентарь" + n + ".голод", pl.getFoodLevel());
		if(pl.getHealth()<0.5) {
			way24.Players.set(p + ".инвентарь" + n + ".жизни", 0.5);
		} else way24.Players.set(p + ".инвентарь" + n + ".жизни", pl.getHealth());
  }
public void loadi(int n) {
	  try {
		  loadExp(n);
		  pl.setOp(getOp());
		  if (way24.Players.getInt(p + ".инвентар" + n + ".жизни") == 0)
		  {
			  pl.setFoodLevel(20);
			  pl.setHealth(20);
		  }
		  else
		  {
			  pl.setFoodLevel(way24.Players.getInt(p + ".инвентар" + n + ".голод"));
			  pl.setHealth(way24.Players.getDouble(p + ".инвентарь" + n + ".жизни"));
		  }
		  pl.setGameMode(GameMode.getByValue(way24.Players.getInt(p + ".GM" + n)));
		  for(String s : way24.Players.getConfigurationSection(p + ".инвентарь" + n).getKeys(true)) {
			  try {
				  pl.getInventory().setItem(Integer.parseInt(s), way24.Players.getItemStack(p + ".инвентарь" + n + "."+Integer.parseInt(s)));
			  }catch(Exception ex) {}
		  }
	  }catch(Exception ex) {}
  }
	public Location getHome() {
		if (!way24.Players.contains(p + ".home"))
			return null;
		return new Location(Bukkit.getWorld(way24.Players.getString(p + ".home.world")),way24.Players.getDouble(p + ".home.x"),way24.Players.getDouble(p + ".home.y"),way24.Players.getDouble(p + ".home.z"),(float)way24.Players.getDouble(p + ".home.yaw"),(float)way24.Players.getDouble(p + ".home.pitch"));
	}
	public void setHome() {
		Location loc = pl.getLocation();
		way24.Players.set(p + ".home.world", loc.getWorld().getName());
		way24.Players.set(p + ".home.x", loc.getX());
		way24.Players.set(p + ".home.y", loc.getY());
		way24.Players.set(p + ".home.z", loc.getZ());
		way24.Players.set(p + ".home.yaw", loc.getYaw());
		way24.Players.set(p + ".home.pitch", loc.getPitch());
	}
	public void setHome(Location loc) {
		way24.Players.set(p + ".home.world", loc.getWorld().getName());
		way24.Players.set(p + ".home.x", loc.getX());
		way24.Players.set(p + ".home.y", loc.getY());
		way24.Players.set(p + ".home.z", loc.getZ());
		way24.Players.set(p + ".home.yaw", loc.getYaw());
		way24.Players.set(p + ".home.pitch", loc.getPitch());
	}
	public Location getLeave() {
		return new Location(Bukkit.getWorld(way24.Players.getString(p + ".выход.world")),way24.Players.getDouble(p + ".выход.x"),way24.Players.getDouble(p + ".выход.y"),way24.Players.getDouble(p + ".выход.z"),(float)way24.Players.getDouble(p + ".выход.yaw"),(float)way24.Players.getDouble(p + ".выход.pitch"));
	}
	public void setLeave() {
		Location loc = pl.getLocation();
		way24.Players.set(p + ".выход.world", loc.getWorld().getName());
		way24.Players.set(p + ".выход.x", loc.getX());
		way24.Players.set(p + ".выход.y", loc.getY());
		way24.Players.set(p + ".выход.z", loc.getZ());
		way24.Players.set(p + ".выход.yaw", loc.getYaw());
		way24.Players.set(p + ".выход.pitch", loc.getPitch());
	}
	
	public void setLeave(Location loc) {
		way24.Players.set(p + ".выход.world", loc.getWorld().getName());
		way24.Players.set(p + ".выход.x", loc.getX());
		way24.Players.set(p + ".выход.y", loc.getY());
		way24.Players.set(p + ".выход.z", loc.getZ());
		way24.Players.set(p + ".выход.yaw", loc.getYaw());
		way24.Players.set(p + ".выход.pitch", loc.getPitch());
	}
	
	public String getStatHead(int ind)
	{
		if (way24.Players.getString(p + ".stat.head." + ind) == null)
		{
			if (ind == 0)
				setStatHead(0, Color.jast + "-\\_/-\\_/-");
			if (ind == 1)
				setStatHead(1, Color.jast + "_/-\\_/-\\_");
		}
		return (way24.Players.getString(p + ".stat.head." + ind));
	}
	
	public void setStatHead(int ind, String head)
	{
		way24.Players.set(p + ".stat.head." + ind, head);
	}
	
	public String getStatDown(int ind)
	{
		if (way24.Players.getString(p + ".stat.down." + ind) == null)
		{
			if (ind == 0)
				setStatDown(0, Color.col1 + "-\\_/-\\_/-");
			if (ind == 1)
				setStatDown(1, Color.col1 + "_/-\\_/-\\_");
		}
		return (way24.Players.getString(p + ".stat.down." + ind));
	}
	
	public int	getStatItem(int ind)
	{
		if (way24.Players.getInt(p + ".stat.item." + ind) == 0)
		{
			if (ind == 0)
				setStatItem(ind, 931);
			if (ind == 1)
				setStatItem(ind, 214);
		}
		return way24.Players.getInt(p + ".stat.item." + ind);
	}
	
	public void setStatItem(int ind, int id)
	{
		way24.Players.set(p + ".stat.item." + ind, id);
	}
	
	public int	getStatColor(int ind)
	{
		if (way24.Players.getInt(p + ".stat.color." + ind) == 0)
		{
			if (ind == 0)
				setStatColor(ind, 15);
			if (ind == 1)
				setStatColor(ind, 3);
		}
		return way24.Players.getInt(p + ".stat.color." + ind);
	}
	
	public void setStatColor(int ind, int id)
	{
		way24.Players.set(p + ".stat.color." + ind, id);
	}
	
	public void setStatDown(int ind, String down)
	{
		way24.Players.set(p + ".stat.down." + ind, down);
	}
	
	public long getTimeInGame()
	{
		return way24.Players.getLong(p + ".time in game");
	}
	
	public void setTimeInGame(long id)
	{
		way24.Players.set(p + ".time in game", id);
	}
	
	public long getTick()
	{
		return way24.Players.getInt(p + ".tick");
	}
	
	public void setTick(int id)
	{
		way24.Players.set(p + ".tick", id);
	}
	
	public long getI(String flag)
	{
		return way24.Players.getInt(p + "." + flag);
	}
	
	public void setI(String flag, int id)
	{
		way24.Players.set(p + "." + flag, id);
	}
	
	public void setFriends(List<String> list)
	{
		way24.Players.set(p + ".участники", list);
	}
	
	public List<String> getFriends()
	{
		return (way24.Players.getStringList(p + ".участники"));
	}
	
	public long getDo()
	{
		return new Date().getTime() - way24.Players.getLong(p + ".do");
	}
	
	public void setDo()
	{
		way24.Players.set(p + ".do", new Date().getTime());
	}
	
	public void addFriend(String reg)
	{
		List list = getFriends();
		list.add(reg);
		setFriends(list);
	}
	
	public void removeFriend(String reg)
	{
		List list = getFriends();
		list.remove(reg);
		setFriends(list);
	}
	
	public int getPortal(int x)
	{
		return way24.Players.getInt(p + ".portal." + x);
	}
	
	public void setPostal(int x, int id)
	{
		way24.Players.set(p + ".portal." + x, id);
	}
	
	public  synchronized static FileConfiguration getconPlayer()
	{
		File file = new File("plugins/Players.yml");
		return YamlConfiguration.loadConfiguration(file);
	}
	
	public static void saveconPlayer()
	{
		File file = new File("plugins/Players.yml");
		try {
			way24.Players.save(file);
		} catch (Exception e) {}
	}
}
