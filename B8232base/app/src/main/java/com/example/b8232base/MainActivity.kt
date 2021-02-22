package com.example.b8232base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ToggleButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.b8232base.R
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class MainActivity : AppCompatActivity() {

    var TAG: String = "MainActivity"

    var selectedCategory = FUNNY
    lateinit var thoughtsAdapter: ThoughtsAdapter
    var thoughts = arrayListOf<Thought>()
    val thoughtsCollectionRef = FirebaseFirestore.getInstance().collection(THOUGHTS_REF)
    lateinit var thoughtsListener: ListenerRegistration

    private var mainFunnyButton: ToggleButton? = null
    private var mainSeriousButton: ToggleButton? = null
    private var mainCrazyButton: ToggleButton? = null
    private var mainPopularButton: ToggleButton? = null
    private var thoughtListView: RecyclerView? = null


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

    }

    override fun onResume() {
        super.onResume()
        setListener()
    }

    fun setListener() {

        if(selectedCategory == POPULAR){
            thoughtsListener = thoughtsCollectionRef
                .orderBy(NUM_LIKES, Query.Direction.DESCENDING)
                .addSnapshotListener(this) {  snapshot,
                                              exception ->
                    if (exception != null) {
                        Log.e("Exception", "Could not retrieve documents: @exception")
                    }

                    if(snapshot != null){
                        parseData(snapshot)
                    }
                }
        }else{
            thoughtsListener = thoughtsCollectionRef
                .orderBy(TIMESTAMP, Query.Direction.DESCENDING)
                .whereEqualTo(CATEGORY, selectedCategory)
                .addSnapshotListener(this) {  snapshot,
                                              exception ->
                    if (exception != null) {
                        Log.e("Exception", "Could not retrieve documents: @exception")
                    }
                    if(snapshot != null){
                        parseData(snapshot)
                    }
                }
        }
    }

    fun parseData(snapshot: QuerySnapshot){
        thoughts.clear()
        for (document in snapshot.documents) {
            val data = document?.data
            val name = data?.get(USERNAME) as String
            val timestamp = data[TIMESTAMP] as Timestamp
            val thoughtText = data?.get(THOUGHT_TEXT) as String
            val numLikes = data?.get(NUM_LIKES) as Number
            val numComments = data?.get(NUM_COMMENTS) as Number
            val documentId = document.id

            val newThought = Thought(
                name, timestamp.toDate(), thoughtText,
                numLikes.toInt(), numComments.toInt(), documentId
            )

            thoughts.add(newThought)
        }
        thoughtsAdapter.notifyDataSetChanged()
    }

    fun mainFunnyClicked(view: View) {
        if (selectedCategory == FUNNY) {
            mainFunnyButton?.isChecked = true
            return
        }
        mainSeriousButton?.isChecked = false
        mainCrazyButton?.isChecked = false
        mainPopularButton?.isChecked = false
        selectedCategory = FUNNY

        thoughtsListener.remove()
        setListener()
    }

    fun mainSeriouseClicked(view: View) {
        if (selectedCategory == SERIOUS) {
            mainSeriousButton?.isChecked = true
            return
        }
        mainFunnyButton?.isChecked = false
        mainCrazyButton?.isChecked = false
        mainPopularButton?.isChecked = false
        selectedCategory = SERIOUS

        thoughtsListener.remove()
        setListener()
    }

    fun mainCrazyClicked(view: View) {
        if (selectedCategory == CRAZY) {
            mainCrazyButton?.isChecked = true
            return
        }
        mainFunnyButton?.isChecked = false
        mainSeriousButton?.isChecked = false
        mainPopularButton?.isChecked = false
        selectedCategory = CRAZY

        thoughtsListener.remove()
        setListener()
    }

    fun mainPopularClicked(view: View) {
        if (selectedCategory == POPULAR) {
            mainPopularButton?.isChecked = true
            return
        }
        mainFunnyButton?.isChecked = false
        mainSeriousButton?.isChecked = false
        mainCrazyButton?.isChecked = false
        selectedCategory = POPULAR

        thoughtsListener.remove()
        setListener()
    }


}