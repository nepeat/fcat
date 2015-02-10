package us.terminallycapricio.nepeat.fcat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandHelp extends CommandBase {

	public CommandHelp() {
		this.name = "help";
		this.desc = "This command";
	}
	
	@Override
	public void onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		sender.sendMessage(ChatColor.YELLOW + "fcat help");
		sender.sendMessage(ChatColor.YELLOW + "---------");
		for (CommandBase c : CommandHandler.commands.values()) {
			sender.sendMessage(ChatColor.GREEN +"/fcat "+ChatColor.DARK_GREEN  + c.name + " - "+ ChatColor.WHITE + c.desc);
		}
	}
}