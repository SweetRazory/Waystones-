package org.sweetrazory.waystonesplus.eventhandlers;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.sweetrazory.waystonesplus.WaystonesPlus;
import org.sweetrazory.waystonesplus.memoryhandlers.LangManager;
import org.sweetrazory.waystonesplus.utils.ColoredText;

public class WaystoneRename {
    public WaystoneRename(PrepareAnvilEvent event) {
        AnvilInventory eventInventory = event.getInventory();
        ItemStack firstSlot = eventInventory.getItem(0);
        ItemStack secondSlot = eventInventory.getItem(1);

        ItemStack waystoneItem;
        NamespacedKey waystoneIdKey = new NamespacedKey(WaystonesPlus.getInstance(), "waystoneId");
        String waystoneId = "";
        if (firstSlot != null && firstSlot.getItemMeta() != null) {
            waystoneId = firstSlot.getItemMeta().getPersistentDataContainer().get(waystoneIdKey, PersistentDataType.STRING);
        } else if (secondSlot != null && secondSlot.getItemMeta() != null) {
            waystoneId = secondSlot.getItemMeta().getPersistentDataContainer().get(waystoneIdKey, PersistentDataType.STRING);
        }

        if (secondSlot == null && waystoneId != null) {
            waystoneItem = firstSlot;
        } else if (firstSlot == null && waystoneId == null && secondSlot.getItemMeta().getPersistentDataContainer().get(waystoneIdKey, PersistentDataType.STRING) != null) {
            waystoneItem = secondSlot;
        } else {
            return;
        }
        Player player = (Player) event.getView().getPlayer();
        boolean playerHasPermission = event.getView().getPlayer().hasPermission("waystonesplus.rename") || player.isOp();

        if (waystoneItem != null && !playerHasPermission) {
            event.setResult(null);
        } else if (waystoneItem != null) {
            ItemMeta renameFixer = waystoneItem.getItemMeta();
            String anvilText = event.getInventory().getRenameText();
            if (anvilText != null && anvilText.length() == 0) {
                event.setResult(null);
            } else {
                renameFixer.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&r&6" + anvilText));

                waystoneItem.setItemMeta(renameFixer);
                event.setResult(waystoneItem);
                event.getInventory().setRepairCost(0);
            }
        } else {
            player.sendMessage(ColoredText.getText(LangManager.noPermission));
        }
    }
}
