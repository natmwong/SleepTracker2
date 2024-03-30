package com.example.sleeptracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val sleepEntries: ArrayList<SleepEntry> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Button declaration for SleepEntry item
        val entryButton = findViewById<Button>(R.id.entryButton)

        // Lookup the RecyclerView in activity layout
        val entriesRv = findViewById<RecyclerView>(R.id.recyclerView)

        // Create adapter passing in the list of sleep entries
        val adapter = SleepEntryAdapter(this, sleepEntries)
        // Attach the adapter to the RecyclerView to populate items
        entriesRv.adapter = adapter
        // Set layout manager to position the items
        entriesRv.layoutManager = LinearLayoutManager(this)

        // Set up the button to navigate to the input page
        entryButton.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }
    }
}