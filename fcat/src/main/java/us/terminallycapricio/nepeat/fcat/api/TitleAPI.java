package us.terminallycapricio.nepeat.fcat.api;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
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
