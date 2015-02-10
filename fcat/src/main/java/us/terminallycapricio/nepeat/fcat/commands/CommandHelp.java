package us.terminallycapricio.nepeat.fcat.commands;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandHelp extends CommandBase {

	public CommandHelp() {
		this.desc = "This command";
	}
	
	@Override
	public void onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		sender.sendMessage(ChatColor.YELLOW + "fcat help");
		sender.sendMessage(ChatColor.YELLOW + "---------");
		for (Entry<String, CommandBase> x : CommandHandler.commands.entrySet()) {
			sender.sendMessage(ChatColor.GREEN + "/fcat " + ChatColor.DARK_GREEN  + x.getKey() + ChatColor.WHITE + " - "+ x.getValue().desc);
		}
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		return new LinkedList<String>();
	}

}