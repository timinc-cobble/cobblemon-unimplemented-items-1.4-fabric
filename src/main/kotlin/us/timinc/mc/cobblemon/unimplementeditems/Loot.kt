package us.timinc.mc.cobblemon.unimplementeditems

import net.fabricmc.fabric.api.loot.v2.LootTableSource
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.util.Identifier

object Loot {
    fun register(source: LootTableSource, id: Identifier, tableBuilder: LootTable.Builder) {
        if (source.isBuiltin && UnimplementedItems.config.lootPoolOverrides.contains(id)) {
            println(id.path)
            val pool = LootPool.builder()
                .with(LootTableEntry.builder(Identifier(UnimplementedItems.MOD_ID, "overrides/${id.path}")))
            tableBuilder.pool(pool)
        }
    }
}