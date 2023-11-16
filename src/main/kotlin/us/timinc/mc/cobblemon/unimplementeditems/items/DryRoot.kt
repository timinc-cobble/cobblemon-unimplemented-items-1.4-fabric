package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import us.timinc.mc.cobblemon.unimplementeditems.ErrorMessages

class DryRoot : PokemonItem(
    FabricItemSettings()) {
    override fun processInteraction(
        itemStack: ItemStack,
        player: PlayerEntity,
        target: PokemonEntity,
        pokemon: Pokemon
    ): ActionResult {
        var changedOne = false
        pokemon.evs.forEach{entry ->
            if (entry.value > .0) {
                changedOne = true
                pokemon.evs[entry.key] = 0
            }
        }

        if (!changedOne) {
            player.sendMessage(Text.translatable(ErrorMessages.alreadyHasZeroEvs))
            return ActionResult.FAIL
        }

        itemStack.count--
        return ActionResult.SUCCESS
    }
}