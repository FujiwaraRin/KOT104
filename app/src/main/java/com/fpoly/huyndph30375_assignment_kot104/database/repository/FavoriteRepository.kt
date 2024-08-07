package com.fpoly.huyndph30375_assignment_kot104.database.repository

import com.fpoly.huyndph30375_assignment_kot104.database.dao.FavoriteDao
import com.fpoly.huyndph30375_assignment_kot104.database.entities.Favorites

class FavoriteRepository(private val favoriteDao: FavoriteDao) {
    suspend fun addToFavorite(favorites: Favorites) {
        favoriteDao.addToFavorite(favorites)
    }

    suspend fun removeFromFavorite(favorites: Favorites) {
        favoriteDao.removeFromFavorite(favorites)
    }

    fun getAllFavorites(email: String) = favoriteDao.getAllFavorite(email)
}