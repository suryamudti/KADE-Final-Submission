package com.example.surya.footballmatch.view.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.ProgressBar

import com.example.surya.footballmatch.R
import com.example.surya.footballmatch.model.FavoriteNext
import com.example.surya.footballmatch.presenter.db.database
import com.example.surya.footballmatch.utils.invisible
import com.example.surya.footballmatch.view.activity.DetailActivity
import com.example.surya.footballmatch.view.adapter.FavoriteNextAdapter
import com.mlsdev.animatedrv.AnimatedRecyclerView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import java.util.ArrayList

class FavoriteNextFragment : Fragment() {

    private lateinit var recyclerView: AnimatedRecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: FavoriteNextAdapter
    private var favorites: MutableList<FavoriteNext> = mutableListOf()
    private lateinit var dataSearch : List<FavoriteNext>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        val rootView:View = inflater.inflate(R.layout.fragment_favorite_next, container, false)
        recyclerView = rootView.findViewById(R.id.rv_next_favorite)
        progressBar = rootView.findViewById(R.id.progressFavNext)

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search,menu)
        val searchView = menu?.findItem(R.id.searchView)?.actionView as SearchView
        searchView.isSubmitButtonEnabled = true

        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        search(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            }
        )

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun init(){
        adapter =
            FavoriteNextAdapter(favorites, this.activity!!, { itemTeams: FavoriteNext -> getItemClick(itemTeams) })
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = layoutManager
    }

    private fun search(keyword: String){
        val filteredList = ArrayList<FavoriteNext>()
        for (s : FavoriteNext in dataSearch) {
            //if the existing elements contains the search input
            if (s.homeTeam?.toLowerCase()?.contains(keyword)!! || s.awayTeam?.toLowerCase()?.contains(keyword)!!) {
                //adding the element to filtered list
                filteredList.add(s)
            }
        }
        adapter =
            FavoriteNextAdapter(filteredList, this.activity!!, { itemTeams: FavoriteNext -> getItemClick(itemTeams) })
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        recyclerView.scheduleLayoutAnimation()
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            progressBar.invisible()
            val result = select(FavoriteNext.TABLE_FAVORITE_NEXT)
            val favorite = result.parseList(classParser<FavoriteNext>())
            dataSearch = favorite
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
            recyclerView.scheduleLayoutAnimation()

        }
    }

    fun getItemClick(item: FavoriteNext) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("id_event", item.idEvent)
        intent.putExtra("id_home", item.idHomeTeam)
        intent.putExtra("id_away", item.idAwayTeam)
        startActivity(intent)
    }


}
