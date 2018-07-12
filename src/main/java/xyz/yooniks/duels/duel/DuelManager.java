package xyz.yooniks.duels.duel;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import xyz.yooniks.duels.manager.BaseManager;

public class DuelManager implements BaseManager<UUID, Duel> {

  private final Map<UUID, Duel> duelMap = new HashMap<>();

  @Override
  public ImmutableList<Duel> asImmutableList() {
    return ImmutableList.copyOf(this.duelMap.values());
  }

  @Override
  public ImmutableMap<UUID, Duel> asImmutableMap() {
    return ImmutableMap.copyOf(this.duelMap);
  }

}
