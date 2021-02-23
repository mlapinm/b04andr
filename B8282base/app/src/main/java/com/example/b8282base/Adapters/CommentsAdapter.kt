package com.example.b8282base.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.b8282base.Model.Comment
import com.example.b8282base.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CommentsAdapter(var comments: ArrayList<Comment>)
    : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val username: TextView
        val timestamp: TextView
        val commentText: TextView


        init {
            username = itemView?.findViewById(R.id.commentListUsername)
            timestamp = itemView?.findViewById(R.id.commentListTimestamp)
            commentText = itemView?.findViewById(R.id.commentListCommentText)
        }

        fun bindComment(comment: Comment) {

            username?.text = comment.username
            commentText?.text = comment.commentText

            val dateFormatter = SimpleDateFormat("MMM d, h:mm a", Locale.getDefault())
            val dateString = dateFormatter.format(comment.timestamp)
            timestamp?.text = dateString

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.comment_list_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.bindComment(comments[position])
    }

    override fun getItemCount(): Int {
        return comments.count()
    }
}