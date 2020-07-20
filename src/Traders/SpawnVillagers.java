package Traders;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.Villager;
import org.bukkit.entity.WanderingTrader;

import com.google.common.graph.ElementOrder.Type;

import Chat.Color;

import org.bukkit.entity.Villager.Profession;


public class SpawnVillagers
{
	public static WanderingTrader getSkits()
	{
		for (Entity e : way24.way24.world.getNearbyEntities(Skits.loc, 5, 5, 5))
			if (e.getName().equals(Skits.name))
				return ((WanderingTrader) e);
		WanderingTrader skits = (WanderingTrader) way24.way24.world.spawnEntity(Skits.loc, EntityType.WANDERING_TRADER);
		skits.setAI(false);
		skits.setInvulnerable(false);
		skits.setCustomName(Skits.name);
		skits.setCustomNameVisible(true);
		return (skits);
	}
	
	public static Villager getPlants()
	{
		for (Entity e : way24.way24.world.getNearbyEntities(Plants.loc, 5, 5, 5))
			if (e.getName().equals(Plants.name))
			{
				((Villager) e).setProfession(Profession.FARMER);
					return ((Villager) e);
			}
		Villager skits = (Villager) way24.way24.world.spawnEntity(Plants.loc, EntityType.VILLAGER);
		skits.setAI(false);
		skits.setInvulnerable(false);
		skits.setCustomName(Plants.name);
		skits.setCustomNameVisible(true);			
		skits.setVillagerType(Villager.Type.SWAMP);
		skits.setProfession(Profession.FARMER);
		return skits;
	}
	
	public static Villager getMiner()
	{
		for (Entity e : way24.way24.world.getNearbyEntities(Miner.loc, 5, 5, 5))
			if (e.getName().equals(Miner.name))
			{
				((Villager) e).setProfession(Profession.SHEPHERD);
				return (Villager) e;
			}
		Villager skits = (Villager) way24.way24.world.spawnEntity(Miner.loc, EntityType.VILLAGER);
		skits.setAI(false);
		skits.setInvulnerable(false);
		skits.setCustomName(Miner.name);
		skits.setCustomNameVisible(true);			
		skits.setVillagerType(Villager.Type.TAIGA);
		skits.setProfession(Profession.SHEPHERD);
		return skits;
	}
	
	public static ArmorStand getWar()
	{
		for (Entity e : way24.way24.world.getNearbyEntities(new Location(way24.way24.world, -4.5, 5, 21.5), 0.1, 0.1, 0.1))
			if (e.getName().equals(Color.c(8) + "��� �������� �����"))
				return (ArmorStand) e;
		ArmorStand ar = (ArmorStand) way24.way24.world.spawnEntity(new Location(way24.way24.world, -4.5, 5, 21.5), EntityType.ARMOR_STAND);
		ar.setCustomName(Color.c(8) + "��� �������� �����");
		ar.setCustomNameVisible(true);
		ar.setGravity(false);
		ar.setVisible(false);
		ar.setItemInHand(Item.Item.create(Material.DIAMOND_SWORD, 1));
		return ar;
	}
	
	public static ArmorStand getWar1()
	{
		for (Entity e : way24.way24.world.getNearbyEntities(new Location(way24.way24.world, -5.5, 5, 21.5), 0.1, 0.1, 0.1))
			if (e.getName().equals(Color.c(8) + " "))
				return (ArmorStand) e;
		ArmorStand ar1 = (ArmorStand) way24.way24.world.spawnEntity(new Location(way24.way24.world, -5.5, 5, 21.5), EntityType.ARMOR_STAND);
		ar1.setCustomName(Color.c(8) + " ");
		//ar1.setCustomNameVisible(true);
		ar1.setGravity(false);
		ar1.setVisible(false);
		ar1.setItemInHand(Item.Item.create(Material.IRON_SWORD, 1));
		return ar1;
	}
	
	public static void spawn()
	{
		getSkits();		
		getMiner();		
		getPlants();
	}
}
