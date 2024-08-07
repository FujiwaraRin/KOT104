package com.fpoly.huyndph30375_assignment_kot104.database.repository

import androidx.lifecycle.LiveData
import com.fpoly.huyndph30375_assignment_kot104.database.dao.SaveUserDao
import com.fpoly.huyndph30375_assignment_kot104.database.entities.SaveUsers

class SaveUserRepository(private val saveUserDao: SaveUserDao) {
    suspend fun addUser(saveUsers: SaveUsers) {
        saveUserDao.addUser(saveUsers)
    }

    suspend fun deleteUser(saveUsers: SaveUsers) {
        saveUserDao.deleteUser(saveUsers)
    }

    val getUser: LiveData<SaveUsers> = saveUserDao.getUser()
}