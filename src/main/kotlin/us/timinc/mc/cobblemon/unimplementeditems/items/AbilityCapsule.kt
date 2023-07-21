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

val settings: FabricItemSettings = FabricItemSettings()
    .group(CobblemonItemGroups.MEDICINE_ITEM_GROUP)
    .maxCount(16)

class AbilityCapsule() : Item(settings) {
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

        if (!target.pokemon.isPlayerOwned() || target.ownerUUID !== player.uuid) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.notYourPokemon))
            return InteractionResult.FAIL
        }

        val form = target.form
        val potentialAbilityPriorityPool =
            form.abilities.mapping[target.pokemon.ability.priority]!!
        if (potentialAbilityPriorityPool.size == 1) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.onlyOneCommonAbility))
            return InteractionResult.FAIL
        }

        val potentialAbility = potentialAbilityPriorityPool[1 - target.pokemon.ability.index]

        val newAbilityBuilder = potentialAbility.template.builder
        val newAbility = newAbilityBuilder.invoke(potentialAbility.template, false)
        newAbility.index = 1 - target.pokemon.ability.index
        newAbility.priority = target.pokemon.ability.priority
        target.pokemon.ability = newAbility

        itemStack.count--
        return InteractionResult.SUCCESS
    }
}