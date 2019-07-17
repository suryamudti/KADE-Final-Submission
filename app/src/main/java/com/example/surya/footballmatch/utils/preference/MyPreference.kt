package com.example.surya.footballmatch.utils.preference

import android.content.Context

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MyPreference(context: Context) {
    val PREFERENCE_NAME = "MyPreference"
    val LEAGUE_ID = "LEAGUE_ID"

    val preference = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)

    fun getLeagueId() : String {
        return preference.getString(LEAGUE_ID,"")
    }

    fun setLeagueId(leagueId : String){
        val editor = preference.edit()
        editor.putString(LEAGUE_ID,leagueId)
        editor.apply()
    }
}