package com.example.surya.footballmatch.view.interfaces

import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.MatchResponse
import com.example.surya.footballmatch.presenter.repository.MatchRepositoryCallback

interface PrevView : MatchRepositoryCallback<MatchResponse> {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Event>)
}