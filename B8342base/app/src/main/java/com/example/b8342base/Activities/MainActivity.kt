package com.example.b8342base.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ToggleButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.b8342base.*
import com.example.b8342base.Adapters.ThoughtsAdapter
import com.example.b8342base.Interfaces.ThoughtOptionsClickListener
import com.example.b8342base.Model.Thought
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class MainActivity : AppCompatActivity(), ThoughtOptionsClickListener {
    var TAG: String = "MainActivity"

    var selectedCategory = FUNNY
    lateinit var thoughtsAdapter: ThoughtsAdapter
    var thoughts = arrayListOf<Thought>()
    val thoughtsCollectionRef = FirebaseFirestore.getInstance().collection(THOUGHTS_REF)
    lateinit var thoughtsListener: ListenerRegistration
    lateinit var auth : FirebaseAuth

    private var mainFunnyButton: ToggleButton? = null
    private var mainSeriousButton: ToggleButton? = null
    private var mainCrazyButton: ToggleButton? = null
    private var mainPopularButton: ToggleButton? = null
    private var fab: FloatingActionButton? = null
    private var thoughtListView: RecyclerView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        mainFunnyButton = findViewById(R.id.mainFunnyButton)
        mainSeriousButton = findViewById(R.id.mainSeriouseButton)
        mainCrazyButton = findViewById(R.id.mainCrazyButton)
        mainPopularButton = findViewById(R.id.mainPopularButton)
        fab = findViewById(R.id.fab)
        thoughtListView = findViewById(R.id.thoughtListView)

        auth = FirebaseAuth.getInstance()


        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val addThoughtIntent = Intent(this, AddThoughtActivity::class.java)
            startActivity(addThoughtIntent)
        }

        thoughtsAdapter = ThoughtsAdapter(thoughts, this){ thought ->
            val commentsActivity = Intent(this, CommentsActivity::class.java)
            commentsActivity.putExtra(DOCUMENT_KEY, thought.documentId)
            startActivity(commentsActivity)
        }
        val layoutManager1 = LinearLayoutManager(this)
        thoughtListView?.layoutManager = layoutManager1
        thoughtListView?.adapter = thoughtsAdapter


    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate( R.menu.menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val menuItem = menu?.getItem(0)
        if(auth.currentUser == null){
            // logged out state
            menuItem?.title = "Login"

        }else{
            // logged in
            menuItem?.title = "Logout"
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun thoughtOptionMenuClicked(thought: Thought) {
        // present the alert dialog

    }

    fun updateUI(){
        if(auth.currentUser == null){
            mainFunnyButton?.isEnabled = false
            mainSeriousButton?.isEnabled = false
            mainCrazyButton?.isEnabled = false
            mainPopularButton?.isEnabled = false
            fab?.isEnabled = false
            thoughts.clear()
            thoughtsAdapter.notifyDataSetChanged()
        }else{
            mainFunnyButton?.isEnabled = true
            mainSeriousButton?.isEnabled = true
            mainCrazyButton?.isEnabled = true
            mainPopularButton?.isEnabled = true
            fab?.isEnabled = true
            setListener()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_login){
            if(auth.currentUser == null){
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
            }else{
                auth.signOut()
                updateUI()
            }
            return true
        }
        return false
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
            val userId = data[USER_ID] as String

            val newThought = Thought(
                name, timestamp.toDate(), thoughtText,
                numLikes.toInt(), numComments.toInt(), documentId,
                userId
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