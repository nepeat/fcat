package us.terminallycapricio.nepeat.fcat;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;

public class CommandHandler implements CommandExecutor {
	private final fcatMain plugin;
 
	public CommandHandler(fcatMain plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}
 
	// Parser
	
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if (cmd.getName().equalsIgnoreCase("killtnt")) {
    		purgeTNT();
    		return true;
    	} else if (cmd.getName().equalsIgnoreCase("setchance")) {
    		if (args.length != 1) {
    			sender.sendMessage(ChatColor.RED + "[fcat] Please check your arguments.");
    			return true;
    		}
    		setChance(sender, args[0]);
    		return true;
    	} else if (cmd.getName().equalsIgnoreCase("getchance")) {
    		sender.sendMessage("[fcat] Explosion chance: " + plugin.getConfig().get("explodechance"));
    		return true;
    	}
    	return false;
    }
	
    // Commands
    
    private void purgeTNT() {
    	// World list
		List<World> worlds = Bukkit.getServer().getWorlds();
		
		// Counter
		int purged = 0;

		for (World world : worlds) {
			List<Entity> entities = world.getEntities();
			for (Entity entity : entities) {
				if (entity instanceof TNTPrimed) {
					entity.remove();
					purged += 1;
				}
			}
		}
		Bukkit.getServer().broadcastMessage(ChatColor.RED + String.format("Purged %s TNT!", purged));
    }
  
    private void setChance(CommandSender sender, String arg) {
    	float floatchance;
		try {
			floatchance = Float.parseFloat(arg);
		} catch (NumberFormatException ex) {
			sender.sendMessage(ChatColor.RED + "[fcat] Invalid number!");
			return;
		}
		plugin.getConfig().set("explodechance", floatchance);
		plugin.saveConfig();
		sender.sendMessage(ChatColor.GREEN + String.format("[fcat] Set to %s!", floatchance));
		return;
    }
    
}