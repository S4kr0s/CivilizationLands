package de.sakros.civilizationlands;

import de.sakros.civilizationlands.commands.*;
import de.sakros.civilizationlands.listeners.LandsListener;
import de.sakros.civilizationlands.listeners.NameListener;
import me.angeschossen.lands.api.integration.LandsIntegration;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public class CivilizationLands extends JavaPlugin {

    public static RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
    public static LuckPerms luckPerms() {
        if(provider != null){
            return provider.getProvider();
        }
        return null;
    }

    public static LandsIntegration landsAddon;
    public JDA jda;

    @Override
    public void onEnable() {
        getLogger().info("CivilizationLands has been enabled!");
        landsAddon = new LandsIntegration(this);
        getServer().getPluginManager().registerEvents(new LandsListener(), this);
        getServer().getPluginManager().registerEvents(new NameListener(), this);
        this.getCommand("color").setExecutor(new CommandColor());
        this.getCommand("color").setTabCompleter(new TabCompleterColor());
        this.getCommand("tag").setExecutor(new CommandTag());
        this.getCommand("ally").setExecutor(new CommandAlly());
        this.getCommand("unally").setExecutor(new CommandUnally());
        this.getCommand("createteam").setExecutor(new CommandCreateTeam());
        this.getCommand("leaveteam").setExecutor(new CommandLeaveTeam());
        this.getCommand("jointeam").setExecutor(new CommandJoinTeam());
        this.getCommand("jointeam").setTabCompleter(new TabCompleterJoinTeam());

        this.getCommand("score").setExecutor(new CommandScore());
        this.getCommand("score").setTabCompleter(new TabCompleterScore());

        getLogger().info("CivilizationLands hooked up with Lands! Let's start controlling over the world!");
        getLogger().info("CivilizationLands looging in to discord..");
        login();
        getLogger().info("Successfully synced with discord. nice.");
    }

    @Override
    public void onDisable() {
    }

    private void login(){
        try {
            JDABuilder jdaBuilder = new JDABuilder();
            jdaBuilder.setActivity(Activity.streaming("Civilization 2", "https://www.twitch.tv/sakros"));
            jdaBuilder.setToken("blank token");
            jda = jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
