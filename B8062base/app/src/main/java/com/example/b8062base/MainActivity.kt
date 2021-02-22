package com.example.b8062base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import android.widget.ToggleButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.util.*

class MainActivity : AppCompatActivity() {

    var TAG : String = "MainActivity"

    var selectedCategory = FUNNY
    lateinit var thoughtsAdapter: ThoughtsAdapter
    var thoughts = arrayListOf<Thought>()
    val thoughtsCollectionRef = FirebaseFirestore.getInstance().collection(THOUGHTS_REF)

    private var mainFunnyButton : ToggleButton? = null
    private var mainSeriousButton : ToggleButton? = null
    private var mainCrazyButton : ToggleButton? = null
    private var mainPopularButton : ToggleButton? = null
    private var thoughtListView : RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        mainFunnyButton = findViewById(R.id.mainFunnyButton)
        mainSeriousButton = findViewById(R.id.mainSeriouseButton)
        mainCrazyButton = findViewById(R.id.mainCrazyButton)
        mainPopularButton = findViewById(R.id.mainPopularButton)
        thoughtListView = findViewById(R.id.thoughtListView)


        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val addThoughtIntent = Intent(this, AddThoughtActivity::class.java)
            startActivity(addThoughtIntent)
        }


        thoughtsAdapter = ThoughtsAdapter(thoughts)
        val layoutManager1 = LinearLayoutManager(this)
        thoughtListView?.layoutManager = layoutManager1
        thoughtListView?.adapter = thoughtsAdapter


//            showTest()
        showCollection()



    }

    fun newThought1() : Thought{
        val name = "name1"
//        val timestamp = FieldValue.serverTimestamp()
        val timestamp = Date()
        val thoughtText = "thoughtText"
        val numLikes = 55
        val numComments = 33
        val documentId = "44"


        return  Thought(name, timestamp, thoughtText,
            numLikes, numComments, documentId)

    }

    fun showTest(){

        var thought1 = newThought1()


        Toast.makeText(this,
            thought1.toString(),
            Toast.LENGTH_LONG).show()

        var array1 = arrayListOf<Thought>()
        array1.add(newThought1())
        Log.e(TAG, array1.toString())


        thoughts.add(thought1)
        thoughts.add(thought1)
        thoughtsAdapter.notifyDataSetChanged()

    }

    fun showCollection(){
        thoughtsCollectionRef
            .get()
            .addOnSuccessListener { snapshot ->
                for(document in snapshot.documents){
                    val data = document?.data
                    val name = data?.get(USERNAME) as String
                    val timestamp = data[TIMESTAMP] as Timestamp
                    val thoughtText = data?.get(THOUGHT_TEXT) as String
//                                                 thoughtText
                    val numLikes = data?.get(NUM_LIKES) as Number
                    val numComments = data?.get(NUM_COMMENTS) as Number
                    val documentId = document.id

                    val newThought = Thought(name, timestamp.toDate(), thoughtText,
                        numLikes.toInt(), numComments.toInt(), documentId)

                    thoughts.add(newThought)
                }
                thoughtsAdapter.notifyDataSetChanged()

            }.addOnFailureListener { exception ->

                Log.e("Exception33", "Could not add post: @exception")
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