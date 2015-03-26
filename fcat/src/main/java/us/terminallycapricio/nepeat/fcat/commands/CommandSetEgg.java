package us.terminallycapricio.nepeat.fcat.commands;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

import us.terminallycapricio.nepeat.fcat.fcatMain;

public class CommandSetEgg extends CommandBase {

	public CommandSetEgg() {
		this.desc = "Sets egg spawn type.";
	}

	private static EntityType[] bannedentities = new EntityType[] {
		EntityType.ENDER_DRAGON,
		EntityType.ENDER_CRYSTAL,
		EntityType.ENDER_PEARL,
		EntityType.ITEM_FRAME,
		EntityType.ENDER_SIGNAL,
		EntityType.LEASH_HITCH,
		EntityType.ARMOR_STAND,
		EntityType.COMPLEX_PART
	};

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length != 1) {
			sender.sendMessage(ChatColor.RED + "[fcat] Please check your arguments.");
			String msg = ChatColor.YELLOW + "[fcat] Mob Types: ";

			for (EntityType et : EntityType.values()){
				if (et.isSpawnable() && !(Arrays.asList(bannedentities).contains(et))) {
					msg += et.name() + ", ";
				}
			}
			sender.sendMessage(msg);
			return;
		}

		changeEgg(sender, args[0]);
		return;
	}

	private void changeEgg(CommandSender sender, String arg) {

		// Fix casing for enum
		arg = arg.toUpperCase();

		try {
			EntityType eggtype = EntityType.valueOf(arg);
			if (Arrays.asList(bannedentities).contains(eggtype)) {
				throw new IllegalArgumentException("Banned type.");
			}
		} catch (IllegalArgumentException ex) {
			sender.sendMessage(ChatColor.RED + "[fcat] Invalid entity!");
			return;
		}

		// Config saving
		fcatMain.plugin.getConfig().set("eggtype", arg);
		fcatMain.plugin.saveConfig();

		sender.sendMessage(ChatColor.GREEN + String.format("[fcat] Changed egg spawn type to %s!", arg));

		return;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		LinkedList<String> output = new LinkedList<String>();
		String lastarg = "";

		if (args.length > 0) {
			lastarg = args[args.length - 1].toLowerCase();
		}

		for (EntityType et : EntityType.values()){
			if (et.isSpawnable() && !(Arrays.asList(bannedentities).contains(et))) {
				if (et.toString().toLowerCase().startsWith(lastarg)) output.add(et.toString());
			}
		}

		return output;
	}

}
