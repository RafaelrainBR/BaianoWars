package br.redebaiana.baianowars.api.module

import br.redebaiana.baianowars.api.config.YamlConfig
import br.redebaiana.baianowars.api.module.manager.Manager
import org.bukkit.plugin.Plugin
import java.io.File

abstract class Module(val name: String) {

    val dataFolder: File by lazy {
        File("plugins", name).apply {
            if (!exists()) mkdir()
        }
    }

    open val manager: Manager<*, *>? = null

    open fun init() {}
    open fun disable() {}
    open suspend fun onSecPassed() {}

    fun log(text: String) {
        println(
            "[%s] %s".format(name, text)
        )
    }

    protected fun config(plugin: Plugin, name: String) =
        lazy(LazyThreadSafetyMode.PUBLICATION) {
            YamlConfig(
                plugin,
                dataFolder,
                name
            )
        }
}