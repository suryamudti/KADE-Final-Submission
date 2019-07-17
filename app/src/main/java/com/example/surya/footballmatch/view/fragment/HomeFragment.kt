package com.example.surya.footballmatch.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.example.surya.footballmatch.R
import com.example.surya.footballmatch.model.League
import com.example.surya.footballmatch.presenter.HomePresenter
import com.example.surya.footballmatch.presenter.MainPresenter
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.utils.preference.MyPreference
import com.example.surya.footballmatch.view.adapter.MyPagerAdapter
import com.example.surya.footballmatch.view.interfaces.HomeView
import com.facebook.shimmer.ShimmerFrameLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeView {

    private lateinit var imgLiga: ImageView
    private lateinit var imgThropy: ImageView
    private lateinit var txtLiga: TextView
    private lateinit var txtWebsite: TextView
    private lateinit var txtLeagueCountry : TextView
    private lateinit var layoutScreen : LinearLayout
    private lateinit var presenter: MainPresenter
    private lateinit var myPreference: MyPreference
    private lateinit var shimmer: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView : View = inflater.inflate(R.layout.fragment_home, container, false)

        imgLiga = rootView.findViewById(R.id.ivLeagueImage)
        imgThropy = rootView.findViewById(R.id.ivLeagueThropy)
        txtLiga = rootView.findViewById(R.id.tvLeagueText)
        txtLeagueCountry = rootView.findViewById(R.id.tvLeagueCountry)
        txtWebsite = rootView.findViewById(R.id.tvLeagueWebsite)
        shimmer = rootView.findViewById(R.id.shimmer_view_container)
        layoutScreen = rootView.findViewById(R.id.layout_screen)

        shimmer.startShimmerAnimation()
        val apiRepository = ApiRepository()
        val presenter = HomePresenter(this,apiRepository)
        myPreference = MyPreference(this.activity!!)
        presenter.getDetailLiga(myPreference.getLeagueId())

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(activity as AppCompatActivity){
            view_pager_main.adapter = MyPagerAdapter(childFragmentManager)
            tabLayout.setupWithViewPager(view_pager_main)
        }
    }

    override fun showLiga(liga: League) {
        shimmer.stopShimmerAnimation()
        shimmer.visibility = View.GONE

        layoutScreen.visibility = View.VISIBLE

        Picasso.get()
            .load(liga.strLogo)
            .into(imgLiga)

        Picasso.get()
            .load(liga.strTrophy)
            .into(imgThropy)

        txtLiga.text = liga.strLeague
        txtLeagueCountry.text = liga.strCountry
        txtWebsite.text = liga.strWebsite

    }

}
