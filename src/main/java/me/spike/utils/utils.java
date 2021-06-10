package me.spike.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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

    public static void pause(long time) {
        long a = System.currentTimeMillis();
        while (a >= System.currentTimeMillis() - time) ;    //do nothing and wait ms.\
    }

    public static Material NameToMaterial(String name) {
        for (Material x : Material.values()) {
            if (x.name().equalsIgnoreCase(name)) {
                return x;
            }
        }
        return null;
    }

    public static int emptySlot(Player player) {
        for (int i = 0; i < 36; i++) {
            if (player.getInventory().getItem(i) == null) {
                return i;
            }
        }
        return -1;
    }
}
