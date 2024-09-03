package org.drone1950.helpless_trapped.mixin;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.drone1950.helpless_trapped.Helpless_Trapped;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Inventory.class)
public class PatchedInventory {
    @Shadow public int selected;

    /***
     * This patching may forbid to player Hotbar selecting and keep selected slot as outside
     * @param cir just mixins thing for handling info between orig and patching methods
     */
    @Inject(method = "getSelected", at = @At("RETURN"), cancellable = true)
    protected void RestrictHotbarSelector(@NotNull CallbackInfoReturnable<ItemStack> cir)
    {
        if(!Helpless_Trapped.EnableTest) return;
        this.selected = 10;
        cir.setReturnValue(ItemStack.EMPTY);
    }
}
