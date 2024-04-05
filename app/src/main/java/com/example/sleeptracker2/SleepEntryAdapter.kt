package com.example.sleeptracker2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SleepEntryAdapter(private val entryListFragment: EntryListFragment, private val entries: List<DisplayEntry>) : RecyclerView.Adapter<SleepEntryAdapter.ViewHolder>() {
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: Create member variables for any view that will be set
        // as you render a row.
        val dateTextView: TextView
        val hoursTextView: TextView
        val qualityTextView: TextView


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // TODO: Store each of the layout's views into
            // the public final member variables created above
            dateTextView = itemView.findViewById<TextView>(R.id.date)
            hoursTextView = itemView.findViewById<TextView>(R.id.hours)
            qualityTextView = itemView.findViewById<TextView>(R.id.quality)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.item_entry, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val entry = entries.get(position)
        // Set item views based on views and data model
        holder.dateTextView.text = "Date: " + entry.date
        holder.hoursTextView.text = "Hours of Sleep: " + entry.hours.toString()
        holder.qualityTextView.text = "Quality of Sleep: " + entry.quality
    }
}