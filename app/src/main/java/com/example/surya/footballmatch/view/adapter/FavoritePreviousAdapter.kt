package com.example.surya.footballmatch.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.surya.footballmatch.R
import com.example.surya.footballmatch.model.FavoritePrevious
import java.text.ParseException
import java.text.SimpleDateFormat

class FavoritePreviousAdapter(
    private val match: List<FavoritePrevious>, private val context: Context,
    private val listener: (FavoritePrevious) -> Unit
) : RecyclerView.Adapter<FavoriteHolderPrev>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): FavoriteHolderPrev {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_match, viewGroup, false)
        return FavoriteHolderPrev(view)
    }

    override fun getItemCount(): Int {
        return match.size
    }

    override fun onBindViewHolder(holder: FavoriteHolderPrev, position: Int) {
        holder.bindItem(match[position],listener)
    }
}

class FavoriteHolderPrev(itemview: View) : RecyclerView.ViewHolder(itemview) {
    val homeTeam: TextView = itemview.findViewById(R.id.homeTeams)
    val awayTeam: TextView = itemview.findViewById(R.id.awayTeams)
    val date: TextView = itemview.findViewById(R.id.dateMatch)
    val scoreHome: TextView = itemview.findViewById(R.id.scoreHome)
    val scoreAway: TextView = itemview.findViewById(R.id.scoreAway)
    fun bindItem(teams: FavoritePrevious, listener: (FavoritePrevious) -> Unit) {
        val getDate: String = teams.date.toString()

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        try {
            val date = simpleDateFormat.parse(getDate)
            val newFormat = SimpleDateFormat("EEEE, MMM dd, yyyy")
            val dateFix = newFormat.format(date)
            this.date.text = dateFix
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