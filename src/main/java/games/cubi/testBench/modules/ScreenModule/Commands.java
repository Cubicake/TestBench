package games.cubi.testBench.modules.ScreenModule;

import games.cubi.testBench.TestBench;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commands implements BasicCommand {

    public Commands() {
    }

    @Override
    public void execute(CommandSourceStack commandSourceStack, String @NotNull [] args) {
        Player player = (Player) commandSourceStack.getExecutor();

        new Screen(player);
    }
}