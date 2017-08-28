package us.terminallycapricio.nepeat.fcat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.TNTPrimed;
import us.terminallycapricio.nepeat.fcat.fcatMain;

import java.util.LinkedList;
import java.util.List;

public class CommandButcher extends CommandBase {

    public CommandButcher() {
        this.desc = "Removes most entities from the world.";
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // World list
        List<World> worlds = Bukkit.getServer().getWorlds();

        // Counter
        int purged = 0;

        // Egg entity
        EntityType eggentity = EntityType.valueOf(fcatMain.plugin.getConfig().getString("eggtype"));

        for (World world : worlds) {
            List<Entity> entities = world.getEntities();
            for (Entity entity : entities) {
                if (entity instanceof TNTPrimed || entity instanceof Monster || entity.getType() == eggentity) {
                    entity.remove();
                    purged += 1;
                }
            }
        }
        Bukkit.getServer().broadcastMessage(ChatColor.RED + String.format("[fcat] %s purged from all worlds.", purged));

        return;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new LinkedList<String>();
    }

}
