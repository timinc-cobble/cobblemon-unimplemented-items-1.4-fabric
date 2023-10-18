package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.api.events.battles.BattleVictoryEvent
import com.cobblemon.mod.common.pokemon.Pokemon
import net.minecraft.item.ItemStack

interface PostBattleItem {
    fun doPostBattle(itemStack: ItemStack, pokemon: Pokemon, event: BattleVictoryEvent)
}