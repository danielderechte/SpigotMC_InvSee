package de.danielderechte.invsee.utils;

import de.danielderechte.invsee.main.InvSee;

import java.io.File;

public class Config {

    public Config(){
        load();
    }

    private void load(){
        if (!InvSee.getInstance().getDataFolder().exists()){
            InvSee.getInstance().getDataFolder().mkdir();
        }

        if (!(new File(InvSee.getInstance().getDataFolder(), "config.yml").exists())){
            InvSee.getInstance().saveDefaultConfig();
        }
    }

    public String getPrefix() {
        return InvSee.getInstance().getConfig().getString("Prefix");
    }



}
