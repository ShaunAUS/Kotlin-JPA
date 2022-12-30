package com.group.libraryapp.domain.book

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity // JPA entity로 등록하기 위해서느 기본생성자 필요함 -> id 'org.jetbrains.kotlin.plugin.jpa'version'1.6.21'
class Book(

        val name: String,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,  // 처음 기본값 무조건 null 인것이 관례상 맨밑으로
) {

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
        }
    }
}