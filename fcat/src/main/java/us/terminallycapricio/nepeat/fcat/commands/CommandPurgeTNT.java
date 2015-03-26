package us.terminallycapricio.nepeat.fcat.commands;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;

public class CommandPurgeTNT extends CommandBase {

	public CommandPurgeTNT() {
		this.desc = "Removes all TNT from the world.";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
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

		return;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		return new LinkedList<String>();
	}

}
