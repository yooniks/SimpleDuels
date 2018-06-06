package xyz.yooniks.duels.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DuelStartEvent extends Event {

  private static final HandlerList handlers = new HandlerList();

  private final Player playerA, playerB;

  public DuelStartEvent(Player playerA, Player playerB) {
    this.playerA = playerA;
    this.playerB = playerB;
  }

  public Player getPlayerA() {
    return playerA;
  }

  public Player getPlayerB() {
    return playerB;
  }

  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return DuelStartEvent.handlers;
  }

}
