package com.example.surya.footballmatch.view.interfaces

import com.example.surya.footballmatch.model.Table

/**
 * Created by suryamudti on 27/07/2019.
 */
interface KlasemenView {
    fun showKlasemen(klasemen : List<Table>)
    fun showLoading()
    fun hideLoading()
}