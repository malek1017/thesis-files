package com.example.groceryapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.R
import com.example.groceryapp.adapter.OrdersAdapter
import com.example.groceryapp.adapter.ProductsAdapter
import com.example.groceryapp.app.UtilFunctions.Companion.databaseDao
import com.example.groceryapp.databinding.ActivityCartBinding
import com.example.groceryapp.model.Order
import com.example.groceryapp.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class CartActivity : AppCompatActivity() {

    private val context = this@CartActivity

    private lateinit var binding: ActivityCartBinding

    private lateinit var adapter: OrdersAdapter

    private var orders: MutableList<Order> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
    }

    private fun setupActionBar(){
        supportActionBar?.title = "Cart"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getOrders(){
        CoroutineScope(IO).launch {
            orders.clear()
            orders.addAll(databaseDao.getOrders())
            CoroutineScope(Main).launch {
                initRecyclerView()
            }
        }
    }

    private fun initRecyclerView(){
        adapter = OrdersAdapter(orders, null)
        val linearLayoutManager = LinearLayoutManager(context)
        binding.rvOrders.layoutManager = linearLayoutManager
        binding.rvOrders.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.make_order){
            startActivity(Intent(context, OrderActivity::class.java))
        }
        else if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        getOrders()
    }

}