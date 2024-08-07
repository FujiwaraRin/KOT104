package com.fpoly.huyndph30375_assignment_kot104.database.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fpoly.huyndph30375_assignment_kot104.database.repository.SaveUserRepository
import com.fpoly.huyndph30375_assignment_kot104.database.viewModel.SaveUserViewModel

class SaveUserFactory(private val saveUserRepository: SaveUserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SaveUserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SaveUserViewModel(saveUserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}