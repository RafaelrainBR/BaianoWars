package net.winternetwork.bedwars.api.util

import org.koin.core.context.KoinContextHandler
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

inline fun <reified T> inject(
        qualifier: Qualifier? = null,
        noinline parameters: ParametersDefinition? = null
) = lazy(LazyThreadSafetyMode.PUBLICATION) { KoinContextHandler.get().get<T>(qualifier, parameters) }