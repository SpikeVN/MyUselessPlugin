package me.spike.commands;

import me.spike.utils.utils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class commands {
    public static void blow(CommandSender sender, String[] a) {
        Player victim;
        if(utils.hasNoArgs(a)) {
            victim = (Player) sender;
        } else {
            victim = utils.NameToPlayer(a[0]);
        }
        victim.getWorld().spawnEntity(victim.getLocation(), EntityType.PRIMED_TNT);
    }
    public static void crackhead(CommandSender sender, String[] a) {
        Player victim;
        if (utils.hasNoArgs(a)) {
            victim = (Player) sender;
        } else {
            victim = utils.NameToPlayer(a[0]);
        }
            Location location = victim.getLocation().add(0, 2, 0);
            location.add(0, -1, 0).getBlock().setType(Material.BEDROCK);
            location.getBlock().setType(Material.ANVIL, true);
            location.add(0, -1, 0).getBlock().setType(Material.AIR, true);
        }
    public static void trap(CommandSender sender, String[] a) {
        Player victim;
        if (utils.hasNoArgs(a)) {
            victim = (Player) sender;
        } else {
            victim = utils.NameToPlayer(a[0]);
        }
        Location victimFeet = victim.getLocation();
        Material BlockOccupied = victimFeet.add(0,-1,0).getBlock().getType();
        System.out.println(victim.getName() + "has been trapped by " + sender + " at " + victimFeet);
        victimFeet.add(0,-1,0).getBlock().setType(Material.BEDROCK);
        victimFeet.add(1, 0, 0).getBlock().setType(Material.BEDROCK);
        victimFeet.add(-1, 0, 0).getBlock().setType(Material.BEDROCK);
        victimFeet.add(0, 0, 1).getBlock().setType(Material.BEDROCK);
        victimFeet.add(0, 0, -1).getBlock().setType(Material.BEDROCK);
        victimFeet.add(0, 2, 0).getBlock().setType(Material.BEDROCK);   //TRAP VICTIM IN BEDROCK HOLE
        victim.sendMessage(ChatColor.RED + "Bạn đã bị bẫy trong bedrock bởi " + sender);
        for (int i = 0; i <= 5; i++) {
            victim.sendMessage(ChatColor.RED + "Bạn sẽ được thả sau" + i + "s");
            utils.pause(1000);
        }
        victimFeet.add(0,-1,0).getBlock().setType(BlockOccupied);
    }
}
