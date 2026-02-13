package me.syarkkya.burnit.mixin;

import me.syarkkya.burnit.BurnLogic;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.List;

@Mixin(AbstractContainerScreen.class)
public abstract class BurnTooltipMixin<T extends AbstractContainerMenu> extends Screen implements MenuAccess<T> {
    @Shadow protected Slot hoveredSlot;
    public BurnTooltipMixin(Component component) { super(component); }

    @Inject(method = "renderTooltip", at = @At("TAIL"))
    protected void renderTooltip(GuiGraphics guiGraphics, int i, int j, CallbackInfo ci) {
        if(this.hoveredSlot != null && this.hoveredSlot.hasItem() && !getMenu().getCarried().isEmpty()) {
            if(BurnLogic.canBurnItem(this.hoveredSlot.getItem(), getMenu().getCarried(), this.hoveredSlot, Minecraft.getInstance().player)) {
                String translationKey = Screen.hasShiftDown() ? "burnit.all.tooltip" : "burnit.tooltip";
                guiGraphics.renderTooltip(this.font, List.of(Component.translatable(translationKey).withStyle(ChatFormatting.RED)), this.hoveredSlot.getItem().getTooltipImage(), i, j);
            }
        }
    }
}