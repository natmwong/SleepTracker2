package com.example.sleeptracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class InputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val dateEt = findViewById<EditText>(R.id.editTextDate)
        val hoursEt = findViewById<EditText>(R.id.editTextHours)
        val qualityEt = findViewById<EditText>(R.id.editTextQuality)
        val recordButton = findViewById<Button>(R.id.button)

        // Set up the submit button
        recordButton.setOnClickListener {
            val date = dateEt.text
            val hours = hoursEt.text.toString().toInt()
            val quality = qualityEt.text.toString()

            // Add the new sleep log to the database
            databaseHelper.addSleepLog(SleepLog(date, quality, hours))

            // Navigate back to the main activity
            finish()
        }
    }
}