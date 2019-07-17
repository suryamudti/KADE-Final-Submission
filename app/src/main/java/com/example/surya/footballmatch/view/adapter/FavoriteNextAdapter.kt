package com.example.surya.footballmatch.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.surya.footballmatch.R
import com.example.surya.footballmatch.model.FavoriteNext
import java.text.ParseException
import java.text.SimpleDateFormat

class FavoriteNextAdapter(private val match: List<FavoriteNext>,
                          private val context: Context,
                          private val listener: (FavoriteNext) -> Unit
) : RecyclerView.Adapter<FavoriteHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): FavoriteHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_match, viewGroup, false)
        return FavoriteHolder(view)
    }

    override fun getItemCount(): Int {
        return match.size
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bindItem(match[position],listener)
    }
}

class FavoriteHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    val homeTeam: TextView = itemview.findViewById(R.id.homeTeams)
    val awayTeam: TextView = itemview.findViewById(R.id.awayTeams)
    val tanggal: TextView = itemview.findViewById(R.id.dateMatch)
    val scoreHome: TextView = itemview.findViewById(R.id.scoreHome)
    val scoreAway: TextView = itemview.findViewById(R.id.scoreAway)
    fun bindItem(teams: FavoriteNext, listener: (FavoriteNext) -> Unit) {
        val getDate: String = teams.date.toString()

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        try {
            val date = simpleDateFormat.parse(getDate)
            val newFormat = SimpleDateFormat("EEEE, MMM dd, yyyy")
            val dateFix = newFormat.format(date)
            tanggal.text = dateFix
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        scoreAway.text = teams.scoreAway
        scoreHome.text = teams.scoreHome


        awayTeam.text = teams.awayTeam
        homeTeam.text = teams.homeTeam

        itemView.setOnClickListener { listener(teams) }
    }
}
