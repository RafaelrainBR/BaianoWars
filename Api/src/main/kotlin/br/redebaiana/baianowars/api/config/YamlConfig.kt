package br.redebaiana.baianowars.api.config

import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

class YamlConfig(
    private val plugin: Plugin,
    parent: File,
    fileName: String
) : YamlConfiguration() {

    private val file: File

    init {
        with(parent) {
            if (!exists() && !mkdir())
                throw IllegalStateException("Could not create parent file.")

            if (!isDirectory)
                throw IllegalArgumentException("Parent is not a directory.")
        }

        this.file = File(parent, fileName)
        reload()
    }

    @Suppress("UNCHECKED_CAST")
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

    fun section(name: String): ConfigurationSection {
        return getConfigurationSection(name) ?: createSection(name)
    }

    @Throws
    fun reload() =
        with(file) {
            if (!exists()) plugin.saveResource(name, false)

            if (isDirectory) throw IllegalStateException("File is a directory.")

            val reader = InputStreamReader(
                FileInputStream(this),
                StandardCharsets.UTF_8
            )

                load(reader)
            }

}