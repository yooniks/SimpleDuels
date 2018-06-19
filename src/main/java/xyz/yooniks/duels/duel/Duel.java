package xyz.yooniks.duels.duel;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import xyz.yooniks.duels.config.DuelSettings;
import xyz.yooniks.duels.user.DuelUser;
import xyz.yooniks.duels.user.UserManager;

public class Duel {

  private final Player playerA, playerB;
  private final UserManager userManager;

  public Duel(UserManager userManager, Player playerA, Player playerB) {
    this.userManager = userManager;
    this.playerA = playerA;
    this.playerB = playerB;

    this.start();
  }

  public void start() {
    final DuelUser userA = this.userManager.getOrCreateUser(this.playerA);
    final DuelUser userB = this.userManager.getOrCreateUser(this.playerB);

    userA.sendMessage(DuelSettings.MESSAGE$CHAT$STARTED);
    userB.sendMessage(DuelSettings.MESSAGE$CHAT$STARTED);
    //...
  }

  public void end() {

  }

  public void cancel() {
    if (this.playerA.isOnline()) {
      final DuelUser userA = this.userManager.getOrCreateUser(this.playerA);
      userA.heal();
      this.playerA.teleport(DuelSettings.DUEL$LOCATION_A, TeleportCause.PLUGIN);
    }
    else if (this.playerB.isOnline()) {
      final DuelUser userB = this.userManager.getOrCreateUser(this.playerB);
      userB.heal();
      this.playerB.teleport(DuelSettings.DUEL$LOCATION_B, TeleportCause.PLUGIN);
    }
  }

}
