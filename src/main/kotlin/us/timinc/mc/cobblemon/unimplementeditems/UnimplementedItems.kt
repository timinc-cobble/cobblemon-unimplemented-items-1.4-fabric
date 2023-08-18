package us.timinc.mc.cobblemon.unimplementeditems

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.fabricmc.fabric.api.loot.v2.LootTableEvents.Modify
import net.fabricmc.fabric.api.loot.v2.LootTableSource
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.LootTables
import net.minecraft.world.level.storage.loot.entries.LootItem
import us.timinc.mc.cobblemon.unimplementeditems.blocks.UnimplementedItemsBlocks
import us.timinc.mc.cobblemon.unimplementeditems.items.UnimplementedItemsItems

object UnimplementedItems : ModInitializer {
    const val MOD_ID = "unimplemented_items"

    override fun onInitialize() {
        LootTableEvents.MODIFY.register(Modify { _: ResourceManager, _: LootTables, id: ResourceLocation, tableBuilder: LootTable.Builder, source: LootTableSource ->
            if (source.isBuiltin && id == BuiltInLootTables.FISHING_TREASURE) {
                val unimplementedItemsPool = LootPool.Builder()
                    .with(LootItem.lootTableItem { UnimplementedItemsItems.BOTTLE_CAP }.setWeight(10).build())
                    .with(LootItem.lootTableItem { UnimplementedItemsItems.BOTTLE_CAP_GOLD }.setWeight(1).build())
                    .with(LootItem.lootTableItem { UnimplementedItemsItems.ABILITY_PATCH }.setWeight(1).build())
                    .with(LootItem.lootTableItem { Items.AIR }.setWeight(88).build())

                tableBuilder.withPool(unimplementedItemsPool)
            }

            if (source.isBuiltin && id == Blocks.GRASS.lootTable) {
                val dryRootPool = LootPool.lootPool()
                    .with(LootItem.lootTableItem { UnimplementedItemsItems.DRY_ROOT }.setWeight(1).build())
                    .with(LootItem.lootTableItem { Items.AIR }.setWeight(9).build())
                tableBuilder.withPool(dryRootPool)
            }
        })

        Registry.register(Registry.ITEM, myResourceLocation("ability_capsule"), UnimplementedItemsItems.ABILITY_CAPSULE)
        Registry.register(Registry.ITEM, myResourceLocation("bottle_cap_atk"), UnimplementedItemsItems.BOTTLE_CAP_ATK)
        Registry.register(Registry.ITEM, myResourceLocation("bottle_cap_def"), UnimplementedItemsItems.BOTTLE_CAP_DEF)
        Registry.register(Registry.ITEM, myResourceLocation("bottle_cap_sa"), UnimplementedItemsItems.BOTTLE_CAP_SA)
        Registry.register(Registry.ITEM, myResourceLocation("bottle_cap_sd"), UnimplementedItemsItems.BOTTLE_CAP_SD)
        Registry.register(Registry.ITEM, myResourceLocation("bottle_cap_spd"), UnimplementedItemsItems.BOTTLE_CAP_SPD)
        Registry.register(Registry.ITEM, myResourceLocation("bottle_cap_hp"), UnimplementedItemsItems.BOTTLE_CAP_HP)
        Registry.register(Registry.ITEM, myResourceLocation("bottle_cap"), UnimplementedItemsItems.BOTTLE_CAP)
        Registry.register(Registry.ITEM, myResourceLocation("bottle_cap_gold"), UnimplementedItemsItems.BOTTLE_CAP_GOLD)
        Registry.register(Registry.ITEM, myResourceLocation("potion"), UnimplementedItemsItems.POTION)
        Registry.register(Registry.ITEM, myResourceLocation("potion_hyper"), UnimplementedItemsItems.POTION_HYPER)
        Registry.register(Registry.ITEM, myResourceLocation("potion_max"), UnimplementedItemsItems.POTION_MAX)
        Registry.register(Registry.ITEM, myResourceLocation("ether"), UnimplementedItemsItems.ETHER)
        Registry.register(Registry.ITEM, myResourceLocation("elixir"), UnimplementedItemsItems.ELIXIR)
        Registry.register(Registry.ITEM, myResourceLocation("ability_patch"), UnimplementedItemsItems.ABILITY_PATCH)
        Registry.register(Registry.ITEM, myResourceLocation("dry_root"), UnimplementedItemsItems.DRY_ROOT)

        UnimplementedItemsBlocks.register()
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun myResourceLocation(str: String): ResourceLocation {
        return ResourceLocation(MOD_ID, str)
    }
}