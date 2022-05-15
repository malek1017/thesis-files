package com.example.groceryapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "product_id")
    val productId: Int = 0

): Serializable

data class OrderProductId(
    @ColumnInfo(name = "product_id")
    val productId: Int = 0,
)