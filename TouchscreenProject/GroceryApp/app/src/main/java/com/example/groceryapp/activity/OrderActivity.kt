package com.example.groceryapp.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.groceryapp.R
import com.example.groceryapp.app.MyApplication
import com.example.groceryapp.app.showToast
import com.example.groceryapp.databinding.ActivityOrderBinding
import com.example.groceryapp.model.Events
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    private val appContainer by lazy {
        (applicationContext as MyApplication).appContainer
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        initListeners()
    }

    private fun setupActionBar() {
        supportActionBar?.title = "Make Order"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initListeners() {
        binding.btnMakeOrder.setOnClickListener {
            if (validateForm()) {
                val fullName = binding.etFullName.text.toString()
                val address = binding.etAddress.text.toString()
                CoroutineScope(IO).launch {
                    appContainer.orderDao.deleteAllOrders()
                    appContainer.eventDao.insertEvent(
                        eventName = Events.ORDER_SUBMITTED,
                        extraInformation = "FullName:$fullName Address:$address"
                    )
                    withContext(Main) {
                        this@OrderActivity.showToast("Order submitted")
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
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}