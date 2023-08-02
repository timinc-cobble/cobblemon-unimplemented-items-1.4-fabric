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

class DryRoot : PokemonItem(
    FabricItemSettings()
    .group(CobblemonItemGroups.MEDICINE_ITEM_GROUP)) {
    override fun processInteraction(
        itemStack: ItemStack,
        player: Player,
        target: PokemonEntity,
        pokemon: Pokemon
    ): InteractionResult {
        var changedOne = false
        pokemon.evs.forEach{entry ->
            if (entry.value > .0) {
                changedOne = true
                pokemon.evs[entry.key] = 0
            }
        }

        if (!changedOne) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.alreadyHasZeroEvs))
            return InteractionResult.FAIL
        }

        return InteractionResult.SUCCESS
    }
}