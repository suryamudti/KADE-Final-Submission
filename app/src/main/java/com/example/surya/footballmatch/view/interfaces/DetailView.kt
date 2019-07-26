package com.example.surya.footballmatch.view.interfaces

import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.MatchResponse
import com.example.surya.footballmatch.model.Teams
import com.example.surya.footballmatch.presenter.repository.MatchRepositoryCallback

interface DetailView : MatchRepositoryCallback<MatchResponse> {
    fun showLoading()
    fun hideLoading()
    fun showView()
    fun showDetailEvent(event: Event?)
    fun showHomeTeam(teams : Teams?)
    fun showAwayTeam(teams : Teams?)

}