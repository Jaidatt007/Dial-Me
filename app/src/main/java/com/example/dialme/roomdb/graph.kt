package com.example.dialme.roomdb

import android.content.Context
import androidx.room.Room

object database_graph {
    lateinit var database : DialerDatabase

    val contactRepository by lazy {

//        WishRepository(wishDao = database.wishDao())
    }

    val callHistoryRepository by lazy {

//        WishBinRepository(wishBinDao = database.wishBinDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context,
            DialerDatabase::class.java,
            name = "dialer.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}