package com.example.surya.footballmatch.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.surya.footballmatch.nextmatch.NextFragment
import com.example.surya.footballmatch.view.fragment.FragmentPrevious

class MyPagerAdapter(fragment : FragmentManager) : FragmentPagerAdapter(fragment) {
    private val pages = listOf(
        FragmentPrevious(),
        NextFragment()
    )

    override fun getItem(position: Int): Fragment {
       return pages[position]
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