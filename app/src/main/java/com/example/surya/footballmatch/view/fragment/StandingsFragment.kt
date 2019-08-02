package com.example.surya.footballmatch.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast

import com.example.surya.footballmatch.R
import com.example.surya.footballmatch.model.Table
import com.example.surya.footballmatch.presenter.KlasemenPresenter
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.utils.invisible
import com.example.surya.footballmatch.utils.preference.MyPreference
import com.example.surya.footballmatch.utils.visible
import com.example.surya.footballmatch.view.adapter.KlasemenAdapter
import com.example.surya.footballmatch.view.interfaces.KlasemenView
import com.facebook.shimmer.ShimmerFrameLayout
import com.mlsdev.animatedrv.AnimatedRecyclerView


/**
 * A simple [Fragment] subclass.
 *
 */
class KlasemenFragment : Fragment(), KlasemenView {

    private lateinit var recycle: AnimatedRecyclerView
    private var adapter: KlasemenAdapter? = null
    private lateinit var presenter: KlasemenPresenter
    private var table: MutableList<Table> = mutableListOf()
    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var preference: MyPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_klasemen, container, false)
        recycle = rootView.findViewById(R.id.rvKlasemen)

        shimmer = rootView.findViewById(R.id.shimmer_view_container)

        preference = MyPreference(this.activity!!)
        showKlasemen()
        return rootView
    }

    fun showKlasemen() {
        val apiRepository = ApiRepository()
        presenter = KlasemenPresenter(this, apiRepository)
        presenter.getKlasemenList(preference.getLeagueId())

    }

    override fun showKlasemen(klasemen: List<Table>) {
        table.clear()
        table.addAll(klasemen)
        adapter = KlasemenAdapter(table, { itemMatch: Table -> itemMatchClicked(itemMatch) })
        recycle!!.adapter = adapter
        recycle!!.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)

        recycle!!.layoutManager = layoutManager

        adapter!!.notifyDataSetChanged()
        recycle.scheduleLayoutAnimation()
    }

    override fun showLoading() {
        shimmer.startShimmerAnimation()
    }

    override fun hideLoading() {
        shimmer.visibility = View.GONE
    }

    private fun itemMatchClicked(item: Table) {

        Toast.makeText(context, "DI KLIK SUKSES", Toast.LENGTH_SHORT).show()
    }




}
