package at.zjanny.queueplugin.queue;

import at.zjanny.queueplugin.QueuePlugin;
import at.zjanny.queueplugin.config.configs.QueueConfig;
import at.zjanny.queueplugin.helpers.ServerStatus;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class PlayerQueue {
    private Queue<Player> playerQueue = new LinkedList<>();
    private static PlayerQueue INSTANCE;
    private final ServerStatus serverStatus;
    private final QueueConfig queueConfig;

    private PlayerQueue(){
        queueConfig = new QueueConfig();
        serverStatus = new ServerStatus(queueConfig);
    }

    public static PlayerQueue getPlayerQueue(){
        if(Objects.isNull(INSTANCE)){
            INSTANCE = new PlayerQueue();
        }

        return INSTANCE;
    }

    public void addPlayer(Player player){
        playerQueue.add(player);
    }

    public void removePlayer(Player player){
        playerQueue.remove(player);
    }

    private void sendPlayersToServer(int count){
        Bukkit.getScheduler().runTask(QueuePlugin.getPlugin(), new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < count; i++){
                    if(playerQueue.isEmpty()) return;

                    Player player = playerQueue.poll();
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();

                    out.writeUTF("Connect");
                    out.writeUTF(queueConfig.getTargetServer());

                    player.sendPluginMessage(QueuePlugin.getPlugin(), "BungeeCord", out.toByteArray());
                }
            }
        });
    }

    private void updatePlayers(){
        Bukkit.getScheduler().runTask(QueuePlugin.getPlugin(), new Runnable() {
            @Override
            public void run() {
                int place = 0;
                for(Player player : playerQueue){
                    player.sendMessage("§cSERVER FULL");
                    player.sendMessage("§aPlace in queue §6" + place);

                    place++;
                }
            }
        });
    }

    private void serverDownMessage(){
        Bukkit.getScheduler().runTask(QueuePlugin.getPlugin(), new Runnable() {
            @Override
            public void run() {
                int place = 0;
                for(Player player : playerQueue){
                    player.sendMessage("§cMain server is down");

                    place++;
                }
            }
        });
    }

    public void startQueue(){
        Bukkit.getScheduler().runTaskTimerAsynchronously(QueuePlugin.getPlugin(), new Runnable() {
            @Override
            public void run() {
                int openSlots = serverStatus.getOpenSlots();
                if(openSlots == -1) {
                    serverDownMessage();
                    return;
                }

                sendPlayersToServer(openSlots);
                updatePlayers();
            }

        }, 0, 20 * 5);
    }
}
