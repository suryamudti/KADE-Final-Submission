package com.example.surya.footballmatch.presenter.repository

import com.example.surya.footballmatch.model.LeagueResponse
import com.example.surya.footballmatch.model.MatchResponse
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.api.MyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchRepository {

    fun getNextMatch(id: String, callback: MatchRepositoryCallback<MatchResponse?>) {
        MyRetrofit
            .createService(MyApi::class.java)
            .getScheduleNext(id)
            .enqueue(object : Callback<MatchResponse?> {
                override fun onFailure(call: Call<MatchResponse?>?, t: Throwable?) {
                    callback.onDataError()
                }

                override fun onResponse(call: Call<MatchResponse?>?, response: Response<MatchResponse?>?) {
                    response?.let {
                        if (it.isSuccessful) {
                            callback.onDataLoaded(it.body())
                        } else {
                            callback.onDataError()
                        }
                    }
                }
            })
    }

    fun getPreviousMatch(id: String, callback: MatchRepositoryCallback<MatchResponse?>) {
        MyRetrofit
            .createService(MyApi::class.java)
            .getLastMatch(id)
            .enqueue(object : Callback<MatchResponse?> {
                override fun onFailure(call: Call<MatchResponse?>?, t: Throwable?) {
                    callback.onDataError()
                }

                override fun onResponse(call: Call<MatchResponse?>?, response: Response<MatchResponse?>?) {
                    response?.let {
                        if (it.isSuccessful) {
                            callback.onDataLoaded(it.body())
                        } else {
                            callback.onDataError()
                        }
                    }
                }
            })
    }

    fun getSearchMatch(id: String, callback: MatchRepositoryCallback<MatchResponse?>) {
        MyRetrofit
            .createService(MyApi::class.java)
            .getSearchMatch(id)
            .enqueue(object : Callback<MatchResponse?> {
                override fun onFailure(call: Call<MatchResponse?>?, t: Throwable?) {
                    callback.onDataError()
                }

                override fun onResponse(call: Call<MatchResponse?>?, response: Response<MatchResponse?>?) {
                    response?.let {
                        if (it.isSuccessful) {
                            callback.onDataLoaded(it.body())
                        } else {
                            callback.onDataError()
                        }
                    }
                }
            })
    }

    fun getDetailEvent(id: String, callback: MatchRepositoryCallback<MatchResponse?>){
        MyRetrofit
            .createService(MyApi::class.java)
            .getDetailEvent(id)
            .enqueue(object : Callback<MatchResponse?> {
                override fun onFailure(call: Call<MatchResponse?>?, t: Throwable?) {
                    callback.onDataError()
                }

                override fun onResponse(call: Call<MatchResponse?>?, response: Response<MatchResponse?>?) {
                    response?.let {
                        if (it.isSuccessful) {
                            callback.onDataLoaded(it.body())
                        } else {
                            callback.onDataError()
                        }
                    }
                }
            })
    }

    fun getDetailTeam(id: String, callback: MatchRepositoryCallback<MatchResponse?>){
        MyRetrofit
            .createService(MyApi::class.java)
            .getDetailTeam(id)
            .enqueue(object : Callback<MatchResponse?> {
                override fun onFailure(call: Call<MatchResponse?>?, t: Throwable?) {
                    callback.onDataError()
                }

                override fun onResponse(call: Call<MatchResponse?>?, response: Response<MatchResponse?>?) {
                    response?.let {
                        if (it.isSuccessful) {
                            callback.onDataLoaded(it.body())
                        } else {
                            callback.onDataError()
                        }
                    }
                }
            })
    }

    fun getDetailLeague(id: String, callback: MatchRepositoryCallback<LeagueResponse>){
        MyRetrofit
            .createService(MyApi::class.java)
            .getDetailLeague(id)
            .enqueue(object : Callback<LeagueResponse?> {
                override fun onFailure(call: Call<LeagueResponse?>?, t: Throwable?) {
                    callback.onDataError()
                }

                override fun onResponse(call: Call<LeagueResponse?>?, response: Response<LeagueResponse?>?) {
                    response?.let {
                        if (it.isSuccessful) {
                            callback.onDataLoaded(it.body())
                        } else {
                            callback.onDataError()
                        }
                    }
                }
            })
    }

}