package com.example.surya.footballmatch.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.surya.footballmatch.view.fragment.FavoriteFragment
import com.example.surya.footballmatch.view.fragment.FavoriteNextFragment
import com.example.surya.footballmatch.view.fragment.FavoritePreviousFragment

class PagerFavoriteAdapter(fragment: FragmentManager) : FragmentPagerAdapter(fragment) {

    private val pages = listOf(
        FavoritePreviousFragment(),
        FavoriteNextFragment()
    )


    override fun getItem(p0: Int): Fragment {
        return pages[p0]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Previous Match"
            else -> "Next Match"
        }
    }


}