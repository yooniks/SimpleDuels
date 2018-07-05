package xyz.yooniks.duels.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.yooniks.duels.ItemFactory;
import xyz.yooniks.duels.duel.Duel;
import xyz.yooniks.duels.user.DuelUser;
import xyz.yooniks.duels.user.UserManager;

public class PlayerJoinQuit implements Listener {

  private final UserManager userManager;
  private final ItemFactory itemFactory;

  public PlayerJoinQuit(UserManager userManager, ItemFactory itemFactory) {
    this.userManager = userManager;
    this.itemFactory = itemFactory;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    final Player player = event.getPlayer();
    final DuelUser user = this.userManager.getOrCreateUser(player);

    user.heal();
    this.itemFactory.addItems(player);
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    final DuelUser user = this.userManager.getOrCreateUser(event.getPlayer());
    user.getDuel().ifPresent(Duel::end);
  }

}
