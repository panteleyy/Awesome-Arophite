package com.panteley.arophitemod.block;

import net.minecraft.world.level.block.Block;
import com.panteley.arophitemod.ArophiteMod;
import com.panteley.arophitemod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ArophiteMod.MOD_ID);

    public static final DeferredBlock<Block> AROPHITE_ORE = registerBlock("arophite_ore",
    () -> new Block(Block.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> DEEPSLATE_AROPHITE_ORE = registerBlock("deepslate_arophite_ore",
            () -> new Block(Block.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> UNSTABLE_AROPHITE_BLOCK = registerBlock("unstable_arophite_block",
            () -> new Block(Block.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> STABLE_AROPHITE_BLOCK = registerBlock("stable_arophite_block",

            () -> new Block(Block.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> AROPHITE_CHARGER = registerBlock("arophite_charger",
            () -> new Block(Block.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}