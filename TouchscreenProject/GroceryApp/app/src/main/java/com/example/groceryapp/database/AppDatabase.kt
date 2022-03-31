package com.example.groceryapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.groceryapp.model.Order
import com.example.groceryapp.model.Product

@Database(entities = [Product::class, Order::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun databaseDao(): DatabaseDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "app.db")
                .fallbackToDestructiveMigration()
                .addCallback(
                    object:Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                        }

                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                        }}
                )
                .build()
    }

}