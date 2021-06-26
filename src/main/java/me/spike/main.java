package me.spike;

import me.spike.commands.commands;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;



public final class main extends JavaPlugin implements Listener {
    String prefix = "[MyUselessPlugin] ";
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        System.out.println(prefix + "Implementing listener...");
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println(prefix + "Listener implemented.");
        System.out.println(prefix + "Checking config...");

        // Config part
        // It doesn't work when in its own file somehow.
        config.addDefault("enable-troll", true);
        config.addDefault("enable-troll.blow", true);
        config.addDefault("enable-troll.crackhead", true);
        config.addDefault("enable-troll.kaboom", true);
        this.saveDefaultConfig();

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
        if (args.length == 0) {
            victim = (Player) sender;
        } else {
            victim = me.spike.utils.utils.NameToPlayer(args[0]);


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


                // Gets the name of command.
                String cmd = command.getName();
                // Individual command listener

                if (cmd.equalsIgnoreCase("blow")) {
                    if (config.getBoolean("enable-troll.blow")) {
                        commands.blow(victim);  // good luck surviving}
                    } else {
                        sender.sendMessage(prefix + ChatColor.RED + "This command is not enabled. Please check config.yml");
                    }
                }

                if (cmd.equalsIgnoreCase("crackhead")) {
                    if (config.getBoolean("enable-troll.blow")) {
                        commands.crackhead(victim, sender);
                    } else {
                        sender.sendMessage(prefix + ChatColor.RED + "This command is not enabled. Please check config.yml");
                    }
                }

                if (cmd.equalsIgnoreCase("kaboom")) {
                    if (config.getBoolean("enable-troll.blow")) {
                        commands.kaboom(victim);
                    } else {
                        sender.sendMessage(prefix + ChatColor.RED + "This command is not enabled. Please check config.yml");
                    }
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
                    if (args[0].equalsIgnoreCase("perf")) {
                        if (args[1].equalsIgnoreCase("on")) {
                            commands.onperf(victim);
                            sender.sendMessage("Performance bossbar" + ChatColor.GREEN + " enabled" + ChatColor.WHITE + ".");
                        } else if (args[1].equalsIgnoreCase("off")) {
                            sender.sendMessage("Performance bossbar cannot be" + ChatColor.RED + " disabled" + ChatColor.WHITE + ", LOL.");
                        }
                    }
                } else {
                    sender.sendMessage("¯\\_(ツ)_/¯");
                }
            }
            // I can make it return false, but ¯\_(ツ)_/¯

        }
        return true;
    }

}
