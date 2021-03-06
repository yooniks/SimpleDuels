package xyz.yooniks.duels;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import xyz.yooniks.duels.util.ItemUtil;

public class ItemFactory {

  private final Map<Integer, ItemStack> itemsInventory = new HashMap<>();
  private final Map<EquipmentSlot, ItemStack> itemsArmor = new HashMap<>();

  private final ItemEquiper itemEquiper = new ItemEquiper();

  private final Plugin plugin;

  public ItemFactory(Plugin plugin) {
    this.plugin = plugin;
  }

  public void addItems(Player player) {
    player.getInventory().clear();
    player.getInventory().setArmorContents(null);

    //maybe foreach?
    for (Map.Entry<Integer, ItemStack> entry : this.itemsInventory.entrySet()) {
      player.getInventory().setItem(entry.getKey(), entry.getValue());
    }

    for (Map.Entry<EquipmentSlot, ItemStack> entry : this.itemsArmor.entrySet()) {
      this.itemEquiper.equip(player, entry.getKey(), entry.getValue());
    }
  }

  void init() {
    final FileConfiguration config = this.plugin.getConfig();
    final Logger logger = this.plugin.getLogger();

    for (String itemType : config.getConfigurationSection("items").getKeys(false)) {

      if (itemType.equalsIgnoreCase("armor")) {

        for (String id : config.getConfigurationSection("items." + itemType)
            .getKeys(false)) {

          final ConfigurationSection section = config
              .getConfigurationSection("items." + itemType + "." + id);
          int slot;
          try {
            slot = Integer.parseInt(id);
          } catch (NumberFormatException ex) {
            slot = 1;
            logger
                .warning("String \"" + id + "\" cannot be parsed to Integer! Using default: 1");
          }
          this.itemsInventory.put(slot, ItemUtil.fromSection(section));
        }
      } else {

        for (String id : config.getConfigurationSection("items." + itemType)
            .getKeys(false)) {

          final ConfigurationSection section = config
              .getConfigurationSection("items." + itemType + "." + id);

          EquipmentSlot slot;
          try {
            slot = EquipmentSlot.valueOf(id);
          } catch (Exception ex) {
            logger.warning("String \"" + id
                + "\" cannot be parsed to EquipmentSlot! Using default: HEAD. Correct types: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/inventory/EquipmentSlot.html");
            slot = EquipmentSlot.HEAD;
          }

          this.itemsArmor.put(slot, ItemUtil.fromSection(section));
        }
      }
    }
  }

  static class ItemEquiper {

    void equip(Player player, EquipmentSlot slot, ItemStack item) {
      switch (slot) {
        case HEAD: {
          player.getInventory().setHelmet(item);
          break;
        }
        case CHEST: {
          player.getInventory().setChestplate(item);
          break;
        }
        case LEGS: {
          player.getInventory().setLeggings(item);
          break;
        }
        case FEET:
        default: {
          player.getInventory().setBoots(item);
          break;
        }
      }
    }
  }


}
