package xyz.yooniks.duels.user;

import java.util.Objects;
import java.util.UUID;
import org.bukkit.entity.Player;
import xyz.yooniks.duels.DuelsPlugin;
import xyz.yooniks.duels.duel.Duel;
import xyz.yooniks.duels.util.ChatUtil;

public class User {

  private final String name;
  private final UUID uniqueId;

  private Duel duel;

  private final DuelsPlugin plugin;

  public User(DuelsPlugin plugin, Player player) {
    this.plugin = plugin;
    this.name = player.getName();
    this.uniqueId = player.getUniqueId();
  }

  public void sendMessage(String text) {
    final Player player = this.plugin.getServer().getPlayer(this.uniqueId);
    if (player != null) {
      player.sendMessage(ChatUtil.fixColor(text));
    }
  }

  public void addDuelItems() {
    final Player player = this.plugin.getServer().getPlayer(this.uniqueId);
    this.plugin.addItems(player);
  }

  public void heal() {
    final Player player = this.plugin.getServer().getPlayer(this.uniqueId);
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

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", uniqueId=" + uniqueId +
        ", duel=" + duel +
        ", plugin=" + plugin +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(name, user.name) &&
        Objects.equals(uniqueId, user.uniqueId) &&
        Objects.equals(duel, user.duel) &&
        Objects.equals(plugin, user.plugin);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, uniqueId, duel, plugin);
  }

}
