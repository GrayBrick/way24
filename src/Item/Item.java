package Item;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item
{
  @SuppressWarnings("deprecation")
public static ItemStack create(int x, String s)
  {
    ItemStack item = new ItemStack(Material.ACACIA_DOOR);
    ItemMeta meta = item.getItemMeta();
    item.setType(getTypeId(x));
    meta.setDisplayName(s);
    item.setItemMeta(meta);
    return item;
  }
  @SuppressWarnings("deprecation")
public static ItemStack create(int x,byte s)
  {
    ItemStack item = new ItemStack(Material.ACACIA_DOOR);
    item.setType(getTypeId(x));
    item.setDurability(s);
    return item;
  }
  @SuppressWarnings("deprecation")
public static ItemStack create(int x,int am)
  {
    ItemStack item = new ItemStack(Material.ACACIA_DOOR);
    item.setType(getTypeId(x));
    item.setAmount(am);
    return item;
  }
  @SuppressWarnings("deprecation")
  public static ItemStack create(Material m,int am)
    {
      ItemStack item = new ItemStack(m);
      item.setAmount(am);
      return item;
    }
  @SuppressWarnings("deprecation")
public static ItemStack create(int x, String s,int am)
  {
    ItemStack item = new ItemStack(Material.ACACIA_DOOR);
    ItemMeta meta = item.getItemMeta();
    item.setType(getTypeId(x));
    item.setAmount(am);
    meta.setDisplayName(s);
    item.setItemMeta(meta);
    return item;
  }
  @SuppressWarnings("deprecation")
public static ItemStack create(int x,byte am,String s)
  {
    ItemStack item = new ItemStack(Material.ACACIA_DOOR);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(s);
    item.setItemMeta(meta);
    item.setType(getTypeId(x));
    item.setDurability(am);
    return item;
  }
  @SuppressWarnings({ "deprecation" })
public static ItemStack create(int x,byte am,String s,List<String> a)
  {
    ItemStack item = new ItemStack(Material.ACACIA_DOOR);
    ItemMeta meta = item.getItemMeta();
    item.setType(getTypeId(x));
    item.setDurability(am);
    meta.setDisplayName(s);
    meta.setLore(a);
    item.setItemMeta(meta);
    return item;
  }
  @SuppressWarnings("deprecation")
public static ItemStack create(int x,byte am,String s,int a)
  {
    ItemStack item = new ItemStack(Material.ACACIA_DOOR);
    ItemMeta meta = item.getItemMeta();
    item.setType(getTypeId(x));
    item.setDurability(am);
    meta.setDisplayName(s);
    item.setItemMeta(meta);
    item.setAmount(a);
    return item;
  }
  public static ItemStack create(Material m, String s)
  {
    ItemStack item = new ItemStack(m);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(s);
    item.setItemMeta(meta);
    return item;
  }
  @SuppressWarnings({ "deprecation" })
public static ItemStack create(int x, String s, List<String> l) {
    ItemStack item = new ItemStack(Material.ACACIA_DOOR);
    item.setType(getTypeId(x));
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(s);
    meta.setLore(l);
    item.setItemMeta(meta);
    return item;
  }
  public static ItemStack create(Material m, String s,int x, List<String> l) {
	    ItemStack item = new ItemStack(m);
	    ItemMeta meta = item.getItemMeta();
	    item.setAmount(x);
	    meta.setDisplayName(s);
	    meta.setLore(l);
	    item.setItemMeta(meta);
	    return item;
	  }
  @SuppressWarnings({ "deprecation" })
public static ItemStack create(int m, String s,int x, List<String> l) {
	    ItemStack item = new ItemStack(Material.ACACIA_DOOR);
	    item.setType(getTypeId(m));
	    ItemMeta meta = item.getItemMeta();
	    item.setAmount(x);
	    meta.setDisplayName(s);
	    meta.setLore(l);
	    item.setItemMeta(meta);
	    return item;
	  }
  @SuppressWarnings({ "deprecation" })
public static ItemStack create(int m, String s,int x, List<String> l,Enchantment e) {
	    ItemStack item = new ItemStack(Material.ACACIA_DOOR);
	    item.setType(getTypeId(m));
	    ItemMeta meta = item.getItemMeta();
	    item.setAmount(x);
	    meta.setDisplayName(s);
	    meta.setLore(l);
	    item.setItemMeta(meta);
	    item.addUnsafeEnchantment(e, 1);
	    return item;
	  }
  public static ItemStack create(int m,int x, List<String> l,Enchantment e) {
	    ItemStack item = new ItemStack(Material.ACACIA_DOOR);
	    item.setType(getTypeId(m));
	    ItemMeta meta = item.getItemMeta();
	    item.setAmount(x);
	    meta.setLore(l);
	    item.setItemMeta(meta);
	    item.addUnsafeEnchantment(e, 1);
	    return item;
	  }
  public static Material getTypeId(int id) {
	  for(Material x : Material.values()) {
		  if(id==x.getId())return x;
	  }
	  return null;
  }
  public static String getEnchantName(int id) {
	  switch(id) {
	  case 0 : {
		  return "Защита";
	  }
	  case 1 : {
		  return "Огнеупорность";
	  }
	  case 2 : {
		  return "Невесомость";
	  }
	  case 3 : {
		  return "Взрывоустойчивость";
	  }
	  case 4 : {
		  return "Защита от снарядов";
	  }
	  case 5 : {
		  return "Подводное дыхание";
	  }
	  case 6 : {
		  return "Подводник";
	  }
	  case 7 : {
		  return "Шипы";
	  }
	  case 8 : {
		  return "Подводная ходьба";
	  }
	  case 9 : {
		  return "Ледоход";
	  }
	  case 10 : {
		  return "Проклятие несъёмности";
	  }
	  case 16 : {
		  return "Острота";
	  }
	  case 17 : {
		  return "Небесная кара";
	  }
	  case 18 : {
		  return "Бич членистоногих";
	  }
	  case 19 : {
		  return "Отдача";
	  }
	  case 20 : {
		  return "Заговор огня";
	  }
	  case 21 : {
		  return "Добыча";
	  }
	  case 22 : {
		  return "Разящий клинок";
	  }
	  case 32 : {
		  return "Эффективность";
	  }
	  case 33 : {
		  return "Шелковое касание";
	  }
	  case 34 : {
		  return "Прочность";
	  }
	  case 35 : {
		  return "Удача";
	  }
	  case 49 : {
		  return "Откидывание";
	  }
	  case 61 : {
		  return "Везучий рыбак";
	  }
	  case 62 : {
		  return "Приманка";
	  }
	  }
	  return null;
  }
}