package com.example.khassaaz.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.khassaaz.model.CartItem
import com.example.khassaaz.model.Product

class CartDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "CartDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """CREATE TABLE cart_items (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT,
            category TEXT,
            price REAL,
            imageResId INTEGER,
            selectedSize TEXT,
            selectedColor TEXT
        )"""
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS cart_items")
        onCreate(db)
    }

    fun addItem(item: CartItem) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", item.product.name)
            put("category", item.product.category)
            put("price", item.product.price)
            put("imageResId", item.product.imageResId)
            put("selectedSize", item.selectedSize)
            put("selectedColor", item.selectedColor)
        }
        db.insert("cart_items", null, values)
        db.close()
    }

    fun getItems(): List<CartItem> {
        val list = mutableListOf<CartItem>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM cart_items", null)
        while (cursor.moveToNext()) {
            val product = Product(
                id = cursor.getInt(0),
                name = cursor.getString(1),
                category = cursor.getString(2),
                price = cursor.getDouble(3),
                imageResId = cursor.getInt(4)
            )
            val selectedSize = cursor.getString(5)
            val selectedColor = cursor.getString(6)
            list.add(CartItem(product, selectedSize, selectedColor))
        }
        cursor.close()
        db.close()
        return list
    }

    fun clearCart() {
        writableDatabase.execSQL("DELETE FROM cart_items")
    }

    fun getTotalPrice(): Double {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT SUM(price) FROM cart_items", null)
        var total = 0.0
        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0)
        }
        cursor.close()
        return total
    }

}