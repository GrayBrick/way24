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
		  return "������";
	  }
	  case 1 : {
		  return "�������������";
	  }
	  case 2 : {
		  return "�����������";
	  }
	  case 3 : {
		  return "������������������";
	  }
	  case 4 : {
		  return "������ �� ��������";
	  }
	  case 5 : {
		  return "��������� �������";
	  }
	  case 6 : {
		  return "���������";
	  }
	  case 7 : {
		  return "����";
	  }
	  case 8 : {
		  return "��������� ������";
	  }
	  case 9 : {
		  return "�������";
	  }
	  case 10 : {
		  return "��������� �����������";
	  }
	  case 16 : {
		  return "�������";
	  }
	  case 17 : {
		  return "�������� ����";
	  }
	  case 18 : {
		  return "��� �������������";
	  }
	  case 19 : {
		  return "������";
	  }
	  case 20 : {
		  return "������� ����";
	  }
	  case 21 : {
		  return "������";
	  }
	  case 22 : {
		  return "������� ������";
	  }
	  case 32 : {
		  return "�������������";
	  }
	  case 33 : {
		  return "�������� �������";
	  }
	  case 34 : {
		  return "���������";
	  }
	  case 35 : {
		  return "�����";
	  }
	  case 49 : {
		  return "�����������";
	  }
	  case 61 : {
		  return "������� �����";
	  }
	  case 62 : {
		  return "��������";
	  }
	  }
	  return null;
  }
}