package com.example.khassaaz

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.khassaaz.database.CartDatabaseHelper
import com.example.khassaaz.model.CartItem
import com.example.khassaaz.model.Product

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var nameText: TextView
    private lateinit var categoryText: TextView
    private lateinit var priceText: TextView
    private lateinit var imageView: ImageView
    private lateinit var sizeSpinner: Spinner
    private lateinit var colorSpinner: Spinner
    private lateinit var addToCartBtn: Button

    private lateinit var product: Product
    private lateinit var dbHelper: CartDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        nameText = findViewById(R.id.productNameTextView)
        categoryText = findViewById(R.id.productCategoryTextView)
        priceText = findViewById(R.id.productPriceTextView)
        imageView = findViewById(R.id.productImageView)
        sizeSpinner = findViewById(R.id.sizeSpinner)
        colorSpinner = findViewById(R.id.colorSpinner)
        addToCartBtn = findViewById(R.id.addToCartButton)

        dbHelper = CartDatabaseHelper(this)

        val name = intent.getStringExtra("name") ?: ""
        val category = intent.getStringExtra("category") ?: ""
        val price = intent.getDoubleExtra("price", 0.0)
        val imageResId = intent.getIntExtra("imageResId", R.drawable.brandlogo)

        product = Product(0, name, category, price, imageResId)

        nameText.text = name
        categoryText.text = getString(R.string.category_label, category)
        priceText.text = getString(R.string.price_label, price)
        imageView.setImageResource(imageResId)

        val sizes = listOf("Small", "Medium", "Large")
        val colors = listOf("Red", "Blue", "Black", "White")

        sizeSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sizes)
        colorSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, colors)

        addToCartBtn.setOnClickListener {
            val selectedSize = sizeSpinner.selectedItem.toString()
            val selectedColor = colorSpinner.selectedItem.toString()
            val item = CartItem(product, selectedSize, selectedColor)
            dbHelper.addItem(item)
            Toast.makeText(this, getString(R.string.added_to_cart), Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}