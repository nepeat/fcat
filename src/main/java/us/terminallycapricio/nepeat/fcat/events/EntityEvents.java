package us.terminallycapricio.nepeat.fcat.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;
import us.terminallycapricio.nepeat.fcat.fcatMain;

public class EntityEvents implements Listener {

    private final fcatMain plugin;

    public EntityEvents(fcatMain plugin) {
        this.plugin = plugin; // Store the plugin in situations where you need it.
    }

    @EventHandler
    public void onEgg(PlayerEggThrowEvent event) {

        EntityType entity = EntityType.valueOf(this.plugin.getConfig().getString("eggtype"));

        event.setHatching(true);
        event.setHatchingType(entity);

        if (entity != EntityType.PRIMED_TNT) {
            event.setNumHatches((byte) 5);
        } else {
            event.setNumHatches((byte) 1);
        }
    }
}
