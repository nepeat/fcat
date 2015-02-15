package us.terminallycapricio.nepeat.fcat.commands;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import us.terminallycapricio.nepeat.fcat.fcatMain;

public class CommandSetExplode extends CommandBase {

	public CommandSetExplode() {
		this.desc = "Sets block explosion type.";
	}
	
	private static Material[] bannedmaterials = new Material[] {};
	
	@Override
	public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length != 1) {
			sender.sendMessage(ChatColor.RED + "[fcat] Please check your arguments.");
			String msg = ChatColor.YELLOW + "[fcat] Block Types: ";
			
			for (Material mat : Material.values()){
				if (!(Arrays.asList(bannedmaterials).contains(mat))) {
					msg += mat.name() + ", ";
				}
			}
			sender.sendMessage(msg);
			return;
		}

		changeMaterial(sender, args[0]);
		return;
	}
	
    private void changeMaterial(CommandSender sender, String arg) {
    	
    	// Fix casing for enum
    	arg = arg.toUpperCase();
    	
		try {
			Material mattype = Material.valueOf(arg);
			if (Arrays.asList(bannedmaterials).contains(mattype)) {
				throw new IllegalArgumentException("Banned type.");
			}
		} catch (IllegalArgumentException ex) {
			sender.sendMessage(ChatColor.RED + "[fcat] Invalid entity!");
			return;
		}
		
		// Config saving
		fcatMain.plugin.getConfig().set("explodematerial", arg);
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
		
		for (Material mat : Material.values()){
			if (!(Arrays.asList(bannedmaterials).contains(mat))) {
				if (mat.toString().toLowerCase().startsWith(lastarg)) output.add(mat.toString());
			}
		}
		
		return output;
	}
    
}
