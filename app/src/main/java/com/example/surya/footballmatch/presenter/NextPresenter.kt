package com.example.surya.footballmatch.presenter


import android.content.Context
import android.widget.Toast
import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.MatchRespone
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.view.interfaces.NextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextPresenter( private val view : NextView, private val apiRepository: ApiRepository) {

        private var context: Context? = null

        fun NextPresenter(context: Context){
            this.context = context
        }

        fun getTeamList(liga : String){
            view.showLoading()
            val connect : MyApi = apiRepository.getUrl().create(
                MyApi::class.java)
            connect.getScheduleNext(liga).enqueue(object : Callback<MatchRespone>{
                override fun onFailure(call: Call<MatchRespone>, t: Throwable) {

                }

                override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                   view.hideLoading()
                    val get : List<Event>? = response.body()!!.events
                    if(get == null){
                        Toast.makeText(context, "This match already finish", Toast.LENGTH_SHORT).show()
                    }else{
                        view.showScheduleList(get!!)

                    }


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

                if(get == null){
                    Toast.makeText(context, "This match already finish", Toast.LENGTH_SHORT).show()
                }else{
                    view.showScheduleList(get!!)
                }
            }
        })
    }
}