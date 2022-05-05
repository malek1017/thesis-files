package com.example.groceryapp.app

import android.app.Application
import com.example.groceryapp.R
import com.example.groceryapp.database.ProductDao
import com.example.groceryapp.model.Events
import com.example.groceryapp.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyApplication : Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
        CoroutineScope(Dispatchers.IO).launch {
            setDefaultProducts(appContainer.productDao)
            appContainer.eventDao.insertEvent(Events.APP_STARTED)
        }
    }

    private suspend fun setDefaultProducts(productDao: ProductDao) {
        if (productDao.getProducts().isNotEmpty()) return
        val apple = Product(null, "Apple", getString(R.string.apple_description), R.drawable.ic_apple, "$5")
        val banana = Product(null, "Banana", getString(R.string.banana_description), R.drawable.ic_banana, "$10")
        val strawberry = Product(null, "Strawberry", getString(R.string.strawberry_description), R.drawable.ic_strawberry, "$7")
        val orange = Product(null, "Orange", getString(R.string.strawberry_description), R.drawable.ic_orange, "$7")
        val pineapple = Product(null, "Pineapple", getString(R.string.strawberry_description), R.drawable.ic_pineapple, "$7")
        val kiwi = Product(null, "Kiwi", getString(R.string.strawberry_description), R.drawable.ic_kiwi, "$7")
        val grape = Product(null, "Grape", getString(R.string.strawberry_description), R.drawable.ic_grape, "$7")
        // ic_blackberry is too big and crashes the app, just use another picture for now
        val blackberry = Product(null, "Blackberry", getString(R.string.strawberry_description), R.drawable.apple, "$7")

        productDao.insertProducts(
            listOf(
                apple,
                banana,
                strawberry,
                orange,
                pineapple,
                grape,
                kiwi,
                blackberry
            )
        )
    }
}