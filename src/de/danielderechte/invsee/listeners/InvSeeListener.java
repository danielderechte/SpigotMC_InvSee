package de.danielderechte.invsee.listeners;

import de.danielderechte.invsee.commands.InvSeeCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InvSeeListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if (InvSeeCommand.uuid.contains(player.getUniqueId().toString())){
            event.setCancelled(true);
        }else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();

        if (InvSeeCommand.uuid.contains(player.getUniqueId().toString())){
            InvSeeCommand.uuid.remove(player.getUniqueId().toString());
        }

    }

}
