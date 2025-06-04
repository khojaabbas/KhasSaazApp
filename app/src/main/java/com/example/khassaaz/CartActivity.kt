package com.example.khassaaz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.khassaaz.database.CartDatabaseHelper
import com.example.khassaaz.R.id.cartTitle

class CartActivity : AppCompatActivity() {
    private lateinit var cartTextView: TextView
    private lateinit var totalTextView: TextView
    private lateinit var checkoutButton: Button
    private lateinit var dbHelper: CartDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartTextView = findViewById(cartTitle)
        totalTextView = findViewById(R.id.cartTotalTextView)
        checkoutButton = findViewById(R.id.checkoutButton)
        dbHelper = CartDatabaseHelper(this)

        loadCartItems()

        checkoutButton.setOnClickListener {
            if (dbHelper.getItems().isNotEmpty()) {
                Toast.makeText(this, "Checkout complete. Thank you!", Toast.LENGTH_SHORT).show()
                dbHelper.clearCart()
                loadCartItems()
            } else {
                Toast.makeText(this, "Your cart is empty.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadCartItems() {
        val items = dbHelper.getItems()

        if (items.isEmpty()) {
            cartTextView.text = "Your cart is empty"
            totalTextView.text = "Total: Rs. 0.0"
            return
        }

        val displayText = StringBuilder()
        var total = 0.0
        for (item in items) {
            val line = "${item.product.name} - Size: ${item.selectedSize} - Color: ${item.selectedColor} - Rs. ${item.product.price}"
            displayText.append(line).append("\n\n")
            total += item.product.price
        }

        cartTextView.text = displayText.toString()
        totalTextView.text = "Total: Rs. %.2f".format(total)
    }

}