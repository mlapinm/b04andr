package com.example.b8392base.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.b8392base.Interfaces.ThoughtOptionsClickListener
import com.example.b8392base.Model.Thought
import com.example.b8392base.NUM_LIKES
import com.example.b8392base.R
import com.example.b8392base.THOUGHTS_REF
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ThoughtsAdapter(
    var thoughts: ArrayList<Thought>,
    val thoughtOptionsListener: ThoughtOptionsClickListener,
    val itemClick: (Thought) -> Unit
) : RecyclerView.Adapter<ThoughtsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View, itemClick: (Thought) -> Unit) : RecyclerView.ViewHolder(itemView) {

        val username: TextView
        val timestamp: TextView
        val thoughtText: TextView
        val numLikes: TextView
        val likesImage: ImageView
        val numCommentsLabel: TextView
        val optionsImage : ImageView

        init {
            username = itemView?.findViewById(R.id.listViewUsername)
            timestamp = itemView?.findViewById(R.id.listViewTimestamp)
            thoughtText = itemView?.findViewById(R.id.listViewThoughtText)
            numLikes = itemView?.findViewById(R.id.listViewNumLikesLabel)
            likesImage = itemView?.findViewById(R.id.listViewLikesImage)
            numCommentsLabel = itemView?.findViewById(R.id.numCommentsLabel)
            optionsImage = itemView?.findViewById(R.id.thougtOptionImage)
        }

        fun bindThought(thought: Thought) {

            optionsImage.visibility = View.INVISIBLE

            username?.text = thought.username
            thoughtText?.text = thought.thoughtTxt
            numLikes?.text = thought.numLikes.toString()
            numCommentsLabel?.text = thought.NumComments.toString()

            val dateFormatter = SimpleDateFormat("MMM d, h:mm a", Locale.getDefault())
            val dateString = dateFormatter.format(thought.timestamp)
            timestamp?.text = dateString

            itemView.setOnClickListener {
                itemClick(thought)
            }

            likesImage?.setOnClickListener {
                FirebaseFirestore.getInstance().collection(THOUGHTS_REF)
                    .document(thought.documentId)
                    .update(NUM_LIKES, thought.numLikes + 1)
            }

            if(FirebaseAuth.getInstance().currentUser?.uid == thought.userId){
                optionsImage.visibility = View.VISIBLE
                optionsImage.setOnClickListener {
                    thoughtOptionsListener.thoughtOptionMenuClicked(thought)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.thought_list_view, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.bindThought(thoughts[position])
    }

    override fun getItemCount(): Int {
        return thoughts.size
    }
}