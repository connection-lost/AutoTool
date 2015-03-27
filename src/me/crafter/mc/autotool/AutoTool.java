package me.crafter.mc.autotool;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoTool extends JavaPlugin {

	public final Logger logger = Logger.getLogger("Mincraft");
	public final ToolListener pl = new ToolListener();
	

    public void onEnable(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this.pl, this);
    	new ToolStorage();
    }
 

    public void onDisable() {
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	
    	if (!(sender instanceof Player)){
    		return false;
    	}
    	
    	Player p = (Player)sender;
    	
    	if (cmd.getName().equals("autotool")){
    		if (pl.toggle(p)){
    			p.sendMessage(ChatColor.YELLOW + "[AutoTool] " + ChatColor.GREEN + "�㿪�����Զ����߹���~��");
    		} else {
    			p.sendMessage(ChatColor.YELLOW + "[AutoTool] " + ChatColor.RED + "��ر����Զ����߹���~��");
    		}
    	}
    	
    	return true;
    }

	
}
