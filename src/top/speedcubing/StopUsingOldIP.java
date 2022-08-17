package top.speedcubing;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class StopUsingOldIP extends JavaPlugin implements Listener {
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void a(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.setGameMode(GameMode.ADVENTURE);
        player.setAllowFlight(true);
        player.sendMessage("§bWe're changing domain name!\n§aPlease use §6speedcubing.top §aInstead!");
    }
}
