package us.timinc.mc.cobblemon.unimplementeditems.config

import com.google.gson.GsonBuilder
import net.minecraft.block.Blocks
import net.minecraft.loot.LootTables
import net.minecraft.util.Identifier
import us.timinc.mc.cobblemon.unimplementeditems.UnimplementedItems
import java.io.File
import java.io.FileReader
import java.io.PrintWriter

class BaseConfig {
    val abilityPatchGen9: Boolean = true
    val lootPoolOverrides: List<Identifier> = listOf(
        LootTables.FISHING_TREASURE_GAMEPLAY,
        Blocks.GRASS.lootTableId
    )

    class Builder {
        companion object {
            fun load(): BaseConfig {
                val gson = GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create()

                var config = BaseConfig()
                val configFile = File("config/${UnimplementedItems.MOD_ID}.json")
                configFile.parentFile.mkdirs()

                if (configFile.exists()) {
                    try {
                        val fileReader = FileReader(configFile)
                        config = gson.fromJson(fileReader, BaseConfig::class.java)
                        fileReader.close()
                    } catch (e: Exception) {
                        println("Error reading config file")
                    }
                }

                val pw = PrintWriter(configFile)
                gson.toJson(config, pw)
                pw.close()

                return config
            }
        }
    }
}