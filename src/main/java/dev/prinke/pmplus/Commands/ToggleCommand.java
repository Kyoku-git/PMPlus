package dev.prinke.pmplus.Commands;

import dev.prinke.pmplus.PMPlus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ToggleCommand implements CommandExecutor {

    public static ArrayList<String> toggled = new ArrayList<>();

    public static ArrayList<String> getToggled() {
        return toggled;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (toggled.contains(p.getName())) {
                toggled.remove(p.getName());
                String msgtoggledon = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.MessagesToggledOn"));
                msgtoggledon = msgtoggledon.replace("%sender%", ((Player) sender).getDisplayName());
                p.sendMessage(msgtoggledon);
            } else {
                toggled.add(p.getName());
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
