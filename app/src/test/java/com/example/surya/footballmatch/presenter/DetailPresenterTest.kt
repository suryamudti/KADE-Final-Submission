package com.example.surya.footballmatch.presenter

import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.MatchRespone
import com.example.surya.footballmatch.model.Teams
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.view.interfaces.DetailView
import com.nhaarman.mockito_kotlin.argumentCaptor
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenterTest {

    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var apiRepository: ApiRepository
    private lateinit var presenter: DetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        apiRepository = ApiRepository()
        presenter = DetailPresenter(view,apiRepository)
    }

    @Test
    fun getDetailEvent(){
        val id_event = "600441"
        presenter.getDetailEvent(id_event)
        val connect : MyApi = apiRepository.getUrl().create(MyApi::class.java)
        argumentCaptor<DetailView>().apply {
            connect.getDetailEvent(id_event).enqueue(object : Callback<MatchRespone> {
                override fun onFailure(call: Call<MatchRespone>, t: Throwable) {
                }

                override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                    val getDetail : Event = response.body()!!.events.get(0)
                    firstValue.showDetailEvent(getDetail)
                    Mockito.verify(view.showDetailEvent(getDetail))


                }


            })
        }
    }

    @Test
    fun getDetailHome() {
        val id_teams = "134553"
        presenter.getDetailHome(id_teams)
        val connect : MyApi = apiRepository.getUrl().create(MyApi::class.java)
        argumentCaptor<DetailView>().apply {
            connect.getDetailTeam(id_teams).enqueue(object : Callback<MatchRespone>{
                override fun onFailure(call: Call<MatchRespone>, t: Throwable) {

                }

                override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                    val getTeams : Teams = response.body()!!.teams.get(0)
                    firstValue.showHomeTeam(getTeams)
                    Mockito.verify(view.showHomeTeam(getTeams))

                }

            })
        }

    }

    @Test
    fun getDetailAway() {
        val id_teams = "134440"
        presenter.getDetailAway(id_teams)
        val connect : MyApi = apiRepository.getUrl().create(MyApi::class.java)
        argumentCaptor<DetailView>().apply {
            connect.getDetailTeam(id_teams).enqueue(object : Callback<MatchRespone>{
                override fun onFailure(call: Call<MatchRespone>, t: Throwable) {

                }

                override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                    val getTeams : Teams = response.body()!!.teams.get(0)
                    firstValue.showAwayTeam(getTeams)
                    Mockito.verify(view.showAwayTeam(getTeams))


                }

            })
        }

    }

}