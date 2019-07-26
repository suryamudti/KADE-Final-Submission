package com.example.surya.footballmatch.presenter

import android.util.Log
import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.MatchResponse
import com.example.surya.footballmatch.presenter.api.MyApi
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.presenter.repository.MatchRepository
import com.example.surya.footballmatch.presenter.repository.MatchRepositoryCallback
import com.example.surya.footballmatch.view.interfaces.NextView
import com.example.surya.footballmatch.view.interfaces.PrevView
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

class NextPresenterTest {
    @Mock
    private lateinit var view: NextView

    @Mock
    private lateinit var apiRepository: MatchRepository

    @Mock
    private lateinit var matchResponse: MatchResponse

    @Mock
    private lateinit var presenter: NextPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextPresenter(view, apiRepository)
    }

    @Test
    fun getTeamListTest() {

        val id = "4328"

        presenter.getTeamList(id)

        argumentCaptor<MatchRepositoryCallback<MatchResponse?>>().apply {

            verify(apiRepository).getNextMatch(eq(id), capture())
            firstValue.onDataLoaded(matchResponse)
        }

        Mockito.verify(view).showLoading()
        Mockito.verify(view).onDataLoaded(matchResponse)


    }

    @Test
    fun getTeamSearchTest() {
        val query = "chelsea"
        presenter.getTeamSearch(query)

        argumentCaptor<MatchRepositoryCallback<MatchResponse?>>().apply {
            Mockito.verify(apiRepository).getSearchMatch(eq(query), capture())
            firstValue.onDataLoaded(matchResponse)
        }

        Mockito.verify(view).showLoading()
        Mockito.verify(view).onDataLoaded(matchResponse)
        Mockito.verify(view).hideLoading()

    }
}