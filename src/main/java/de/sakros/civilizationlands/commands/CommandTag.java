package de.sakros.civilizationlands.commands;

import de.sakros.civilizationlands.CivilizationLands;
import de.sakros.civilizationlands.Static;
import de.sakros.civilizationlands.Utils;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class CommandTag implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            LandPlayer landPlayer = CivilizationLands.landsAddon.getLandPlayer(player.getUniqueId());

            ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
            Team playerTeam = scoreboard.getPlayerTeam(player);

            if(playerTeam != null){
                if(landPlayer.getOwningLand() != null){
                    if(landPlayer.getOwningLand().getName().equals(playerTeam.getName())){
                        if (strings[0].length() <= Static.max_tag_size) {
                            playerTeam.setPrefix(strings[0] + Static.tag_separator);
                            playerTeam.setDisplayName(strings[0]);
                        } else {
                            Utils.sendMessageFailed(player, "Der Tag darf nicht länger als " + Static.max_tag_size + " Zeichen sein.");
                        }
                    }
                } else {
                    Utils.sendMessageFailed(player, "Du bist nicht der Anführer deines Volks.");
                }
            } else {
                Utils.sendMessageFailed(player, "Du bist in keinem Volk.");
            }
        }
        return true;
    }
}
