package com.mk.api

interface DataParser<in INPUT, out OUTPUT> {
    @Throws(ParsingException::class)
    fun parse(input: INPUT?): OUTPUT
}

class ParsingException(msg: String, cause: Throwable? = null) : Exception(msg, cause)
