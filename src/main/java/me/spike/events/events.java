package me.spike.events;

import me.spike.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class events {
    public static void onePlayerSleep(PlayerBedEnterEvent event) {
        Player sleeper = event.getPlayer();
        if (sleeper.getWorld().getTime() >= 12543) {    // 12543 is the time can sleep
            utils.pause(5000);   // time for normal sleep
            sleeper.getWorld().setTime(0);
            Bukkit.broadcastMessage(ChatColor.YELLOW + sleeper.getName() + ChatColor.GOLD +" đã ngủ. Chúc ngủ ngon!");
        }

    }
}
