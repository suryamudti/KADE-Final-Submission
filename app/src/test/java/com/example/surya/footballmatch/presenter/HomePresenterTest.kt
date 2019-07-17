package com.example.surya.footballmatch.presenter

import com.example.surya.footballmatch.model.League
import com.example.surya.footballmatch.model.LeagueResponse
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.view.interfaces.HomeView
import com.nhaarman.mockito_kotlin.argumentCaptor
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenterTest {
    @Mock
    private lateinit var view: HomeView
    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var presenter: HomePresenter
    @Mock
    private lateinit var leagueResponse: League

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiRepository = ApiRepository()
        presenter = HomePresenter(view, apiRepository)
    }


    @Test
    fun getDetailLiga() {
        val id = "4328"
        presenter.getDetailLiga(id)
        val connect : MyApi = apiRepository.getUrl().create(MyApi::class.java)
        argumentCaptor<HomeView>().apply {
            connect.getDetailLeague(id).enqueue(object : Callback<LeagueResponse> {
                override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                    Mockito.verify(t.message)
                }

                override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>) {
                    val get: League = response.body()!!.leagues.get(0)
                    firstValue.showLiga(get)
                    Mockito.verify(view.showLiga(get))
                }

            })
        }

    }
}