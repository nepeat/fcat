package us.terminallycapricio.nepeat.fcat.commands;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import us.terminallycapricio.nepeat.fcat.fcatMain;

public class CommandInfo extends CommandBase {

	public CommandInfo() {
		this.desc = "Gets plugin config info.";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		sender.sendMessage("[fcat] Explosion chance: " + fcatMain.plugin.getConfig().get("explodechance"));
		sender.sendMessage("[fcat] Egg type: " + fcatMain.plugin.getConfig().get("eggtype"));
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		return new LinkedList<String>();
	}

}
