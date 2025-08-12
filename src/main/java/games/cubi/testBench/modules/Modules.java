package games.cubi.testBench.modules;

import games.cubi.testBench.TestBench;

public class Modules {
    public static void enableModules() {
        games.cubi.testBench.modules.ScreenModule.Main.onEnable();
        games.cubi.testBench.modules.TemplateModule.Main.onEnable();
    }
    public static void disableModules() {
        games.cubi.testBench.modules.ScreenModule.Main.onDisable();
        games.cubi.testBench.modules.TemplateModule.Main.onDisable();
    }
}
