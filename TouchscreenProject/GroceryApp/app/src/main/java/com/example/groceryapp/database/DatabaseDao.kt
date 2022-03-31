package com.example.groceryapp.database

import androidx.room.*
import com.example.groceryapp.model.Order
import com.example.groceryapp.model.Product

@Dao
interface DatabaseDao {

    //================Product=====================//

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(productList: List<Product>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM products")
    fun getProducts(): List<Product>

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    @Query("SELECT * FROM products WHERE id=:id")
    suspend fun getProductById(id: Int): Product

    @Delete
    suspend fun deleteProduct(product: Product)

    //================Order=====================//

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrders(orderList: List<Order>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order)

    @Query("SELECT * FROM orders")
    fun getOrders(): List<Order>

    @Query("DELETE FROM orders")
    suspend fun deleteAllOrders()

    @Query("SELECT * FROM orders WHERE id=:id")
    suspend fun getOrderById(id: Int): Order

    @Delete
    suspend fun deleteOrder(order: Order)

}