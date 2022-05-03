package kyoku.cloud.pmplus.Commands;

import kyoku.cloud.pmplus.PMPlus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToggleCommand implements CommandExecutor {

    public static List<UUID> toggled = new ArrayList<>();

    public static List<UUID> getToggled() {
        return toggled;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!toggled.contains(((Player) sender).getUniqueId())) {
                toggled.add(p.getUniqueId());
                String msgtoggledoff = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.MessagesToggledOff"));
                msgtoggledoff = msgtoggledoff.replace("%sender%", ((Player) sender).getDisplayName());
                p.sendMessage(msgtoggledoff);
            } else {
                toggled.remove(p.getUniqueId());
                String msgtoggledon = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.MessagesToggledOn"));
                msgtoggledon = msgtoggledon.replace("%sender%", ((Player) sender).getDisplayName());
                p.sendMessage(msgtoggledon);
            }
        }

        return true;
    }
}
