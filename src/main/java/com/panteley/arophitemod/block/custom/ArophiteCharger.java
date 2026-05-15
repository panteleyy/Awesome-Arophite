package com.panteley.arophitemod.block.custom;

import com.panteley.arophitemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ArophiteCharger extends Block {
    public ArophiteCharger(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            if (itemEntity.getItem().getItem() == ModItems.UNSTABLE_AROPHITE_DUST.get()) {
                LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                lightning.moveTo(pos.getX(), pos.getY(), pos.getZ());
                lightning.setVisualOnly(true);
                level.addFreshEntity(lightning);

                itemEntity.setItem(new ItemStack(ModItems.CHARGED_AROPHITE_DUST.get(), itemEntity.getItem().getCount()));

            }

        }

        super.stepOn(level, pos, state, entity);
    }

}
