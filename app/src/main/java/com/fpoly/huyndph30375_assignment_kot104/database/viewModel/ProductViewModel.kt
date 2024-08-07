package com.fpoly.huyndph30375_assignment_kot104.database.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fpoly.huyndph30375_assignment_kot104.database.entities.Products
import com.fpoly.huyndph30375_assignment_kot104.database.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    val getAllProduct: LiveData<List<Products>> = productRepository.getAllProduct
    fun getItemProduct(id: Int) = productRepository.getItemProduct(id)

    fun addProduct(products: Products) {
        viewModelScope.launch {
            productRepository.addProduct(products)
        }
    }
}