package dev.prinke.pmplus.Commands;

import dev.prinke.pmplus.PMPlus;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public class MuteCommand implements CommandExecutor {

    public static ArrayList<UUID> muted = new ArrayList<>();

    public static ArrayList<UUID> getToggled() {
        return muted;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("pmplus.mute")) {
                Player target = Bukkit.getPlayer(args[1]);
                if (!(target.hasPermission("pmplus.bypass"))) {
                    if (!muted.contains(target.getUniqueId())) {
                        muted.add(target.getUniqueId());
                        // send message to muter
                        String mute = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.MutedPlayer"));
                        mute = mute.replace("%target%", target.getDisplayName());
                        mute = mute.replace("%player%", p.getDisplayName());
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            mute = PlaceholderAPI.setPlaceholders(target, mute);
                        }
                        sender.sendMessage(mute);

                        // send message to muted player

                        StringBuilder sb = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        sb.setLength(sb.length() - 1);
                        String reason = sb.toString();

                        String muted = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.MuteMessage"));
                        muted = muted.replace("%player%", target.getDisplayName());
                        muted = muted.replace("%reason%", reason);
                        muted = muted.replace("%sender%", p.getDisplayName());
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            muted = PlaceholderAPI.setPlaceholders(target, muted);
                        }
                        sender.sendMessage(muted);
                    } else {
                        muted.remove(target.getUniqueId());
                        String unmute = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.UnmutedPlayer"));
                        unmute = unmute.replace("%player%", target.getDisplayName());
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            unmute = PlaceholderAPI.setPlaceholders(target, unmute);
                        }
                        sender.sendMessage(unmute);
                    }
                } else {
                    String noperm = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.BypassedMute"));
                    noperm = noperm.replace("%sender%", ((Player) sender).getDisplayName());
                    noperm = noperm.replace("%player%", p.getDisplayName());
                    if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                        noperm = PlaceholderAPI.setPlaceholders(target, noperm);
                    }
                    sender.sendMessage(noperm);
                }
            } else {
                String noperm = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.NoPermission"));
                noperm = noperm.replace("%sender%", ((Player) sender).getDisplayName());
                noperm = noperm.replace("%player%", p.getDisplayName());
                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    noperm = PlaceholderAPI.setPlaceholders(p, noperm);
                }
                sender.sendMessage(noperm);
            }
        }

        return true;
    }
}