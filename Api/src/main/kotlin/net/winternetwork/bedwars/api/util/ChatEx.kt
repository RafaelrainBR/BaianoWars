package net.winternetwork.bedwars.api.util

import org.bukkit.ChatColor

val String.color: String
    get() = ChatColor.translateAlternateColorCodes('&', this)