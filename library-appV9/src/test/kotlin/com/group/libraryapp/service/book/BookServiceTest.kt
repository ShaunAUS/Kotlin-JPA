package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.book.BookType
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.assertThrows

@SpringBootTest
class BookServiceTest @Autowired constructor(
        private val bookService: BookService,
        private val bookRepository: BookRepository,
        private val userRepository: UserRepository,
        private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {


    @AfterEach
    fun clean() {
        bookRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("책 저장 정상 동작 ")
    fun saveBook() {

        //given
        val requestBook = BookRequest("하하하책", BookType.COMPUTER)
        //when
        bookService.saveBook(requestBook)
        //then
        val findAll = bookRepository.findAll()

        assertThat(findAll).hasSize(1)
        assertThat(findAll[0].name).isEqualTo("하하하책")
        assertThat(findAll[0].type).isEqualTo(BookType.COMPUTER)
    }
    @Test
    fun loanBook() {

        //given
        bookRepository.save(Book.fixture("책이름"))
        userRepository.save(User("kmj", 11))
        val requestLoanBook = BookLoanRequest("kmj", "책이름")

        //when
        bookService.loanBook(requestLoanBook)

        //then
        val findAll = userLoanHistoryRepository.findAll()
        assertThat(findAll).hasSize(1)
        assertThat(findAll[0].bookName).isEqualTo("책이름")
        assertThat(findAll[0].status).isEqualTo(UserLoanStatus.LOANED)


    }

    @Test
    fun loanBookFailTest() {

        //given
        bookRepository.save(Book.fixture("책이름"))
        val savedUser = userRepository.save(User("kmj", 11))
        userLoanHistoryRepository.save(UserLoanHistory.fixture(savedUser, "책이름"))
        val requestLoanBook = BookLoanRequest("kmj", "책이름")


        //when ,then
        assertThrows<java.lang.IllegalArgumentException> {
            bookService.loanBook(requestLoanBook)
        }.apply {
            assertThat(message).isEqualTo("진닥 대출되어 있는 책입니다")
        }

    }
    @Test
    fun countLoanBookTest(){
        //given

        val savedUser = userRepository.save(User("minjae", null))
        userLoanHistoryRepository.saveAll(listOf(
                UserLoanHistory.fixture(savedUser,"A"),
                UserLoanHistory.fixture(savedUser,"B",UserLoanStatus.RETURNED),
                UserLoanHistory.fixture(savedUser,"C",UserLoanStatus.RETURNED),

        ))
        //when
        val result = bookService.countLoanbook();
        //then
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun getBookStatisticsTest(){
        bookRepository.saveAll(listOf(
                Book.fixture("A",BookType.COMPUTER),
                Book.fixture("B",BookType.COMPUTER),
                Book.fixture("C",BookType.ECONOMY)
        ))

        val results = bookService.getBookStatistics()

        assertThat(results).hasSize(2)
        val computerDto =results.first{result -> result.type == BookType.COMPUTER}
        assertThat(computerDto.count).isEqualTo(2)

        val economyDto =results.first{result -> result.type == BookType.ECONOMY}
        assertThat(economyDto.count).isEqualTo(1)


    }
}
