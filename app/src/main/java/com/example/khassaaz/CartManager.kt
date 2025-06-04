package com.example.khassaaz

import com.example.khassaaz.model.CartItem

object CartManager {
    private val cartItems = mutableListOf<CartItem>()
    fun addItem(item: CartItem) {
        cartItems.add(item)
    }

    fun getItems(): List<CartItem> {
        return cartItems
    }

    fun getTotalPrice(): Double {
        return cartItems.sumOf { it.product.price }
    }

    fun clearCart() {
        cartItems.clear()
    }

}