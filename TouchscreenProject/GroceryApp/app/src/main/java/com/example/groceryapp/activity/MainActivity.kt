package com.example.groceryapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.R
import com.example.groceryapp.adapter.ProductsAdapter
import com.example.groceryapp.app.MyApplication
import com.example.groceryapp.databinding.ActivityMainBinding
import com.example.groceryapp.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ProductsAdapter.Interaction {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ProductsAdapter

    private var products: MutableList<Product> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productDao = (applicationContext as MyApplication).appContainer.productDao

        initRecyclerView()
        CoroutineScope(IO).launch {
            products.addAll(productDao.getProducts())
            adapter.notifyDataSetChanged()
        }

    }

    private fun initRecyclerView() {
        adapter = ProductsAdapter(products, this)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvProducts.layoutManager = linearLayoutManager
        binding.rvProducts.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.cart) {
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onItemSelected(position: Int, item: Product) {
        startActivity(ViewProductActivity.newInstance(this, item))
    }

}