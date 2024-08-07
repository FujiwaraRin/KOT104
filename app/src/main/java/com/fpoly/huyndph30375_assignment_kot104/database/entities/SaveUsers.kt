package com.fpoly.huyndph30375_assignment_kot104.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SaveUsers(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val email: String
)