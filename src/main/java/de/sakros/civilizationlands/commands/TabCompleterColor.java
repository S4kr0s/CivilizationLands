package de.sakros.civilizationlands.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class TabCompleterColor implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("color")) {
            if(commandSender instanceof Player){
                if(strings.length <= 1){
                    List<String> list = new ArrayList<>();
                    list.add("Lila");
                    list.add("Pink");
                    list.add("Rot");
                    list.add("Orange");
                    list.add("Gelb");
                    list.add("Grün");
                    list.add("Dunkelgrün");
                    list.add("Türkis");
                    list.add("Hellblau");
                    list.add("Blau");
                    list.add("Dunkelblau");
                    list.add("Grau");
                    list.add("Dunkelgrau");
                    list.add("Schwarz");
                    list.add("Weiß");
                    list.removeIf(str -> (!str.toLowerCase().startsWith(strings[0].toLowerCase())));
                    Collections.sort(list);
                    return list;
                }
            }
        }
        return null;
    }
}
