package com.example.surya.footballmatch.presenter

import android.util.Log
import com.example.surya.footballmatch.view.interfaces.DetailView
import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.MatchResponse
import com.example.surya.footballmatch.model.Teams
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.presenter.repository.MatchRepository
import com.example.surya.footballmatch.presenter.repository.MatchRepositoryCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(private var view : DetailView, private var apiRepository: MatchRepository) {

    fun getDetailEvent(id : String){
        view.showLoading()

        apiRepository.getDetailEvent(id, object : MatchRepositoryCallback<MatchResponse?> {
            override fun onDataLoaded(data: MatchResponse?) {

//                val detail = data?.events?.get(0)
                view.onDataLoaded(data)

//                view.showDetailEvent(detail)
                view.showView()
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.hideLoading()
    }

    fun getDetailHome(id : String){
        view.showLoading()

        apiRepository.getDetailTeam(id, object : MatchRepositoryCallback<MatchResponse?> {
            override fun onDataLoaded(data: MatchResponse?) {

                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.hideLoading()
    }
    fun getDetailAway(id : String){
        view.showLoading()

        apiRepository.getDetailTeam(id, object : MatchRepositoryCallback<MatchResponse?> {
            override fun onDataLoaded(data: MatchResponse?) {
//                val detail = data?.teams?.get(0)
//                view.showAwayTeam(detail)
                view.showView()
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.hideLoading()
    }
}