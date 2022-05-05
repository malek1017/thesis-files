package com.example.groceryapp.app

import android.content.Context
import com.example.groceryapp.database.AppDatabase
import com.example.groceryapp.database.EventDao
import com.example.groceryapp.database.OrderDao
import com.example.groceryapp.database.ProductDao


class AppContainer(context: Context) {
    private val appDatabase: AppDatabase = AppDatabase.getDatabase(context)
    val orderDao: OrderDao by lazy { appDatabase.orderDao() }
    val productDao: ProductDao by lazy { appDatabase.productDao() }
    val eventDao: EventDao by lazy { appDatabase.eventDao() }
}