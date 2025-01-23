package at.zjanny.queueplugin.commands;

import at.zjanny.queueplugin.config.configs.QueueLocationConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetQueueLocationCommand implements CommandExecutor {
    private final QueueLocationConfig queueLocationConfig;

    public SetQueueLocationCommand(QueueLocationConfig queueLocationConfig) {
        this.queueLocationConfig = queueLocationConfig;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(commandSender instanceof Player){
            Player player = (Player)commandSender;

            if(!player.isOp()) return false;

            queueLocationConfig.setQueueLocation(player.getLocation());

            player.sendMessage("§6Queue location has been set");
        }
        return false;
    }
}
