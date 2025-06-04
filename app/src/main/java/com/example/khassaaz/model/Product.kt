package com.example.khassaaz.model

data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val price: Double,
    val imageResId: Int  // <- use drawable resource ID
)
