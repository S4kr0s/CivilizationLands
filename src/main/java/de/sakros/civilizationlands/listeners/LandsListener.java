package de.sakros.civilizationlands.listeners;

import de.sakros.civilizationlands.Static;
import me.angeschossen.lands.api.events.*;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LandsListener implements Listener {

    @EventHandler
    public static void onLandCreate (LandCreateEvent event){
        System.out.print("Land Creating!");
        LandPlayer landPlayer = event.getLandPlayer();
        Player player = landPlayer.getPlayer();

        String landName = event.getLand().getName();
        String trimmedLandName;
        if(landName.length() < Static.max_tag_size){
            trimmedLandName = landName;
        } else {
            trimmedLandName = landName.substring(0, Static.max_tag_size);
        }

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Team team = scoreboard.registerNewTeam(landName);
        team.setCanSeeFriendlyInvisibles(false);
        team.setAllowFriendlyFire(false);
        team.setPrefix(trimmedLandName + " - ");
        team.setColor(ChatColor.GRAY);
        team.addEntry(player.getName());
    }

    @EventHandler
    public static void onLandDelete (LandDeleteEvent event){
        String landName = event.getLand().getName();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getMainScoreboard();
        Team team = scoreboard.getTeam(landName);
        team.unregister();
    }

    @EventHandler
    public static void onLandRename (LandRenameEvent event){
        List<UUID> list = new ArrayList<>(event.getLand().getTrustedPlayers());
        String landName = event.getNewName();

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        Team oldTeam = scoreboard.getTeam(event.getCurrentName());

        Team newTeam = scoreboard.registerNewTeam(landName);
        newTeam.setCanSeeFriendlyInvisibles(oldTeam.canSeeFriendlyInvisibles());
        newTeam.setAllowFriendlyFire(oldTeam.allowFriendlyFire());
        newTeam.setPrefix(oldTeam.getPrefix());
        newTeam.setColor(oldTeam.getColor());
        oldTeam.unregister();
        for (UUID uuid : list) {
            newTeam.addPlayer(Bukkit.getPlayer(uuid));
        }
    }

    @EventHandler
    public static void onLandTrustPlayer (LandTrustPlayerEvent event) {
        System.out.print("TRUST CALLED");
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        Team team = scoreboard.getTeam(event.getLand().getName());
        team.addPlayer(Bukkit.getOfflinePlayer(event.getTargetUUID()));
    }

    @EventHandler
    public static void onLandUntrustPlayer (LandUntrustPlayerEvent event) {
        System.out.print("UNTRUST CALLED");
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        Team team = scoreboard.getTeam(event.getLand().getName());
        if(scoreboard.getPlayerTeam(Bukkit.getOfflinePlayer(event.getTargetUUID())).equals(team)){
            team.removePlayer(Bukkit.getOfflinePlayer(event.getTargetUUID()));
        }
    }

    @EventHandler
    public static void onPlayerLeaveLand (PlayerLeaveLandEvent event){
        System.out.print("LEAVE CALLED");
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        Team team = scoreboard.getTeam(event.getLand().getName());
        if(scoreboard.getPlayerTeam(event.getLandPlayer().getPlayer()).equals(team)){
            team.removePlayer(event.getLandPlayer().getPlayer());
        }
    }
}
