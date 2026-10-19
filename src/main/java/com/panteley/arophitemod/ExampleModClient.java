package com.panteley.arophitemod;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import com.panteley.arophitemod.attachment.ModAttachments;
import com.panteley.arophitemod.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = ArophiteMod.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = ArophiteMod.MOD_ID, value = Dist.CLIENT)
public class ExampleModClient {
    public ExampleModClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> ItemProperties.register(
                ModItems.AROPHITE_STAR.get(),
                ResourceLocation.fromNamespaceAndPath(ArophiteMod.MOD_ID, "instability"),
                (stack, level, entity, seed) -> {
                    var player = Minecraft.getInstance().player;
                    return player == null ? 0f : (float) player.getData(ModAttachments.EFFECT_INSTABILITY);
                }));
        // Some client setup code
        ArophiteMod.LOGGER.info("HELLO FROM CLIENT SETUP");
        ArophiteMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
}
