package us.terminallycapricio.nepeat.fcat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import us.terminallycapricio.nepeat.fcat.fcatMain;

public class CommandSetChance extends CommandBase {

	public CommandSetChance() {
		this.desc = "Sets sand explosion percentage.";
	}
	
	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	float floatchance;
		try {
			floatchance = Float.parseFloat(args[0]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
			sender.sendMessage(ChatColor.RED + "[fcat] Invalid number!");
			return;
		}
		fcatMain.plugin.getConfig().set("explodechance", floatchance);
		fcatMain.plugin.saveConfig();
		sender.sendMessage(ChatColor.GREEN + String.format("[fcat] Set to %s!", floatchance));
		return;
		
	}

}
