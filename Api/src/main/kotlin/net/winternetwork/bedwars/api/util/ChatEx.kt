package net.winternetwork.bedwars.api.util

import org.bukkit.Bukkit
import org.bukkit.ChatColor

val String.color: String
    get() = ChatColor.translateAlternateColorCodes('&', this)

fun logColoredMessage(vararg messages: String) {
    Bukkit.getConsoleSender().run {
        messages.forEach {
            sendMessage(it)
        }
    }
}