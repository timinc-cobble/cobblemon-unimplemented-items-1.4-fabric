package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import us.timinc.mc.cobblemon.unimplementeditems.ErrorMessages
import us.timinc.mc.cobblemon.unimplementeditems.util.Ownership

abstract class PokemonItem(settings: FabricItemSettings) : Item(settings) {
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

        val pokemon = target.pokemon
        val storeCoordinates = pokemon.storeCoordinates.get()
        val ownership = when {
            storeCoordinates == null -> Ownership.WILD
            storeCoordinates.store.uuid == player.uuid -> Ownership.OWNER
            else -> Ownership.OWNED_ANOTHER
        }
        if (ownership != Ownership.OWNER) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.notYourPokemon))
            return InteractionResult.FAIL
        }

        return this.processInteraction(itemStack, player, target, target.pokemon)
    }

    abstract fun processInteraction(
        itemStack: ItemStack,
        player: Player,
        target: PokemonEntity,
        pokemon: Pokemon
    ): InteractionResult
}