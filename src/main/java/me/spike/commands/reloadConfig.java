package me.spike.commands;

import me.spike.files.configManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class reloadConfig {

    public static void reloadConfigCommand(CommandSender sender) {
            sender.sendMessage(ChatColor.GOLD + "[MyUselessPlugin] Reloading config...");
            configManager.reload();
            sender.sendMessage(ChatColor.GOLD + "[MyUselessPlugin] Config has been reloaded successfully.");
        }
    }

