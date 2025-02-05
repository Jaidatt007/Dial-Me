package com.example.dialme.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [contact_entity::class, call_history_entity::class],
    version = 2,
    exportSchema = false
)
abstract class DialerDatabase : RoomDatabase(){
    abstract fun contactDao() : ContactDao
    abstract fun callHistoryDao() : CallHistoryDao
}