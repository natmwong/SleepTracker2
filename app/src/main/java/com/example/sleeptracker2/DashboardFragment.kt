package com.example.sleeptracker2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {
    private lateinit var avgHrs: TextView
    private lateinit var minHrs: TextView
    private lateinit var maxHrs: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        avgHrs = view.findViewById<TextView>(R.id.avgHrs)
        minHrs = view.findViewById<TextView>(R.id.minHrs)
        maxHrs = view.findViewById<TextView>(R.id.maxHrs)

        val viewModel = (activity?.application as EntryApplication)

        viewModel.db.sleepEntryDao().getAvgHours().asLiveData().observe(viewLifecycleOwner, Observer { value ->
            avgHrs.text = if (value != null) String.format("%.2f", value) else "0.00"
        })

        viewModel.db.sleepEntryDao().getMinHours().asLiveData().observe(viewLifecycleOwner, Observer { value ->
            minHrs.text = value?.toString() ?: "0"
        })

        viewModel.db.sleepEntryDao().getMaxHours().asLiveData().observe(viewLifecycleOwner, Observer { value ->
            maxHrs.text = value?.toString() ?: "0"
        })

        return view
    }

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }
}