package us.timinc.mc.cobblemon.unimplementeditems

import net.fabricmc.api.ModInitializer
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import us.timinc.mc.cobblemon.unimplementeditems.items.UnimplementedItemsItems

object UnimplementedItems : ModInitializer {
    @Suppress("MemberVisibilityCanBePrivate")
    const val MOD_ID = "unimplemented_items"

    override fun onInitialize() {
        Registry.register(Registry.ITEM, myResourceLocation( "ability_capsule"), UnimplementedItemsItems.ABILITY_CAPSULE)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun myResourceLocation(str : String): ResourceLocation {
        return ResourceLocation(MOD_ID, str)
    }
}