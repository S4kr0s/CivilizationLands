package de.sakros.civilizationlands.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabCompleterJoinTeam implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("jointeam")) {
            if(commandSender instanceof Player){
                if(strings.length <= 1){
                    ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
                    Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
                    List<String> list = new ArrayList<>();
                    for (Team team : scoreboard.getTeams()) {
                        list.add(team.getName());
                    }
                    list.removeIf(str -> (!str.toLowerCase().startsWith(strings[0].toLowerCase())));
                    Collections.sort(list);
                    return list;
                }
            }
        }
        return null;
    }
}
