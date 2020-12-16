package de.danielderechte.invsee.commands;

import de.danielderechte.invsee.main.InvSee;
import de.danielderechte.invsee.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.ibex.nestedvm.util.Seekable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class InvSeeCommand implements CommandExecutor {

    private Config config;
    private InvSee instance;
    public static ArrayList<String> uuid = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;
        if (!(commandSender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(config.getPrefix() + "&cYou can only use this command as a player&8.");
        }else {
            if (!player.hasPermission(InvSee.getInstance().getConfig().getString("PermissionForCommand"))){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', InvSee.getInstance().getConfig().getString("NoPermission")));
                return true;
            }

            if (args.length == 0){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', InvSee.getInstance().getConfig().getString("Prefix") + InvSee.getInstance().getConfig().getString("WrongSyntax")));
            }else if (args.length == 1){

                if (args[0].equalsIgnoreCase("reload")){
                    if (player.hasPermission(InvSee.getInstance().getConfig().getString("PermissionForReload"))){
                        File file = InvSee.getInstance().getDataFolder();
                        YamlConfiguration.loadConfiguration(file);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', InvSee.getInstance().getConfig().getString("Prefix")) + "§aYou have reloaded the config.yml.");
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', InvSee.getInstance().getConfig().getString("NoPermission")));
                    }
                    return true;
                }

                Player target = Bukkit.getPlayer(args[0]);

                if (target == null){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', InvSee.getInstance().getConfig().getString("Prefix") + InvSee.getInstance().getConfig().getString("PlayerNotOnline").replace("%PLAYER%", args[0])));
                }else {
                    Inventory inventory = target.getInventory();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', InvSee.getInstance().getConfig().getString("Prefix") + InvSee.getInstance().getConfig().getString("InventoryOpenedMessage").replace("%PLAYER%", args[0])));

                    if (InvSee.getInstance().getConfig().getBoolean("TargetMessage")){
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', InvSee.getInstance().getConfig().getString("Prefix") + InvSee.getInstance().getConfig().getString("OpenInventoryTargetMessage").replace("%PLAYER%", player.getName())));
                    }

                    if (!player.hasPermission(InvSee.getInstance().getConfig().getString("PermissionForMoveItems"))){
                        uuid.add(player.getUniqueId().toString());
                    }

                    player.openInventory(inventory);
                }
            }else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', InvSee.getInstance().getConfig().getString("Prefix") + InvSee.getInstance().getConfig().getString("WrongSyntax")));
            }
        }

        return false;
    }

}
