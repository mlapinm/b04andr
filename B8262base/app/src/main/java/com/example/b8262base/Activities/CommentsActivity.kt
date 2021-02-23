package com.example.b8262base.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.example.b8262base.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class CommentsActivity : AppCompatActivity() {

    lateinit var thoughtDocumentId: String

    var enterCommentText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        enterCommentText = findViewById(R.id.enterCommentText)

        thoughtDocumentId = intent.getStringExtra(DOCUMENT_KEY)!!
        println(thoughtDocumentId)
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