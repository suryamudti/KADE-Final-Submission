package com.example.surya.footballmatch.presenter

import com.example.surya.footballmatch.view.interfaces.DetailView
import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.MatchRespone
import com.example.surya.footballmatch.model.Teams
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(private var view : DetailView, private var apiRepository: ApiRepository) {

    fun getDetailEvent(id_event : String){
        view.showLoading()

        val connect : MyApi = apiRepository.getUrl().create(MyApi::class.java)
        connect.getDetailEvent(id_event).enqueue(object : Callback<MatchRespone>{
            override fun onFailure(call: Call<MatchRespone>, t: Throwable) {

            }

            override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                val getDetail : Event = response.body()!!.events.get(0)
                view.showDetailEvent(getDetail)
                view.hideLoading()
                view.showView()

            }


        })
    }

    fun getDetailHome(id_teams : String){
        view.showLoading()

        val connect : MyApi = apiRepository.getUrl().create(MyApi::class.java)
        connect.getDetailTeam(id_teams).enqueue(object : Callback<MatchRespone>{
            override fun onFailure(call: Call<MatchRespone>, t: Throwable) {

            }

            override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                val getTeams : Teams = response.body()!!.teams.get(0)
                view.showHomeTeam(getTeams)
                view.hideLoading()
                view.showView()
            }
        })
    }
    fun getDetailAway(id_teams : String){
        view.showLoading()

        val connect : MyApi = apiRepository.getUrl().create(MyApi::class.java)
        connect.getDetailTeam(id_teams).enqueue(object : Callback<MatchRespone>{
            override fun onFailure(call: Call<MatchRespone>, t: Throwable) {

            }

            override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                val getTeams : Teams = response.body()!!.teams.get(0)
                view.showAwayTeam(getTeams)
                view.hideLoading()
                view.showView()


            }
        })
    }
}