package me.crafter.mc.autotool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ToolStorage {
	
	private static Map<Material, List<Material>> info = new LinkedHashMap<Material, List<Material>>();
	
	public ToolStorage(){
		
		final Material[] pick = {Material.STONE, Material.COBBLESTONE, Material.SANDSTONE, Material.RAILS, Material.POWERED_RAIL, Material.DOUBLE_STEP, 
				Material.BRICK, Material.MOSSY_COBBLESTONE, Material.FURNACE, Material.DETECTOR_RAIL, Material.COBBLESTONE_STAIRS, Material.STONE_PLATE,
				Material.ICE, Material.NETHERRACK, Material.CLAY_BRICK, Material.BRICK_STAIRS, Material.NETHER_BRICK, Material.NETHER_BRICK_STAIRS, 
				Material.NETHER_BRICK_STAIRS, Material.ENDER_STONE, Material.REDSTONE_LAMP_ON, Material.REDSTONE_LAMP_OFF, Material.GLOWSTONE, Material.COBBLE_WALL, 
				Material.QUARTZ_ORE, Material.QUARTZ_BLOCK, Material.QUARTZ_STAIRS, Material.ACTIVATOR_RAIL, Material.DISPENSER, Material.HARD_CLAY, Material.COAL_BLOCK, 
				Material.PACKED_ICE, Material.COAL_ORE, Material.IRON_ORE, Material.IRON_BLOCK};
		final Material[] stone = {Material.EMERALD_ORE, Material.EMERALD_BLOCK, Material.ANVIL, Material.IRON_FENCE};
		final Material[] iron = {Material.GOLD_ORE, Material.IRON_BLOCK, Material.DIAMOND_ORE, Material.DIAMOND_BLOCK, Material.REDSTONE_ORE, Material.REDSTONE_BLOCK};
		final Material[] diamond = {Material.OBSIDIAN, Material.BEACON, Material.ENCHANTMENT_TABLE};
		
		final Material[] spade = {Material.GRASS, Material.DIRT, Material.SAND, Material.GRAVEL, Material.SNOW, Material.CLAY, Material.SOUL_SAND, Material.MYCEL};
		
		final Material[] axe = {Material.WOOD, Material.LOG, Material.NOTE_BLOCK, Material.PISTON_STICKY_BASE, Material.PISTON_BASE, Material.BOOKSHELF, 
				Material.WOOD_STAIRS, Material.CHEST, Material.WORKBENCH, Material.LADDER, Material.WOOD_PLATE, Material.JUKEBOX, Material.FENCE, Material.FENCE_GATE, 
				Material.LOG_2
				
		};
		
		final Material[] shears = {Material.WEB, Material.LEAVES, Material.LEAVES_2, Material.WOOL};
		
		List<Material> picker = new ArrayList<Material>();
		picker.addAll(Arrays.asList(pick));
		info.put(Material.WOOD_PICKAXE, new ArrayList<Material>(picker));
		picker.addAll(Arrays.asList(stone));
		info.put(Material.STONE_PICKAXE, new ArrayList<Material>(picker));
		picker.addAll(Arrays.asList(iron));
		info.put(Material.IRON_PICKAXE, new ArrayList<Material>(picker));
		picker.addAll(Arrays.asList(diamond));
		info.put(Material.DIAMOND_PICKAXE, new ArrayList<Material>(picker));
		info.put(Material.WOOD_SPADE, Arrays.asList(spade));
		info.put(Material.STONE_SPADE, Arrays.asList(spade));
		info.put(Material.IRON_SPADE, Arrays.asList(spade));
		info.put(Material.DIAMOND_SPADE, Arrays.asList(spade));
		info.put(Material.WOOD_AXE, Arrays.asList(axe));
		info.put(Material.STONE_AXE, Arrays.asList(axe));
		info.put(Material.IRON_AXE, Arrays.asList(axe));
		info.put(Material.DIAMOND_AXE, Arrays.asList(axe));
		info.put(Material.SHEARS, Arrays.asList(shears));
	}
	
	public static boolean suitable(Player p, Material block){
		if (p.getItemInHand() == null) return false;
		Material tool = p.getItemInHand().getType();
		if (tool == null) return false;
		if (info.containsKey(tool)){
			return info.get(tool).contains(block);
		} else {
			return false;
		}
	}
	
	public static void recommand(Player p, Material block){
		for (Material mat : info.keySet()){ //loop through all available tools
			if (info.get(mat).contains(block)){ //hit
				for (int x = 0; x < 9; x++){
					if (p.getInventory().getItem(x) != null && p.getInventory().getItem(x).getType() == mat){
						p.getInventory().setHeldItemSlot(x);
						return;
					}
				}
			}
		}
	}

}
