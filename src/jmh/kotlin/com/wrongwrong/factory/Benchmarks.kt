package com.wrongwrong.factory

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
open class Benchmarks {
    private val javaMapper = ObjectMapper()
    private val kotlinMapper = jacksonObjectMapper()

    private val expected = Target(1, 2, 3, 4, 5)
    private val input: ByteArray = javaMapper.writeValueAsBytes(expected)

    @Benchmark
    fun useJavaMapper(): Target = javaMapper.readValue(input, Target::class.java)

    @Benchmark
    fun useKotlinMapper(): Target = kotlinMapper.readValue(input, Target::class.java)

    private val defaultInput: ByteArray =
        javaMapper.writeValueAsBytes(mapOf("foo" to 1, "bar" to 2, "baz" to 3, "qux" to 4))

    @Benchmark
    fun useDefaultKotlinMapper(): Target = kotlinMapper.readValue(defaultInput, Target::class.java)
}
