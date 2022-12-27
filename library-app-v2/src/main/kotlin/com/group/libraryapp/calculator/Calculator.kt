package com.group.libraryapp.calculator

//data class equals 자동구현
 class Calculator(private var _number: Int) {


    //public getter를 통해 private 변수 접근
    //코틀린은 변수밑에 게터,세터!
    val number :Int
        get() = this._number

    fun add(operand: Int) {
        this._number += operand
    }

    fun minus(operand: Int) {
        this._number -= operand
    }

    fun multiply(operand: Int) {
        this._number *= operand
    }

    fun divide(operand: Int) {

        if (operand == 0) {
            throw IllegalArgumentException("0으로 나눌수 없습니다.")
        }
        this._number /= operand
    }
}