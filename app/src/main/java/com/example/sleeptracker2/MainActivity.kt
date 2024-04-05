package com.example.sleeptracker2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.sleeptracker2.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Call helper method to swap the FrameLayout with the fragment
        replaceFragment(EntryListFragment())

        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val entryListFragment: Fragment = EntryListFragment()
        val dashboardFragment: Fragment = DashboardFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.action_log -> fragment = entryListFragment
                R.id.action_dashboard -> fragment = dashboardFragment
            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.action_log

        //Button declarations for DisplayEntry item
        val entryButton = findViewById<Button>(R.id.entryButton)
        val clearButton = findViewById<Button>(R.id.clearButton)

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

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.entry_frame_layout, fragment)
        fragmentTransaction.commit()
    }
}