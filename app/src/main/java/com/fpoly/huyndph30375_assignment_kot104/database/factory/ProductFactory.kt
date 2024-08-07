package com.fpoly.huyndph30375_assignment_kot104.database.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fpoly.huyndph30375_assignment_kot104.database.repository.ProductRepository
import com.fpoly.huyndph30375_assignment_kot104.database.viewModel.ProductViewModel

class ProductFactory(private val productRepository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(productRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
