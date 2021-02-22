package com.example.b8042base

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ToggleButton

class MainActivity : AppCompatActivity() {

    var selectedCategory = FUNNY

    private var mainFunnyButton : ToggleButton? = null
    private var mainSeriousButton : ToggleButton? = null
    private var mainCrazyButton : ToggleButton? = null
    private var mainPopularButton : ToggleButton? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        mainFunnyButton = findViewById(R.id.mainFunnyButton)
        mainSeriousButton = findViewById(R.id.mainSeriouseButton)
        mainCrazyButton = findViewById(R.id.mainCrazyButton)
        mainPopularButton = findViewById(R.id.mainPopularButton)


        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val addThoughtIntent = Intent(this, AddThoughtActivity::class.java)
            startActivity(addThoughtIntent)
        }

    }

    fun mainFunnyClicked(view: View) {
        if(selectedCategory == FUNNY){
            mainFunnyButton?.isChecked = true
            return
        }
        mainSeriousButton?.isChecked = false
        mainCrazyButton?.isChecked = false
        mainPopularButton?.isChecked = false
        selectedCategory = FUNNY
    }
    fun mainSeriouseClicked(view: View) {
        if(selectedCategory == SERIOUS){
            mainSeriousButton?.isChecked = true
            return
        }
        mainFunnyButton?.isChecked = false
        mainCrazyButton?.isChecked = false
        mainPopularButton?.isChecked = false
        selectedCategory = SERIOUS
    }
    fun mainCrazyClicked(view: View) {
        if(selectedCategory == CRAZY){
            mainCrazyButton?.isChecked = true
            return
        }
        mainFunnyButton?.isChecked = false
        mainSeriousButton?.isChecked = false
        mainPopularButton?.isChecked = false
        selectedCategory = CRAZY
    }

    fun mainPopularClicked(view: View) {
        if(selectedCategory == POPULAR){
            mainCrazyButton?.isChecked = true
            return
        }
        mainFunnyButton?.isChecked = false
        mainSeriousButton?.isChecked = false
        mainCrazyButton?.isChecked = false
        selectedCategory = POPULAR
    }


}