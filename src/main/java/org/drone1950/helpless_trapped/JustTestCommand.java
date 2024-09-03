package org.drone1950.helpless_trapped;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class JustTestCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        dispatcher.register(Commands.literal("JustTest").executes((command) -> {
            if(command.getSource().getEntity() instanceof Player player)
            {
                Helpless_Trapped.EnableTest = !Helpless_Trapped.EnableTest;
                player.sendSystemMessage(Component.literal("Test " + (Helpless_Trapped.EnableTest ? "Enabled" : "Disabled")));
                if(!Helpless_Trapped.EnableTest) return Command.SINGLE_SUCCESS;
                int freeSlot = player.getInventory().getFreeSlot();
                ItemStack OffhandsItem = player.getOffhandItem();
                if(OffhandsItem.isEmpty()) return Command.SINGLE_SUCCESS;
                player.getInventory().offhand.clear();
                if(freeSlot >= 0)
                {
                    player.getInventory().items.set(freeSlot, OffhandsItem);
                }
                else
                {
                    player.drop(OffhandsItem, false, false);
                }
            }
            return Command.SINGLE_SUCCESS;
        }));
    }
}
