package com.panteley.arophitemod.event;

import com.panteley.arophitemod.attachment.ModAttachments;
import com.panteley.arophitemod.block.ModBlocks;
import com.panteley.arophitemod.item.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

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
            if (event.getCrafting().getItem() == ModItems.UNSTABLE_AROPHITE_PICKAXE.get() ||
                    event.getCrafting().getItem() == ModItems.UNSTABLE_AROPHITE_AXE.get() ||
                    event.getCrafting().getItem() == ModItems.UNSTABLE_AROPHITE_SWORD.get() ||
                    event.getCrafting().getItem() == ModItems.UNSTABLE_AROPHITE_SHOVEL.get() ||
                    event.getCrafting().getItem() == ModItems.UNSTABLE_AROPHITE_HOE.get()) {


                System.out.println("Игрок жестко СКРАФТИЛ из арофитикс");
                int current_instability = player.getData(ModAttachments.EFFECT_INSTABILITY);
                player.setData(ModAttachments.EFFECT_INSTABILITY, current_instability + 5);

                System.out.println("Его нестабильность " + player.getData(ModAttachments.EFFECT_INSTABILITY));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {

        if (!(event.getEntity() instanceof ServerPlayer player)) {
                return;
        }

        int current_instability = player.getData(ModAttachments.EFFECT_INSTABILITY);
        if (player.tickCount % 300 == 0) {

            if (current_instability > 100) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,
                        200, 1, false, true, true));
            }
        }


    }
}

