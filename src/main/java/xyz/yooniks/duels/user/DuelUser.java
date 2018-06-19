package xyz.yooniks.duels.user;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.yooniks.duels.ItemsManager;
import xyz.yooniks.duels.duel.Duel;

public class DuelUser implements Messageable {

  private final String name;
  private final UUID uniqueId;

  private Duel duel;

  public DuelUser(String name, UUID uuid) {
    this.name = name;
    this.uniqueId = uuid;
  }

  public void addDuelItems(ItemsManager itemsManager) {
    final Player player = Bukkit.getPlayer(this.uniqueId);
    itemsManager.addItems(player);
  }

  public void sendMessage(String text) {
    this.sendMessage(this, text);
  }

  public void heal() {
    final Player player = Bukkit.getPlayer(this.uniqueId);
    player.setHealth(20.0D);
    player.setExp(0.0F);
    player.setLevel(0);
    player.setFoodLevel(20);
  }

  public Duel getDuel() {
    return duel;
  }

  public void setDuel(Duel duel) {
    this.duel = duel;
  }

  public UUID getUniqueId() {
    return uniqueId;
  }

  public String getName() {
    return name;
  }

}
