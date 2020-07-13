package net.winternetwork.bedwars.api.module

abstract class Module(
        val name: String,
        val priority: ModulePriority
) {

    fun init() {}
    fun disable() {}
    fun onSecPassed() {}

    fun log(text: String) {
        println(
                "[%s] %s".format(name, text)
        )
    }
}

enum class ModulePriority(val id: Int) {
    LOWEST(4),
    LOW(3),
    NORMAL(2),
    HIGH(1),
    HIGHEST(0)
}