package com.example.surya.footballmatch.presenter

import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.MatchRespone
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.repository.ApiRepository
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

class PrevPresenterTest {

    @Mock
    private lateinit var view: PrevView
    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var presenter: PrevPresenter



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiRepository = ApiRepository()
        presenter = PrevPresenter(view, apiRepository)
    }

    @Test
    fun getLastMatchTest() {
        val id = "4328"
        presenter.getLastMatch(id)
        val connect : MyApi = apiRepository.getUrl().create(MyApi::class.java)
        argumentCaptor<PrevView>().apply {
            connect.getLastMatch(id).enqueue(object : Callback<MatchRespone> {
                override fun onFailure(call: Call<MatchRespone>, t: Throwable) {

                }

                override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                    view.hideLoading()
                    val get = response.body()!!.events
                    firstValue.ShowMatchList(get)
                    Mockito.verify(view.ShowMatchList(get))
                }
            })
        }
    }

    @Test
    fun getTeamSearchTest() {
        val query = "barcelona"
        val connect: MyApi = apiRepository.getUrl().create(MyApi::class.java)
        presenter.getTeamSearch(query)
        argumentCaptor<PrevView>().apply {
            connect.getSearchMatch(query).enqueue(object : Callback<MatchRespone> {
                override fun onFailure(call: Call<MatchRespone>, t: Throwable) {

                }

                override fun onResponse(call: Call<MatchRespone>, response: Response<MatchRespone>) {
                    view.hideLoading()
                    val get: List<Event>? = response.body()?.event
                    firstValue.ShowMatchList(get!!)
                    Mockito.verify(view.ShowMatchList(get!!))

                }

            })
        }

    }
}