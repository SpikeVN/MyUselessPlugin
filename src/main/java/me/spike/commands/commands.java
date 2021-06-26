package me.spike.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class commands {
    String prefix = "[MyUselessPlugin] ";

    public static void blow(Player victim) {
        victim.getWorld().spawnEntity(victim.getLocation(), EntityType.PRIMED_TNT);
    }

    public static void crackhead(Player victim, CommandSender sender) {
        if (victim.getLocation().add(0,5,0).getBlock().isEmpty()) {
            victim.getLocation().add(0,5,0).getBlock().setType(Material.ANVIL, true);
            victim.getLocation().add(0,4,0).getBlock().setType(Material.AIR, true);
        } else sender.sendMessage(ChatColor.RED + "Five block above victim head must be clear to use this command.");
    }

    public static void kaboom(Player victim) {
        victim.getWorld().spawnEntity(victim.getLocation(), EntityType.FIREBALL);
        victim.getWorld().spawnEntity(victim.getLocation(), EntityType.LIGHTNING);
        victim.sendMessage(ChatColor.GOLD + "KABOOM!");
    }

    boolean stop = false;
    public void disableBossbarTracker(Player player) {
        stop = true;
    }

    public void bossbarTracker(Player player, Boolean enable) {
        BossBar performance = null;
        try {
            while (true) {
                if (stop) {
                    throw new Exception();
                }
                double tps = Double.parseDouble(PlaceholderAPI.setPlaceholders(player, "%server_tps%"));
                performance.setColor(BarColor.YELLOW);
                performance.setProgress(0.05 * tps);
                performance.setTitle("TPS: " + ChatColor.GOLD + tps);
            }
        } catch (Exception ass) {};

    }

}
