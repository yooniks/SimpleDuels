package xyz.yooniks.duels.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;
import xyz.yooniks.duels.DuelsPlugin;

public class UserManager {

  private final DuelsPlugin plugin;

  public UserManager(DuelsPlugin plugin) {
    this.plugin = plugin;
  }

  private final Map<UUID, User> usersMap = new HashMap<>();

  public User getOrCreateUser(Player player) {
    User user = this.usersMap.get(player.getUniqueId());
    if (user == null) {
      this.usersMap.put(player.getUniqueId(),
          user = new User(this.plugin, player));
    }
    return user;
  }

  public Map<UUID, User> getUsersMap() {
    return usersMap;
  }
}
