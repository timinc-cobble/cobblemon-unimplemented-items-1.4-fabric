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
        val preGen9 = !UnimplementedItems.config.abilityPatchGen9;

        val hasHidden = pokemon.ability.priority == Priority.LOW
        if (hasHidden && preGen9) {
            player.sendMessage(Text.translatable(ErrorMessages.alreadyHasHiddenAbility))
            return ActionResult.FAIL
        }

        val tForm = pokemon.form
        val potentialAbilityMapping = tForm.abilities.mapping[Priority.LOW]
        if (potentialAbilityMapping == null) {
            player.sendMessage(Text.translatable(ErrorMessages.noHiddenAbility))
            return ActionResult.FAIL
        }

        val targetAbilityMapping = tForm.abilities.mapping[if (hasHidden) Priority.LOWEST else Priority.LOW]
        val potentialAbility = targetAbilityMapping?.get(0) ?: return ActionResult.FAIL
        val newAbilityBuilder = potentialAbility.template.builder
        val newAbility = newAbilityBuilder.invoke(potentialAbility.template, false)
        newAbility.index = 0
        newAbility.priority = Priority.LOW
        pokemon.ability = newAbility

        itemStack.count--
        return ActionResult.SUCCESS
    }
}