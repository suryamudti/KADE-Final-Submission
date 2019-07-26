package com.example.surya.footballmatch.presenter


import android.content.Context
import android.widget.Toast
import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.MatchResponse
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.presenter.repository.MatchRepository
import com.example.surya.footballmatch.presenter.repository.MatchRepositoryCallback
import com.example.surya.footballmatch.view.interfaces.NextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextPresenter( private val view : NextView, private val apiRepository: MatchRepository) {

//        private var context: Context? = null
//
//        fun NextPresenter(context: Context){
//            this.context = context
//        }

        fun getTeamList(id : String){
            view.showLoading()

            apiRepository.getNextMatch(id, object : MatchRepositoryCallback<MatchResponse?> {
                override fun onDataLoaded(data: MatchResponse?) {
                    data?.events?.let { view.showScheduleList(it) }
                    view.onDataLoaded(data)
                }

                override fun onDataError() {
                    view.onDataError()
                }
            })
            view.hideLoading()




        }
    fun getTeamSearch(query : String){
        view.showLoading()

        apiRepository.getSearchMatch(query, object : MatchRepositoryCallback<MatchResponse?> {
            override fun onDataLoaded(data: MatchResponse?) {
                data?.events?.let { view.showScheduleList(it) }
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.hideLoading()

    }
}