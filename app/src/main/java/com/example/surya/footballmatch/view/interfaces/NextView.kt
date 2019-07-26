package com.example.surya.footballmatch.view.interfaces

import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.MatchResponse
import com.example.surya.footballmatch.presenter.repository.MatchRepositoryCallback

interface NextView :MatchRepositoryCallback<MatchResponse> {
    fun showLoading()
    fun hideLoading()
    fun showScheduleList(data : List<Event>)

}