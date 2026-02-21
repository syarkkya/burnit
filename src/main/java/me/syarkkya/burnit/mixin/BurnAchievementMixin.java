package me.syarkkya.burnit.mixin;

import me.syarkkya.burnit.BurnIt;
import me.syarkkya.burnit.BurnLogic;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerMenu.class)
public abstract class BurnAchievementMixin {
    @Shadow public abstract ItemStack getCarried();
    @Shadow public abstract Slot getSlot(int i);

    @Inject(method = "clicked", at = @At("HEAD"))
    private void burnit$onBurnAchievement(int slotId, int button, ClickType clickType, Player player, CallbackInfo ci) {
        if (!(player instanceof ServerPlayer sp) || button != 1 || slotId < 0) return;
        Slot slot = this.getSlot(slotId);
        if (slot == null || !slot.hasItem()) return;
        ItemStack stackInSlot = slot.getItem();
        ItemStack incoming = this.getCarried();

        if (BurnLogic.canBurnItem(stackInSlot, incoming, slot, player)) {
            int amount = (clickType == ClickType.QUICK_MOVE) ? incoming.getCount() : 1;
            sp.awardStat(Stats.CUSTOM.get(BurnIt.ITEMS_BURNED), amount);

            if (incoming.is(Items.DIAMOND)) {
                var adv = sp.server.getAdvancements().get(ResourceLocation.fromNamespaceAndPath("burnit", "burn_diamond"));
                if (adv != null) sp.getAdvancements().award(adv, "burned");
            }

            int total = sp.getStats().getValue(Stats.CUSTOM.get(BurnIt.ITEMS_BURNED));
            if (total >= 1000) {
                var adv1000 = sp.server.getAdvancements().get(ResourceLocation.fromNamespaceAndPath("burnit", "burn_1000"));
                if (adv1000 != null) sp.getAdvancements().award(adv1000, "burned_enough");
            }
        }
    }
}