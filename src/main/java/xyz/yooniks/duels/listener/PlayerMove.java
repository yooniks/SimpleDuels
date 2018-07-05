package xyz.yooniks.duels.listener;

import com.google.common.base.Objects;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.yooniks.duels.user.DuelUser;
import xyz.yooniks.duels.user.UserManager;
import xyz.yooniks.duels.util.BlockUtil;

public class PlayerMove implements Listener {

  private final UserManager userManager;

  public PlayerMove(UserManager userManager) {
    this.userManager = userManager;
  }

  @EventHandler(ignoreCancelled = true)
  public void onMove(PlayerMoveEvent event) {
    if (Objects.equal(event.getTo(), event.getFrom())) {
      return;
    }
    final Player player = event.getPlayer();
    final DuelUser user = this.userManager.getOrCreateUser(player);

    user.getDuel().ifPresent((duel) -> {
      if (duel.getStartLocation().distanceSquared(player.getLocation()) > 10) {
        BlockUtil.createIndividualWall(player);
      }
    });
  }

}
