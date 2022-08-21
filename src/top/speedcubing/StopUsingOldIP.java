package top.speedcubing;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.lang.reflect.Field;

public class StopUsingOldIP extends JavaPlugin implements Listener {
    TextComponent build;

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        build = new TextComponent("§8--------------------------------------------\n§bWe're changing domain name!\n§aPlease use ");
        TextComponent component = new TextComponent("§6§lspeedcubing.top");
        component.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "speedcubing.top"));
        build.addExtra(component);
        build.addExtra(" §ainstead!\n§b我們換了server ip!\n§a請用 ");
        build.addExtra(component);
        build.addExtra(" §a重新登入!\n§8--------------------------------------------");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.spigot().sendMessage(build);
            }
        }, 0, 100);
    }

    @EventHandler
    public void wddw(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void dwdw(ServerListPingEvent e) {
        e.setMotd("§bWe're changing domain name!\n§aPlease use §6§lspeedcubing.top §ainstead!");
    }

    @EventHandler
    public void dw(PlayerMoveEvent e) {
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE && (Math.abs(e.getTo().getX()) > 100 || Math.abs(e.getTo().getZ()) > 100))
            e.getPlayer().teleport(e.getFrom());
    }

    @EventHandler
    public void dwawd(EntityDamageEvent e) {
//        if (e.getEntity() instanceof Player)
//            e.setCancelled(true);
    }

    @EventHandler
    public void dwd(PlayerRespawnEvent e) {
        e   .setRespawnLocation(new Location(Bukkit.getWorld("world"), 0.5, 4, 0.5, 0, 0));
        e.getPlayer().setAllowFlight(true);
    }

    @EventHandler
    public void a(PlayerJoinEvent e) throws Exception {
        Player player = e.getPlayer();
        e.setJoinMessage("");
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        scoreboard.registerNewObjective("§c❤", "health").setDisplaySlot(DisplaySlot.BELOW_NAME);
        player.setScoreboard(scoreboard);
        player.setGameMode(GameMode.ADVENTURE);
        player.setAllowFlight(true);
        PacketPlayOutPlayerListHeaderFooter headerFooter = new PacketPlayOutPlayerListHeaderFooter(new ChatComponentText("§6speedcubing.top"));
        Field f = headerFooter.getClass().getDeclaredField("b");
        f.setAccessible(true);
        player.teleport(new Location(player.getWorld(), 0.5, 4, 0.5, 0, 0));
        f.set(headerFooter, new ChatComponentText("§6speedcubing.top"));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(headerFooter);
        player.sendTitle("§bWe're changing domain name!", "§aPlease use §6speedcubing.top §ainstead!");
    }
}
