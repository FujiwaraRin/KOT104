package com.fpoly.huyndph30375_assignment_kot104.database.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fpoly.huyndph30375_assignment_kot104.database.repository.FavoriteRepository
import com.fpoly.huyndph30375_assignment_kot104.database.viewModel.FavoriteViewModel

class FavoriteFactory(private val favoriteRepository: FavoriteRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteViewModel(favoriteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}