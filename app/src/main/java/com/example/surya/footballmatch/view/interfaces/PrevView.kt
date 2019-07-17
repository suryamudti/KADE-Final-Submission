package com.example.surya.footballmatch.view.interfaces

import com.example.surya.footballmatch.model.Event

interface PrevView {
    fun showLoading()
    fun hideLoading()
    fun ShowMatchList(data : List<Event>)
}