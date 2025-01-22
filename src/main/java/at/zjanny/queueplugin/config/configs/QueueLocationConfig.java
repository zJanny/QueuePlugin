package at.zjanny.queueplugin.config.configs;

import at.zjanny.queueplugin.config.Config;
import org.bukkit.Location;

public class QueueLocationConfig extends Config {

    public QueueLocationConfig() {
        super("location.yml");
    }

    public Location getQueueLocation(){
        return config.getLocation("location");
    }

    public void setQueueLocation(Location location){
        config.set("location", location);

        super.save();
    }
}
