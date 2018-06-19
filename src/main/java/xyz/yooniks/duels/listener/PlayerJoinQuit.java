package xyz.yooniks.duels.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.yooniks.duels.DuelsPlugin;
import xyz.yooniks.duels.user.DuelUser;

public class PlayerJoinQuit implements Listener {

  private final DuelsPlugin plugin; //i'm gonna use more its functions later

  public PlayerJoinQuit(DuelsPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    final DuelUser user = this.plugin.getUserManager().getOrCreateUser(event.getPlayer());
    user.heal();
    user.addDuelItems(this.plugin.getItemsManager());
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    //TODO: checking if player is during fight etc.

  }

}
