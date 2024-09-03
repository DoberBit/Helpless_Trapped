package org.drone1950.helpless_trapped.mixin.client;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.drone1950.helpless_trapped.Helpless_Trapped;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInjector;

@Mixin(AbstractContainerScreen.class)
public class PatchedPlayerInventoryScreen {

    /***
     * This patching may forbid to player interacting with inventory any slots
     * @param p_97748_ (ScreenX)
     * @param p_97749_ (ScreenY)
     * @param p_97750_ (MouseButton 0 = LMB, 1 = RMB) if it understands correctly
     * @param cir just mixins thing for handling info between orig and patching methods
     */
    @Inject(method = "mouseClicked", at = @At(value = "RETURN", ordinal = 0, shift = At.Shift.BY, by = 2), cancellable = true)
    protected void RestrictClickOnInventorySlot(double p_97748_, double p_97749_, int p_97750_, CallbackInfoReturnable<Boolean> cir)
    {
        if(!Helpless_Trapped.EnableTest) return;
        cir.setReturnValue(true);
    }
}
