package de.danielderechte.invsee.main;

import de.danielderechte.invsee.commands.InvSeeCommand;
import de.danielderechte.invsee.listeners.InvSeeListener;
import de.danielderechte.invsee.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class InvSee extends JavaPlugin {

    static InvSee instance;
    private Config config;

    public void onEnable(){

        Bukkit.getConsoleSender().sendMessage("§7[§5InvSee§7] §eThe plugin is loading...");
        try {
            register();
            config = new Config();
            Bukkit.getConsoleSender().sendMessage("§7[§5InvSee§7] §aPlugin loaded succesfully.");
            Bukkit.getConsoleSender().sendMessage("§7[§5InvSee§7] §eVersion: §f2.0.1");
            Bukkit.getConsoleSender().sendMessage("§7[§5InvSee§7] §3Developer§7: §bDanielDerEchte");
            Bukkit.getConsoleSender().sendMessage("§7[§5InvSee§7] §eCheck here for Updates: §7https://www.spigotmc.org/resources/invsee-plugin-with-config-1-10-1-16-1.74292/history");
            Bukkit.getConsoleSender().sendMessage("§7[§5InvSee§7] §eYou can get support here: §7https://discord.gg/nFpdJ5XWfG");
        }catch (Exception e){
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§7[§5InvSee§7] §cThe Plugin can't load! Please contact the Developer§8.");
        }

    }

    public void onDisable(){

    }

    private void register(){
        getCommand("invsee").setExecutor(new InvSeeCommand());
        Bukkit.getPluginManager().registerEvents(new InvSeeListener(), this);
    }

    public InvSee() { instance = this;}

    public static InvSee getInstance() {return instance;}

}
