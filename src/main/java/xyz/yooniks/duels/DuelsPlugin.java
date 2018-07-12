package xyz.yooniks.duels;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.yooniks.duels.config.DuelSettings;
import xyz.yooniks.duels.duel.DuelManager;
import xyz.yooniks.duels.user.UserManager;

public final class DuelsPlugin extends JavaPlugin {

  private UserManager userManager;
  private ItemFactory itemFactory;
  private DuelManager duelManager;

  @Override
  public void onEnable() {
    this.userManager = new UserManager();
    this.duelManager = new DuelManager();
    this.itemFactory = new ItemFactory(this);

    this.saveDefaultConfig();
    this.itemFactory.init();

    DuelSettings.init(this);
  }

  @Override
  public void onDisable() {

  }

  public DuelManager getDuelManager() {
    return duelManager;
  }

  public UserManager getUserManager() {
    return userManager;
  }

  public ItemFactory getItemFactory() {
    return itemFactory;
  }

}
