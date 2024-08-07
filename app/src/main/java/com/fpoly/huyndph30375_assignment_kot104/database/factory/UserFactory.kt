package com.fpoly.huyndph30375_assignment_kot104.database.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fpoly.huyndph30375_assignment_kot104.database.repository.UserRepository
import com.fpoly.huyndph30375_assignment_kot104.database.viewModel.UserViewModel

class UserFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}