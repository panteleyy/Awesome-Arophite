package com.panteley.arophitemod.event;

import com.panteley.arophitemod.attachment.ModAttachments;
import com.panteley.arophitemod.block.ModBlocks;
import com.panteley.arophitemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.world.level.Level;


@EventBusSubscriber
public class ModEvents {

    public static final Map<BlockPos, Integer> chargingBlocks = new HashMap<>();

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

        Level level = player.level();

        if (level instanceof ServerLevel serverLevel) {

            List<ItemEntity> items_around = serverLevel.getEntitiesOfClass(
                    ItemEntity.class, player.getBoundingBox().inflate(10.0)
            );

            for (ItemEntity item : items_around) {
                if (item.getItem().getItem() == ModItems.UNSTABLE_AROPHITE_DUST.get()) {
                    BlockPos itemPos = item.blockPosition();
                    BlockPos belowPos = itemPos.below();

                    if (level.getBlockState(belowPos).getBlock() == ModBlocks.AROPHITE_CHARGER.get()) {
                        if (!chargingBlocks.containsKey(belowPos)) {
                            chargingBlocks.put(belowPos, 120);
                        }
                    }
                }
            }
        }


        List<BlockPos> readyToStrike = new ArrayList<>();

        for (Map.Entry<BlockPos, Integer> entry : chargingBlocks.entrySet()) {

            int ticks = entry.getValue() - 1;
            entry.setValue(ticks);

            BlockPos pos = entry.getKey();

            if (level instanceof ServerLevel serverLevel) {

                serverLevel.sendParticles(ParticleTypes.ENCHANT,
                        pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                        20, 0.5, 1, 0.5, 0.1);

            }

            player.addEffect(new MobEffectInstance(MobEffects.DARKNESS,
                    30, 1, false, true, true));


            if (ticks == 40) {
                player.addEffect(new MobEffectInstance(MobEffects.LEVITATION,
                        30, 1, false, true, true));

            }
            if (ticks <= 0) {
                readyToStrike.add(pos);
            }
        }

        for (BlockPos pos : readyToStrike) {
            chargingBlocks.remove(pos);

            if (level instanceof ServerLevel serverLevel) {
                LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                lightning.moveTo(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
                lightning.setVisualOnly(true);
                level.addFreshEntity(lightning);

                List<ItemEntity> items = serverLevel.getEntitiesOfClass(
                        ItemEntity.class,
                        new AABB(pos).inflate(1.0)
                );

                for (ItemEntity item : items) {
                    if (item.getItem().getItem() == ModItems.UNSTABLE_AROPHITE_DUST.get()) {
                        item.setItem(new ItemStack(ModItems.CHARGED_AROPHITE_DUST.get(),
                                item.getItem().getCount()));
                    }
                }

            }
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

