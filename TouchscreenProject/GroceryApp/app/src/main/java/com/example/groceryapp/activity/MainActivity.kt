package com.example.groceryapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.R
import com.example.groceryapp.adapter.ProductsAdapter
import com.example.groceryapp.app.UtilFunctions.Companion.databaseDao
import com.example.groceryapp.databinding.ActivityMainBinding
import com.example.groceryapp.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ProductsAdapter.Interaction {

    private val context = this@MainActivity

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: ProductsAdapter

    private var products: MutableList<Product> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        CoroutineScope(IO).launch {
            products.addAll(databaseDao.getProducts())
            adapter.notifyDataSetChanged()
        }

    }

    private fun initRecyclerView(){
        adapter = ProductsAdapter(products, this)
        val linearLayoutManager = LinearLayoutManager(context)
        binding.rvProducts.layoutManager = linearLayoutManager
        binding.rvProducts.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.cart){
            startActivity(Intent(context, CartActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onItemSelected(position: Int, item: Product) {
        val intent = Intent(context, ViewProductActivity::class.java)
        intent.putExtra("product", item)
        startActivity(intent)
    }

}