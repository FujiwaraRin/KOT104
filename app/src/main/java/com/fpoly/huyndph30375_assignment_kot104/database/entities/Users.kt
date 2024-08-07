package com.fpoly.huyndph30375_assignment_kot104.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val email: String,
    val name: String,
    val password: String
)