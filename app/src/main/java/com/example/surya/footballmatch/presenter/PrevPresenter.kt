package com.example.surya.footballmatch.presenter

import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.MatchRespone
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.view.interfaces.PrevView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrevPresenter( private val view : PrevView, private val apiRepository: ApiRepository) {

    fun getLastMatch(liga : String){
        view.showLoading()
        val connect : MyApi = apiRepository.getUrl().create(MyApi::class.java)
        connect.getLastMatch(liga).enqueue(object :  Callback<MatchRespone> {
            override fun onFailure(call: Call<MatchRespone>, t: Throwable) {

            }

            override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                view.hideLoading()
                val get = response.body()!!.events
                view.ShowMatchList(get)
            }
        })
    }

    fun getTeamSearch(query : String){
        view.showLoading()
        val connect : MyApi = apiRepository.getUrl().create(MyApi::class.java)
        connect.getSearchMatch(query).enqueue(object : Callback<MatchRespone>{
            override fun onFailure(call: Call<MatchRespone>, t: Throwable) {

            }

            override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                view.hideLoading()
                val get : List<Event>? = response.body()!!.event
                view.ShowMatchList(get!!)
            }
        })
    }
}