package com.group.libraryapp.calculator

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JunitTetst {

    //BeforeAll , AfterAll 은 @JvmStatic, companion 안에있어야함
    companion object {
        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            println("테스트 시작전 최초 1회")
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            println("테스트 시작후 최초 1회")
        }
    }

    @BeforeEach
    fun beforeEach() {
        println("각 메서드 실행전")
    }

    @AfterEach
    fun afterEach() {
        println("각 메서드 실행전")
    }

    @Test
    fun test() {
        println("test1")
    }

}