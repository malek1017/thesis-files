package com.example.groceryapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.example.groceryapp.R
import com.example.groceryapp.databinding.ActivityViewProductBinding
import com.example.groceryapp.model.Product

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
            val intent = Intent(context, OrderActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
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