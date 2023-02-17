package com.group.libraryapp.utils

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

//nothing = 함수의 실행이 끝나도 호출 코드로 복귀 안함 즉 의도적으로 예외를 발생시킴
fun fail(): Nothing {
    throw IllegalArgumentException()
}


fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id:ID):T{
    return this.findByIdOrNull(id)?: fail()
}

