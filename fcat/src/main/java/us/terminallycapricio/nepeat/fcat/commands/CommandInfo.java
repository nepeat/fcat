package us.terminallycapricio.nepeat.fcat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import us.terminallycapricio.nepeat.fcat.fcatMain;

public class CommandInfo extends CommandBase {

	public CommandInfo() {
		this.name = "info";
		this.desc = "Gets plugin config info.";
	}
	
	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		sender.sendMessage("[fcat] Explosion chance: " + fcatMain.plugin.getConfig().get("explodechance"));
		sender.sendMessage("[fcat] Egg type: " + fcatMain.plugin.getConfig().get("eggtype"));
	}
}
