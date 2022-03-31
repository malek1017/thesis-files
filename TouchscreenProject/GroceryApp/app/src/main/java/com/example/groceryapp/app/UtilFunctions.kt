package com.example.groceryapp.app

import android.content.Context
import android.widget.Toast
import androidx.room.Room
import com.example.groceryapp.database.AppDatabase
import com.example.groceryapp.database.DatabaseDao


class UtilFunctions {
    companion object {

        val appDatabase: AppDatabase =
            Room.databaseBuilder(MyApplication.instance, AppDatabase::class.java, "app.db")
                .fallbackToDestructiveMigration().build()

        val databaseDao: DatabaseDao = appDatabase.databaseDao()

        fun showToast(context: Context, message: String){
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }
}