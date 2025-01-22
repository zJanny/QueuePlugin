package at.zjanny.queueplugin;

import at.zjanny.queueplugin.events.EventHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class QueuePlugin extends JavaPlugin {
    private static QueuePlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        registerListener();
    }

    private void registerListener(){
        Bukkit.getPluginManager().registerEvents(new EventHandler(), this);
    }

    public static QueuePlugin getPlugin(){
        return plugin;
    }
}
