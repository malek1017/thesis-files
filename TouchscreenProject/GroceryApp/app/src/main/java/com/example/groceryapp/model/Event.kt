package com.example.groceryapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "event_name")
    val eventName: String,
    @ColumnInfo(name = "extra_information")
    val extraInformation: String?,
    @ColumnInfo(name = "created_at")
    val createdAt: Instant,
)

data class EventNameExtraInformationAndTimestamp(
    @ColumnInfo(name = "event_name")
    val eventName: String,
    @ColumnInfo(name = "extra_information")
    val extraInformation: String?,
    @ColumnInfo(name = "created_at")
    val createdAt: Instant = Instant.now(),
)

object Events {
    const val APP_STARTED = "app_started"
    const val ORDER_SUBMITTED = "order_submitted"
    const val ADDED_TO_CART = "added_to_cart"
    const val BUTTON_CLICK_X = "button_click_x"
}