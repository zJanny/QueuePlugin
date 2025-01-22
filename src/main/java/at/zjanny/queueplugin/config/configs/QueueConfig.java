package at.zjanny.queueplugin.config.configs;

import at.zjanny.queueplugin.config.Config;

public class QueueConfig extends Config {
    private final String targetServer;
    private final String targetServerIP;
    private final int targetServerPort;
    private final int targetServerMaxPlayers;

    public QueueConfig() {
        super("queue.yml");

        targetServer = config.getString("queue.target-server");
        targetServerIP = config.getString("queue.target-server-ip");
        targetServerPort = config.getInt("queue.target-server-port");
        targetServerMaxPlayers = config.getInt("queue.target-server-max-players");
    }

    public String getTargetServer() {
        return targetServer;
    }

    public String getTargetServerIP() {
        return targetServerIP;
    }

    public int getTargetServerPort() {
        return targetServerPort;
    }

    public int getTargetServerMaxPlayers() {
        return targetServerMaxPlayers;
    }
}
