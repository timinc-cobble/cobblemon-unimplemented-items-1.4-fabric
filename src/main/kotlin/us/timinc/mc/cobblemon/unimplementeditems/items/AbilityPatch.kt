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
import us.timinc.mc.cobblemon.unimplementeditems.UnimplementedItems

class AbilityPatch : PokemonItem(
    FabricItemSettings().maxCount(16)
) {
    override fun processInteraction(
        itemStack: ItemStack, player: PlayerEntity, target: PokemonEntity, pokemon: Pokemon
    ): ActionResult {
        // Pre-Gen9, if you already had your hidden, it had no effect.
        val preGen9 = !UnimplementedItems.config.abilityPatchGen9;

        // It has its hidden if the priority is Low.
        val hasHidden = pokemon.ability.priority == Priority.LOW
        // If we already have our hidden and we're pre-gen9, get out.
        if (hasHidden && preGen9) {
            player.sendMessage(Text.translatable(ErrorMessages.alreadyHasHiddenAbility))
            return ActionResult.FAIL
        }

        val tForm = pokemon.form
        // If we don't have our hidden, check to make sure there is a hidden.
        if (!hasHidden && tForm.abilities.mapping[Priority.LOW] == null) {
            player.sendMessage(Text.translatable(ErrorMessages.noHiddenAbility))
            return ActionResult.FAIL
        }

        // Get the new ability.
        val priority = if (hasHidden) Priority.LOWEST else Priority.LOW
        val targetAbilityMapping = tForm.abilities.mapping[priority]
        val potentialAbility = targetAbilityMapping?.get(0) ?: return ActionResult.FAIL
        val newAbilityBuilder = potentialAbility.template.builder
        val newAbility = newAbilityBuilder.invoke(potentialAbility.template, false)
        newAbility.index = 0
        newAbility.priority = priority
        pokemon.ability = newAbility

        // Consume and succeed.
        itemStack.count--
        return ActionResult.SUCCESS
    }
}