package com.panteley.arophitemod.event;

import com.panteley.arophitemod.attachment.ModAttachments;
import com.panteley.arophitemod.block.ModBlocks;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber
public class ModEvents {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getState().getBlock() == ModBlocks.AROPHITE_ORE.get()) {
            System.out.println("Игрок жестко сломал АРОФИТИКС");
            if (event.getPlayer() instanceof ServerPlayer player) {
                player.setData(ModAttachments.EFFECT_INSTABILITY, 100);

                int instability = player.getData(ModAttachments.EFFECT_INSTABILITY);

                System.out.println("Его нестабильность " + instability);
            }

        }
    }
}
