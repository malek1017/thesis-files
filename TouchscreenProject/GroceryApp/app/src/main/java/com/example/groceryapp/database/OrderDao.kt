package com.example.groceryapp.database

import androidx.room.*
import com.example.groceryapp.model.Order
import com.example.groceryapp.model.OrderProductId
import com.example.groceryapp.model.Product

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrders(orderList: List<Order>)

    @Insert(entity = Order::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderProductId): Long

    @Query("SELECT * FROM orders")
    suspend fun getOrders(): List<Order>

    @Query("DELETE FROM orders")
    suspend fun deleteAllOrders()

    @Query("SELECT * FROM orders WHERE id=:id")
    suspend fun getOrderById(id: Long): Order

    @Delete
    suspend fun deleteOrder(order: Order)
}