package com.group.libraryapp.calculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class JunitCalculatorTest {

    @Test
    fun minusTest() {
        // given
        val calculator = Calculator(5)
        // when
        calculator.minus(3)
        // then
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun multiplyTest() {
        // given
        val calculator = Calculator(5)
        // when
        calculator.multiply(3)
        // then
        assertThat(calculator.number).isEqualTo(15)
    }

    @Test
    fun divideTest() {

        // given
        val calculator = Calculator(5)
        // when
        calculator.minus(3)
        // then
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun divideExceptionTest() {

        // given
        val calculator = Calculator(5)

        // when & then
        //에러메세지
        /*val message = assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.message
        assertThat(message).isEqualTo("0으로 나눌수 없습니다.")*/

        //when & then
        /**
         * apply함수 사용
         */
        assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.apply {
            assertThat(message).isEqualTo("0으로 나눌수 없습니다.")
        }

    }

}