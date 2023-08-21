package us.timinc.mc.cobblemon.unimplementeditems

import net.fabricmc.api.ModInitializer
import net.minecraft.resources.ResourceLocation
import us.timinc.mc.cobblemon.unimplementeditems.blocks.UnimplementedItemsBlocks
import us.timinc.mc.cobblemon.unimplementeditems.items.UnimplementedItemsItems

object UnimplementedItems : ModInitializer {
    const val MOD_ID = "unimplemented_items"

    override fun onInitialize() {
        UnimplementedItemsItems.register()
        UnimplementedItemsBlocks.register()
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun myResourceLocation(str: String): ResourceLocation {
        return ResourceLocation(MOD_ID, str)
    }
}