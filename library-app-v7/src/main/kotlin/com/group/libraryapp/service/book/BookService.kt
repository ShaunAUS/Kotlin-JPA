package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.utils.fail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService constructor(


        //kotlin 자동 매개변수 생성자 생성 = 생성자 주입
        private val bookRepository: BookRepository,
        private val userRepository: UserRepository,
        private val userLoanHistoryRepository: UserLoanHistoryRepository,

        ) {

    @Transactional
    fun saveBook(request: BookRequest) {
        bookRepository.save(Book(request.name))
    }


    @Transactional
    fun loanBook(request: BookLoanRequest) {
        //val book = bookRepository.findByName(request.bookName)?: throw IllegalArgumentException()
        val book = bookRepository.findByName(request.bookName)?: fail() //utils 사용
        if (userLoanHistoryRepository.findByBookNameAndIsReturn(request.bookName, false) != null) {
            throw IllegalArgumentException("진닥 대출되어 있는 책입니다")
        }

        val user = userRepository.findByName(request.userName)?: fail()
        user.loanBook(book)


    }


    @Transactional
    fun returnBook(request: BookReturnRequest) {
        val user = userRepository.findByName(request.userName)?: fail()
        user.returnBook(request.bookName)

    }


}