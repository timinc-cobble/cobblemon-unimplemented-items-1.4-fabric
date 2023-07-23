package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.item.CobblemonItemGroups
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import us.timinc.mc.cobblemon.unimplementeditems.ErrorMessages

class Elixir(private val healAmount: Int?) : Item(
    FabricItemSettings()
        .group(CobblemonItemGroups.MEDICINE_ITEM_GROUP)
) {
    override fun interactLivingEntity(
        itemStack: ItemStack,
        player: Player,
        target: LivingEntity,
        interactionHand: InteractionHand
    ): InteractionResult {
        if (player.level.isClientSide) {
            return InteractionResult.PASS
        }

        if (target !is PokemonEntity) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.notPokemon))
            return InteractionResult.FAIL
        }

        val tPokemon = target.pokemon
        if (!tPokemon.isPlayerOwned() || target.ownerUUID !== player.uuid) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.notYourPokemon))
            return InteractionResult.FAIL
        }

        val healableMoves = tPokemon.moveSet.filter { it.currentPp <= it.maxPp }
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