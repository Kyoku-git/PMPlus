package dev.prinke.pmplus.Commands;

import dev.prinke.pmplus.PMPlus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public class SoundToggleCommand implements CommandExecutor {

    public static ArrayList<UUID> soundtoggled = new ArrayList<>();

    public static ArrayList<UUID> getSoundtoggled() {
        return soundtoggled;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (PMPlus.plugin.getConfig().getBoolean("Options.SoundOnMessage")) {
                if (soundtoggled.contains(p.getUniqueId())) {
                    soundtoggled.remove(p.getUniqueId());
                    String soundtoggledon = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.SoundToggledOn"));
                    soundtoggledon = soundtoggledon.replace("%sender%", ((Player) sender).getDisplayName());
                    p.sendMessage(soundtoggledon);
                } else {
                    soundtoggled.add(p.getUniqueId());
                    String soundtoggledoff = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.SoundToggledOff"));
                    soundtoggledoff = soundtoggledoff.replace("%sender%", ((Player) sender).getDisplayName());
                    p.sendMessage(soundtoggledoff);
                }
            } else {
                String sounddisabled = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.SoundDisabled"));
                sounddisabled = sounddisabled.replace("%sender%", ((Player) sender).getDisplayName());
                p.sendMessage(sounddisabled);
            }
        }
        return true;
    }
}