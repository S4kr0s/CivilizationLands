package de.sakros.civilizationlands;

import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

public class Utils {

    public static void sendMessageNormal(Player player, String message){
        String prefix = "&6[&eCivilization&6] &e";
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }

    public static void sendMessageSuccess(Player player, String message){
        String prefix = "&2[&aCivilization&2] &a";
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }

    public static void sendMessageFailed(Player player, String message){
        String prefix = "&4[&cCivilization&4] &c";
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }

    public static void sendMessageActionbar(Player player, String message){
        String prefix = "&2[&aCivilization&2] &a";
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', prefix + message)));
    }

    public static void sendBroadcast(String message){
        String prefix = "&5[&dCivilization&5] &d";
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }
}