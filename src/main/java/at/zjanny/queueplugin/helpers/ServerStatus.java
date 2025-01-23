package at.zjanny.queueplugin.helpers;

import at.zjanny.queueplugin.config.configs.QueueConfig;
import me.dilley.MineStat;

public class ServerStatus {
    private final QueueConfig config;

    public ServerStatus(QueueConfig config) {
        this.config = config;
    }

    public int getOpenSlots(){
        MineStat server = new MineStat(config.getTargetServerIP(), config.getTargetServerPort());
        if(!server.isServerUp()) return -1;

        return config.getTargetServerMaxPlayers() - server.getCurrentPlayers();
    }
}
