package com.example.surya.footballmatch.presenter

import com.example.surya.footballmatch.model.League
import com.example.surya.footballmatch.model.LeagueResponse
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.view.interfaces.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private var view: MainView, private var apiRepository: ApiRepository) {
    fun getDetailLiga(id : String){

        val connect : MyApi = apiRepository.getUrl().create(MyApi::class.java)
        connect.getDetailLeague(id).enqueue(object : Callback<LeagueResponse>{
            override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>) {
               val getDetail : League = response.body()?.leagues!!.get(0)
                view.showLeague(getDetail)

           }
        })
    }
}