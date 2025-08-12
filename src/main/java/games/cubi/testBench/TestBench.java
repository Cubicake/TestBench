package games.cubi.testBench;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

public final class TestBench extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> event.registrar().register("testbench", new Commands(this)));

        getServer().getPluginManager().registerEvents(new EventsHandler(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
