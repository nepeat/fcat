package us.terminallycapricio.nepeat.fcat;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import us.terminallycapricio.nepeat.fcat.commands.CommandHandler;
import us.terminallycapricio.nepeat.fcat.events.EventListener;

public final class fcatMain extends JavaPlugin implements Listener {

    public static fcatMain plugin;

    @Override
    public void onEnable() {
        // TODO Insert logic to be performed when the plugin is enabled

        plugin = this;

        getLogger().info("onEnable has been invoked!");

        // Event hooks
        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);

        // Command hooks
        this.getCommand("fcat").setExecutor(new CommandHandler(this));

        // Config
        loadConfig();
    }

    public void loadConfig() {
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // TODO Insert logic to be performed when the plugin is disabled
        plugin = null;
        getLogger().info("onDisable has been invoked!");
        this.saveConfig();
    }

}