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

            int current_instability = player.getData(ModAttachments.EFFECT_INSTABILITY);

            // ЕСЛИ СЛОМАЕТ АРОФИТОВУЮ РУДУ
            if (event.getState().getBlock() == ModBlocks.AROPHITE_ORE.get()) {

                int random_instability = getRandom(1, 2);
                player.setData(ModAttachments.EFFECT_INSTABILITY, current_instability + random_instability);
                System.out.println("Нестабильность " + player.getData(ModAttachments.EFFECT_INSTABILITY) + " " +
                        "Добавилось" + random_instability);
            }
            if (event.getState().getBlock() == ModBlocks.DEEPSLATE_AROPHITE_ORE.get()) {

                int random_instability = getRandom(1, 4);
                player.setData(ModAttachments.EFFECT_INSTABILITY, current_instability + random_instability);
                System.out.println("Нестабильность " + player.getData(ModAttachments.EFFECT_INSTABILITY) +
                        "Добавилось " + random_instability);
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
                int random_instability = getRandom(3, 5) ;
                player.setData(ModAttachments.EFFECT_INSTABILITY, current_instability + random_instability);

                System.out.println("Нестабильность " + player.getData(ModAttachments.EFFECT_INSTABILITY) +
                        "Добавилось " + random_instability);
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

            if (current_instability >= 100) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,
                        200, 1, false, true, true));
            }
            else if (current_instability >= 50) {
                player.addEffect(new MobEffectInstance(MobEffects.LEVITATION,
                        200, 1, false, true, true));
            }
        }


    }

    public static int getRandom(int min, int max) {

        int range = (max - min) + 1;
        int random = (int) ((range * Math.random()) + min);
        return random;
    }
}

