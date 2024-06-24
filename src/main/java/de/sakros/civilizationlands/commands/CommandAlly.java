package de.sakros.civilizationlands.commands;

import de.sakros.civilizationlands.CivilizationLands;
import de.sakros.civilizationlands.Utils;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class CommandAlly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender,Command command,String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            Utils.sendMessageFailed(player, "Dieser Befehl funktioniert aktuell nicht.");
            /*
            Utils.sendMessageFailed(player, "Dieser Befehl ist noch nicht vollständig! Aktuell trusted er nur alle Mitglieder aus dem angegbenen Land!");

            LandPlayer landPlayer = CivilizationLands.landsAddon.getLandPlayer(player.getUniqueId());

            ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
            Team playerTeam = scoreboard.getPlayerTeam(player);


            if(playerTeam != null){
                if(landPlayer.getOwningLand() != null){
                    if(landPlayer.getOwningLand().getName().equals(playerTeam.getName())){
                        if(strings.length == 1){
                            if(CivilizationLands.landsAddon.getLand(strings[0]) != null){
                                if(!CivilizationLands.landsAddon.getLand(strings[0]).equals(landPlayer.getOwningLand())){
                                    Land owningLand = landPlayer.getOwningLand();
                                    Land allyLand = CivilizationLands.landsAddon.getLand(strings[0]);
                                    Team allyTeam = scoreboard.getPlayerTeam(Bukkit.getOfflinePlayer(allyLand.getOwnerUID()));
                                    List<OfflinePlayer> offlinePlayers = new ArrayList<OfflinePlayer>(allyTeam.getPlayers());
                                    for (OfflinePlayer offlinePlayer : offlinePlayers) {
                                        if(!allyLand.isTrusted(offlinePlayer.getUniqueId())){
                                            owningLand.trustPlayer(offlinePlayer.getUniqueId());
                                            Utils.sendMessageSuccess(player, offlinePlayer.getName() + " wurde nun trusted.");
                                        } else {
                                            Utils.sendMessageSuccess(player, offlinePlayer.getName() + " ist schon trusted.");
                                        }
                                    }
                                    Utils.sendMessageSuccess(player, "Du hast nun alle Mitglieder vom Land " + allyLand.getName() + " trusted.");
                                } else {
                                    Utils.sendMessageFailed(player, "Du kannst dich nicht mit deinem eigenen Volk verbünden.");
                                }
                            } else {
                                Utils.sendMessageFailed(player, "Das angegebene Volk existiert nicht!");
                            }
                        } else {
                            Utils.sendMessageFailed(player, "Syntax Falsch! /ally <land-name>");
                        }
                    }
                } else {
                    Utils.sendMessageFailed(player, "Du bist nicht der Anführer deines Volks.");
                }
            } else {
                Utils.sendMessageFailed(player, "Du bist in keinem Volk.");
            }
             */
        }
        return true;
    }
}
