package us.terminallycapricio.nepeat.fcat;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
public final class fcatMain extends JavaPlugin implements Listener {
	
	public static void registerEvents(Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			plugin.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}
	
    @Override
    public void onEnable() {
        // TODO Insert logic to be performed when the plugin is enabled
    	getLogger().info("onEnable has been invoked!");
    	
    	// Event hooks
    	registerEvents(this, new EventListener(this));
    	getServer().getPluginManager().registerEvents(this, this);
    	
    	// Command hooks
    	this.getCommand("killtnt").setExecutor(new CommandHandler(this));
    	this.getCommand("getchance").setExecutor(new CommandHandler(this));
    	this.getCommand("setchance").setExecutor(new CommandHandler(this));

    	// Config
    	loadConfig();
    }
 
    public void loadConfig() {
    	this.saveDefaultConfig();
    }
    
    @Override
    public void onDisable() {
        // TODO Insert logic to be performed when the plugin is disabled
    	getLogger().info("onDisable has been invoked!");
    	this.saveConfig();
    }

}