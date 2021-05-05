package com.wrongwrong.constructor

import com.fasterxml.jackson.annotation.JsonProperty

data class Target(
    @param:JsonProperty("foo") val foo: Int,
    @param:JsonProperty("bar") val bar: Int,
    @param:JsonProperty("baz") val baz: Int,
    @param:JsonProperty("qux") val qux: Int,
    @param:JsonProperty("quux") val quux: Int = -1
)
