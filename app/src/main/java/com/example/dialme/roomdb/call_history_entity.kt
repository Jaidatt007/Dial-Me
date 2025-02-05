package com.example.dialme.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class call_history_entity(

    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,

    @ColumnInfo(name = "user_name")
    val name : String,

    @ColumnInfo(name = "contact_number")
    val number : String,

    @ColumnInfo(name = "last_status") //missed received rejected
    val lastStatus : String,

    @ColumnInfo(name = "last_call") //time
    val lastCall : Long = 0L
)