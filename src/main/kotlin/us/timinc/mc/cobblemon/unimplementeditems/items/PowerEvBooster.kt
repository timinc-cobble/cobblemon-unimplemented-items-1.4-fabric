package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.api.events.battles.BattleVictoryEvent
import com.cobblemon.mod.common.api.pokemon.stats.Stat
import com.cobblemon.mod.common.item.CobblemonItemGroups
import com.cobblemon.mod.common.pokemon.Pokemon
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class PowerEvBooster(private val stat: Stat, private val boostAmount: Int) : Item(FabricItemSettings().group(CobblemonItemGroups.HELD_ITEM_GROUP).maxCount(1)), PostBattleItem {
    override fun doPostBattle(itemStack: ItemStack, pokemon: Pokemon, event: BattleVictoryEvent) {
        pokemon.evs.add(stat, boostAmount)
    }
}