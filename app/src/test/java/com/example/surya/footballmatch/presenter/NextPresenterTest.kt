package com.example.surya.footballmatch.presenter

import android.util.Log
import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.MatchRespone
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.view.interfaces.NextView
import com.example.surya.footballmatch.view.interfaces.PrevView
import com.nhaarman.mockito_kotlin.argumentCaptor
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextPresenterTest {
    @Mock
    private lateinit var view: NextView
    @Mock
    private lateinit var apiRepository: ApiRepository
    private lateinit var presenter: NextPresenter
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiRepository = ApiRepository()
        presenter = NextPresenter(view, apiRepository)
    }

    @Test
    fun getTeamList() {
        val id = "4328"
        presenter.getTeamList(id)
        val connect: MyApi = apiRepository.getUrl().create(MyApi::class.java)
        argumentCaptor<NextView>().apply {
            connect.getScheduleNext(id).enqueue(object : Callback<MatchRespone> {
                override fun onFailure(call: Call<MatchRespone>, t: Throwable) {

                }

                override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {

                    val get: List<Event>? = response.body()?.events
                    firstValue.showScheduleList(get!!)
                    Mockito.verify(view.showScheduleList(get!!))
                }

            })
        }

    }

    @Test
    fun getTeamSearch() {
        val club = "chelsea"
        presenter.getTeamSearch(club)
        val connect: MyApi = apiRepository.getUrl().create(MyApi::class.java)
        argumentCaptor<PrevView>().apply {
            connect.getSearchMatch(club).enqueue(object : Callback<MatchRespone> {
                override fun onFailure(call: Call<MatchRespone>, t: Throwable) {
                    Log.d("tag", "responsennya ${t.message}")

                }

                override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                    view.hideLoading()
                    val get: List<Event>? = response.body()!!.event
                    firstValue.ShowMatchList(get!!)
                    Mockito.verify(view.showScheduleList(get!!))


                }

            })
        }

    }
}