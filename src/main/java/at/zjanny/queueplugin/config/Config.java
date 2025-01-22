package at.zjanny.queueplugin.config;

import at.zjanny.queueplugin.QueuePlugin;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    protected File file;
    protected FileConfiguration config;
    protected final QueuePlugin plugin = QueuePlugin.getPlugin();
    private final String configName;

    public Config(String configName){
        this.configName = configName;
        init();
    }

    private void init(){
        file = new File(plugin.getDataFolder(), configName);
        if(!file.exists()){
            file.getParentFile().mkdirs();
            plugin.saveResource(configName, false);
        }

        config = new YamlConfiguration();
        try{
            config.load(file);
        } catch(IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }
    }

    protected void save(){
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
