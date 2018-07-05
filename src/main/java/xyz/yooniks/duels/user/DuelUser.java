package xyz.yooniks.duels.user;

import java.util.Optional;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.yooniks.duels.duel.Duel;

public class DuelUser implements Messageable {

  private final String name;
  private final UUID uniqueId;

  private Duel duel;

  public DuelUser(String name, UUID uuid) {
    this.name = name;
    this.uniqueId = uuid;
  }

  public void sendMessage(String text) {
    this.sendMessage(this, text);
  }

  public void heal() {
    this.getBukkitPlayer().ifPresent(player -> {
      player.setHealth(20.0D);
      player.setExp(0.0F);
      player.setLevel(0);
      player.setFoodLevel(20);
    });
  }

  public Optional<Duel> getDuel() {
    return Optional.ofNullable(this.duel);
  }

  public Optional<Player> getBukkitPlayer() {
    return Optional.ofNullable(Bukkit.getPlayer(this.uniqueId));
  }

  public void setDuel(Duel duel) {
    this.duel = duel;
  }

  public UUID getUniqueId() {
    return this.uniqueId;
  }

  public String getName() {
    return this.name;
  }

}
