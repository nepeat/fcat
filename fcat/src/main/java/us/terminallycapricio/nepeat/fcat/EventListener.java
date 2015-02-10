package us.terminallycapricio.nepeat.fcat;

import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;

public class EventListener implements Listener {
	
	private Random r = new Random();
	private static Material explodematerial = Material.SAND;
	
	private final fcatMain plugin;
	 
	public EventListener(fcatMain plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}
	
	private void explode(Block block, Boolean explode) {
		// Get explosion chance from config
		float explodechance = (float) this.plugin.getConfig().getDouble("explodechance");
		
		// Drop if 0
		if (explodechance == 0.00) return; // #fundisabled
		
		// ?? percent chance of exploding. Overridden by explode
		if (r.nextFloat() > explodechance && explode != true) return; // nah, no explosions today
		
		// Spawn TNT
		TNTPrimed tnt = block.getWorld().spawn(block.getLocation().add(0, 1, 0), TNTPrimed.class);
		
		// Change the TNT stats
		tnt.setFuseTicks(10);
		float force = r.nextFloat() * (6.0F - 4.0F) + 6.0F; // (max - min) + min
		tnt.setIsIncendiary(r.nextBoolean());
		tnt.setYield(force);
	}
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
    	Block block = event.getBlock();
    	Player player = event.getPlayer();
    	
    	if (player.getItemInHand().getType() == Material.EGG && block.getType() == explodematerial) explode(block, true);
    }
    
    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
    	List<Block> blocks = event.blockList();
    	
    	// Explosions
    	for (Block block : blocks) {
    		if (block.getType() == explodematerial) {
    			event.setYield(0.0f); // Let's not drop any blocks to reduce lag.
    			explode(block, false);
    		}
    	}
    }
    
    @EventHandler
    public void onEgg(PlayerEggThrowEvent event) {
    	
    	EntityType entity = EntityType.valueOf(this.plugin.getConfig().getString("eggtype"));
    	
    	event.setHatching(true);
    	event.setHatchingType(entity);
 
    	event.setNumHatches((byte) 5);
    }
}
