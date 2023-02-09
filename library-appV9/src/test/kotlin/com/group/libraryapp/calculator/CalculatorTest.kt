package com.group.libraryapp.calculator

import org.junit.jupiter.api.Test
import java.lang.IllegalStateException

class CalculatorTest {

    @Test
    fun add() {

        //given
        val calculator = Calculator(5);

        //when
        calculator.add(3)


        /**
         * Data class
         */
        /* val expectedCalculator = Calculator(8)
         if(calculator != expectedCalculator){
             throw IllegalArgumentException("addTest 실패")
         }*/

        /**
         * public getter을 통해 private 변수 접근
         */
        //then
        val expectedCalculator = Calculator(8)
        if (calculator.number != expectedCalculator.number) {
            throw IllegalArgumentException("addTest 실패")
        }

    }

    @Test
    fun minus() {

        //given
        val calculator = Calculator(5);

        //when
        calculator.minus(3)

        //then
        val expectedCalculator = Calculator(2)
        if (calculator.number != expectedCalculator.number) {
            throw IllegalArgumentException("addTest 실패")
        }

    }

    @Test
    fun multiply() {
        //given
        val calculator = Calculator(5);

        //when
        calculator.multiply(3)

        //then
        val expectedCalculator = Calculator(15)
        if (calculator.number != expectedCalculator.number) {
            throw IllegalArgumentException("addTest 실패")
        }
    }

    @Test
    fun devide() {
        //given
        val calculator = Calculator(10);

        //when
        calculator.divide(2)

        //then
        val expectedCalculator = Calculator(5)
        if (calculator.number != expectedCalculator.number) {
            throw IllegalArgumentException("addTest 실패")
        }
    }

    @Test
    fun devideExceptionTest() {
        //given
        val calculator = Calculator(5);

        try {

            //when
            calculator.divide(0)
        } catch (e: IllegalArgumentException) {

            if(e.message != "0으로 나눌수 없습니다."){
            throw IllegalStateException("메세지가 다릅니다")
            }
            //테스트 성공
        } catch (e: Exception) {
            //테스트 실패 원하는 예외 x
            throw IllegalStateException("테스트 실패")
        }

        throw IllegalStateException("예외 발생 x")

    }
}