package ru.pavlov.smlr.utils

import org.mockito.Mockito

fun <T> whenever(call: T) = Mockito.`when`(call)