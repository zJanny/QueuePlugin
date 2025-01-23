package at.zjanny.queueplugin;

import at.zjanny.queueplugin.commands.SetQueueLocationCommand;
import at.zjanny.queueplugin.config.configs.QueueLocationConfig;
import at.zjanny.queueplugin.events.EventHandler;
import at.zjanny.queueplugin.queue.PlayerQueue;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class QueuePlugin extends JavaPlugin {
    private static QueuePlugin plugin;
    private QueueLocationConfig queueLocationConfig;

    @Override
    public void onEnable() {
        plugin = this;
        queueLocationConfig = new QueueLocationConfig();

        registerListener();
        registerCommands();
        registerProxyChannel();

        PlayerQueue.getPlayerQueue().startQueue();
    }

    private void registerCommands(){
        getCommand("setlocation").setExecutor(new SetQueueLocationCommand(queueLocationConfig));
    }

    private void registerProxyChannel(){
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    private void registerListener(){
        Bukkit.getPluginManager().registerEvents(new EventHandler(queueLocationConfig), this);
    }

    public static QueuePlugin getPlugin(){
        return plugin;
    }

    public QueueLocationConfig getQueueLocationConfig() {
        return queueLocationConfig;
    }
}
