package net.winternetwork.bedwars.api.storage.adapter

interface Adapter<T, C> {

    fun from(c: C): T

    fun to(t: T): C
}