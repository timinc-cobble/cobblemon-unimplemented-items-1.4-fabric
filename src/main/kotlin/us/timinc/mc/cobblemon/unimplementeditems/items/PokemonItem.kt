package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import us.timinc.mc.cobblemon.unimplementeditems.ErrorMessages
import us.timinc.mc.cobblemon.unimplementeditems.util.Ownership

abstract class PokemonItem(settings: FabricItemSettings) : Item(settings) {
    override fun useOnEntity(
        itemStack: ItemStack,
        player: PlayerEntity,
        target: LivingEntity,
        hand: Hand
    ): ActionResult  {
        if (player.world.isClient) {
            return ActionResult.PASS
        }

        if (target !is PokemonEntity) {
            player.sendMessage(Text.translatable(ErrorMessages.notPokemon))
            return ActionResult.FAIL
        }

        val pokemon = target.pokemon
        val storeCoordinates = pokemon.storeCoordinates.get()
        val ownership = when {
            storeCoordinates == null -> Ownership.WILD
            storeCoordinates.store.uuid == player.uuid -> Ownership.OWNER
            else -> Ownership.OWNED_ANOTHER
        }
        if (ownership != Ownership.OWNER) {
            player.sendMessage(Text.translatable(ErrorMessages.notYourPokemon))
            return ActionResult.FAIL
        }

        return this.processInteraction(itemStack, player, target, target.pokemon)
    }

    abstract fun processInteraction(
        itemStack: ItemStack,
        player: PlayerEntity,
        target: PokemonEntity,
        pokemon: Pokemon
    ): ActionResult
}