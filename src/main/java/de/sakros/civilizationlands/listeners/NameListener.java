package de.sakros.civilizationlands.listeners;

import de.sakros.civilizationlands.CivilizationLands;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.event.LuckPermsEvent;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class NameListener implements Listener {

    @EventHandler
    public static void onPlayerJoin (PlayerJoinEvent event) {
        updateName(event.getPlayer());
    }

    @EventHandler
    public static void onPlayerQuit (PlayerQuitEvent event) {
        updateName(event.getPlayer());
    }

    @EventHandler
    public static void onAsyncPlayerChat (AsyncPlayerChatEvent event) {
        updateName(event.getPlayer());
    }

    public static void updateName (Player player) {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        LuckPerms luckPerms = provider.getProvider();
        QueryOptions queryOptions = luckPerms.getContextManager().getQueryOptions(player);
        String prefix = luckPerms.getUserManager().getUser(player.getName()).getCachedData().getMetaData(queryOptions).getPrefix();

        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        Team playerTeam = scoreboard.getPlayerTeam(player);

        String playlistNameRaw = player.getName();
        if(playerTeam != null) {
            playlistNameRaw = prefix + playerTeam.getDisplayName() + " " + player.getName() + "&r";
        } else {
            playlistNameRaw = prefix + player.getName() + "&r";
        }

        player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', playlistNameRaw));
        player.setDisplayName(ChatColor.translateAlternateColorCodes('&', playlistNameRaw));
    }
}
