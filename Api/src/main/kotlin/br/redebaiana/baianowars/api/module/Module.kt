package br.redebaiana.baianowars.api.module

import br.redebaiana.baianowars.api.module.manager.Manager
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
}