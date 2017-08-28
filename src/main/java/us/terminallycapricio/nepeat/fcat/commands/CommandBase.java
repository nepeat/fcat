package us.terminallycapricio.nepeat.fcat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class CommandBase {

    public String desc = "";

    public abstract void onCommand(CommandSender sender, Command cmd, String label, String[] args);

    public abstract List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args);

}