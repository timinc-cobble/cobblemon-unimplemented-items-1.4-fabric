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

class Elixir(private val healAmount: Int?) : PokemonItem(
    FabricItemSettings()
        .group(CobblemonItemGroups.MEDICINE_ITEM_GROUP)
) {
    override fun processInteraction(
        itemStack: ItemStack,
        player: Player,
        target: PokemonEntity,
        pokemon: Pokemon
    ): InteractionResult {
        if (!pokemon.isPlayerOwned() || target.ownerUUID !== player.uuid) {
            println("Is player owned: ${target.pokemon.isPlayerOwned()}")
            println("Owner according to Pokemon: ${target.ownerUUID}")
            println("Player's ID: ${player.uuid}")
            player.sendSystemMessage(Component.translatable(ErrorMessages.notYourPokemon))
            return InteractionResult.FAIL
        }

        val healableMoves = pokemon.moveSet.filter { it.currentPp <= it.maxPp }
        if (healableMoves.isEmpty()) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.alreadyPerfectPps))
            return InteractionResult.FAIL
        }

        for (healableMove in healableMoves) {
            healableMove.currentPp = if (healAmount == null) healableMove.maxPp else (healableMove.currentPp + healAmount).coerceAtMost(
                healableMove.maxPp
            )
        }

        itemStack.count--
        return InteractionResult.SUCCESS
    }
}