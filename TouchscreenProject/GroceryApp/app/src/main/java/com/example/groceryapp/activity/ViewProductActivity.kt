package com.example.groceryapp.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.groceryapp.app.UtilFunctions
import com.example.groceryapp.databinding.ActivityViewProductBinding
import com.example.groceryapp.model.Order
import com.example.groceryapp.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewProductActivity : AppCompatActivity() {

    private val context = this@ViewProductActivity

    private lateinit var binding: ActivityViewProductBinding

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        getIntentData()
        initListeners()
        setProductData()
    }

    private fun setupActionBar(){
        supportActionBar?.title = "View Product"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getIntentData(){
        product = intent.getSerializableExtra("product") as Product
    }

    private fun initListeners(){
        binding.btnAddToCart.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                val order = Order(null, product.id?:0)
                UtilFunctions.databaseDao.insertOrder(order)

                CoroutineScope(Dispatchers.Main).launch {
                    UtilFunctions.showToast(context, "Added to cart")
                    finish()
                }
            }
        }
    }

    private fun setProductData(){
        binding.ivImg.setImageDrawable(ContextCompat.getDrawable(context, product.image))
        binding.tvTitle.text = product.title
        binding.tvDescription.text = product.description
        binding.tvPrice.text = product.cost
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}