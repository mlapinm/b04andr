package com.example.b8282base.Model

import com.google.firebase.Timestamp
import java.util.*

data class Thought(
    val username: String,
    val timestamp: Date,
    val thoughtTxt: String,
    val numLikes: Int,
    val NumComments: Int,
    val documentId: String
)