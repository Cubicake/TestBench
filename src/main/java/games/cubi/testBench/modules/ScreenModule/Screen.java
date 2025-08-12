package games.cubi.testBench.modules.ScreenModule;

import games.cubi.testBench.TestBench;
import games.cubi.testBench.modules.Modules;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;
import org.joml.Vector3f;

public class Screen {
    private final NamespacedKey key;

    public Screen(Player player) {
        preparePlayer(player);
        key = new NamespacedKey("test","lmbdowm");
    }

    public void startCursor(Player p, Location screenLoc) {

        // --- 1. Create the display entity -------------------------------------------------
        ItemDisplay cursor = screenLoc.getWorld().spawn(screenLoc, ItemDisplay.class);

        Transformation tf = cursor.getTransformation();
        tf.getScale().set(new Vector3f(0.2f, 0.2f, 0.2f));
        cursor.setTransformation(tf);

        cursor.setInterpolationDelay(0);
        cursor.setInterpolationDuration(3);


        cursor.setItemStack(new ItemStack(Material.STRUCTURE_VOID));


        // --- 3. Repeating task that keeps the cursor in sync ---------
        int taskId = new BukkitRunnable() {
            double oldx = 12;
            double oldy = 4;
            int tickCounter;
            @Override
            public void run() {
                tickCounter++;
                if (!p.isOnline()) {         // clean up if the player leaves
                    cursor.remove();
                    cancel();
                    return;
                }

                /* Map yaw (‑180↔180) ➜ 0↔360 ➜ 0‑15 */
                float rawYaw = p.getYaw();
                float yaw360 = (-rawYaw % 360 + 360) % 360;      // 0‑360
                double x = yaw360 / 360.0 * 20.0;

                /* Map pitch (‑90 up … +90 down) ➜ 0‑180 ➜ 0‑8 */
                float pitch = p.getPitch();
                double pitchNorm = (-pitch) + 90.0;              // 0‑180
                double y = pitchNorm / 180.0 * 10.0;

                // Show the coordinates in the action‑bar (debug)
                p.sendActionBar(Component.text(String.format("%.1f  %.1f, old: %.1f  %.1f", x, y, oldx, oldy)));

                // Teleport the display entity relative to the screen’s corner
                //cursor.teleport(screenCorner.clone().add(x, y, 0));
                if (oldx == 0 || oldy == 0) {oldx = 1; oldy = 1;}
                double change = Math.abs(oldx*oldy - x*y);
                if (change > 0.01) {
                    Bukkit.broadcast(MiniMessage.miniMessage().deserialize("" + change));
                    if (change < 5) {
                        boolean result = Boolean.TRUE.equals(p.getPersistentDataContainer().get(key, PersistentDataType.BOOLEAN));
                        if (result) {
                            tf.getScale().set(new Vector3f(0.1f, 0.1f, 0.1f));
                            p.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, false);
                        }
                        else {
                            tf.getScale().set(new Vector3f(0.2f, 0.2f, 0.2f));
                        }

                        tf.getTranslation().set(x - 10, y - 5, 0);
                        cursor.setTransformation(tf);
                        cursor.setInterpolationDelay(0);
                        oldx = x;
                        oldy = y;
                    }
                    else if (tickCounter % 20 == 0) {
                        oldx = x; //sometimes old and current values get out of sync and need to be fixed
                        oldy = y;
                    }
                }
            }
        }.runTaskTimer(TestBench.get(), 0L, 1L).getTaskId();
    }

    private void preparePlayer(Player player) {
        Location loc = player.getLocation();
        loc.setYaw(90f);
        loc.setPitch(0f);
        player.teleport(loc);

        Entity cameraEntity = player.getWorld().spawnEntity(player.getLocation(), EntityType.TEXT_DISPLAY);
        Entity ridingEntity = player.getWorld().spawnEntity(player.getLocation().add(0,5,0), EntityType.TEXT_DISPLAY);

        player.setGameMode(GameMode.SPECTATOR);
        player.setSpectatorTarget(cameraEntity);
        ridingEntity.addPassenger(player);
        player.setGameMode(GameMode.SPECTATOR);
        player.setInvisible(true);

        startCursor(player, player.getLocation().add(-5, -5, 0));
    }
}
