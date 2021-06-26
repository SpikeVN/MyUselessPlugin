package me.spike.files;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

// This was stolen from Youtube.
public class configManager {

    private static File file;
    private static FileConfiguration configFile;    // G E N A R A T E

    // Set up function
    public static void setup(){
        // Find directory and set it up.
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("MyUselessPlugin").getDataFolder(), "config.yml");
        if (!file.exists()){    // Only generates if file exists
            try{
                file.createNewFile();   // B E T T E R   G E N A T E
            }catch (IOException e){
                // oh, no, exception
                System.out.println(ChatColor.RED + "[MyUselessPlugin] FUCK! EXCEPTION!" + e);
            }
        }
        configFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return configFile;
    }

    public static void save(){
        try{
            configFile.save(file);
        }catch (IOException e){
            System.out.println(ChatColor.RED + "[MyUselessPlugin] Could not save config: " +  e);
        }
    }


    public static void reload(){
        configFile = YamlConfiguration.loadConfiguration(file);
    }

    public static void setDefaultValue() {

        configManager.get().addDefault("enable-trolls", "true");
        configManager.get().addDefault("blow", "true");
        configManager.get().addDefault("crackhead", "true");
        configManager.get().addDefault("kaboom", "true");
        configManager.get().options().copyDefaults(true);
    }
}