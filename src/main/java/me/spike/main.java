package me.spike;

import me.spike.commands.commands;
import me.spike.files.configManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import me.spike.utils.*;

import static me.spike.commands.reloadConfig.reloadConfigCommand;

public final class main extends JavaPlugin implements Listener {
    String prefix = "[MyUselessPlugin] ";
    @Override
    public void onEnable() {
        System.out.println(prefix + "Implementing listener...");
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println(prefix + "Listener implemented.");
        System.out.println(prefix + "Checking config...");
        // creates config if not had already
        // getConfig().options().copyDefaults();
        saveDefaultConfig();
        // set default messages
        // configManager.setup();
        // configManager.setDefaultValue();
        // configManager.save();
        System.out.println(prefix + "Plugin load complete.");
    }

    @Override
    public void onDisable() {
        System.out.println(prefix + "Disabling listener...");
        System.out.println(prefix + "Listener has been disabled.");
        System.out.println(prefix + "Closing storage...");
        System.out.println(prefix + "Goodbye :)");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //try {
            Player victim;

            /* Tells if console is executing command.
             * Or if the command is targeting something else, it executes.
             * The exception is the reload command and the version command.
             */
            if (!(sender instanceof Player)) {
                System.out.println(prefix + ChatColor.RED + "Console can't execute this command."); //pesky console, get out!
                /* Checks if enable-troll is enabled.
                 * Because it is on the outer layer, it will overlap individual module settings
                 */

            // The troll part
            } else {
                /* Get victim and sender for commands
                 * If the command has 1 argument, it will take sender as victim.
                 * If the command has 2 arguments, it will take the second arguments as victim.
                 */

                if (args.length == 0) {
                    victim = (Player) sender;
                } else {
                    victim = me.spike.utils.utils.NameToPlayer(args[0]);
                }

                // Gets the name of command.
                String cmd = command.getName();
                // Individual command listener

                if (cmd.equalsIgnoreCase("blow")) {
                    commands.blow(victim);  // good luck surviving
                }

                if (cmd.equalsIgnoreCase("crackhead")) {
                    commands.crackhead(victim, sender);
                }

                if (cmd.equalsIgnoreCase("kaboom")) {
                    commands.kaboom(victim);
                }
            }
            /*
             * The main plugin command.
             * No args, it return version
             * has args and is reload, it reload.
             */
            // Not troll part
            if (command.getName().equalsIgnoreCase("mup")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.GOLD + "MyUselessPlugin, version 0.8\n" + ChatColor.GOLD + "No help will be available, you stupid.");
                } else if (args.length == 1) {
                    // Temp code, will improve later.
                    //if (args[1].equalsIgnoreCase("reload")) {
                    //    reloadConfigCommand(sender);
                    //} else if (args[1].equalsIgnoreCase("help")) {
                    sender.sendMessage(ChatColor.GOLD + prefix + "no help.");
                } else if (args.length == 2) {
                    if (args[1].equalsIgnoreCase("perf")) {
                        if (args[2].equalsIgnoreCase("on")) {
                            sender.sendMessage("Performance bossbar" + ChatColor.GREEN + " enabled" + ChatColor.WHITE + ".");
                        } else if (args[2].equalsIgnoreCase("off")) {
                            sender.sendMessage("Performance bossbar" + ChatColor.RED + " disabled" + ChatColor.WHITE + ".");
                        }
                    }
                } else {
                    sender.sendMessage("no");
                }
            }
        // I can make it return false, but ¯\_(ツ)_/¯
        return true;
    }
}
