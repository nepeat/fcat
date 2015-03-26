package us.terminallycapricio.nepeat.fcat.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class CommandBase {

	public String desc = "";

	public abstract void onCommand(CommandSender sender, Command cmd, String label, String[] args);

	public abstract List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args);

}