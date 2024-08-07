package com.fpoly.huyndph30375_assignment_kot104.database.repository

import androidx.lifecycle.LiveData
import com.fpoly.huyndph30375_assignment_kot104.database.dao.ProductDao
import com.fpoly.huyndph30375_assignment_kot104.database.entities.Products

class ProductRepository(private val productDao: ProductDao) {
    val getAllProduct: LiveData<List<Products>> = productDao.getAllProduct()
    fun getItemProduct(id: Int): LiveData<Products> = productDao.getItemProduct(id)
    suspend fun addProduct(products: Products) {
        productDao.addProduct(products)
    }
}