package com.capstoneproject.csd190.model

data class Question(
    var question: String,
    var id: Int,
    var option1: String,
    var option2: String,
    var option3: String,
    var option4: String,
    var answer: Int
)
