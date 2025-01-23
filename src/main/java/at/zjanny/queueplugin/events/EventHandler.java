package at.zjanny.queueplugin.events;

import at.zjanny.queueplugin.QueuePlugin;
import at.zjanny.queueplugin.config.configs.QueueLocationConfig;
import at.zjanny.queueplugin.queue.PlayerQueue;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class EventHandler implements Listener {
    private final QueueLocationConfig queueLocationConfig;
    private final PlayerQueue playerQueue;

    public EventHandler(QueueLocationConfig queueLocationConfig) {
        this.queueLocationConfig = queueLocationConfig;
        this.playerQueue = PlayerQueue.getPlayerQueue();
    }

    @org.bukkit.event.EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        playerQueue.addPlayer(event.getPlayer());
        event.getPlayer().setGameMode(GameMode.SPECTATOR);

        for(Player player : Bukkit.getOnlinePlayers()){
            for(Player target : Bukkit.getOnlinePlayers()){
                if(player.equals(target)) continue;

                player.hidePlayer(QueuePlugin.getPlugin(), target);
            }
        }


        event.getPlayer().sendMessage("§aPlease wait while we connect you to the server");
        event.joinMessage(null);
    }

    @org.bukkit.event.EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        playerQueue.removePlayer(event.getPlayer());

        event.quitMessage(null);
    }

    @org.bukkit.event.EventHandler
    public void onPlayerSpawnLocation(PlayerSpawnLocationEvent event){
        event.setSpawnLocation(queueLocationConfig.getQueueLocation());
    }

    @org.bukkit.event.EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if(!event.getPlayer().isOp()){
            event.setCancelled(true);
        }
    }
}
