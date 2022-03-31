package com.example.groceryapp.app

import android.app.Application
import android.content.res.Resources
import com.example.groceryapp.R
import com.example.groceryapp.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyApplication : Application() {

    companion object {
        lateinit var instance: Application
        lateinit var resourses: Resources
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        resourses = resources

        setDefaultProducts()
    }

    private fun setDefaultProducts(){
        CoroutineScope(Dispatchers.IO).launch {
            if(UtilFunctions.databaseDao.getProducts().isEmpty()){
                val apple = Product(null, "Apple", getString(R.string.apple_description), R.drawable.ic_apple, "$5")
                val banana = Product(null, "Banana", getString(R.string.banana_description), R.drawable.ic_banana, "$10")
                val strawberry = Product(null, "Strawberry", getString(R.string.strawberry_description), R.drawable.ic_strawberry, "$7")

                UtilFunctions.databaseDao.insertProduct(apple)
                UtilFunctions.databaseDao.insertProduct(banana)
                UtilFunctions.databaseDao.insertProduct(strawberry)
            }
        }
    }

}