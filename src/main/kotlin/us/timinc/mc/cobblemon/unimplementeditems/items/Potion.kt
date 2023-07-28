package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.item.CobblemonItemGroups
import com.cobblemon.mod.common.pokemon.Pokemon
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import us.timinc.mc.cobblemon.unimplementeditems.ErrorMessages

class Potion(private val healingAmount: Int?): PokemonItem(
    FabricItemSettings()
        .group(CobblemonItemGroups.MEDICINE_ITEM_GROUP)
) {
    override fun processInteraction(
        itemStack: ItemStack,
        player: Player,
        target: PokemonEntity,
        pokemon: Pokemon
    ): InteractionResult {
        if (pokemon.currentHealth >= pokemon.hp) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.alreadyPerfectHp))
            return InteractionResult.FAIL
        }

        if (healingAmount != null) {
            pokemon.currentHealth += healingAmount
        } else {
            pokemon.currentHealth = pokemon.hp
        }

        itemStack.count--
        return InteractionResult.SUCCESS
    }
}