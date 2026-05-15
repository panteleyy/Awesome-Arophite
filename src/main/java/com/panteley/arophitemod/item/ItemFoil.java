package com.panteley.arophitemod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemFoil extends Item {

    public ItemFoil(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
