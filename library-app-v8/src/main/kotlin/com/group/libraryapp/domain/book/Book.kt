package com.group.libraryapp.domain.book

import javax.persistence.*

@Entity // JPA entity로 등록하기 위해서느 기본생성자 필요함 -> id 'org.jetbrains.kotlin.plugin.jpa'version'1.6.21'
class Book(

        val name: String,

        @Enumerated(EnumType.STRING)
        val type:BookType,
        //enum type 사용 이유
        // 1. typo 검증
        // 2. 다형성을 이용해 when 절 깔끔하게 정리 가능 // 필수값(ex:점수) 빼먹을 수 없다.

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,  // 처음 기본값 무조건 null 인것이 관례상 맨밑으로
) {

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
        }
    }

    // 이러한 패턴은 object mother 이라 부르고 이렇게 생겨난것을 object fixture
    //TestCode 용  -> 멤버변수 type이 추가되더라도 테스트 코드에는 별 영향력이 없으니 추가되도 테스트 코드 상관없게 하기 위함
    //도메인은 테스트 코드에 많이 사용해서 자주 만들지마 DTO는 API를 테스트 하는거라 잘 안만듬(current testcode = service)
    //companion object 는 맨밑 관례상
    companion object{

        fun fixture(
                name: String = "책 이름",
                type: BookType = BookType.COMPUTER,
                id:Long? = null,
        ): Book {
            return Book(name,type,id)

        }
    }
}