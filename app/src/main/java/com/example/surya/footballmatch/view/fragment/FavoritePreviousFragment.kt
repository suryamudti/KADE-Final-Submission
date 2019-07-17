package com.example.surya.footballmatch.view.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.ProgressBar

import com.example.surya.footballmatch.R
import com.example.surya.footballmatch.model.FavoritePrevious
import com.example.surya.footballmatch.presenter.db.database
import com.example.surya.footballmatch.utils.invisible
import com.example.surya.footballmatch.view.activity.DetailActivity
import com.example.surya.footballmatch.view.adapter.FavoritePreviousAdapter
import com.mlsdev.animatedrv.AnimatedRecyclerView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import java.util.ArrayList


class FavoritePreviousFragment : Fragment() {

    private lateinit var recyclerView: AnimatedRecyclerView
    private lateinit var proggressbar: ProgressBar
    private lateinit var adapter: FavoritePreviousAdapter

    private var favorites: MutableList<FavoritePrevious> = mutableListOf()
    private lateinit var dataSearch : List<FavoritePrevious>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_favorite_previous, container, false)

        recyclerView = rootView.findViewById(R.id.rv_prev_Fav)
        proggressbar = rootView.findViewById(R.id.progressFavPrev)

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_search, menu)
        val searchview = menu!!.findItem(R.id.searchView)?.actionView as SearchView
        searchview.isSubmitButtonEnabled = true
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(querys: String?): Boolean {
                if (querys != null) {
                    search(querys)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }

        })
    }

    private fun search(keyword : String){
        val filteredList = ArrayList<FavoritePrevious>()
        for (s : FavoritePrevious in dataSearch) {

            if (s.homeTeam?.toLowerCase()?.contains(keyword)!! || s.awayTeam?.toLowerCase()?.contains(keyword)!!) {
                filteredList.add(s)
                Log.e("Test : ",s.homeTeam)
            }
        }
        adapter =
            FavoritePreviousAdapter(filteredList, this.activity!!, { itemTeams: FavoritePrevious -> getItemClick(itemTeams) })
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        recyclerView.scheduleLayoutAnimation()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
    }

    private fun initAdapter(){
        adapter =
            FavoritePreviousAdapter(favorites, this.activity!!, { itemTeams: FavoritePrevious -> getItemClick(itemTeams) })
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = layoutManager
    }

    fun getItemClick(item: FavoritePrevious) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("id_event", item.idEvent)
        intent.putExtra("id_home", item.idHomeTeam)
        intent.putExtra("id_away", item.idAwayTeam)
        startActivity(intent)
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            proggressbar.invisible()
            val result = select(FavoritePrevious.TABLE_FAVORITE_PREVIOUS)
            val favorite = result.parseList(classParser<FavoritePrevious>())
            favorites.addAll(favorite)
            dataSearch = favorite
            adapter.notifyDataSetChanged()
            recyclerView.scheduleLayoutAnimation()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

}
