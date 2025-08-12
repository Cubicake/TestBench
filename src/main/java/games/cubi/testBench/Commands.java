package games.cubi.testBench;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.RegionAccessor.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commands implements BasicCommand {
    TestBench plugin;

    public Commands(TestBench bench) {
        plugin = bench;
    }

    @Override
    public void execute(CommandSourceStack commandSourceStack, String @NotNull [] args) {
        Player player = (Player) commandSourceStack.getExecutor();


        int SCREEN_WIDTH = 10;
        double DISTANCE_TO_SCREEN = SCREEN_WIDTH/2.606;

        new Screen(plugin, player);
    }
}