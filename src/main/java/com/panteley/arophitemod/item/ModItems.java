package com.panteley.arophitemod.item;

import com.panteley.arophitemod.ArophiteMod;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ArophiteMod.MOD_ID);

    /* ITEMS  */
    public static final DeferredItem<Item> RAW_AROPHITE = ITEMS.register("raw_arophite",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> UNSTABLE_AROPHITE_INGOT = ITEMS.register("unstable_arophite_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> STABLE_AROPHITE_INGOT = ITEMS.register("stable_arophite_ingot",
            () -> new Item(new Item.Properties()));

    /* TOOLS */

    public static final DeferredItem<SwordItem> UNSTABLE_AROPHITE_SWORD =
            ITEMS.register("unstable_arophite_sword",
            () -> new SwordItem(ModToolMaterials.UNSTABLE_AROPHITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(
                            ModToolMaterials.UNSTABLE_AROPHITE, 3, -2.4f))));

    public static final DeferredItem<PickaxeItem> UNSTABLE_AROPHITE_PICKAXE =
            ITEMS.register("unstable_arophite_pickaxe",
                () -> new PickaxeItem(ModToolMaterials.UNSTABLE_AROPHITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(
                            ModToolMaterials.UNSTABLE_AROPHITE,1, -2.8f))));

    public static final DeferredItem<AxeItem> UNSTABLE_AROPHITE_AXE =
            ITEMS.register("unstable_arophite_axe",
                    () -> new AxeItem(ModToolMaterials.UNSTABLE_AROPHITE, new Item.Properties()
                            .attributes(AxeItem.createAttributes(
                                    ModToolMaterials.UNSTABLE_AROPHITE, 6, -3.0f))));

    public static final DeferredItem<ShovelItem> UNSTABLE_AROPHITE_SHOVEL =
            ITEMS.register("unstable_arophite_shovel",
                    () -> new ShovelItem(ModToolMaterials.UNSTABLE_AROPHITE, new Item.Properties()
                            .attributes(ShovelItem.createAttributes(
                                    ModToolMaterials.UNSTABLE_AROPHITE, 1.5f, -3.0f))));

    public static final DeferredItem<HoeItem> UNSTABLE_AROPHITE_HOE =
            ITEMS.register("unstable_arophite_hoe",
                    () -> new HoeItem(ModToolMaterials.UNSTABLE_AROPHITE, new Item.Properties()
                            .attributes(HoeItem.createAttributes(
                                    ModToolMaterials.UNSTABLE_AROPHITE, 0, -3.0f))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
