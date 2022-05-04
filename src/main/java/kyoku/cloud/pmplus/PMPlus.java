package kyoku.cloud.pmplus;

import kyoku.cloud.pmplus.Commands.*;
import lombok.Getter;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class PMPlus extends JavaPlugin implements CommandExecutor, Listener {

    public messageManager mM;
    @Getter static List<Player> spies = new ArrayList<Player>();

    public static PMPlus plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getCommand("message").setExecutor(new MessageCommand(this));
        getCommand("togglepms").setExecutor(new ToggleCommand());
        getCommand("socialspy").setExecutor(new SocialSpyCommand());
        getCommand("reply").setExecutor(new ReplyCommand(this));
        mM = new messageManager(this);
        createFiles();
    }

    private File configf;
    private FileConfiguration config;

    private void createFiles() {
        configf = new File(getDataFolder(), "config.yml");

        if (!configf.exists()) {
            configf.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        config = new YamlConfiguration();

        try {
            config.load(configf);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}