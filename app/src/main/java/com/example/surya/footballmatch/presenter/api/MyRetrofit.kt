package com.example.surya.footballmatch.presenter.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofit {

    private fun iniRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return iniRetrofit().create(service)
    }

}