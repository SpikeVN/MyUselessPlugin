package me.spike.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class utils {
    public static Player NameToPlayer(String name) {
        Player victim = null;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equals(name)) {
                victim = player;
            }
        }
        return victim;
    }

    public static boolean hasNoArgs(String[] a) {
        boolean b = false;
        try {
            Player player1 = NameToPlayer(a[0]);
        } catch (Exception x) {     //Exception happens when no parameters is used
            b = true;
        }
        return b;
    }

    public static void pause(long time) {
        long a = System.currentTimeMillis();
        while (a >= System.currentTimeMillis() - time) ;    //do nothing and wait ms.
    }
}
