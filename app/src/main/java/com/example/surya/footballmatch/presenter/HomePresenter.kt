package com.example.surya.footballmatch.presenter

import com.example.surya.footballmatch.model.League
import com.example.surya.footballmatch.model.LeagueResponse
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.view.interfaces.HomeView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(private var view: HomeView, private var apiRepository: ApiRepository) {

    fun getDetailLiga(id: String) {
        val connect: MyApi = apiRepository.getUrl().create(MyApi::class.java)
        connect.getDetailLeague(id).enqueue(object : Callback<LeagueResponse> {
            override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>) {
                val get: League? = response.body()?.leagues?.get(0)
                view?.showLiga(get!!)
            }

        })
    }
}