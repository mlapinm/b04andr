package com.example.b8022base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AddThoughtActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_thought)
    }

    fun addFannyClicked(view: View) {}
    fun addSeriouseClicked(view: View) {}
    fun addCrazyClicked(view: View) {}
    fun addPostClicked(view: View) {}
}