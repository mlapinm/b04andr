package com.example.b8052base

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore


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
        data.put("category", selectedCategory)
        data.put("numComments", 0)
        data.put("numLikes", 0)
        data.put("thoughtTxt", addThoughtText?.text.toString())
        data.put("timestamp", FieldValue.serverTimestamp())
        data.put("username", addUsername?.text.toString())

//        val database = FirebaseDatabase.getInstance()
//        val myRef = database.getReference("message")
//
//        myRef.setValue("Hello, World!")

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