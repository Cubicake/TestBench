package games.cubi.testBench.modules.TemplateModule;

import games.cubi.testBench.TestBench;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

import static org.bukkit.Bukkit.getServer;

public class Main {

    public static void onEnable() {
        TestBench testBench = TestBench.get();
        // Plugin startup logic
        testBench.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> event.registrar().register("templatebench", new Commands()));

        getServer().getPluginManager().registerEvents(new EventsHandler(), testBench);
    }

    public static void onDisable() {
        // Plugin shutdown logic
    }

}
