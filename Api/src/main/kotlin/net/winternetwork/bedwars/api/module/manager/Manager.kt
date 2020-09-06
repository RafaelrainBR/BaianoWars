package net.winternetwork.bedwars.api.module.manager

import net.winternetwork.bedwars.api.config.YamlConfig

abstract class Manager<ID, V>(val config: YamlConfig) {

    private val map = mutableMapOf<ID, V>()

    abstract fun loadAll()

    abstract fun unloadAll()

    fun add(id: ID, value: V) = map.put(id, value)

    operator fun get(id: ID) = map[id]

    fun remove(id: ID) = map.remove(id)

    fun all(action: (V) -> Unit) = map.values.forEach(action)

    val name: String
        get() = this::class.simpleName!!

    val first: V
        get() = map.values.first()
}