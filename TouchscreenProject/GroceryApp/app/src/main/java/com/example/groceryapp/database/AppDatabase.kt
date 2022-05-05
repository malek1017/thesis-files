package com.example.groceryapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.groceryapp.database.converter.InstantConverter
import com.example.groceryapp.model.Event
import com.example.groceryapp.model.Order
import com.example.groceryapp.model.Product

@Database(
    entities = [Product::class, Order::class, Event::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(InstantConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun orderDao(): OrderDao
    abstract fun productDao(): ProductDao
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(appContext: Context): AppDatabase {
            return Room.databaseBuilder(appContext, AppDatabase::class.java, "app.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}