package com.example.groceryapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "orders")
data class Order(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "product_id")
    var productId: Int = 0

): Serializable