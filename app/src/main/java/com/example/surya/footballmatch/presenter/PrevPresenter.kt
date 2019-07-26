package com.example.surya.footballmatch.presenter

import android.util.Log
import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.MatchResponse
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.repository.MatchRepository
import com.example.surya.footballmatch.presenter.repository.MatchRepositoryCallback
import com.example.surya.footballmatch.view.interfaces.PrevView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrevPresenter( private val view : PrevView, private val apiRepository: MatchRepository) {

    fun getLastMatch(id : String){
        view.showLoading()
        apiRepository.getPreviousMatch(id, object : MatchRepositoryCallback<MatchResponse?> {
            override fun onDataLoaded(data: MatchResponse?) {

                data?.events?.let { view.showMatchList(it) }
                view.onDataLoaded(data)
                view.hideLoading()
            }

            override fun onDataError() {
                view.hideLoading()
                view.onDataError()
            }
        })

    }

    fun getTeamSearch(query : String){
        view.showLoading()

        apiRepository.getSearchMatch(query, object : MatchRepositoryCallback<MatchResponse?> {
            override fun onDataLoaded(data: MatchResponse?) {
                data?.event?.let { view.showMatchList(it) }
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.hideLoading()
    }
}