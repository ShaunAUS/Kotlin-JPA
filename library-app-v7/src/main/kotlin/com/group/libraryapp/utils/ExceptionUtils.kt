package com.group.libraryapp.utils

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun fail(): Nothing {
    throw IllegalArgumentException()
}


fun <T, ID> CrudRepository<T, ID>.findByIdOrThorw(id:ID):T{
    return this.findByIdOrNull(id)?: fail()
}

