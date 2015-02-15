package us.terminallycapricio.nepeat.fcat.api;

import java.util.Collection;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.EnumTitleAction;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutTitle;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public class TitleAPI {
    public static void broadcastTitle(String text) {
    	for (Player player : Bukkit.getServer().getOnlinePlayers()) {
    		sendTitle(text, player);
    	}
    	
    }
    
    public static void sendTitle(String text, Player player) {
    	// Encode JSON
    	JSONObject json = new JSONObject();
    	json.put("text", text);
    	
    	// Create title packet
    	IChatBaseComponent chatTitle = ChatSerializer.a(json.toString());
    	PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
    	
    	// Send title packet
    	((CraftPlayer) player.getPlayer()).getHandle().playerConnection.sendPacket(title);
    }
    
}
