package dev.prinke.pmplus.Commands;

import dev.prinke.pmplus.PMPlus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

public class SoundToggleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (PMPlus.plugin.getConfig().getBoolean("Options.SoundOnMessage")) {
                if (p.hasMetadata("sound_off")) {
                    p.removeMetadata("sound_off", PMPlus.plugin);
                    String soundtoggledon = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.SoundToggledOn"));
                    soundtoggledon = soundtoggledon.replace("%sender%", ((Player) sender).getDisplayName());
                    p.sendMessage(soundtoggledon);
                } else {
                    p.setMetadata("sound_off", new FixedMetadataValue(PMPlus.plugin, true));
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
