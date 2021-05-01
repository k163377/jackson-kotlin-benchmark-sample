package com.wrongwrong

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
open class Benchmarks {
    private val javaMapper = ObjectMapper()
    private val kotlinMapper = jacksonObjectMapper()

    private val expected = TargetClazz(1, 2, 3, 4, 5)
    private val input: ByteArray = javaMapper.writeValueAsBytes(expected)

    @Benchmark
    fun useJavaMapper(): TargetClazz = javaMapper.readValue(input, TargetClazz::class.java)

    @Benchmark
    fun useKotlinMapper(): TargetClazz = kotlinMapper.readValue(input, TargetClazz::class.java)
}
