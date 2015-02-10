package us.terminallycapricio.nepeat.fcat.commands;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import us.terminallycapricio.nepeat.fcat.fcatMain;

public class CommandHandler implements CommandExecutor {

	private final fcatMain plugin;
	static HashMap<String, CommandBase> commands = new HashMap<String, CommandBase>();
 
	public CommandBase getExecutor(String name) {
		return commands.get(name);
	}
	
	public CommandHandler(fcatMain plugin) {
		// Store the plugin in situations where you need it.
		this.plugin = plugin;
		
		// Initiate commands
		commands.put("help", new CommandHelp());
		commands.put("killtnt", new CommandPurgeTNT());
		commands.put("info", new CommandInfo());
		commands.put("setegg", new CommandSetEgg());
		commands.put("setchance", new CommandSetChance());
	}
 
	// Parser
	
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if (args.length == 0) {
    		getExecutor("help").onCommand(sender, cmd, label, args);
    		return true;
    	}
    	
		if (commands.containsKey(args[0])) {
			getExecutor(args[0]).onCommand(sender, cmd, label, Arrays.copyOfRange(args, 1, args.length));
			return true;
		} else {
			sender.sendMessage(ChatColor.RED + "[fcat] Do /fcat help to see valid commands.");
		}
    	
    	return false;
    }
    
}