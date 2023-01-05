package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach


@SpringBootTest
class UserServiceTest
@Autowired constructor(
        private val userRepository: UserRepository,
        private val userService: UserService, // 코틀린은 마지막에 , 가능
) {

    @AfterEach
    fun clean() {
        userRepository.deleteAll()
    }

    @Test
    fun saveUserTest() {

        //given
        val request = UserCreateRequest("kmj", null)
        //when
        userService.saveUser(request)
        //then
        val findAll = userRepository.findAll()
        assertThat(findAll).hasSize(1)
        assertThat(findAll[0].name).isEqualTo("kmj")
        assertThat(findAll[0].age).isNull()   //코틀린 입장에서는 애노테이션 안쓰면 널이 들어갈수있는지 없는지 판단못함

    }

    @Test
    fun getUserstTest() {
        //given
        userRepository.saveAll(listOf(
                User("aaa", 30),
                User("bbb", 20)

        ))

        //when
        val findAll = userRepository.findAll();
        //then
        assertThat(findAll).hasSize(2)
        assertThat(findAll).extracting("name").containsExactlyInAnyOrder("aaa", "bbb")


    }

    @Test
    fun updateUserTest() {
        //given
        val savedUser = userRepository.save(
                User("kmj", 22)
        )
        val userUpdateRequest = UserUpdateRequest(savedUser.id!!, "mmm");
        // !!이유 = saveUser Id => null가능   //UserUpdateRequest id => null 불가

        //when
        userService.updateUserName(userUpdateRequest)

        //then

        val user = userRepository.findAll()[0]
        assertThat(user.name).isEqualTo("mmm")

    }

    @Test
    fun deleteTest() {

        //given
        val savedUser = userRepository.save(
                User("kmj", 22)
        )
        //when
        userService.deleteUser("kmj")
        //then
        assertThat(userRepository.findAll()).hasSize(0)
    }


}