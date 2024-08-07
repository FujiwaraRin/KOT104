package com.fpoly.huyndph30375_assignment_kot104.database.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fpoly.huyndph30375_assignment_kot104.database.entities.Favorites
import com.fpoly.huyndph30375_assignment_kot104.database.repository.FavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {
    fun addToFavorite(favorites: Favorites) {
        viewModelScope.launch {
            favoriteRepository.addToFavorite(favorites)
        }
    }

    fun removeFromFavorite(favorites: Favorites) {
        viewModelScope.launch {
            favoriteRepository.removeFromFavorite(favorites)
        }
    }

    fun getAllFavorite(email: String): LiveData<List<Favorites>> =
        favoriteRepository.getAllFavorites(email)
}