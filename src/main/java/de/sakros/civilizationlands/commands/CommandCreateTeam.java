package de.sakros.civilizationlands.commands;

import de.sakros.civilizationlands.CivilizationLands;
import de.sakros.civilizationlands.Static;
import de.sakros.civilizationlands.Utils;
import de.sakros.civilizationlands.listeners.NameListener;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

public class CommandCreateTeam implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender,Command command,String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            LandPlayer landPlayer = CivilizationLands.landsAddon.getLandPlayer(player.getUniqueId());

            ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
            Team playerTeam = scoreboard.getPlayerTeam(player);

            if(landPlayer.getOwningLand() != null){
                if(playerTeam == null) {
                    String landName = CivilizationLands.landsAddon.getLandPlayer(player.getUniqueId()).getOwningLand().getName();
                    String trimmedLandName;
                    if(landName.length() < Static.max_tag_size){
                        trimmedLandName = landName;
                    } else {
                        trimmedLandName = landName.substring(0, Static.max_tag_size);
                    }

                    Team team = scoreboard.registerNewTeam(landName);
                    team.setCanSeeFriendlyInvisibles(false);
                    team.setAllowFriendlyFire(false);
                    team.setPrefix(trimmedLandName + Static.tag_separator);
                    playerTeam.setDisplayName(trimmedLandName);
                    team.setColor(ChatColor.GRAY);
                    team.addEntry(player.getName());
                    for (UUID uuid : CivilizationLands.landsAddon.getLandPlayer(player.getUniqueId()).getOwningLand().getTrustedPlayers()) {
                        if(scoreboard.getPlayerTeam(Bukkit.getOfflinePlayer(uuid)) == null){
                            team.addPlayer(Bukkit.getOfflinePlayer(uuid));
                        }
                    }
                    NameListener.updateName(player);
                    Utils.sendMessageSuccess(player, "Erfolg! Dein Tablist Team wurde erstellt!");
                }
            } else {
                Utils.sendMessageFailed(player, "Du bist nicht der Anführer deines Volks.");
            }
        }
        return true;
    }
}
