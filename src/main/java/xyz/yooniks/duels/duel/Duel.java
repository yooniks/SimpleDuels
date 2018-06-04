package xyz.yooniks.duels.duel;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import xyz.yooniks.duels.DuelsPlugin;
import xyz.yooniks.duels.config.DuelSettings;
import xyz.yooniks.duels.user.User;

public class Duel {

  private final Player playerA, playerB;
  private final DuelsPlugin plugin;

  public Duel(DuelsPlugin plugin, Player playerA, Player playerB) {
    this.plugin = plugin;
    this.playerA = playerA;
    this.playerB = playerB;
  }

  public void start() {
  }

  public void end() {

  }

  public void cancel() {
    if (this.playerA.isOnline()) {
      final User userA = this.plugin.getUserManager().getOrCreateUser(this.playerA);
      userA.heal();
      this.playerA.teleport(DuelSettings.DUEL$LOCATION, TeleportCause.PLUGIN);
    }
  }

}
