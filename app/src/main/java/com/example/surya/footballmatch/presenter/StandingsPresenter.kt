package com.example.surya.footballmatch.presenter

import com.example.surya.footballmatch.model.KlasemenResponse
import com.example.surya.footballmatch.model.Table
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.view.interfaces.StandingsView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by suryamudti on 27/07/2019.
 */
class KlasemenPresenter(private val view: StandingsView, private val apiRepository: ApiRepository) {

    fun getKlasemenList(id_liga : String){
        view.showLoading()

        val connect : MyApi = apiRepository.getUrl().create(MyApi::class.java)
        connect.getKlasemen(id_liga).enqueue(object : Callback<KlasemenResponse> {
            override fun onFailure(call: Call<KlasemenResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<KlasemenResponse>, response: Response<KlasemenResponse>) {
                view.hideLoading()
                val get : List<Table> = response.body()!!.table
                view.showKlasemen(get)
            }

        })
    }
}