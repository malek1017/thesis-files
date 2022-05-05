package com.example.groceryapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.groceryapp.app.MyApplication
import com.example.groceryapp.app.showToast
import com.example.groceryapp.databinding.ActivityViewProductBinding
import com.example.groceryapp.model.Events
import com.example.groceryapp.model.OrderProductId
import com.example.groceryapp.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewProductBinding
    private val product: Product by lazy {
        intent.getSerializableExtra(PRODUCT_EXTRA_ID) as Product
    }

    private val appContainer by lazy {
        (applicationContext as MyApplication).appContainer
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        initListeners()
        setProductData()
    }

    private fun setupActionBar() {
        supportActionBar?.title = "View Product"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initListeners() {
        val orderDao = appContainer.orderDao
        val eventDao = appContainer.eventDao
        binding.btnAddToCart.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val order = OrderProductId(product.id ?: 0)
                val insertedOrderId = orderDao.insertOrder(order)
                val insertedOrder = orderDao.getOrderById(insertedOrderId)
                eventDao.insertEvent(Events.ADDED_TO_CART, "$insertedOrder")

                CoroutineScope(Dispatchers.Main).launch {
                    this@ViewProductActivity.showToast("Added to cart")
                    finish()
                }
            }
        }
    }

    private fun setProductData() {
        binding.ivImg.setImageDrawable(ContextCompat.getDrawable(this, product.image))
        binding.tvTitle.text = product.title
        binding.tvDescription.text = product.description
        binding.tvPrice.text = product.cost
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val PRODUCT_EXTRA_ID = "product"

        fun newInstance(
            context: Context,
            product: Product,
        ): Intent {
            return Intent(context, ViewProductActivity::class.java)
                .putExtra(PRODUCT_EXTRA_ID, product)
        }
    }
}