package com.example.sleeptracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

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
            val date = dateEt.text.toString()
            val hours = hoursEt.text.toString().toInt()
            val quality = qualityEt.text.toString()

            if (date.isNotBlank() && hours != null && quality.isNotBlank()) {
                // Add the new sleep log to the database
                lifecycleScope.launch(IO) {
                    (application as EntryApplication).db.sleepEntryDao()
                        .insert(SleepEntryEntity(0, date, hours, quality))
                }
                // Navigate back to the main activity
                finish()
            } else {
                // Show a message to the user to fill in all fields
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}