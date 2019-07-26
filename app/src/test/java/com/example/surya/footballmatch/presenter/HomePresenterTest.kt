package com.example.surya.footballmatch.presenter

import com.example.surya.footballmatch.model.LeagueResponse
import com.example.surya.footballmatch.presenter.repository.MatchRepository
import com.example.surya.footballmatch.presenter.repository.MatchRepositoryCallback
import com.example.surya.footballmatch.view.interfaces.HomeView
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class HomePresenterTest {
    @Mock
    private lateinit var view: HomeView
    @Mock
    private lateinit var apiRepository: MatchRepository
    @Mock
    private lateinit var presenter: HomePresenter
    @Mock
    private lateinit var leagueResponse: LeagueResponse

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = HomePresenter(view, apiRepository)
    }

    @Test
    fun getDetailLeagueTest() {
        val id = "4328"
        presenter.getDetailLeague(id)

        argumentCaptor<MatchRepositoryCallback<LeagueResponse>>().apply {

            verify(apiRepository).getDetailLeague(eq(id), capture())
            firstValue.onDataLoaded(leagueResponse)
        }

        Mockito.verify(view).onDataLoaded(leagueResponse)

    }
}