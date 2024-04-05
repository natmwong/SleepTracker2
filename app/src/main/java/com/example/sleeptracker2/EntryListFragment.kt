package com.example.sleeptracker2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class EntryListFragment : Fragment() {
    // Add these properties
    private val sleepEntries: ArrayList<DisplayEntry> = arrayListOf()
    private lateinit var entriesRv: RecyclerView
    private lateinit var sleepEntryAdapter: SleepEntryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_entry_list, container, false)

        // Lookup the RecyclerView in activity layout
        entriesRv = view.findViewById<RecyclerView>(R.id.entry_recycler_view)
        // Create adapter passing in the list of sleep entries
        sleepEntryAdapter = SleepEntryAdapter(this, sleepEntries)
        // Attach the adapter to the RecyclerView to populate items
        entriesRv.adapter = sleepEntryAdapter
        // Set layout manager to position the items
        entriesRv.layoutManager = LinearLayoutManager(context)

        // Update the return statement to return the inflated view from above
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        fetchEntries()
    }

    companion object {
        fun newInstance(): EntryListFragment {
            return EntryListFragment()
        }
    }

    private fun fetchEntries() {
        lifecycleScope.launch {
            (activity?.application as EntryApplication).db.sleepEntryDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayEntry(
                        entity.date,
                        entity.hours,
                        entity.quality
                    )
                }.also { mappedList ->
                    sleepEntries.clear()
                    sleepEntries.addAll(mappedList)
                    sleepEntryAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}