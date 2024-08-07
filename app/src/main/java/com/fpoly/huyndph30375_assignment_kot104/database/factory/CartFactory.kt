package com.fpoly.huyndph30375_assignment_kot104.database.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fpoly.huyndph30375_assignment_kot104.database.repository.CartRepository
import com.fpoly.huyndph30375_assignment_kot104.database.viewModel.CartViewModel

class CartFactory(private val cartRepository: CartRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CartViewModel(cartRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}