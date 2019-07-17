package com.example.surya.footballmatch.presenter.repository

interface RepositoryUnitTest<T> {
    fun onDataLoaded(data: T?)
    fun onDataError()
}