/*
  Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

    None.
just the license. same to none
*/
package me.spike.main;

import me.spike.commands.commands;
import me.spike.utils.utils;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public final class main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("[UselessPlugin] Plugin has started successfully.");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        System.out.println("[UselessPlugin] Deactivating...");
        System.out.println("[UselessPlugin] Goodbye! :)");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName().toUpperCase(Locale.ROOT);
        if (cmd.equals("BLOW")) {
            commands.blow(sender, args);
        }
        if (cmd.equals("CRACKHEAD")) {
            commands.crackhead(sender, args);
        }
        if (cmd.equals("TRAP")) {
            commands.trap(sender, args);
        }
        return true;
    }
}
