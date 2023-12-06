package dev.prinke.pmplus.Commands;

import de.myzelyam.api.vanish.VanishAPI;
import dev.prinke.pmplus.PMPlus;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand implements CommandExecutor {

    PMPlus plugin;

    public ReplyCommand(PMPlus pmPlus) {
        plugin = pmPlus;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("pmplus.reply")) {

                // check if super vanish or premium vanish is enabled
                boolean hasVanishPlugin = false;
                if (Bukkit.getPluginManager().isPluginEnabled("SuperVanish") || Bukkit.getPluginManager().isPluginEnabled("PremiumVanish")) {
                    hasVanishPlugin = true;
                }

                // check if the recipient is vanished
                if (PMPlus.plugin.getConfig().getBoolean("Options.VanishSupport") == true) {
                    if (hasVanishPlugin) {
                        if (VanishAPI.isInvisible(plugin.mM.getReplyTarget((Player) sender)) && !(sender.hasPermission("pmplus.bypass"))) {
                            String cannotfind = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.NoReplyTarget"));
                            cannotfind = cannotfind.replace("%sender%", ((Player) sender).getDisplayName());
                            cannotfind = cannotfind.replace("%recipient%", args[0]);
                            sender.sendMessage(cannotfind);
                            return true;
                        }
                    }
                }

                // check to see if the sender if muted
                if (((Player) sender).hasMetadata("muted")) {
                    String muted = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.Muted"));
                    muted = muted.replace("%sender%", ((Player) sender).getDisplayName());
                    sender.sendMessage(muted);
                    return true;
                }

                if (plugin.mM.getReplyTarget((Player) sender) == null) {
                    String noreplytarget = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.NoReplyTarget"));
                    noreplytarget = noreplytarget.replace("%sender%", ((Player) sender).getDisplayName());
                    sender.sendMessage(noreplytarget);
                    return true;
                }
                if (args.length > 0) {
                    Player recipient = plugin.mM.getReplyTarget( (Player) sender);
                    Player sendr = (Player) sender;
                    if (sender.hasPermission("pmplus.bypass") || !(recipient.hasMetadata("messages_off"))) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        sb.setLength(sb.length() - 1);
                        String message = sb.toString();

                        // send the message to the sender
                        String msg = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.MessageToSender"));
                        msg = msg.replace("%sender%", ((Player) sender).getDisplayName());
                        msg = msg.replace("%message%", message);
                        msg = msg.replace("%recipient%", recipient.getDisplayName());
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            msg = PlaceholderAPI.setPlaceholders(recipient, msg);
                        }
                        sender.sendMessage(msg);

                        // send message to the recipient
                        String msgto = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.MessageToRecipient"));
                        msgto = msgto.replace("%sender%", ((Player) sender).getDisplayName());
                        msgto = msgto.replace("%message%", message);
                        msgto = msgto.replace("%recipient%", recipient.getDisplayName());
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            msgto = PlaceholderAPI.setPlaceholders(sendr, msgto);
                        }
                        recipient.sendMessage(msgto);
                        if (PMPlus.plugin.getConfig().getBoolean("Options.SoundOnMessage") == true && !(recipient.hasMetadata("sound_off"))) {
                            recipient.playSound(recipient.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        }

                        // send message to players with social spy
                        for (Player spy : Bukkit.getOnlinePlayers()) {
                            if (spy.hasMetadata("message_spy")) {
                                if (!spy.getName().equals(sender.getName()) && !spy.getName().equals(recipient.getName())) {
                                    String msgspy = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("SocialSpyFormat"));
                                    msgspy = msgspy.replace("%sender%", ((Player) sender).getDisplayName());
                                    msgspy = msgspy.replace("%message%", message);
                                    msgspy = msgspy.replace("%receiver%", recipient.getDisplayName());
                                    spy.sendMessage(msgspy);
                                }
                            }
                        }
                    } else {
                        String msgtoggled = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("Messages.RecipientMessagesOff"));
                        msgtoggled = msgtoggled.replace("%recipient%", recipient.getDisplayName());
                        msgtoggled = msgtoggled.replace("%sender%", ((Player) sender).getDisplayName());
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            msgtoggled = PlaceholderAPI.setPlaceholders((Player) sender, msgtoggled);
                        }
                        sender.sendMessage(msgtoggled);
                    }
                } else {
                    String invalidargsreply = ChatColor.translateAlternateColorCodes('&', PMPlus.plugin.getConfig().getString("ReplyCommandUsage"));
                    invalidargsreply = invalidargsreply.replace("%sender%", ((Player) sender).getDisplayName());
                    sender.sendMessage(invalidargsreply);
                }
            } else {
                Bukkit.getServer().getLogger().info(ChatColor.RED + "Only players can use this command.");
            }
        } else {
            Bukkit.getServer().getLogger().info(ChatColor.RED + "Only players can use this command.");
        }
        return true;
    }
}
