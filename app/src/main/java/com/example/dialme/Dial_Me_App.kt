package com.example.dialme

import android.app.Application
import com.example.dialme.roomdb.database_graph

class DialMeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        database_graph.provide(this)
    }
}