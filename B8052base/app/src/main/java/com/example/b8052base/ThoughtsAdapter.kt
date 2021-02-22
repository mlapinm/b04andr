package com.example.b8052base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ThoughtsAdapter(val thoughts: ArrayList<Thought>) : RecyclerView.Adapter<ThoughtsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val username = itemView?.findViewById<TextView>(R.id.listViewUsername)
        val timestamp = itemView?.findViewById<TextView>(R.id.listViewTimestamp)
        val thoughtText = itemView?.findViewById<TextView>(R.id.listViewThoughtText)
        val numLikes = itemView?.findViewById<TextView>(R.id.listViewNumLikesLabel)
        val likesImage = itemView?.findViewById<TextView>(R.id.listViewLikesImage)

        fun bindTought(thought: Thought){

            username?.text = thought.username
            thoughtText?.text = thought.thoughtText
            numLikes?.text = thought.numLikes.toString()

            val dateFormatter = SimpleDateFormat("MMM d, h:mm a", Locale.getDefault())
            val dateString = dateFormatter.format(thought.timestamp)
            timestamp?.text = dateString

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.thought_list_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.bindTought(thoughts[position])
    }

    override fun getItemCount(): Int {
        return thoughts.count()
    }
}