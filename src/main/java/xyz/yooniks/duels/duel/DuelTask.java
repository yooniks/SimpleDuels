package xyz.yooniks.duels.duel;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.yooniks.duels.DuelsPlugin;
import xyz.yooniks.duels.ItemsManager;
import xyz.yooniks.duels.config.DuelSettings;
import xyz.yooniks.duels.user.DuelUser;
import xyz.yooniks.duels.user.UserManager;

public class DuelTask extends BukkitRunnable {

  private final Player playerA, playerB;

  private final UserManager userManager;
  private final ItemsManager itemsManager;

  private int count = DuelSettings.DUEL$TIME_TO_START;

  public DuelTask(DuelsPlugin plugin, Player playerA, Player playerB) {
    Validate.notNull(plugin, "Plugin cannot be null!");

    this.userManager = plugin.getUserManager();
    this.itemsManager = plugin.getItemsManager();

    this.playerA = playerA;
    this.playerB = playerB;

    this.prepare();
    this.runTaskTimerAsynchronously(plugin, 0L, 20L);
  }

  @Override
  public void run() {
    if (!this.playerA.isOnline() || !this.playerB.isOnline()) {
      if (this.playerA.isOnline()) {
        final DuelUser userA = this.userManager.getOrCreateUser(this.playerA);
        userA.sendMessage(DuelSettings.MESSAGE$CHAT$DUEL_CANCELLED_ERROR);
      }
      else if (this.playerB.isOnline()) {
        final DuelUser userB = this.userManager.getOrCreateUser(this.playerB);
        userB.sendMessage(DuelSettings.MESSAGE$CHAT$DUEL_CANCELLED_ERROR);
      }
      this.cancel();
      return;
    }
    if (count <= 0) {
      final DuelUser userA = this.userManager.getOrCreateUser(this.playerA);
      final DuelUser userB = this.userManager.getOrCreateUser(this.playerB);

      final Duel duel = new Duel(this.userManager, this.playerA, this.playerB);
      userA.setDuel(duel);
      userB.setDuel(duel);

      this.cancel();
      return;
    }

    final DuelUser userA = this.userManager.getOrCreateUser(this.playerA);
    final DuelUser userB = this.userManager.getOrCreateUser(this.playerB);

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

    final DuelUser userA = this.userManager.getOrCreateUser(this.playerA);
    final DuelUser userB = this.userManager.getOrCreateUser(this.playerB);

    userA.addDuelItems(this.itemsManager);
    userB.addDuelItems(this.itemsManager);

    userA.heal();
    userB.heal();
  }

}
