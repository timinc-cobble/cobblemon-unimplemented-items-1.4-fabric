@file:Suppress("MemberVisibilityCanBePrivate")

package us.timinc.mc.cobblemon.unimplementeditems.blocks

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.core.Registry
import net.minecraft.world.item.BlockItem
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Material
import us.timinc.mc.cobblemon.unimplementeditems.UnimplementedItems

object UnimplementedItemsBlocks {
    val REPEL = Block(FabricBlockSettings.of(Material.STONE).strength(1.5f).requiresTool())

    fun register() {
        Registry.register(Registry.BLOCK, UnimplementedItems.myResourceLocation("repel"), REPEL)
        Registry.register(Registry.ITEM, UnimplementedItems.myResourceLocation("repel"), BlockItem(REPEL, FabricItemSettings()))
    }
}