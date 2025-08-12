package games.cubi.testBench;

import com.destroystokyo.paper.event.player.PlayerStopSpectatingEntityEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDismountEvent;
import org.bukkit.event.player.PlayerInputEvent;
import org.bukkit.persistence.PersistentDataType;

public class EventsHandler implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerStopSpectating(PlayerStopSpectatingEntityEvent event) {
        event.setCancelled(true);
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void entityDismountEvent(EntityDismountEvent event) {
        event.setCancelled(true);
    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void inputEvent(PlayerInputEvent event) {
        Bukkit.broadcast(MiniMessage.miniMessage().deserialize("Input! "+event.getInput()));
        if (event.getInput().isLeft()) {
            Player player = event.getPlayer();
            player.getPersistentDataContainer().set(new NamespacedKey("test","lmbdown"), PersistentDataType.BOOLEAN, true);
            Bukkit.broadcast(MiniMessage.miniMessage().deserialize("sfgb"));
        }
    }
}
