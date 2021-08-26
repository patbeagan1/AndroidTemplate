package com.example.base

interface Converter<IN, OUT> {
    fun convert(incoming: IN): OUT
}