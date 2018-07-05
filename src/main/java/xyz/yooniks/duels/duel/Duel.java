package xyz.yooniks.duels.duel;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import xyz.yooniks.duels.config.DuelSettings;
import xyz.yooniks.duels.event.DuelStartEvent;
import xyz.yooniks.duels.user.DuelUser;
import xyz.yooniks.duels.user.UserManager;

public class Duel {

  private final DuelUser victim, opponent;
  private final UserManager userManager;

  private final Location startLocation;

  public Duel(UserManager userManager, DuelUser victim, DuelUser opponent, Location startLocation) {
    this.userManager = userManager;
    this.victim = victim;
    this.opponent = opponent;

    this.startLocation = startLocation;
  }

  public void start() {
    if (!this.victim.getBukkitPlayer().isPresent() || !this.opponent.getBukkitPlayer()
        .isPresent()) {
      this.end();
      return;
    }
    Bukkit.getPluginManager().callEvent(new DuelStartEvent(this.victim.getBukkitPlayer().get(),
        this.opponent.getBukkitPlayer().get()));
    this.victim.sendMessage(DuelSettings.MESSAGE$CHAT$STARTED);
    this.opponent.sendMessage(DuelSettings.MESSAGE$CHAT$STARTED);
    //...
  }

  public void end() {

  }

  public void cancel() {
    this.victim.getBukkitPlayer().ifPresent(playerB -> {
      this.victim.heal();
    });
    this.opponent.getBukkitPlayer().ifPresent(playerB -> {
      this.opponent.heal();
    });
  }

  public DuelUser getOpponent() {
    return this.opponent;
  }

  public DuelUser getVictim() {
    return this.victim;
  }

  public Location getStartLocation() {
    return this.startLocation;
  }

}
