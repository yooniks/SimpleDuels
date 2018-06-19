package xyz.yooniks.duels;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.yooniks.duels.config.DuelSettings;
import xyz.yooniks.duels.user.UserManager;

public final class DuelsPlugin extends JavaPlugin {

  private final UserManager userManager;
  private final ItemsManager itemsManager;

  public DuelsPlugin() {
    this.userManager = new UserManager();
    this.itemsManager = new ItemsManager();
  }

  @Override
  public void onEnable() {
    this.saveDefaultConfig();
    this.itemsManager.setupWith(this);

    DuelSettings.load(this);
  }

  @Override
  public void onDisable() {
  }

  public UserManager getUserManager() {
    return userManager;
  }

  public ItemsManager getItemsManager() {
    return itemsManager;
  }

}
