package org.drone1950.helpless_trapped.event_handlers;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.drone1950.helpless_trapped.Helpless_Trapped;
import org.drone1950.helpless_trapped.JustTestCommand;

@Mod.EventBusSubscriber(modid = Helpless_Trapped.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RestrictionsWorker {
    @SubscribeEvent
    public static void PickupRestrict(EntityItemPickupEvent event)
    {
        Player ply = event.getEntity();
        if(ply == null|| !Helpless_Trapped.EnableTest) return;
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void BreakeRestrict(BlockEvent.BreakEvent event)
    {
        Player ply = event.getPlayer();
        if(ply == null || !Helpless_Trapped.EnableTest) return;
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void LeftClickRestrict(PlayerInteractEvent.LeftClickBlock event)
    {
        Player ply = event.getEntity();
        if(ply == null || !Helpless_Trapped.EnableTest) return;
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void RightClickRestrict(PlayerInteractEvent.RightClickBlock event)
    {
        Player ply = event.getEntity();
        if(ply == null || !Helpless_Trapped.EnableTest) return;
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void GamemodeChangeRestrict(PlayerEvent.PlayerChangeGameModeEvent event)
    {
        Player ply = event.getEntity();
        if(ply == null || !Helpless_Trapped.EnableTest) return;

        if(event.getCurrentGameMode() == GameType.SURVIVAL && event.getNewGameMode() != GameType.SURVIVAL)
            event.setCanceled(true);
    }

    @SubscribeEvent
    public static void RegisterCommandsEventHandler(RegisterCommandsEvent event)
    {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        JustTestCommand.register(dispatcher);
    }
}
