package com.example.to_do_listapp.db

import androidx.room.TypeConverter
import java.util.Date


//Type Converter is used when you want to add custom objects in your db
class DateConverter {

    // From date to long to store into db.
    @TypeConverter
    fun fromDateToLong(date: Date):Long{
        return date.time
    }

    //from long to date to read from the db.
    @TypeConverter
    fun fromLongToDate(long: Long):Date{
        return Date(long)
    }
}