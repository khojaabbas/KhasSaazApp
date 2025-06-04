package com.example.khassaaz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khassaaz.R
import com.example.khassaaz.model.Product

class ProductAdapter(
    private val context: Context,
    private val productList: MutableList<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.cartItemNameTextView)
        val priceText: TextView = itemView.findViewById(R.id.cartItemPriceTextView)
        val imageView: ImageView = itemView.findViewById(R.id.cartItemImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.nameText.text = product.name
        holder.priceText.text = "Rs. ${product.price}"
        holder.imageView.setImageResource(product.imageResId)

        holder.itemView.setOnClickListener {
            onItemClick(product)
        }
    }

    fun updateProducts(newList: List<Product>) {
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }

}
