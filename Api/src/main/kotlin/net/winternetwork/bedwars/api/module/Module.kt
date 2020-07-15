package net.winternetwork.bedwars.api.module

abstract class Module(val name: String) {

    open fun init() {}
    open fun disable() {}
    open fun onSecPassed() {}

    fun log(text: String) {
        println(
                "[%s] %s".format(name, text)
        )
    }
}