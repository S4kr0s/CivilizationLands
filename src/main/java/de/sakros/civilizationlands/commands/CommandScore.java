package de.sakros.civilizationlands.commands;

import de.sakros.civilizationlands.CivilizationLands;
import de.sakros.civilizationlands.Utils;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class CommandScore implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(Bukkit.getPluginManager().getPlugin("CivilizationLands"),
            new Runnable() {
                @Override
                public void run() {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;

                        if (args[0] != null) {
                            if (args[1] != null){
                                ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
                                Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
                                Objective objective = scoreboard.getObjective(args[1]);

                                if(args[0].equalsIgnoreCase("all")){
                                    Utils.sendMessageNormal(player, "Alle Challenge Scores von &6" + objective.getName() + ":");
                                    int number = 1;
                                    List<de.sakros.civilizationlands.commands.Team> teams = new ArrayList<>();
                                    for (Team team : scoreboard.getTeams()) {
                                        int score = 0;
                                        for ( OfflinePlayer offlinePlayer : team.getPlayers()) {
                                            score += objective.getScore(offlinePlayer).getScore();
                                        }
                                        de.sakros.civilizationlands.commands.Team _team = new de.sakros.civilizationlands.commands.Team(team.getName(), score);
                                        teams.add(_team);
                                    }
                                    for (de.sakros.civilizationlands.commands.Team team : teams) {
                                        Utils.sendMessageNormal(player, team.name + " &7- &e" + team.value);
                                    }
                                    return;
                                }
                                Team team = scoreboard.getTeam(args[0]);
                                int score = 0;
                                for ( OfflinePlayer offlinePlayer : team.getPlayers()) {
                                    score += objective.getScore(offlinePlayer).getScore();
                                }
                                Utils.sendMessageSuccess(player, "Das Team &2" + args[0] + "&a hat bei der Challenge &2" + args[1] + "&a einen Score von &2" + score + "&a erreicht.");
                            } else {
                                Utils.sendMessageFailed(player, "Die Syntax ist falsch. /score <team> <challenge>");
                            }
                        } else {
                            Utils.sendMessageFailed(player, "Die Syntax ist falsch. /score <team> <challenge>");
                        }
                    }
                }
            }
        );
        return true;
    }
}
