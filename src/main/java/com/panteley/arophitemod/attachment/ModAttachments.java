package com.panteley.arophitemod.attachment;

import com.mojang.serialization.Codec;
import com.panteley.arophitemod.ArophiteMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;


public class ModAttachments {

    public static final DeferredRegister<AttachmentType<?>> INSTABILITY =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ArophiteMod.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Integer>> EFFECT_INSTABILITY =
            INSTABILITY.register("effect_instability",
                    () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).copyOnDeath().build());

    public static void register(IEventBus eventBus) {
        INSTABILITY.register(eventBus);
    }
}
