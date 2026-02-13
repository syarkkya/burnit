package me.syarkkya.burnit.mixin;

import me.syarkkya.burnit.BurnIt;
import me.syarkkya.burnit.BurnLogic;
import me.syarkkya.burnit.BurnSoundManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerMenu.class)
public abstract class BurnLogicMixin {
    @Shadow public abstract ItemStack getCarried();
    @Shadow public abstract Slot getSlot(int i);

    @Inject(method = "clicked", at = @At("HEAD"), cancellable = true)
    public void clicked(int slotId, int button, ClickType clickType, Player player, CallbackInfo ci) {
        if (button == 1 && slotId >= 0) {
            Slot slot = this.getSlot(slotId);
            if (slot != null && slot.hasItem()) {
                ItemStack stackInSlot = slot.getItem();
                ItemStack incoming = this.getCarried();
                int burnResult = BurnLogic.performBurn(stackInSlot, incoming, slot, player, (AbstractContainerMenu)(Object)this);
                if (burnResult > 0) {
                    if (player.level().isClientSide && burnResult == 2) {
                        BurnSoundManager.startSounds(player);
                    }
                    if (clickType == ClickType.QUICK_MOVE) {
                        incoming.setCount(0);
                    } else {
                        incoming.consume(1, player);
                    }
                    ci.cancel();
                }
            }
        }
    }

    @Inject(method = "removed", at = @At("HEAD"))
    public void removed(Player player, CallbackInfo ci) {
        BurnIt.isFireSet.remove((AbstractContainerMenu)(Object)this);
        if (player.level().isClientSide) {
            BurnSoundManager.stopSounds(player);
        }
    }
}