package com.example.sleeptracker2

import android.app.Application

class EntryApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}