package dev.prinke.pmplus.Commands;

import dev.prinke.pmplus.PMPlus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;


public class ToggleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasMetadata("messages_off")) {
                p.removeMetadata("messages_off", PMPlus.plugin);
                String msgtoggledon = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.MessagesToggledOn"));
                msgtoggledon = msgtoggledon.replace("%sender%", ((Player) sender).getDisplayName());
                p.sendMessage(msgtoggledon);
            } else {
                p.setMetadata("messages_off", new FixedMetadataValue(PMPlus.plugin, true));
                String msgtoggledoff = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.MessagesToggledOff"));
                msgtoggledoff = msgtoggledoff.replace("%sender%", ((Player) sender).getDisplayName());
                p.sendMessage(msgtoggledoff);
            }
        } else {
            Bukkit.getServer().getLogger().info(ChatColor.RED + "Only players can use this command.");
        }

        return true;
    }
}
