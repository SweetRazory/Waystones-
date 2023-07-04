package org.sweetrazory.waystonesplus.menu.submenus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.sweetrazory.waystonesplus.menu.Menu;
import org.sweetrazory.waystonesplus.menu.MenuManager;
import org.sweetrazory.waystonesplus.utils.ColoredText;
import org.sweetrazory.waystonesplus.utils.ItemBuilder;
import org.sweetrazory.waystonesplus.utils.ItemUtils;
import org.sweetrazory.waystonesplus.waystone.Waystone;

import java.util.Arrays;

public class SettingsMenu extends Menu {
    public SettingsMenu() {
        super(45, ColoredText.getText("&cWaystone Settings"), 0);
    }

    @Override
    public void initializeItems(Player player, Waystone waystone) {
        ItemStack[] items;
        ItemStack visibilityMenu = new ItemBuilder(Material.SPYGLASS)
                .persistentData("action", "visibilitySettings")
                .displayName(ColoredText.getText("&6Change Visibility"))
                .build();
        ItemStack particleMenu = new ItemBuilder(Material.PRISMARINE_SHARD)
                .persistentData("action", "particleSettings")
                .displayName(ColoredText.getText("&6Change Particles"))
                .build();
        ItemStack explorerList = new ItemBuilder(Material.PLAYER_HEAD)
                .persistentData("action", "explorerSettings")
                .displayName(ColoredText.getText("&6Explorers list"))
                .build();
        ItemStack typeMenu = new ItemBuilder(Material.YELLOW_GLAZED_TERRACOTTA)
                .displayName(ColoredText.getText("&6Change Waystone Type"))
                .persistentData("action", "typeSettings")
                .build();
        ItemStack filler = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                .displayName(" ")
                .build();
        inventory.setContents(Arrays.asList(filler, filler, filler, filler, filler, filler, filler, filler, filler,
                filler, null, null, null, null, null, null, null, filler,
                filler, null, null, null, null, null, null, null, filler,
                filler, null, null, null, null, null, null, null, filler,
                filler, filler, filler, filler, filler, filler, filler, filler, filler).toArray(new ItemStack[0]));
        setItem(10, visibilityMenu);
        setItem(11, particleMenu);
        setItem(12, explorerList);
        setItem(13, typeMenu);

        setItem(40, new ItemBuilder(Material.BARRIER).displayName(ColoredText.getText("&cReturn to option select")).persistentData("action", "selectorMenu").build());
    }

    @Override
    public void handleClick(Player player, ItemStack item) {
        String action = ItemUtils.getPersistentString(item, "action");
        if (action != null) {
            switch (action) {
                case "selectorMenu":
                    Menu selectorMenu = new SelectorMenu();
                    MenuManager.openMenu(player, selectorMenu, waystone);
                    break;
                case "visibilitySettings":
                    Menu visibilitySettingsMenu = new VisibilitySettingsMenu();
                    MenuManager.openMenu(player, visibilitySettingsMenu, waystone);
                    break;
                case "particleSettings":
                    Menu particleMenu = new ParticleMenu();
                    MenuManager.openMenu(player, particleMenu, waystone);
                    break;
                case "explorerSettings":
                    Menu exploredMenu = new ExploredMenu();
                    MenuManager.openMenu(player, exploredMenu, waystone);
                    break;
                case "typeSettings":
                    Menu typeMenu = new TypeMenu();
                    MenuManager.openMenu(player, typeMenu, waystone);
            }
        }
    }
}
