package us.terminallycapricio.nepeat.fcat.events;

import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import us.terminallycapricio.nepeat.fcat.fcatMain;

public class BlockEvents implements Listener {
	
	private final fcatMain plugin;
	
	private Random r = new Random();
	
	public BlockEvents(fcatMain plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}

	private static Material[] randomblocks = new Material[] {
		Material.SNOW_BLOCK,
		Material.ICE,
		Material.GLOWSTONE,
		Material.GLASS
	};
	
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
		tnt.setFuseTicks(r.nextInt(5));
		float force = r.nextFloat() * (6.0F - 4.0F) + 6.0F; // (max - min) + min
		tnt.setIsIncendiary(r.nextBoolean());
		tnt.setYield(force);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();

		Material explodematerial = Material.valueOf(this.plugin.getConfig().getString("explodematerial"));

		if (player.getItemInHand().getType() == Material.EGG && block.getType() == explodematerial) explode(block, true);
	}

	@EventHandler
	public void onExplosion(EntityExplodeEvent event) {
		event.setCancelled(true);

		List<Block> blocks = event.blockList();

		Material explodematerial = Material.valueOf(this.plugin.getConfig().getString("explodematerial"));

		// Explosions
		for (Block block : blocks) {
			if (block.getType() == explodematerial) {
				event.setYield(0.0f); // Let's not drop any blocks to reduce lag.
				explode(block, false);
			}
			block.setType(randomblocks[r.nextInt(randomblocks.length)]);
		}
	}
	
	@EventHandler
	public void onMelt(BlockFadeEvent event) {
		if (event.getBlock().getType() == Material.ICE) {
			event.setCancelled(true);
		}
	}
}
