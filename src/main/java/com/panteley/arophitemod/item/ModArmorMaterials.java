package com.panteley.arophitemod.item;

import com.panteley.arophitemod.ArophiteMod; // ← замени на свой главный класс
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {

    public static final Holder<ArmorMaterial> UNSTABLE_AROPHITE = register(
            "unstable_arophite", // название материала (для текстур)
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
            }),
            15,                              // enchantability (как у алмаза)
            SoundEvents.ARMOR_EQUIP_NETHERITE, // звук надевания
            2.0f,                            // toughness
            0.1f,                            // knockback resistance
            () -> Ingredient.of(/* сюда предмет для ремонта, например ModItems.AROPHITE_INGOT.get() */)
    );

    private static Holder<ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> defense,
            int enchantability,
            Holder<SoundEvent> equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairIngredient
    ) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(ArophiteMod.MOD_ID, name);

        List<ArmorMaterial.Layer> layers = List.of(
                new ArmorMaterial.Layer(location)
        );

        ArmorMaterial material = new ArmorMaterial(
                defense,
                enchantability,
                equipSound,
                repairIngredient,
                layers,
                toughness,
                knockbackResistance
        );

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location, material);
    }
}