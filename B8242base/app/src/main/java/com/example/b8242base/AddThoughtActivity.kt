package com.example.b8242base

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap


class AddThoughtActivity : AppCompatActivity() {
    private var addFunnyButton : ToggleButton? = null
    private var addSeriousButton : ToggleButton? = null
    private var addCrazyButton : ToggleButton? = null
    private var addThoughtText : EditText? = null
    private var addUsername : EditText? = null


    private var selectedCategory = FUNNY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_thought)
        addFunnyButton = findViewById(R.id.addFunnyButton)
        addSeriousButton = findViewById(R.id.addSeriouseButton)
        addCrazyButton = findViewById(R.id.addCrazyButton)
        addThoughtText = findViewById(R.id.addThoughtText)
        addUsername = findViewById(R.id.addUsernameText)
    }

    fun addPostClicked(view: View) {

        val data = HashMap<String, Any>()
        data.put(CATEGORY, selectedCategory)
        data.put(NUM_COMMENTS, 0)
        data.put(NUM_LIKES, 0)
        data.put(THOUGHT_TEXT, addThoughtText?.text.toString())
        data.put(TIMESTAMP, FieldValue.serverTimestamp())
//        data.put(TIMESTAMP, Date())
        data.put(USERNAME, addUsername?.text.toString())

        FirebaseFirestore.getInstance().collection(THOUGHTS_REF)
                .add(data)
                .addOnSuccessListener {
                    finish()
                }.addOnFailureListener { exception ->
                    Log.e("Exception", "Could not add post: $exception")
                }
    }

    fun addFannyClicked(view: View) {
        if(selectedCategory == FUNNY){
            addFunnyButton?.isChecked = true
            return
        }
        addSeriousButton?.isChecked = false
        addCrazyButton?.isChecked = false
        selectedCategory = FUNNY
    }
    fun addSeriouseClicked(view: View) {
        if(selectedCategory == SERIOUS){
            addSeriousButton?.isChecked = true
            return
        }
        addFunnyButton?.isChecked = false
        addCrazyButton?.isChecked = false
        selectedCategory = SERIOUS
    }
    fun addCrazyClicked(view: View) {
        if(selectedCategory == CRAZY){
            addCrazyButton?.isChecked = true
            return
        }
        addFunnyButton?.isChecked = false
        addSeriousButton?.isChecked = false
        selectedCategory = CRAZY
    }
}