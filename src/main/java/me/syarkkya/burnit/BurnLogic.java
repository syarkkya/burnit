package me.syarkkya.burnit;

import me.syarkkya.burnit.config.ConfigLoader;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ShulkerBoxBlock;

public class BurnLogic {
    public static boolean canBurnItem(ItemStack stack, ItemStack incoming, Slot slot, Player player) {
        ConfigLoader config = ConfigLoader.getConfig();
        String sourceId = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
        String targetId = BuiltInRegistries.ITEM.getKey(incoming.getItem()).toString();
        if (!config.burnSources.contains(sourceId)) return false;
        if (incoming.isEmpty() || player.getAbilities().instabuild || !slot.allowModification(player)) return false;
        if (config.preventEnchantedItems && incoming.isEnchanted()) return false;
        if (config.preventFireResistant && incoming.getComponents().has(DataComponents.FIRE_RESISTANT)) return false;
        if (config.preventShalkerBoxes && incoming.getItem() instanceof BlockItem bi && bi.getBlock() instanceof ShulkerBoxBlock) return false;
        if (config.preventBurnSources && config.burnSources.contains(targetId)) return false;
        if (config.preventCustomItems.contains(targetId)) return false;
        return !ItemStack.isSameItem(stack, incoming);
    }

    public static int performBurn(ItemStack stack, ItemStack incoming, Slot slot, Player player, net.minecraft.world.inventory.AbstractContainerMenu menu) {
        if (!canBurnItem(stack, incoming, slot, player)) return 0;
        if (!BurnIt.isFireSet.containsKey(menu)) {
            if (!me.syarkkya.burnit.config.ConfigLoader.getConfig().keepDurability && !me.syarkkya.burnit.config.ConfigLoader.getConfig().preventCustomItems.contains(net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem()).toString())) {
                if (stack.isDamageableItem()) {
                    stack.setDamageValue(stack.getDamageValue() + 1);
                    if (stack.getDamageValue() >= stack.getMaxDamage()) {
                        stack.setCount(0);
                    }
                } else if (stack.isStackable()) {
                    stack.consume(1, player);
                }
            }
            BurnIt.isFireSet.put(menu, true);
        }
        return 2;
    }
}