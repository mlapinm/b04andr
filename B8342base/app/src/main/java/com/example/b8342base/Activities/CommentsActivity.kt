package com.example.b8342base.Activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.b8342base.*
import com.example.b8342base.Adapters.CommentsAdapter
import com.example.b8342base.Interfaces.CommentOptionsClickListener
import com.example.b8342base.Model.Comment
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlin.collections.HashMap

class CommentsActivity : AppCompatActivity(), CommentOptionsClickListener {

    var commentListView: RecyclerView? = null

    lateinit var thoughtDocumentId: String
    lateinit var commentsAdapter: CommentsAdapter
    var comments = arrayListOf<Comment>()

    var enterCommentText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        commentListView = findViewById(R.id.commentListView)
        enterCommentText = findViewById(R.id.enterCommentText)

        thoughtDocumentId = intent.getStringExtra(DOCUMENT_KEY)!!

        commentsAdapter = CommentsAdapter(comments, this)
        commentListView?.adapter = commentsAdapter
        val layoutManager = LinearLayoutManager(this)
        commentListView?.layoutManager = layoutManager

        FirebaseFirestore.getInstance().collection(THOUGHTS_REF).document(thoughtDocumentId)
            .collection(COMMENTS_REF)
            .orderBy(TIMESTAMP, Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, exception ->

                if (exception != null) {
                    Log.e("Exception", "Could not retrieve comment ${exception.localizedMessage}")
                }

                if (snapshot != null) {
                    comments.clear()
                    for (document in snapshot.documents) {
                        val data = document.data
                        val name = data?.get(USERNAME) as String
                        val timestamp = data[TIMESTAMP] as Timestamp
                        val commentText = data[COMMENT_TEXT] as String
                        val documentId = document.id
                        val userId = data[USER_ID] as String

                        val newComment = Comment(
                            name,
                            timestamp.toDate(),
                            commentText,
                            documentId,
                            userId
                        )
                        comments.add(newComment)
                    }
                    commentsAdapter.notifyDataSetChanged()
                }
            }
    }

    override fun optionsMenuClicked(comment: Comment) {
//        this is where we present alert dialog.
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
            data.put(USER_ID, FirebaseAuth.getInstance().currentUser?.uid.toString())

            transaction.set(newCommentRef, data)

        }
            .addOnSuccessListener {
                enterCommentText?.setText("")
                hideKeyboard()
            }
            .addOnFailureListener { exception ->
                Log.e("Exception", "Could not add comment ${exception.localizedMessage}")
            }


    }

    private fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputManager.isAcceptingText) {
            inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

}