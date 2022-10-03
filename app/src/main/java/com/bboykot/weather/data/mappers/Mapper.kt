package com.bboykot.weather.data.mappers

abstract class Mapper<FROM, TO> {
    abstract fun map(from: FROM): TO
}