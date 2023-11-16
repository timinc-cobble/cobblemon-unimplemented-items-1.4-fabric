@file:Suppress("MemberVisibilityCanBePrivate")

package us.timinc.mc.cobblemon.unimplementeditems.blocks

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import us.timinc.mc.cobblemon.unimplementeditems.UnimplementedItems

object UnimplementedItemsBlocks {
    val REPEL = Block(FabricBlockSettings.copyOf(Blocks.STONE))

    fun register() {
        Registry.register(Registries.BLOCK, UnimplementedItems.myResourceLocation("repel"), REPEL)
        Registry.register(
            Registries.ITEM, UnimplementedItems.myResourceLocation("repel"), BlockItem(REPEL, FabricItemSettings())
        )

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register { content ->
            content.add(REPEL)
        }
    }
}