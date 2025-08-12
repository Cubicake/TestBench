package games.cubi.testBench.modules.ScreenModule;

import games.cubi.testBench.TestBench;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

import static org.bukkit.Bukkit.getServer;

public class Main {

    public static void onEnable() {
        // Plugin startup logic
        TestBench.get().getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> event.registrar().register("screenbench", new Commands()));

        getServer().getPluginManager().registerEvents(new EventsHandler(), TestBench.get());
    }

    public static void onDisable() {
        // Plugin shutdown logic
    }

}
