package me.spike.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

import static org.bukkit.Bukkit.getLogger;

public class utils {
    public static Player NameToPlayer(String a) {
        Player player = null;
        for (Player suspect : Bukkit.getOnlinePlayers()) {
            if (suspect.getName().equals(a)) {
                player = suspect;
                break;
            }
        }
        return player;
    }
    public static void pause(long ms) {
        long start = System.currentTimeMillis();
        while (ms <= System.currentTimeMillis() - start) { /* do nothing and wait ms*/ }
    }
    public static void exceptionHandler(Exception e, CommandSender sender) {
        getLogger().log(Level.SEVERE, "[MyUselessPlugin] An error occurred when executing command. Please consult the server administrator." );
    }
}
