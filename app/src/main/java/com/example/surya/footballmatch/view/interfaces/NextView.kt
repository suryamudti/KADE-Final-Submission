package com.example.surya.footballmatch.view.interfaces

import com.example.surya.footballmatch.model.Event

interface NextView {
    fun showLoading()
    fun hideLoading()
    fun showScheduleList(data : List<Event>)

}