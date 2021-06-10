package me.spike.commands;

import me.spike.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class commands {
    public static void blow(CommandSender sender, String[] a) {
        Player victim;
        if(a.length == 0) {
            victim = (Player) sender;
        } else {
            victim = utils.NameToPlayer(a[0]);
        }
        victim.getWorld().spawnEntity(victim.getLocation(), EntityType.PRIMED_TNT);
    }
    public static void crackhead(CommandSender sender, String[] a) {
        Player victim;
        if (a.length == 0) {
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
        if (a.length == 0) {
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
        victimFeet.add(0, 2, 0).getBlock().setType(Material.BEDROCK);   //TRAP VICTIM IN BEDROCK CAGE
        victim.sendMessage(ChatColor.RED + "Bạn đã bị bẫy trong bedrock bởi " + sender);
        for (int i = 0; i <= 5; i++) {
            victim.sendMessage(ChatColor.RED + "Bạn sẽ được thả sau" + i + "s");
            utils.pause(1000);
        }
        victimFeet.add(0,-1,0).getBlock().setType(BlockOccupied);
    }

    public static void gm(CommandSender sender, String[] a, Command cmd) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("gmc")) {
                ((Player) sender).setGameMode(GameMode.CREATIVE);
                sender.sendMessage(ChatColor.GOLD + "Chế độ chơi của bạn đã được chuyển thành chế độ" + ChatColor.RED + " sáng tạo" + ChatColor.GOLD + ".");
            }
            if (cmd.getName().equalsIgnoreCase("gms")) {
                ((Player) sender).setGameMode(GameMode.SURVIVAL);
                sender.sendMessage(ChatColor.GOLD + "Chế độ chơi của bạn đã được chuyển thành chế độ" + ChatColor.RED + " sinh tồn" + ChatColor.GOLD + ".");
            }
            if (cmd.getName().equalsIgnoreCase("gma")) {
                ((Player) sender).setGameMode(GameMode.ADVENTURE);
                sender.sendMessage(ChatColor.GOLD + "Chế độ chơi của bạn đã được chuyển thành chế độ" + ChatColor.RED + " phiêu lưu" + ChatColor.GOLD + ".");
            }
            if (cmd.getName().equalsIgnoreCase("gmsp")) {
                ((Player) sender).setGameMode(GameMode.SPECTATOR);
                sender.sendMessage(ChatColor.GOLD + "Chế độ chơi của bạn đã được chuyển thành chế độ" + ChatColor.RED + " khán giả" + ChatColor.GOLD + ".");
            }
            if (cmd.getName().equalsIgnoreCase("gm")) {
                if (a.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Lỗi: bạn phải liệt kê chế độ chơi của bạn.");
                } else {
                    if (a[0].equalsIgnoreCase("c") || a[0].equalsIgnoreCase("creative")) {
                        ((Player) sender).setGameMode(GameMode.CREATIVE);
                        sender.sendMessage(ChatColor.GOLD + "Chế độ chơi của bạn đã được chuyển thành chế độ" + ChatColor.RED + " sáng tạo" + ChatColor.GOLD + ".");
                    }
                    if (a[0].equalsIgnoreCase("s") || a[0].equalsIgnoreCase("survival")) {
                        ((Player) sender).setGameMode(GameMode.CREATIVE);
                        sender.sendMessage(ChatColor.GOLD + "Chế độ chơi của bạn đã được chuyển thành chế độ" + ChatColor.RED + " sinh tồn" + ChatColor.GOLD + ".");
                    }
                    if (a[0].equalsIgnoreCase("a") || a[0].equalsIgnoreCase("adventure")) {
                        ((Player) sender).setGameMode(GameMode.CREATIVE);
                        sender.sendMessage(ChatColor.GOLD + "Chế độ chơi của bạn đã được chuyển thành chế độ" + ChatColor.RED + " phiêu lưu" + ChatColor.GOLD + ".");
                    }
                    if (a[0].equalsIgnoreCase("sp") || a[0].equalsIgnoreCase("spectator")) {
                        ((Player) sender).setGameMode(GameMode.CREATIVE);
                        sender.sendMessage(ChatColor.GOLD + "Chế độ chơi của bạn đã được chuyển thành chế độ" + ChatColor.RED + " khán giả" + ChatColor.GOLD + ".");
                    }
                }
            }
        } else {
            System.out.println("[MyUselessPlugin] " + ChatColor.RED + "How do I supposed to switch your gamemode?");
        }
    }
    public static void give(CommandSender sender, String[] a) {
        if (a.length == 0) {
            sender.sendMessage(ChatColor.RED + "Bạn phải nêu thứ để đưa!");
        } else if (a.length == 1) {
            if (sender instanceof Player) {
                if (utils.emptySlot((Player) sender) != -1) {
                    ((Player) sender).getInventory().setItem(utils.emptySlot((Player) sender), new ItemStack(Material.getMaterial(a[0]), 64));
                }
            } else {
                System.out.println("[MyUselessPlugin] " + ChatColor.RED + "Cannot give Console item(s).");
            }
        } else if (a.length == 2) {
            if (sender instanceof Player) {
                if (utils.emptySlot((Player) sender) != -1) {
                    ((Player) sender).getInventory().setItem(utils.emptySlot((Player) sender), new ItemStack(Objects.requireNonNull(Material.getMaterial(a[0])), Integer.parseInt(a[0])));
                }
            } else {
                System.out.println("[MyUselessPlugin] " + ChatColor.RED + "Cannot give Console item(s).");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Câu lệnh của bạn bị thừa!");
        }
    }
}
