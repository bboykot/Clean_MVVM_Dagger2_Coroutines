package com.bboykot.weather.app.mappers

abstract class Mapper<FROM, TO> {
    abstract fun map(from: FROM): TO
}