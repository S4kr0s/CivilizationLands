package de.sakros.civilizationlands.commands;

import de.sakros.civilizationlands.CivilizationLands;
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

public class CommandColor implements CommandExecutor {

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
                        ChatColor color;
                        switch(strings[0].toLowerCase()){
                            case "lila":
                            case "purple":
                            case "purpur":
                                color = ChatColor.DARK_PURPLE;
                                break;
                            case "pink":
                            case "magenta":
                            case "rosa":
                            case "light_purple":
                                color = ChatColor.LIGHT_PURPLE;
                                break;
                            case "darkred":
                            case "dark_red":
                            case "dunkelrot":
                            case "dunkel_rot":
                                color = ChatColor.DARK_RED;
                                break;
                            case "rot":
                            case "red":
                                color = ChatColor.RED;
                                break;
                            case "gold":
                            case "orange":
                                color = ChatColor.GOLD;
                                break;
                            case "yellow":
                            case "gelb":
                                color = ChatColor.YELLOW;
                                break;
                            case "green":
                            case "grün":
                            case "hellgrün":
                            case "light_green":
                            case "lime":
                                color = ChatColor.GREEN;
                                break;
                            case "dunkelgrün":
                            case "dunkel_grün":
                            case "dark_green":
                            case "darkgreen":
                                color = ChatColor.DARK_GREEN;
                                break;
                            case "türkis":
                            case "dark_aqua":
                            case "tuerkis":
                                color = ChatColor.DARK_AQUA;
                                break;
                            case "hellblau":
                            case "aqua":
                            case "light_blue":
                            case "hell_blau":
                                color = ChatColor.AQUA;
                                break;
                            case "blau":
                            case "blue":
                                color = ChatColor.BLUE;
                                break;
                            case "dunkelblau":
                            case "dunkel_blau":
                            case "darkblue":
                            case "dark_blue":
                                color = ChatColor.DARK_BLUE;
                                break;
                            case "grau":
                            case "gray":
                            case "grey":
                                color = ChatColor.GRAY;
                                break;
                            case "dunkelgrau":
                            case "dunkel_grau":
                            case "dark_grey":
                            case "darkgrey":
                            case "dark_gray":
                            case "darkgray":
                                color = ChatColor.DARK_GRAY;
                                break;
                            case "schwarz":
                            case "black":
                                color = ChatColor.BLACK;
                                break;
                            case "weiß":
                            case "weiss":
                            case "white":
                                color = ChatColor.WHITE;
                                break;
                            default:
                                color = ChatColor.GRAY;
                                break;
                        }
                        playerTeam.setColor(color);
                        NameListener.updateName(player);
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
