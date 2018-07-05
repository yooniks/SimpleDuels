package xyz.yooniks.duels;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.yooniks.duels.config.DuelSettings;
import xyz.yooniks.duels.user.UserManager;

public final class DuelsPlugin extends JavaPlugin {

  private UserManager userManager;
  private ItemFactory itemFactory;

  @Override
  public void onEnable() {
    this.userManager = new UserManager();
    this.itemFactory = new ItemFactory(this);

    this.saveDefaultConfig();
    this.itemFactory.load();

    DuelSettings.setupWith(this);
  }

  @Override
  public void onDisable() {
  }

  public UserManager getUserManager() {
    return userManager;
  }

  public ItemFactory getItemFactory() {
    return itemFactory;
  }

}
