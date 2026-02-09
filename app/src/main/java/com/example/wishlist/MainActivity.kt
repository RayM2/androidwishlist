package com.example.wishlist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val wishlistItems = mutableListOf<WishlistItem>()
    private lateinit var wishlistAdapter: WishlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val wishlistRv = findViewById<RecyclerView>(R.id.wishlistRv)
        val nameEt = findViewById<EditText>(R.id.nameEt)
        val priceEt = findViewById<EditText>(R.id.priceEt)
        val urlEt = findViewById<EditText>(R.id.urlEt)
        val addBtn = findViewById<Button>(R.id.addBtn)

        wishlistAdapter = WishlistAdapter(wishlistItems)
        wishlistRv.adapter = wishlistAdapter
        wishlistRv.layoutManager = LinearLayoutManager(this)

        addBtn.setOnClickListener {
            val name = nameEt.text.toString()
            val price = priceEt.text.toString().toDoubleOrNull() ?: 0.0
            val url = urlEt.text.toString()

            if (name.isNotEmpty() && url.isNotEmpty()) {
                val newItem = WishlistItem(name, price, url)
                wishlistItems.add(newItem)
                wishlistAdapter.notifyItemInserted(wishlistItems.size - 1)

                nameEt.text.clear()
                priceEt.text.clear()
                urlEt.text.clear()
            }
        }
    }
}