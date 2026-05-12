package com.panteley.arophitemod.event;

import com.panteley.arophitemod.attachment.ModAttachments;
import com.panteley.arophitemod.block.ModBlocks;
import com.panteley.arophitemod.item.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.minecraft.world.level.block.Blocks;

@EventBusSubscriber
public class ModEvents {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {

        if (event.getPlayer() instanceof ServerPlayer player) {

            // ЕСЛИ СЛОМАЕТ АРОФИТОВУЮ РУДУ
            if (event.getState().getBlock() == ModBlocks.AROPHITE_ORE.get() ||
                    event.getState().getBlock() == ModBlocks.DEEPSLATE_AROPHITE_ORE.get()) {

                System.out.println("Игрок жестко сломал АРОФИТИКС");
                int current_instability = player.getData(ModAttachments.EFFECT_INSTABILITY);
                player.setData(ModAttachments.EFFECT_INSTABILITY, current_instability + 10);

                System.out.println("Его нестабильность " + player.getData(ModAttachments.EFFECT_INSTABILITY));

            }
        }
    }
    @SubscribeEvent
    public static void onCraftItem(PlayerEvent.ItemCraftedEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            if (event.getCrafting().getItem() == ModItems.UNSTABLE_AROPHITE_PICKAXE.get()) {

                System.out.println("Игрок жестко СКРАФТИЛ из арофитикс");
                int current_instability = player.getData(ModAttachments.EFFECT_INSTABILITY);
                player.setData(ModAttachments.EFFECT_INSTABILITY, current_instability + 5);

                System.out.println("Его нестабильность " + player.getData(ModAttachments.EFFECT_INSTABILITY));
            }
        }
    }
}

