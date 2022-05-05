package com.example.groceryapp.database.converter

import androidx.room.TypeConverter
import java.time.Instant

object InstantConverter {
    @TypeConverter
    fun instantToLong(instant: Instant): Long {
        return instant.toEpochMilli()
    }

    @TypeConverter
    fun longToInstant(long: Long): Instant {
        return Instant.ofEpochMilli(long)
    }
}