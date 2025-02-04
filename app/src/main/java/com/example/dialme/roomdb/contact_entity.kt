package com.example.dialme.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class contact_entity (

    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,

    @ColumnInfo(name = "user_name")
    val name : String,

    @ColumnInfo(name = "contact_number")
    val number : String,

    @ColumnInfo(name = "favourite")
    val favourite : Boolean,

)