package com.example.khassaaz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.khassaaz.adapter.ProductAdapter
import com.example.khassaaz.model.Product

class MainActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var allProducts: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.productRecyclerView)
        val menBtn: Button = findViewById(R.id.menBtn)
        val womenBtn: Button = findViewById(R.id.womenBtn)
        val kidsBtn: Button = findViewById(R.id.kidsBtn)
        val goToCartBtn: Button = findViewById(R.id.goToCartBtn)

        // Updated Sample Data using drawable resources
        allProducts = listOf(
            Product(1, "T-Shirt", "Men", 1200.0, R.drawable.tshirt),
            Product(2, "Kurta", "Men", 2200.0, R.drawable.kurta),
            Product(3, "Hoodie", "Men", 1800.0, R.drawable.hoodie),
            Product(4, "Abaya", "Women", 2500.0, R.drawable.abaya),
            Product(5, "Scarf", "Women", 500.0, R.drawable.scarf),
            Product(6, "Jeans", "Women", 1600.0, R.drawable.jeans),
            Product(7, "Kids T-Shirt", "Kids", 700.0, R.drawable.kidstshirt),
            Product(8, "Frock", "Kids", 1300.0, R.drawable.frock)
        )

        productAdapter = ProductAdapter(this, allProducts.toMutableList()) { selectedProduct ->
            val intent = Intent(this, ProductDetailActivity::class.java).apply {
                putExtra("name", selectedProduct.name)
                putExtra("category", selectedProduct.category)
                putExtra("price", selectedProduct.price)
                putExtra("imageResId", selectedProduct.imageResId)
            }
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productAdapter

        menBtn.setOnClickListener {
            filterProductsByCategory("Men")
        }
        womenBtn.setOnClickListener {
            filterProductsByCategory("Women")
        }
        kidsBtn.setOnClickListener {
            filterProductsByCategory("Kids")
        }

        goToCartBtn.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun filterProductsByCategory(category: String) {
        val filtered = allProducts.filter { it.category == category }
        productAdapter.updateProducts(filtered)
    }

}
