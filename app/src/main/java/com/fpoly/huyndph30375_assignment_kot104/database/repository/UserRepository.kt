package com.fpoly.huyndph30375_assignment_kot104.database.repository

import androidx.lifecycle.LiveData
import com.fpoly.huyndph30375_assignment_kot104.database.dao.UserDao
import com.fpoly.huyndph30375_assignment_kot104.database.entities.Users

class UserRepository(private val userDao: UserDao) {
    fun userSignIn(email: String, password: String): LiveData<List<Users>> =
        userDao.userSignIn(email, password)

    suspend fun userSignUp(users: Users) {
        userDao.userSignUp(users)
    }

    fun getUser(email: String): LiveData<Users> = userDao.getUser(email)
}