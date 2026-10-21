package com.panteley.arophitemod.item;

import com.panteley.arophitemod.attachment.ModAttachments;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.Map;


public class SlotScrollItem extends Item {
    private final String[] slots;

    private static final Map<String, Integer> COSTS = Map.of(
            "instability_slot_0", 10,
            "instability_slot_1", 20,
            "instability_slot_2", 30,
            "stability_slot_0", 40,
            "stability_slot_1", 50
    );

    public SlotScrollItem(Properties props, String... slots) {
        super(props);
        this.slots = slots;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            CuriosApi.getCuriosInventory(player).ifPresent(inv -> {
                for (String slot : slots) {
                    ICurioStacksHandler handler = inv.getCurios().get(slot);
                    boolean open = handler != null && handler.getSlots() > 0;

                    if (!open) {
                        int cost = COSTS.getOrDefault(slot,Integer.MAX_VALUE);
                        int current_instability = player.getData(ModAttachments.EFFECT_INSTABILITY);

                        if (current_instability >= cost) {
                            inv.addPermanentSlotModifier(
                                    slot,
                                    ResourceLocation.fromNamespaceAndPath("awesomearophitemod", "unlock" + slot),
                                    1,
                                    AttributeModifier.Operation.ADD_VALUE);
                            stack.shrink(1);
                            player.setData(ModAttachments.EFFECT_INSTABILITY, current_instability - cost);
                            return;
                        }
                        else{
                            player.displayClientMessage(Component.literal("You don't have enough Instability"),true);
                            return;
                        }
                    }
                }
                player.displayClientMessage(Component.literal("All slots already open"),true);
            });

        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
