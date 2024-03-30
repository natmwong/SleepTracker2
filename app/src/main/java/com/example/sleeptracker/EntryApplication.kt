package com.example.sleeptracker

import android.app.Application

class EntryApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}