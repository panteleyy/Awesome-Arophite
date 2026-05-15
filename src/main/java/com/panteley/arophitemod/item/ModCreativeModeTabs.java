package com.panteley.arophitemod.item;

import com.panteley.arophitemod.ArophiteMod;
import com.panteley.arophitemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public  static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB_DEFERRED_REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ArophiteMod.MOD_ID);

    public static final Supplier<CreativeModeTab> AROPHITE_TAB = CREATIVE_MODE_TAB_DEFERRED_REGISTER.register("arophite_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.UNSTABLE_AROPHITE_INGOT.get()))
                    .title(Component.translatable("creativetab.arophitemod.arophite_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAW_AROPHITE);
                        output.accept(ModItems.UNSTABLE_AROPHITE_INGOT);
                        output.accept(ModItems.STABLE_AROPHITE_INGOT);
                        output.accept(ModItems.UNSTABLE_AROPHITE_DUST);
                        output.accept(ModItems.CHARGED_AROPHITE_DUST);
                        output.accept(ModBlocks.AROPHITE_ORE);
                        output.accept(ModBlocks.DEEPSLATE_AROPHITE_ORE);
                        output.accept(ModBlocks.UNSTABLE_AROPHITE_BLOCK);
                        output.accept(ModBlocks.STABLE_AROPHITE_BLOCK);
                        output.accept(ModBlocks.AROPHITE_CHARGER);
                        output.accept(ModItems.UNSTABLE_AROPHITE_SWORD);
                        output.accept(ModItems.UNSTABLE_AROPHITE_PICKAXE);
                        output.accept(ModItems.UNSTABLE_AROPHITE_AXE);
                        output.accept(ModItems.UNSTABLE_AROPHITE_SHOVEL);
                        output.accept(ModItems.UNSTABLE_AROPHITE_HOE);

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB_DEFERRED_REGISTER.register(eventBus);
    }

}


