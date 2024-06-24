package de.sakros.civilizationlands.commands;

import de.sakros.civilizationlands.CivilizationLands;
import de.sakros.civilizationlands.Utils;
import de.sakros.civilizationlands.listeners.NameListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class CommandJoinTeam implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard = scoreboardManager.getMainScoreboard();

            if(CivilizationLands.landsAddon.getLand(strings[0]).isTrusted(player.getUniqueId())){
                scoreboard.getTeam(strings[0]).addPlayer(player);
                NameListener.updateName(player);
                Utils.sendMessageSuccess(player, "Erfolg! Du bist nun im Team " + strings[0]);
            } else {
                Utils.sendMessageFailed(player, "Du kannst dem Team nicht beitreten, da du nicht im Land getrusted bist.");
            }
         }
        return true;
    }
}
