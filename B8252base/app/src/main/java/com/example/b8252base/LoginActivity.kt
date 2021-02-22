package com.example.b8252base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    var loginEmailText : EditText? = null
    var loginPasswordText : EditText? = null

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginEmailText = findViewById(R.id.loginEmailText)
        loginPasswordText = findViewById(R.id.loginPasswordText)

        auth = FirebaseAuth.getInstance()
    }

    fun loginLoginClicked(view: View) {
        // Perform login
        val email = loginEmailText?.text.toString()
        val password = loginPasswordText?.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                finish()
            }
            .addOnFailureListener { exception ->
                Log.e("exception", "Could not sign in user -  ${exception.localizedMessage}")
            }
    }

    fun loginCreateClicked(view: View) {
        // seque th the create user activity
        val createIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(createIntent)


    }


}