package com.group.libraryapp.domain.book

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface BookRepository:JpaRepository<Book,Long> {


    //Optional 대신 ? 가능 , 지금은 service와의 호환성 때문에 그냥 씀
    fun findByName(bookName:String):Optional<Book>
}