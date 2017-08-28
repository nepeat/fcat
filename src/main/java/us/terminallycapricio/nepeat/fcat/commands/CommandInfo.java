package us.terminallycapricio.nepeat.fcat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import us.terminallycapricio.nepeat.fcat.fcatMain;

import java.util.LinkedList;
import java.util.List;

public class CommandInfo extends CommandBase {

    public CommandInfo() {
        this.desc = "Gets plugin config info.";
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // TODO Auto-generated method stub
        sender.sendMessage("[fcat] Explosion chance: " + fcatMain.plugin.getConfig().get("explodechance"));
        sender.sendMessage("[fcat] Egg type: " + fcatMain.plugin.getConfig().get("eggtype"));
        sender.sendMessage("[fcat] Block type: " + fcatMain.plugin.getConfig().getString("explodematerial"));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new LinkedList<String>();
    }

}
