package com.example.surya.footballmatch.view.interfaces

import com.example.surya.footballmatch.model.League
import com.example.surya.footballmatch.model.LeagueResponse
import com.example.surya.footballmatch.presenter.repository.MatchRepositoryCallback

interface HomeView : MatchRepositoryCallback<LeagueResponse> {
    fun showLeague(liga : League)
}