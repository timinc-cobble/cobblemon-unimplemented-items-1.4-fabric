@file:Suppress("MemberVisibilityCanBePrivate")

package us.timinc.mc.cobblemon.unimplementeditems.items

import com.cobblemon.mod.common.api.pokemon.stats.Stats
import com.cobblemon.mod.common.item.group.CobblemonItemGroups
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.fabricmc.fabric.api.loot.v2.LootTableSource
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.block.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.item.Items
import net.minecraft.loot.LootManager
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTables
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.LootNumberProvider
import net.minecraft.loot.provider.number.LootNumberProviderTypes
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import us.timinc.mc.cobblemon.unimplementeditems.Loot
import us.timinc.mc.cobblemon.unimplementeditems.UnimplementedItems

object UnimplementedItemsItems {
    val ABILITY_CAPSULE = AbilityCapsule()
    val BOTTLE_CAP_ATK = BottleCap(Stats.ATTACK)
    val BOTTLE_CAP_DEF = BottleCap(Stats.DEFENCE)
    val BOTTLE_CAP_SA = BottleCap(Stats.SPECIAL_ATTACK)
    val BOTTLE_CAP_SD = BottleCap(Stats.SPECIAL_DEFENCE)
    val BOTTLE_CAP_SPD = BottleCap(Stats.SPEED)
    val BOTTLE_CAP_HP = BottleCap(Stats.HP)
    val BOTTLE_CAP = Item(FabricItemSettings().maxCount(16))
    val BOTTLE_CAP_GOLD = BottleCap(null)
    val POTION = Item(FabricItemSettings())
    val POTION_HYPER = Item(FabricItemSettings())
    val POTION_MAX = Item(FabricItemSettings())
    val ELIXIR = Item(FabricItemSettings())
    val ETHER = Item(FabricItemSettings())
    val ABILITY_PATCH = AbilityPatch()
    val DRY_ROOT = DryRoot()
    val POWER_WEIGHT = Item(FabricItemSettings())
    val POWER_BRACER = Item(FabricItemSettings())
    val POWER_BELT = Item(FabricItemSettings())
    val POWER_LENS = Item(FabricItemSettings())
    val POWER_BAND = Item(FabricItemSettings())
    val POWER_ANKLET = Item(FabricItemSettings())

    fun register() {
        LootTableEvents.MODIFY.register(LootTableEvents.Modify { resourceManager: ResourceManager, lootManager: LootManager, id: Identifier, tableBuilder: LootTable.Builder, source: LootTableSource ->
            Loot.register(source, id, tableBuilder)
        })

        ItemGroupEvents.modifyEntriesEvent(CobblemonItemGroups.CONSUMABLES_KEY).register { content ->
            content.add(ABILITY_CAPSULE)
            content.add(ABILITY_PATCH)
            content.add(BOTTLE_CAP_ATK)
            content.add(BOTTLE_CAP_DEF)
            content.add(BOTTLE_CAP_SA)
            content.add(BOTTLE_CAP_SD)
            content.add(BOTTLE_CAP_SPD)
            content.add(BOTTLE_CAP_HP)
            content.add(BOTTLE_CAP_GOLD)
        }

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register{ content ->
            content.add(BOTTLE_CAP)
        }

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register{ content ->
            content.add(POTION)
            content.add(POTION_HYPER)
            content.add(POTION_MAX)
            content.add(ELIXIR)
            content.add(ETHER)
            content.add(DRY_ROOT)
            content.add(POWER_ANKLET)
            content.add(POWER_BAND)
            content.add(POWER_BELT)
            content.add(POWER_LENS)
            content.add(POWER_BRACER)
            content.add(POWER_WEIGHT)
        }

        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("ability_capsule"), ABILITY_CAPSULE
        )
        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("bottle_cap_atk"), BOTTLE_CAP_ATK
        )
        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("bottle_cap_def"), BOTTLE_CAP_DEF
        )
        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("bottle_cap_sa"), BOTTLE_CAP_SA
        )
        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("bottle_cap_sd"), BOTTLE_CAP_SD
        )
        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("bottle_cap_spd"), BOTTLE_CAP_SPD
        )
        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("bottle_cap_hp"), BOTTLE_CAP_HP
        )
        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("bottle_cap"), BOTTLE_CAP
        )
        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("bottle_cap_gold"), BOTTLE_CAP_GOLD
        )
        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("potion"), POTION
        )
        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("potion_hyper"), POTION_HYPER
        )
        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("potion_max"), POTION_MAX
        )
        Registry.register(Registries.ITEM, UnimplementedItems.myResourceLocation("ether"), ETHER)
        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("elixir"), ELIXIR
        )
        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("ability_patch"), ABILITY_PATCH
        )
        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("dry_root"), DRY_ROOT
        )
        Registry.register(Registries.ITEM, UnimplementedItems.myResourceLocation("power_weight"), POWER_WEIGHT)
        Registry.register(Registries.ITEM, UnimplementedItems.myResourceLocation("power_bracer"), POWER_BRACER)
        Registry.register(Registries.ITEM, UnimplementedItems.myResourceLocation("power_belt"), POWER_BELT)
        Registry.register(Registries.ITEM, UnimplementedItems.myResourceLocation("power_lens"), POWER_LENS)
        Registry.register(Registries.ITEM, UnimplementedItems.myResourceLocation("power_band"), POWER_BAND)
        Registry.register(Registries.ITEM, UnimplementedItems.myResourceLocation("power_anklet"), POWER_ANKLET)

        FuelRegistry.INSTANCE.add(DRY_ROOT, 100)
        CompostingChanceRegistry.INSTANCE.add(DRY_ROOT, 0.3F)
    }
}