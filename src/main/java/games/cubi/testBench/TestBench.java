package games.cubi.testBench;

import games.cubi.testBench.modules.Modules;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestBench extends JavaPlugin {
    private static TestBench testBench;
    @Override
    public void onEnable() {
        testBench = this;
        Modules.enableModules();
    }

    @Override
    public void onDisable() {
        Modules.disableModules();
    }
    public static TestBench get() {
        return testBench;
    }
}
