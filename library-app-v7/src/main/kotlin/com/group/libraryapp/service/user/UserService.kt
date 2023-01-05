package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.group.libraryapp.utils.fail
import com.group.libraryapp.utils.findByIdOrThorw
import org.springframework.data.repository.findByIdOrNull


@Service
class UserService(
        private val userRepository: UserRepository,  //  매개변수 받는 생성자 자동생성

) {

    //@Transactional 쓸려면 open인 상태, 밑으로 override가능해야하는 상태여야함
    //id 'org.jetbrains.kotlin.plugin.spring'version'1.6.21'
    @Transactional
    fun saveUser(request: UserCreateRequest) {
        val newUser = User(request.name, request.age!!)
        userRepository.save(newUser)
    }

    @Transactional(readOnly =true)
    fun getUsers():List<UserResponse>{
        return userRepository.findAll()
                .map { user -> UserResponse.of(user) }
                //.map { UserResponse(it) }   이 방법도 가능

    }

    // optional 로 반환값이 고정되있는경우 확장함수 사
    //코틀린-spring => crudRepositoryExtension 이미 존재함 이것을 사용!
    @Transactional
    fun updateUserName(request: UserUpdateRequest){
        //val user = userRepository.findById(request.id).orElseThrow(::IllegalArgumentException)
        //val user = userRepository.findByIdOrNull(request.id)?: fail()
        val user = userRepository.findByIdOrThorw(request.id)?: fail()   // 확장함수 한번더
        user.updateName(request.name)
    }
    @Transactional
    fun deleteUser(name:String){
        val user = userRepository.findByName(name)?: fail()
        userRepository.delete(user)
    }
}