package main.OreGenUltimate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class OreGenUltimate extends JavaPlugin {
    public final Logger log = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {
        new Listeners(this);
        getCommand("ogu").setExecutor(new Commands(this));
        if(!getDataFolder().exists()) {
            getConfig().options().copyDefaults(true);
            getConfig().set("Worlds", addWorlds());
                saveConfig();
            }
    }

    public List<String> addWorlds() {
        List<String> worldList = new ArrayList<String>();
        for (World w : getServer().getWorlds()) {
            worldList.add(w.getName());
        }
        return worldList;
    }
}