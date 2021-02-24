package com.example.b8382base.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.example.b8382base.*
import com.google.firebase.firestore.FirebaseFirestore

class UpdateCommentActivity : AppCompatActivity() {
    var updateCommentText : EditText? = null

    lateinit var thoughtDocumentId : String
    lateinit var commentDocumentId : String
    lateinit var commentText : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_comment)

        updateCommentText = findViewById(R.id.updateCommentText)

        thoughtDocumentId = intent.getStringExtra(THOUGHT_DOC_ID_EXTRA).toString()
        commentDocumentId = intent.getStringExtra(COMMENT_DOC_ID_EXTRA).toString()
        commentText = intent.getStringExtra(COMMENT_TEXT_EXTRA).toString()

        updateCommentText?.setText(commentText)

    }

    fun updateCommentClicked(view: View) {
        Log.d("exception", "updateCommentClicked")
        println("updateCommentClicked")
        FirebaseFirestore.getInstance().collection(THOUGHTS_REF)
            .document(thoughtDocumentId)
            .collection(COMMENTS_REF)
            .document(commentDocumentId)
            .update(COMMENT_TEXT, updateCommentText?.text.toString())
            .addOnSuccessListener {
                finish()
            }
            .addOnFailureListener { exception ->
                Log.e("exception", "could not update comment ${exception.localizedMessage}")
            }
    }
}