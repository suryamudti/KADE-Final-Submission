package com.example.surya.footballmatch.presenter

import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.MatchResponse
import com.example.surya.footballmatch.model.Teams
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.presenter.repository.MatchRepository
import com.example.surya.footballmatch.presenter.repository.MatchRepositoryCallback
import com.example.surya.footballmatch.view.interfaces.DetailView
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenterTest {

    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var matchResponse: MatchResponse

    @Mock
    private lateinit var apiRepository: MatchRepository

    @Mock
    private lateinit var presenter: DetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view,apiRepository)
    }

    @Test
    fun getDetailEventTest(){
        val eventId = "600441"

        presenter.getDetailEvent(eventId)

        argumentCaptor<MatchRepositoryCallback<MatchResponse?>>().apply {

            verify(apiRepository).getDetailEvent(eq(eventId), capture())
            firstValue.onDataLoaded(matchResponse)
        }

        Mockito.verify(view).showLoading()
        Mockito.verify(view).onDataLoaded(matchResponse)
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun getDetailHomeTest() {

        val teamId = "134553"

        presenter.getDetailHome(teamId)

        argumentCaptor<MatchRepositoryCallback<MatchResponse?>>().apply {

            verify(apiRepository).getDetailTeam(eq(teamId), capture())
            firstValue.onDataLoaded(matchResponse)
        }

        Mockito.verify(view).showLoading()
        Mockito.verify(view).onDataLoaded(matchResponse)
        Mockito.verify(view).hideLoading()
    }

    @Test
    fun getDetailAwayTest() {
        val teamId = "134440"

        presenter.getDetailAway(teamId)

        argumentCaptor<MatchRepositoryCallback<MatchResponse?>>().apply {

            verify(apiRepository).getDetailTeam(eq(teamId), capture())
            firstValue.onDataLoaded(matchResponse)
        }

        Mockito.verify(view).showLoading()
        Mockito.verify(view).onDataLoaded(matchResponse)
        Mockito.verify(view).hideLoading()
    }

}