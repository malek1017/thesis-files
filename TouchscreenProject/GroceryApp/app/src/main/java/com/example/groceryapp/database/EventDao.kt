package com.example.groceryapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.groceryapp.model.Event
import com.example.groceryapp.model.EventNameExtraInformationAndTimestamp

@Dao
interface EventDao {
    suspend fun insertEvent(eventName: String, extraInformation: String? = null) {
        insertEvent(EventNameExtraInformationAndTimestamp(eventName, extraInformation))
    }

    @Insert(entity = Event::class)
    suspend fun insertEvent(eventNameExtraInformationAndTimestamp: EventNameExtraInformationAndTimestamp)

    @Query("""SELECT * FROM events""")
    suspend fun getAllEvents(): List<Event>

    @Query(
        """
        SELECT *
        FROM events
        WHERE created_at >= :time
        """
    )
    suspend fun getAllEventsSince(time: Long): List<Event>
}