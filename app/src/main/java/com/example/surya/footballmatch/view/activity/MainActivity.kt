package com.example.surya.footballmatch.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.surya.footballmatch.utils.preference.MyPreference
import com.example.surya.footballmatch.R
import com.example.surya.footballmatch.view.fragment.FavoriteFragment
import com.example.surya.footballmatch.view.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    var idLiga: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val preference = MyPreference(this)
        idLiga = intent.getStringExtra("id_liga")

        preference.setLeagueId(idLiga)
        bottomFavorites.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.match -> {
                    loadMatchFragment(savedInstanceState)
                }
                R.id.favorites -> {
                    loadFavoriteFragment(savedInstanceState)
                }
            }
            true
        }
        bottomFavorites.selectedItemId = R.id.match

    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.mainContainerFavorites,
                    FavoriteFragment(), FavoriteFragment::class.java.simpleName
                )
                .commit()
        }
    }

    private fun loadMatchFragment(savedInstanceState: Bundle?){

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.mainContainerFavorites,
                    HomeFragment(), HomeFragment::class.java.simpleName
                )
                .commit()
        }

    }
}
