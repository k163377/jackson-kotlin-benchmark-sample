package com.wrongwrong.constructor

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
open class Benchmarks {
    private val kotlinMapper = jacksonObjectMapper()

    private val fullInput: ByteArray = kotlinMapper.writeValueAsBytes(Target(1, 2, 3, 4, 5))

    @Benchmark
    fun useKotlinMapper(): Target = kotlinMapper.readValue(fullInput, Target::class.java)

    private val defaultInput: ByteArray =
        kotlinMapper.writeValueAsBytes(mapOf("foo" to 1, "bar" to 2, "baz" to 3, "qux" to 4))

    @Benchmark
    fun useDefaultKotlinMapper(): Target = kotlinMapper.readValue(defaultInput, Target::class.java)
}
