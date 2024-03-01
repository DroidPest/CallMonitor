package com.mk.infrastructure

inline fun <reified T : Enum<*>> enumValueOrNull(name: String?, default: T): T =
    T::class.java.enumConstants?.firstOrNull { it.name.equals(name, true) } ?: default

inline fun <T1 : String?, T2 : String?, T3 : String?, R : Any> safeLet(
    p1: T1?,
    p2: T2?,
    p3: T3?,
    block: (T1, T2, T3) -> R?,
): R? = if (!p1.isNullOrBlank() && p2 != null && !p3.isNullOrBlank()) block(p1, p2, p3) else null
