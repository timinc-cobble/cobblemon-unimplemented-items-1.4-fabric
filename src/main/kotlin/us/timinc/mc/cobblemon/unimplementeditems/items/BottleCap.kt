package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.api.pokemon.stats.Stat
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.item.CobblemonItemGroups
import com.cobblemon.mod.common.pokemon.IVs
import com.cobblemon.mod.common.pokemon.Pokemon
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import us.timinc.mc.cobblemon.unimplementeditems.ErrorMessages

class BottleCap(private val stat: Stat?) : PokemonItem(
    FabricItemSettings()
        .group(CobblemonItemGroups.MEDICINE_ITEM_GROUP)
        .maxCount(16)
) {
    override fun processInteraction(
        itemStack: ItemStack,
        player: Player,
        target: PokemonEntity,
        pokemon: Pokemon
    ): InteractionResult {
        if (stat == null) {
            val nonPerfectIvs = pokemon.ivs.filter { it.value != IVs.MAX_VALUE }
            if (nonPerfectIvs.isEmpty()) {
                player.sendSystemMessage(Component.translatable(ErrorMessages.alreadyPerfectIvs))
                return InteractionResult.FAIL
            }

            nonPerfectIvs.forEach { pokemon.ivs[it.key] = IVs.MAX_VALUE }

            itemStack.count--
            return InteractionResult.SUCCESS
        }

        if (pokemon.ivs[stat] == IVs.MAX_VALUE) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.alreadyPerfectIv, stat.displayName))
            return InteractionResult.FAIL
        }

        pokemon.ivs[stat] = IVs.MAX_VALUE

        itemStack.count--
        return InteractionResult.SUCCESS
    }
}