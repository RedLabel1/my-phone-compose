package com.redlabel.domain

abstract class Mapper<in P, out T> {

    abstract fun toDomain(dataModel: P) : T
}
