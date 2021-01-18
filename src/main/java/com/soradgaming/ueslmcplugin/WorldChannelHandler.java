package com.soradgaming.ueslmcplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class WorldChannelHandler implements Listener {

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        String worldFrom = event.getFrom().getName();
        String worldTo = player.getWorld().getName();

        if (worldTo.startsWith("factions") && !worldFrom.startsWith("factions")) {
            changeChannel(name, "factions");
        } else if (worldTo.equals("Creative")) {
            changeChannel(name, "creative");
        } else if (worldTo.startsWith("survival") && !worldFrom.startsWith("survival")) {
            changeChannel(name, "survival");
        } else if (worldTo.equals("Breeze2")) {
            changeChannel(name, "Breeze2");
        } else if (worldTo.startsWith("IridiumSkyblock") && !worldFrom.startsWith("IridiumSkyblock")) {
            changeChannel(name, "IridiumSkyblock");
        } else if (worldTo.equals("PlanetParkour")) {
            changeChannel(name, "PlanetParkour");
        } else if (worldTo.equals("ParkourParadise")) {
            changeChannel(name, "ParkourParadise");
        }
    }

    public static void changeChannel(String name, String channel) {
        Player player = Bukkit.getServer().getPlayer(name);
        assert player != null;
        boolean grant = !player.hasPermission("ultrachat.channel");
        if (grant)
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + name + " permission set ultrachat.channel true");
        PlayerCommandPreprocessEvent e = new PlayerCommandPreprocessEvent(player, "/channel " + channel);
        Bukkit.getPluginManager().callEvent(e);
        if (grant)
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + name + " permission set ultrachat.channel false");
    }
}