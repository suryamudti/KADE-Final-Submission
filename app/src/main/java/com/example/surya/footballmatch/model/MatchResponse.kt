package com.example.surya.footballmatch.model

data class MatchResponse(
    val events: List<Event>,
    val event : List<Event>,
    val teams : List<Teams>
)