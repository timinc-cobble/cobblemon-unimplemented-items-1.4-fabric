package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.api.pokemon.stats.Stat
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.IVs
import com.cobblemon.mod.common.pokemon.Pokemon
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import us.timinc.mc.cobblemon.unimplementeditems.ErrorMessages

class BottleCap(private val stat: Stat?) : PokemonItem(
    FabricItemSettings()
        .maxCount(16)
) {
    override fun processInteraction(
        itemStack: ItemStack,
        player: PlayerEntity,
        target: PokemonEntity,
        pokemon: Pokemon
    ): ActionResult {
        if (stat == null) {
            val nonPerfectIvs = pokemon.ivs.filter { it.value != IVs.MAX_VALUE }
            if (nonPerfectIvs.isEmpty()) {
                player.sendMessage(Text.translatable(ErrorMessages.alreadyPerfectIvs))
                return ActionResult.FAIL
            }

            nonPerfectIvs.forEach { pokemon.ivs[it.key] = IVs.MAX_VALUE }

            itemStack.count--
            return ActionResult.SUCCESS
        }

        if (pokemon.ivs[stat] == IVs.MAX_VALUE) {
            player.sendMessage(Text.translatable(ErrorMessages.alreadyPerfectIv, stat.displayName))
            return ActionResult.FAIL
        }

        pokemon.ivs[stat] = IVs.MAX_VALUE

        itemStack.count--
        return ActionResult.SUCCESS
    }
}