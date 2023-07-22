package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.api.pokemon.stats.Stat
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.item.CobblemonItemGroups
import com.cobblemon.mod.common.pokemon.IVs
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import us.timinc.mc.cobblemon.unimplementeditems.ErrorMessages

class BottleCap(private val stat: Stat?) : Item(
    FabricItemSettings()
        .group(CobblemonItemGroups.MEDICINE_ITEM_GROUP)
        .maxCount(16)
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

        if (stat == null) {
            val nonPerfectIvs = tPokemon.ivs.filter { it.value != IVs.MAX_VALUE }
            if (nonPerfectIvs.isEmpty()) {
                player.sendSystemMessage(Component.translatable(ErrorMessages.alreadyPerfectIvs))
                return InteractionResult.FAIL
            }

            nonPerfectIvs.forEach { tPokemon.ivs[it.key] = IVs.MAX_VALUE }

            itemStack.count--
            return InteractionResult.SUCCESS
        }

        if (tPokemon.ivs[stat] == IVs.MAX_VALUE) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.alreadyPerfectIv, stat.displayName))
            return InteractionResult.FAIL
        }

        tPokemon.ivs[stat] = IVs.MAX_VALUE

        itemStack.count--
        return InteractionResult.SUCCESS
    }
}