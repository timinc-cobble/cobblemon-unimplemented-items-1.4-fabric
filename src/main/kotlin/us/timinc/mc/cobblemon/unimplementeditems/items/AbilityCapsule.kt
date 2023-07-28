package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.item.CobblemonItemGroups
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

class AbilityCapsule() : PokemonItem(FabricItemSettings()
    .group(CobblemonItemGroups.MEDICINE_ITEM_GROUP)
    .maxCount(16)) {
    override fun processInteraction(
        itemStack: ItemStack,
        player: Player,
        target: PokemonEntity,
        pokemon: Pokemon
    ): InteractionResult {
        val form = target.form
        val potentialAbilityPriorityPool =
            form.abilities.mapping[pokemon.ability.priority]!!
        if (potentialAbilityPriorityPool.size == 1) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.onlyOneCommonAbility))
            return InteractionResult.FAIL
        }

        val potentialAbility = potentialAbilityPriorityPool[1 - pokemon.ability.index]

        val newAbilityBuilder = potentialAbility.template.builder
        val newAbility = newAbilityBuilder.invoke(potentialAbility.template, false)
        newAbility.index = 1 - pokemon.ability.index
        newAbility.priority = pokemon.ability.priority
        pokemon.ability = newAbility

        itemStack.count--
        return InteractionResult.SUCCESS
    }
}