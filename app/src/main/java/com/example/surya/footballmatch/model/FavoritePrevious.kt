package com.example.surya.footballmatch.model

data class FavoritePrevious (
    val id : Long?,
    val idEvent : String?,
    val homeTeam : String?,
    val awayTeam : String?,
    val date : String?,
    val scoreHome : String?,
    val scoreAway : String?,
    val idHomeTeam : String?,
    val idAwayTeam : String?
) {
    companion object{
        const val TABLE_FAVORITE_PREVIOUS : String = "TABLE_FAVORITE_PREVIOUS"
        const val ID : String = "ID"
        const val ID_EVENT : String = "ID_EVENT"
        const val HOME_TEAM : String = "HOME_TEAM"
        const val AWAY_TEAM : String = "AWAY_TEAM"
        const val DATE : String = "DATE"
        const val SCORE_HOME : String = "SCORE_HOME"
        const val SCORE_AWAY : String = "SCORE_AWAY"
        const val ID_HOME_TEAM : String = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM : String = "ID_AWAY_TEAM"
    }

}