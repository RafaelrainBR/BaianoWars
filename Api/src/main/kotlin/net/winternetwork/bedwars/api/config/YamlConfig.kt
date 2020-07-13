package net.winternetwork.bedwars.api.config

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

class YamlConfig(
        plugin: Plugin,
        parent: File,
        fileName: String
) : YamlConfiguration() {

    private val file: File

    init {
        if (!parent.exists() && !parent.mkdir())
            throw IllegalStateException("Could not create parent file.")

        if (!parent.isDirectory)
            throw IllegalArgumentException("Parent is not a directory.")

        this.file = File(parent, fileName)

        reload()
    }

    fun <T> getOrDefault(path: String, def: T): T {
        val obj = get(path) ?: {
            set(path, def)
            save()
            def
        }

        return obj as T
    }

    fun save(): Boolean {
        return try {
            save(file)
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    @Throws
    fun reload() =
            with(file) {
                if (!exists() && !createNewFile())
                    throw java.lang.IllegalStateException("Could not create file.")

                if (isDirectory) throw IllegalStateException("File is a directory.")

                val reader = InputStreamReader(
                        FileInputStream(file),
                        StandardCharsets.UTF_8
                )

                load(reader)
            }
}