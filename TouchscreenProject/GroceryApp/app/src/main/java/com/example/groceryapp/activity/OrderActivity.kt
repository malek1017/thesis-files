package com.example.groceryapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import com.example.groceryapp.R
import com.example.groceryapp.app.UtilFunctions.Companion.databaseDao
import com.example.groceryapp.app.UtilFunctions.Companion.showToast
import com.example.groceryapp.databinding.ActivityOrderBinding
import com.example.groceryapp.databinding.ActivityViewProductBinding
import com.example.groceryapp.model.Order
import com.example.groceryapp.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class OrderActivity : AppCompatActivity() {

    private val context = this@OrderActivity

    private lateinit var binding: ActivityOrderBinding

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        getIntentData()
        initListeners()
    }

    private fun setupActionBar(){
        supportActionBar?.title = "Make Order"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getIntentData(){
        product = intent.getSerializableExtra("product") as Product
    }

    private fun initListeners(){
        binding.btnMakeOrder.setOnClickListener {
            if(validateForm()){
                CoroutineScope(IO).launch {

                    val order = Order(null, binding.etFullName.text.toString(), binding.etAddress.text.toString(), product.id?:0)
                    databaseDao.insertOrder(order)

                    CoroutineScope(Main).launch {
                        showToast(context, "Order submitted")
                        finish()
                    }
                }
            }
        }
    }

    private fun validateForm(): Boolean {
        var valid = true

        val mName = binding.etFullName.text.toString()
        if (TextUtils.isEmpty(mName)) {
            binding.etFullName.error = getString(R.string.required)
            valid = false
        } else {
            binding.etFullName.error = null
        }

        val mAddress = binding.etAddress.text.toString()
        if (TextUtils.isEmpty(mAddress)) {
            binding.etAddress.error = getString(R.string.required)
            valid = false
        } else {
            binding.etAddress.error = null
        }

        return valid
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}