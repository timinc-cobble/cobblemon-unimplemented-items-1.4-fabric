package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.item.CobblemonItemGroups
import com.cobblemon.mod.common.pokemon.Pokemon
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import us.timinc.mc.cobblemon.unimplementeditems.ErrorMessages

class AbilityPatch : PokemonItem(
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
        if (pokemon.ability.priority == Priority.LOW) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.alreadyHasHiddenAbility))
            return InteractionResult.FAIL
        }

        val tForm = pokemon.form
        val potentialAbilityMapping = tForm.abilities.mapping[Priority.LOW]
        if (potentialAbilityMapping == null) {
            player.sendSystemMessage(Component.translatable(ErrorMessages.noHiddenAbility))
            return InteractionResult.FAIL
        }

        val potentialAbility = potentialAbilityMapping[0]
        val newAbilityBuilder = potentialAbility.template.builder
        val newAbility = newAbilityBuilder.invoke(potentialAbility.template, false)
        newAbility.index = 0
        newAbility.priority = Priority.LOW
        pokemon.ability = newAbility

        itemStack.count--
        return InteractionResult.SUCCESS
    }
}