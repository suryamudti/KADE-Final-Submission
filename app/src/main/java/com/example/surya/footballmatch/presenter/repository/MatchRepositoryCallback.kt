package com.example.surya.footballmatch.presenter.repository

interface MatchRepositoryCallback<T> {

    fun onDataLoaded(data: T?)
    fun onDataError()
}