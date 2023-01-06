package com.group.libraryapp.dto.user.response

import com.group.libraryapp.domain.user.User

//DTO 클래스는 equals, hash 등등 사용할 수 있어서 dataclass로
data class UserResponse(

        val id: Long,
        val name: String,
        val age: Int?,


        ) {

    //방법 1
    /*constructor(user: User) : this(
            id = user.id!!,
            name = user.name,
            age = user.age,
            )*/

    //방법2
    companion object {
        fun of(user: User): UserResponse {
            return UserResponse(
                    id = user.id!!,
                    name = user.name,
                    age = user.age,
            )
        }
    }
}