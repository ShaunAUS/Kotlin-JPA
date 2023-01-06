package com.group.libraryapp.controller.user

import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.service.user.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.*

@RestController
class UserController(

        private val userService: UserService, // 생성자 주입

        ) {

    @PostMapping("/user")
    fun saveUser(@RequestBody request: UserCreateRequest) {
        userService.saveUser(request)
    }

    @GetMapping("/user")
    fun getUsers(): List<UserResponse> {
        return userService.getUsers()
    }

    //이렇게도 반환 가능
    @PutMapping("/user")
    fun updateUserName(@RequestBody request: UserUpdateRequest) = userService.updateUserName(request)


    //RequestParam default =true  (필수)
    // String? => RequestParam false로 변환 시켜줌 (널도 들어올수 있으니까)
    @DeleteMapping("/user")
    fun deleteUser(@RequestParam name: String) {
        userService.deleteUser(name)
    }

}