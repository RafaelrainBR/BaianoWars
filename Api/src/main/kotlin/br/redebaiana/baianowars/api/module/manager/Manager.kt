package br.redebaiana.baianowars.api.module.manager

import br.redebaiana.baianowars.api.config.YamlConfig

abstract class Manager<ID, V>(val config: YamlConfig) {

    private val map = mutableMapOf<ID, V>()

    abstract fun loadAll()

    abstract fun unloadAll()

    fun add(id: ID, value: V) = map.put(id, value)

    operator fun get(id: ID) = map[id]

    fun remove(id: ID) = map.remove(id)

    fun all() = map.values

    fun all(action: (V) -> Unit) = map.values.forEach(action)

    val name: String
        get() = this::class.simpleName!!

    val first: V
        get() = map.values.first()
}