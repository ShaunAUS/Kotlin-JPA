package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.user.User
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class UserLoanHistory(

        @ManyToOne
        val user: User,

        val bookName: String, //불변

        var isReturn: Boolean,//가변

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,


        ) {

    fun doReturn() {
        this.isReturn = true
    }
}