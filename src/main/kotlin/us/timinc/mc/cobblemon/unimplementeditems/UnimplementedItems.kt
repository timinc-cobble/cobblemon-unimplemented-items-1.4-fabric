package us.timinc.mc.cobblemon.unimplementeditems

import com.cobblemon.mod.common.api.events.CobblemonEvents
import net.fabricmc.api.ModInitializer
import net.minecraft.resources.ResourceLocation
import us.timinc.mc.cobblemon.unimplementeditems.blocks.UnimplementedItemsBlocks
import us.timinc.mc.cobblemon.unimplementeditems.items.PostBattleItem
import us.timinc.mc.cobblemon.unimplementeditems.items.UnimplementedItemsItems

object UnimplementedItems : ModInitializer {
    const val MOD_ID = "unimplemented_items"

    override fun onInitialize() {
        UnimplementedItemsItems.register()
        UnimplementedItemsBlocks.register()

        CobblemonEvents.BATTLE_VICTORY.subscribe { event ->
            val ownedPokemon =
                event.battle.actors.flatMap { it.pokemonList }.map { it.originalPokemon }.filter { it.isPlayerOwned() }
            ownedPokemon.forEach { pokemon ->
                val heldItemStack = pokemon.heldItem()
                val heldItem = heldItemStack.item
                if (heldItem is PostBattleItem) {
                    println(heldItemStack.displayName.string)
                    heldItem.doPostBattle(heldItemStack, pokemon, event)
                }
            }
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun myResourceLocation(str: String): ResourceLocation {
        return ResourceLocation(MOD_ID, str)
    }
}