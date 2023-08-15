package dev.prinke.pmplus;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class messageManager {

    PMPlus plugin;

    HashMap<Player, Player> conversations = new HashMap<Player, Player>();

    public messageManager(PMPlus pmPlus) {
        plugin = pmPlus;
    }

    public void setReplyTarget(Player sender, Player recipient) {
        conversations.put(sender, recipient);
        conversations.put(recipient, sender);
    }

    public Player getReplyTarget(Player sender) {
        return conversations.get(sender);
    }

}
