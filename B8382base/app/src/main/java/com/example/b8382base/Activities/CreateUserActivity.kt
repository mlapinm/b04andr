package com.example.b8382base.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.example.b8382base.DATE_CREATED
import com.example.b8382base.R
import com.example.b8382base.USERNAME
import com.example.b8382base.USERS_REF
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class CreateUserActivity : AppCompatActivity() {

    var createEmailText : EditText? = null
    var createPasswordText : EditText? = null
    var createUsernameText : EditText? = null

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        createEmailText = findViewById(R.id.createEmailText)
        createPasswordText = findViewById(R.id.createPasswordText)
        createUsernameText = findViewById(R.id.createUsernameText)

        auth = FirebaseAuth.getInstance()

    }

    fun createUserClicked(view: View) {

//        Log.e("Exception", "finish")


        val email = createEmailText?.text.toString()
        val password = createPasswordText?.text.toString()
        val username = createUsernameText?.text.toString()

        Log.e("Exception", "finish " + email + password + username )


        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val changeRequest = UserProfileChangeRequest.Builder()
                    .setDisplayName(username)
                    .build()
                result.user!!.updateProfile(changeRequest)
                    .addOnFailureListener { exception ->
                        Log.e("Exception", "Couldn't create user: ${exception.localizedMessage}")
                    }

                val data = HashMap<String, Any>()
                data.put(USERNAME, username)
                data.put(DATE_CREATED, FieldValue.serverTimestamp())

                Log.e("Exception", "finish")


                FirebaseFirestore.getInstance().collection(USERS_REF)
                    .document(result.user!!.uid)
                    .set(data)
                    .addOnSuccessListener {
                        finish()
                        Log.e("Exception", "finish")
                    }
                    .addOnFailureListener { exception ->
                        Log.e("Exception", "Couldn't add user document: ${exception.localizedMessage}")
                    }
            }
            .addOnFailureListener { exception ->
                Log.e("Exception", "Couldn't create user: ${exception.localizedMessage}")
            }
    }
    
    fun createCancelClicked(view: View) {
        finish()
    }
}