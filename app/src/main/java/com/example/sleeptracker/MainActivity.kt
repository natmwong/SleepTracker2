package com.example.sleeptracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val sleepEntries: ArrayList<DisplayEntry> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Button declarations for DisplayEntry item
        val entryButton = findViewById<Button>(R.id.entryButton)
        val clearButton = findViewById<Button>(R.id.clearButton)

        // Lookup the RecyclerView in activity layout
        val entriesRv = findViewById<RecyclerView>(R.id.recyclerView)

        // Create adapter passing in the list of sleep entries
        val adapter = SleepEntryAdapter(this, sleepEntries)
        // Attach the adapter to the RecyclerView to populate items
        entriesRv.adapter = adapter
        // Set layout manager to position the items
        entriesRv.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            (application as EntryApplication).db.sleepEntryDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayEntry(
                        entity.date,
                        entity.hours,
                        entity.quality
                    )
                }.also { mappedList ->
                    sleepEntries.clear()
                    sleepEntries.addAll(mappedList)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        // Set up the button to navigate to the input page
        entryButton.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }

        //Set up the button to clear all sleep entries
        clearButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                (application as EntryApplication).db.sleepEntryDao().deleteAll()
            }
        }
    }
}