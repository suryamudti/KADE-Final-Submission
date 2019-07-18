package com.example.surya.footballmatch.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.surya.footballmatch.R
import com.example.surya.footballmatch.view.adapter.LeagueStatisAdapter
import com.example.surya.footballmatch.model.StaticLeague

class LeagueActivity : AppCompatActivity() {

    private var items : MutableList<StaticLeague> = mutableListOf()
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : LeagueStatisAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)
        recyclerView = findViewById(R.id.recycleLeague)
        initData()
    }

    fun getItemClick(item : StaticLeague){
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("id_liga",item.idLiga)
        startActivity(intent)
    }
    private fun initData(){
        val name = resources.getStringArray(R.array.ligaName)
        val image = resources.obtainTypedArray(R.array.liga_image)
        val idLiga = resources.getStringArray(R.array.id_liga)

        items.clear()
        for (i in name.indices) {
            items.add(StaticLeague(name[i],image.getResourceId(i, 0),idLiga[i]))
        }
        adapter = LeagueStatisAdapter(this,items,{ liga : StaticLeague -> getItemClick(liga)})
        recyclerView?.adapter = adapter
        recyclerView?.setHasFixedSize(true)
        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this,2)
        recyclerView?.layoutManager = layoutManager
        adapter?.notifyDataSetChanged()


        //Recycle the typed array
        image.recycle()
    }
}
