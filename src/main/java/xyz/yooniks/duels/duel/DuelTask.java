package xyz.yooniks.duels.duel;

import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.yooniks.duels.DuelsPlugin;
import xyz.yooniks.duels.config.DuelSettings;
import xyz.yooniks.duels.user.User;
import xyz.yooniks.duels.user.UserManager;

public class DuelTask extends BukkitRunnable {

  private final Player playerA, playerB;

  private final UserManager userManager;

  private int count = DuelSettings.DUEL$TIME_TO_START;

  public DuelTask(DuelsPlugin plugin, Player playerA, Player playerB) {
    this.userManager = plugin.getUserManager();
    this.playerA = playerA;
    this.playerB = playerB;
    this.prepare();
    this.runTaskTimerAsynchronously(plugin, 0L, 20L);
  }

  @Override
  public void run() {
    if (!this.playerA.isOnline() || !this.playerB.isOnline()) {
      if (this.playerA.isOnline()) {
        final User userA = this.userManager.getOrCreateUser(this.playerA);
        userA.sendMessage(DuelSettings.MESSAGE$CHAT$DUEL_CANCELLED_ERROR);
      }
      else if (this.playerB.isOnline()) {
        final User userB = this.userManager.getOrCreateUser(this.playerB);
        userB.sendMessage(DuelSettings.MESSAGE$CHAT$DUEL_CANCELLED_ERROR);
      }
      this.cancel();
      return;
    }
    if (count <= 0) {
      final User userA = this.userManager.getOrCreateUser(this.playerA);
      final User userB = this.userManager.getOrCreateUser(this.playerB);

      final Duel duel = new Duel(this.userManager, this.playerA, this.playerB);
      userA.setDuel(duel);
      userB.setDuel(duel);

      this.cancel();
      return;
    }

    final User userA = this.userManager.getOrCreateUser(this.playerA);
    final User userB = this.userManager.getOrCreateUser(this.playerB);

    userA.sendMessage(
        StringUtils.replace(
        DuelSettings.MESSAGE$CHAT$DUEL_TIME_TO_START,
        "{SECONDS}",
        String.valueOf(this.count)));

    userB.sendMessage(
        StringUtils.replace(
            DuelSettings.MESSAGE$CHAT$DUEL_TIME_TO_START,
            "{SECONDS}",
            String.valueOf(this.count)));


    this.count--;
  }

  private void prepare() {
    this.playerA.teleport(DuelSettings.DUEL$LOCATION_A, TeleportCause.PLUGIN);
    this.playerB.teleport(DuelSettings.DUEL$LOCATION_B, TeleportCause.PLUGIN);

    final User userA = this.userManager.getOrCreateUser(this.playerA);
    final User userB = this.userManager.getOrCreateUser(this.playerB);

    userA.addDuelItems();
    userB.addDuelItems();

    userA.heal();
    userB.heal();
  }

}
