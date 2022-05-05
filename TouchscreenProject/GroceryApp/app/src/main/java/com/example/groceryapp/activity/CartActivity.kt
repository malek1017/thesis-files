package com.example.groceryapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.R
import com.example.groceryapp.adapter.OrdersAdapter
import com.example.groceryapp.app.MyApplication
import com.example.groceryapp.databinding.ActivityCartBinding
import com.example.groceryapp.model.Order
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding

    private val orders: MutableList<Order> = mutableListOf()

    private val appContainer by lazy { (applicationContext as MyApplication).appContainer }
    private val productDao by lazy { appContainer.productDao }
    private val orderDao by lazy { appContainer.orderDao }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
    }

    private fun setupActionBar() {
        supportActionBar?.title = "Cart"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getOrders() {
        CoroutineScope(IO).launch {
            orders.clear()
            orders.addAll(orderDao.getOrders())
            CoroutineScope(Main).launch {
                initRecyclerView()
            }
        }
    }

    private fun initRecyclerView() {
        val adapter = OrdersAdapter(orders, productDao)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvOrders.layoutManager = linearLayoutManager
        binding.rvOrders.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.make_order) {
            startActivity(Intent(this, OrderActivity::class.java))
        } else if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        getOrders()
    }
}