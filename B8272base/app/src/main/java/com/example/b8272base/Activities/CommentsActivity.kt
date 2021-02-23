package com.example.b8272base.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.b8272base.*
import com.example.b8272base.Adapters.CommentsAdapter
import com.example.b8272base.Model.Comment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class CommentsActivity : AppCompatActivity() {

    var commentListView : RecyclerView? = null

    lateinit var thoughtDocumentId: String
    lateinit var commentAdapter : CommentsAdapter
    var comments = arrayListOf<Comment>()

    var enterCommentText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        commentListView = findViewById(R.id.commentListView)
        enterCommentText = findViewById(R.id.enterCommentText)

        thoughtDocumentId = intent.getStringExtra(DOCUMENT_KEY)!!

        commentAdapter = CommentsAdapter(comments)
        commentListView?.adapter = commentAdapter
        val layoutManager = LinearLayoutManager(this)
        commentListView?.layoutManager = layoutManager

    }

    fun addCommentClicked(view: View) {

        val commentText = enterCommentText?.text.toString()
        val thoughtRef = FirebaseFirestore.getInstance().collection(THOUGHTS_REF)
            .document(thoughtDocumentId)

        FirebaseFirestore.getInstance().runTransaction { transaction ->

            val thought = transaction.get(thoughtRef)
            val numComments = thought.getLong(NUM_COMMENTS)!! + 1
            transaction.update(thoughtRef, NUM_COMMENTS, numComments)

            val newCommentRef = FirebaseFirestore.getInstance()
                .collection(THOUGHTS_REF)
                .document(thoughtDocumentId)
                .collection(COMMENTS_REF)
                .document()

            val data = HashMap<String, Any>()
            data.put(COMMENT_TEXT, commentText)
            data.put(TIMESTAMP, FieldValue.serverTimestamp())
            data.put(USERNAME, FirebaseAuth.getInstance().currentUser?.displayName.toString())

            transaction.set(newCommentRef, data)

        }
            .addOnSuccessListener {
                enterCommentText?.setText("")
            }
            .addOnFailureListener { exception ->
                Log.e("Exception", "Could not add comment ${exception.localizedMessage}")
            }


    }
}