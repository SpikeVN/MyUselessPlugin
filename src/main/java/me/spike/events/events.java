package me.spike.events;

import me.spike.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class events implements Listener {
    @EventHandler
    public void onSleep(PlayerBedEnterEvent event) {
        Player sleeper = event.getPlayer();
        World world = sleeper.getWorld();
        utils.pause(5000);
        if (world.getTime() >= 13000 && world.getTime() <= 23000) {
            world.setTime(23000);
            Bukkit.broadcastMessage(ChatColor.GOLD + sleeper.getName() + ChatColor.YELLOW + " đã ngủ. Chúc ngủ ngon!");
        }
    }

    @EventHandler
    public void onOpenInv(InventoryOpenEvent event) {
        Player victim = (Player) event.getPlayer();
        if (victim.getGameMode() == GameMode.ADVENTURE) {
            victim.setHealth(0);
            Bukkit.broadcastMessage(ChatColor.RED + victim.getName() + " đã không làm gì cả nên chúng tôi giết anh ta cho vui.");
        }
    }
}
