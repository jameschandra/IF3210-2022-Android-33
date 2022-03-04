package com.example.perludilindungi.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Bookmark(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val province: String,
    val city: String,
    val faskes_id: Int
    )