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

public class CommandLeaveTeam implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
            Team playerTeam = scoreboard.getPlayerTeam(player);
            if(CivilizationLands.landsAddon.getLandPlayer(player.getUniqueId()).getOwningLand() == null ||
                    CivilizationLands.landsAddon.getLandPlayer(player.getUniqueId()).getOwningLand().getName().equals(playerTeam.getName())){
                playerTeam.removePlayer(player);
                NameListener.updateName(player);
                Utils.sendMessageSuccess(player, "Du hast dein Tablist-Team verlassen! Mit /jointeam kannst du einem neuen Tablist-Team beitreten.");
            } else {
                Utils.sendMessageFailed(player, "Du kannst als Lands-Owner dein Tablist-Team nicht verlassen!");
            }
         }
        return true;
    }
}
