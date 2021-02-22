package com.example.b8052base

import java.sql.Date
import java.sql.Timestamp

data class Thought (
    val username: String,
    val timestamp: Date,
    val thoughtText: String,
    val numLikes: Int,
    val NumComments: Int,
    val documentId: String
)