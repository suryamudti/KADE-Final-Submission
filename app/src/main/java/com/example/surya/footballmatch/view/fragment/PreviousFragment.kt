package com.example.surya.footballmatch.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import com.example.surya.footballmatch.view.activity.DetailActivity
import com.example.surya.footballmatch.utils.preference.MyPreference
import com.example.surya.footballmatch.R
import com.example.surya.footballmatch.view.adapter.MatchAdapter
import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.MatchResponse
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.presenter.PrevPresenter
import com.example.surya.footballmatch.presenter.repository.MatchRepository
import com.example.surya.footballmatch.view.interfaces.PrevView
import com.facebook.shimmer.ShimmerFrameLayout


class PreviousFragment : Fragment() , PrevView {

    private var recycle: RecyclerView? = null
    private var adapter: MatchAdapter? = null
    private lateinit var presenter: PrevPresenter
    private var match: MutableList<Event> = mutableListOf()
    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var myPreference: MyPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootview = inflater.inflate(R.layout.fragment_prev, container, false)
        setHasOptionsMenu(true)

        recycle = rootview.findViewById(R.id.rv_prev)

        shimmer = rootview.findViewById(R.id.shimmer_view_container)
        myPreference = MyPreference(this.activity!!)

        presenter = PrevPresenter(this, MatchRepository())
        showMatch()
        return rootview
    }

    override fun showLoading() {
        shimmer.visibility = View.VISIBLE
        shimmer.startShimmerAnimation()
    }

    override fun hideLoading() {
       shimmer.visibility = View.GONE
    }

    private fun itemMatchClicked(item : Event) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("id_event",item.idEvent)
        intent.putExtra("id_home",item.idHomeTeam)
        intent.putExtra("id_away",item.idAwayTeam)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search, menu)
        val searchview = menu?.findItem(R.id.searchView)?.actionView as SearchView
        searchview.isSubmitButtonEnabled = true
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(querys: String?): Boolean {
                if(querys != null){
                    recycle?.adapter = null
                    searchMatch(querys)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })
    }

    override fun showMatchList(data: List<Event>) {
        match.clear()
        data?.let { match.addAll(it) }
        adapter = MatchAdapter(match,{itemMatch: Event -> itemMatchClicked(itemMatch)})
        recycle?.adapter = adapter
        recycle?.setHasFixedSize(true)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
        recycle?.layoutManager = layoutManager
        adapter?.notifyDataSetChanged()
    }

    fun showMatch(){
        presenter.getLastMatch(myPreference.getLeagueId())
    }

    fun searchMatch(query: String) {
        presenter.getTeamSearch(query)
    }

    override fun onDataLoaded(data: MatchResponse?) {
        data?.events?.let { showMatchList(it) }
    }

    override fun onDataError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
