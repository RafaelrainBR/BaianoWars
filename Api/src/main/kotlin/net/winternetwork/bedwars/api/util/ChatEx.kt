package net.winternetwork.bedwars.api.util

import org.bukkit.ChatColor

val String.color
    get() = ChatColor.translateAlternateColorCodes('&', this)