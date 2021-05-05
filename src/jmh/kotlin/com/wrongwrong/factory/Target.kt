package com.wrongwrong.factory

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Target(val foo: Int, val bar: Int, val baz: Int, val qux: Int, val quux: Int) {
    companion object {
        @JvmStatic
        @JsonCreator
        fun creator(
            @JsonProperty("foo") foo: Int,
            @JsonProperty("bar") bar: Int,
            @JsonProperty("baz") baz: Int,
            @JsonProperty("qux") qux: Int,
            @JsonProperty("quux") quux: Int = -1
        ) = Target(foo, bar, baz, qux, quux)
    }
}
