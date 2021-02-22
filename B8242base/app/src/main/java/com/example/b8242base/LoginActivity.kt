package com.example.b8242base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginLoginClicked(view: View) {
        // Perform login

    }

    fun loginCreateClicked(view: View) {
        // seque th the create user activity

        val createIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(createIntent)


    }


}