package us.terminallycapricio.nepeat.fcat.events;

import org.bukkit.event.Listener;
import us.terminallycapricio.nepeat.fcat.fcatMain;

public class EventListener implements Listener {

    private final fcatMain plugin;

    public EventListener(fcatMain plugin) {
        this.plugin = plugin; // Store the plugin in situations where you need it.

        // Register our events.
        this.plugin.getServer().getPluginManager().registerEvents(new BlockEvents(this.plugin), this.plugin);
        this.plugin.getServer().getPluginManager().registerEvents(new EntityEvents(this.plugin), this.plugin);
    }

}
