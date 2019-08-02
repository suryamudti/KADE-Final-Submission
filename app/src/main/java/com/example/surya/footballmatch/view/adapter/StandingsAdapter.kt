package com.example.surya.footballmatch.view.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.surya.footballmatch.R
import com.example.surya.footballmatch.model.Table

/**
 * Created by suryamudti on 27/07/2019.
 */
class KlasemenAdapter(private val klasemen: List<Table>, private val listener: (Table) -> Unit) :
    RecyclerView.Adapter<KlasemenHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): KlasemenHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_klasemen, viewGroup, false)
        return KlasemenHolder(view)
    }

    override fun getItemCount(): Int {
        return klasemen.size
    }

    override fun onBindViewHolder(holder: KlasemenHolder, position: Int) {
        holder.bindItem(klasemen[position],listener)
        holder.no.text = (position+1).toString()
    }
}

class KlasemenHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val no : TextView = itemView.findViewById(R.id.nomorKlasemen)
    val namaClub: TextView = itemView.findViewById(R.id.nameClub)
    val played : TextView = itemView.findViewById(R.id.playedClub)
    val gfClub : TextView = itemView.findViewById(R.id.gfClub)
    val gdClub : TextView = itemView.findViewById(R.id.gdClub)
    val gaClub : TextView = itemView.findViewById(R.id.gaClub)
    val ptsClub : TextView = itemView.findViewById(R.id.ptsClub)


    @SuppressLint("ResourceType")
    fun bindItem(klasemen: Table, listener: (Table) -> Unit) {

        namaClub.text = klasemen.name
        played.text = klasemen.played.toString()
        gfClub.text = klasemen.goalsfor.toString()
        gdClub.text = klasemen.goalsdifference.toString()
        gaClub.text = klasemen.goalsagainst.toString()
        ptsClub.text = klasemen.total.toString()

        itemView.setOnClickListener { listener(klasemen) }
    }

}
