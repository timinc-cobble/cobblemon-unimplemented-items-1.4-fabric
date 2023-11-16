package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import us.timinc.mc.cobblemon.unimplementeditems.ErrorMessages

class AbilityCapsule : PokemonItem(FabricItemSettings().maxCount(16)) {
    override fun processInteraction(
        itemStack: ItemStack,
        player: PlayerEntity,
        target: PokemonEntity,
        pokemon: Pokemon
    ): ActionResult {
        if (pokemon.ability.priority == Priority.LOW) {
            player.sendMessage(Text.translatable(ErrorMessages.alreadyHasHiddenAbility))
            return ActionResult.FAIL
        }

        val form = target.form
        val potentialAbilityPriorityPool =
            form.abilities.mapping[Priority.LOWEST]!!
        if (potentialAbilityPriorityPool.size == 1) {
            player.sendMessage(Text.translatable(ErrorMessages.onlyOneCommonAbility))
            return ActionResult.FAIL
        }

        val potentialAbility = potentialAbilityPriorityPool[1 - pokemon.ability.index]

        val newAbilityBuilder = potentialAbility.template.builder
        val newAbility = newAbilityBuilder.invoke(potentialAbility.template, false)
        newAbility.index = 1 - pokemon.ability.index
        newAbility.priority = pokemon.ability.priority
        pokemon.ability = newAbility

        itemStack.count--
        return ActionResult.SUCCESS
    }
}