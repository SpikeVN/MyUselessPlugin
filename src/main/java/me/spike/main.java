package me.spike;

import me.spike.commands.commands;
import me.spike.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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
        config.addDefault("trolls", true);
        config.addDefault("trolls.blow", true);
        config.addDefault("trolls.crackhead", true);
        config.addDefault("trolls.kaboom", true);
        config.addDefault("functions",true);
        config.addDefault("functions.throwable-fireball",true);
        config.addDefault("functions.one-player-sleep",true);
        config.options().copyDefaults(true);
        saveConfig();

        System.out.println(prefix + "Plugin load complete.");
    }

    @Override
    public void onDisable() {
        System.out.println(prefix + "Disabling listener...");
        System.out.println(prefix + "Listener has been disabled.");
        System.out.println(prefix + "Closing storage...");
        System.out.println(prefix + "Goodbye :)");
    }

    @EventHandler
    public static void onePlayerSleep(PlayerBedEnterEvent event) {
        Player sleeper = event.getPlayer();
        if (sleeper.getWorld().getTime() >= 12543) {    // 12543 is the time can sleep
            utils.pause(5000);   // time for normal sleep
            sleeper.getWorld().setTime(0);
            Bukkit.broadcastMessage(ChatColor.YELLOW + sleeper.getName() + ChatColor.GOLD +" slept. Good night!");
        }

    }

    @EventHandler
    public static void throwFireball(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType().equals(Material.FIRE_CHARGE)) {
            player.getWorld().spawnEntity(player.getLocation().add(0,1,0), EntityType.FIREBALL);
            player.playSound(player.getLocation(), "ENTITY_BLAZE_SHOOT", 16, 1);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //try {

            /* Tells if console is executing command.
             * Or if the command is targeting something else, it executes.
             * The exception is the reload command and the version command.
             */
            if (!(sender instanceof Player)) {
                System.out.println(prefix + ChatColor.RED + "Console can't execute this command."); //pesky console, get out!
                /* Checks if trolls is enabled.
                 * Because it is on the outer layer, it will overlap individual module settings
                 */

                // The troll part
            } else {
                /* Get victim and sender for commands
                 * If the command has 1 argument, it will take sender as victim.
                 * If the command has 2 arguments, it will take the second arguments as victim.
                 */
                Player victim;
                if (args.length == 0) {
                    victim = (Player) sender;
                } else {
                    victim = me.spike.utils.utils.NameToPlayer(args[0]);
                }
                // Gets the name of command.
                String cmd = command.getName();
                // Individual command listener

                if (cmd.equalsIgnoreCase("blow")) {
                    if (config.getBoolean("trolls.blow")) {
                        commands.blow(victim);  // good luck surviving}
                    } else {
                        sender.sendMessage(prefix + ChatColor.RED + "This command is not enabled. Please check config.yml");
                    }
                }

                if (cmd.equalsIgnoreCase("crackhead")) {
                    if (config.getBoolean("trolls.blow")) {
                        commands.crackhead(victim, sender);
                    } else {
                        sender.sendMessage(prefix + ChatColor.RED + "This command is not enabled. Please check config.yml");
                    }
                }

                if (cmd.equalsIgnoreCase("kaboom")) {
                    if (config.getBoolean("trolls.blow")) {
                        commands.kaboom(victim);
                    } else {
                        sender.sendMessage(prefix + ChatColor.RED + "This command is not enabled. Please check config.yml");
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
                        if (args[0].equalsIgnoreCase("reload")) {
                            reloadConfig();
                            sender.sendMessage(ChatColor.GOLD + "Reloaded successful.");
                        } else if (args[0].equalsIgnoreCase("help")) {
                            sender.sendMessage(ChatColor.GOLD + prefix + "no help, never.");
                        } else {
                            sender.sendMessage(ChatColor.RED + "Invalid Option!");
                        }
                    } else {
                        sender.sendMessage("¯\\_(ツ)_/¯");
                    }
                }
            }
            // I can make it return false, but ¯\_(ツ)_/¯

        return true;
    }

}
