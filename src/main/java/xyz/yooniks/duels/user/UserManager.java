package xyz.yooniks.duels.user;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;

public class UserManager {

  private final Map<UUID, DuelUser> usersMap = new HashMap<>();

  public DuelUser getOrCreateUser(Player player) {
    return this.getOrCreateUser(player.getName(), player.getUniqueId());
  }

  private DuelUser getOrCreateUser(String name, UUID uuid) {
    DuelUser user = this.usersMap.get(uuid);
    if (user == null) {
      this.usersMap.put(uuid,
          user = new DuelUser(name, uuid));
    }
    return user;
  }

  public ImmutableMap<UUID, DuelUser> getUsersMap() {
    return ImmutableMap.copyOf(this.usersMap);
  }

  public ImmutableList<DuelUser> getUsers() {
    return ImmutableList.copyOf(this.usersMap.values());
  }

}
