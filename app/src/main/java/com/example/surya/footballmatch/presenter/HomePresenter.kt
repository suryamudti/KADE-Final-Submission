package com.example.surya.footballmatch.presenter

import com.example.surya.footballmatch.model.LeagueResponse
import com.example.surya.footballmatch.presenter.repository.MatchRepository
import com.example.surya.footballmatch.presenter.repository.MatchRepositoryCallback
import com.example.surya.footballmatch.view.interfaces.HomeView

class HomePresenter(private var view: HomeView, private var apiRepository: MatchRepository) {

    fun getDetailLeague(id: String) {

        apiRepository.getDetailLeague(id, object : MatchRepositoryCallback<LeagueResponse> {


            override fun onDataLoaded(data: LeagueResponse?) {

                view.onDataLoaded(data)


            }

            override fun onDataError() {
                view.onDataError()
            }
        })
    }
}