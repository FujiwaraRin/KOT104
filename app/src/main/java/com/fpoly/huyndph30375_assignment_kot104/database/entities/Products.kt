package com.fpoly.huyndph30375_assignment_kot104.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Products(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nameProduct: String,
    val imgProduct: String,
    val priceProduct: Int,
    val rate: Int
)