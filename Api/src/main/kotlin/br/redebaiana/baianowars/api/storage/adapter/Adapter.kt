package br.redebaiana.baianowars.api.storage.adapter

interface Adapter<T, C> {

    fun from(c: C): T

    fun to(t: T): C
}