package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.api.pokemon.stats.Stats
import com.cobblemon.mod.common.item.CobblemonItemGroups
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.world.item.Item

object UnimplementedItemsItems {
    val ABILITY_CAPSULE = AbilityCapsule()
    val BOTTLE_CAP_ATK = BottleCap(Stats.ATTACK)
    val BOTTLE_CAP_DEF = BottleCap(Stats.DEFENCE)
    val BOTTLE_CAP_SA = BottleCap(Stats.SPECIAL_ATTACK)
    val BOTTLE_CAP_SD = BottleCap(Stats.SPECIAL_DEFENCE)
    val BOTTLE_CAP_SPD = BottleCap(Stats.SPEED)
    val BOTTLE_CAP_HP = BottleCap(Stats.HP)
    val BOTTLE_CAP = Item(
        FabricItemSettings()
            .group(CobblemonItemGroups.MEDICINE_ITEM_GROUP)
            .maxCount(16)
    )
    val BOTTLE_CAP_GOLD = BottleCap(null)
}