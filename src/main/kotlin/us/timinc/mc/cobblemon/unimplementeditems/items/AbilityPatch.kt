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

class AbilityPatch : PokemonItem(
    FabricItemSettings()
        .maxCount(16)
) {
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

        val tForm = pokemon.form
        val potentialAbilityMapping = tForm.abilities.mapping[Priority.LOW]
        if (potentialAbilityMapping == null) {
            player.sendMessage(Text.translatable(ErrorMessages.noHiddenAbility))
            return ActionResult.FAIL
        }

        val potentialAbility = potentialAbilityMapping[0]
        val newAbilityBuilder = potentialAbility.template.builder
        val newAbility = newAbilityBuilder.invoke(potentialAbility.template, false)
        newAbility.index = 0
        newAbility.priority = Priority.LOW
        pokemon.ability = newAbility

        itemStack.count--
        return ActionResult.SUCCESS
    }
}