package com.example.dialme.roomdb

import android.content.Context
import androidx.room.Room
import com.example.dialme.repository.CallHistoryRepository
import com.example.dialme.repository.ContactRepository

object database_graph {
    lateinit var database : DialerDatabase

    val contactRepository by lazy {
        ContactRepository(contactDao = database.contactDao())
    }

    val callHistoryRepository by lazy {
        CallHistoryRepository(callHistoryDao = database.callHistoryDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context,
            DialerDatabase::class.java,
            name = "dialer.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}