package br.redebaiana.baianowars.api.game.flag

import org.bukkit.event.Event

interface Flag<E : Event> {

    val eventClass: Class<E>

    fun execute(e: E)
}