package me.crafter.mc.autotool;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ToolListener implements Listener {
	
	private List<UUID> on = new ArrayList<UUID>();
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		if (event.getPlayer().hasPermission("autotool.tool")){
			if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
				Player p = event.getPlayer();
				if (on.contains(p.getUniqueId())){
					Material mat = event.getClickedBlock().getType();
					if (!ToolStorage.suitable(p, mat)){
						ToolStorage.recommand(p, mat);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerCombat(EntityDamageByEntityEvent event){
		if (event.getDamager() instanceof Player){
			Player p = (Player)event.getDamager();
			if (p.hasPermission("autotool.combat")){
				if (p.getItemInHand() == null || !p.getItemInHand().getType().toString().endsWith("SWORD")){
					for (int x = 0; x < 9; x++){
						if (p.getInventory().getItem(x) != null && p.getInventory().getItem(x).getType().toString().endsWith("SWORD")){
							p.getInventory().setHeldItemSlot(x);
							return;
						}
					}
				}
			}
		}
	}
	
	public boolean toggle(Player p){
		if (on.contains(p.getUniqueId())){
			on.remove(p.getUniqueId());
			return false;
		} else {
			on.add(p.getUniqueId());
			return true;
		}
	}
	

}


