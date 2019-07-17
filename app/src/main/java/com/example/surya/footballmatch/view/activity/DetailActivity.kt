package com.example.surya.footballmatch.view.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.surya.footballmatch.view.interfaces.DetailView
import com.example.surya.footballmatch.R
import com.example.surya.footballmatch.model.Event
import com.example.surya.footballmatch.model.FavoriteNext
import com.example.surya.footballmatch.model.FavoritePrevious
import com.example.surya.footballmatch.model.Teams
import com.example.surya.footballmatch.presenter.DetailPresenter
import com.example.surya.footballmatch.presenter.db.database
import com.example.surya.footballmatch.presenter.repository.ApiRepository
import com.example.surya.footballmatch.utils.invisible
import com.example.surya.footballmatch.utils.visible
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var id_event: String
    var id_home: String? = null
    var id_away: String? = null
    private lateinit var presenter: DetailPresenter
    var txtScoreHome: TextView? = null
    var txtScoreAway: TextView? = null
    var txtNamaTeams: TextView? = null
    var txtGoalHome: TextView? = null
    var txtGoalAway: TextView? = null
    var txtKiperHome: TextView? = null
    var txtKiperAway: TextView? = null
    var txtBekHome: TextView? = null
    var txtBekAway: TextView? = null
    var txtGelandangHome: TextView? = null
    var txtGelandangAway: TextView? = null
    var txtPenyerangHome: TextView? = null
    var txtPenyerangAway: TextView? = null
    var imgHome: ImageView? = null
    var imgAway: ImageView? = null
    var progress: ProgressBar? = null
    var linear: LinearLayout? = null

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var matchGo: Event? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        txtScoreAway = findViewById(R.id.scoreAway)
        txtScoreHome = findViewById(R.id.scoreHome)
        imgHome = findViewById(R.id.imgViewHome)
        imgAway = findViewById(R.id.imgAway)
        txtNamaTeams = findViewById(R.id.namaTeamsBertanding)
        txtGoalHome = findViewById(R.id.goalHome)
        txtGoalAway = findViewById(R.id.goalAway)
        txtKiperHome = findViewById(R.id.kiperHome)
        txtKiperAway = findViewById(R.id.kiperAway)
        txtBekHome = findViewById(R.id.bekHome)
        txtBekAway = findViewById(R.id.bekAway)
        txtGelandangHome = findViewById(R.id.gelandangHome)
        txtGelandangAway = findViewById(R.id.gelandangAway)
        txtPenyerangHome = findViewById(R.id.penyerangHome)
        txtPenyerangAway = findViewById(R.id.penyerangAway)
        progress = findViewById(R.id.progressDetail)
        linear = findViewById(R.id.linearLayout)


        val apiRepository = ApiRepository()
        presenter = DetailPresenter(this, apiRepository)
        id_event = intent.getStringExtra("id_event")
        id_home = intent.getStringExtra("id_home")
        id_away = intent.getStringExtra("id_away")

        Log.d("Id Event : ", id_home)
        presenter.getDetailEvent(id_event)
        presenter.getDetailHome(id_home!!)
        presenter.getDetailAway(id_away!!)

        favoriteStateNext()
        favoriteStatePrev()
        Log.i("suryaf","is favorite last "+isFavorite)

    }

    override fun showHomeTeam(teams: Teams) {
        Picasso.get()
            .load(teams.strTeamBadge)
            .into(imgHome)
    }

    override fun showAwayTeam(teams: Teams) {
        Picasso.get()
            .load(teams.strTeamBadge)
            .into(imgAway)

    }


    override fun showView() {

        linear?.visible()
    }

    override fun showLoading() {
        progress?.visible()

    }

    override fun hideLoading() {
        progress?.invisible()

    }

    override fun showDetailEvent(event: Event) {
        supportActionBar?.title = event.strEvent
        matchGo = event
        txtScoreHome?.text = event.intHomeScore
        txtScoreAway?.text = event.intAwayScore
        txtNamaTeams?.text = event.strEvent
        txtGoalHome?.text = event.strHomeGoalDetails
        txtGoalAway?.text = event.strAwayGoalDetails
        txtKiperHome?.text = event.strHomeLineupGoalkeeper
        txtKiperAway?.text = event.strAwayLineupGoalkeeper
        txtBekHome?.text = event.strHomeLineupDefense
        txtBekAway?.text = event.strAwayLineupDefense
        txtPenyerangHome?.text = event.strHomeLineupForward
        txtPenyerangAway?.text = event.strAwayLineupForward
        txtGelandangHome?.text = event.strHomeLineupMidfield
        txtGelandangAway?.text = event.strAwayLineupMidfield
        setFavorite()
    }

    private fun addToFavorite() {

        if (matchGo?.intHomeScore == null) {
            try {
                database.use {
                    insert(
                        FavoriteNext.TABLE_FAVORITE_NEXT,
                        FavoriteNext.ID_EVENT to matchGo?.idEvent,
                        FavoriteNext.HOME_TEAM to matchGo?.strHomeTeam,
                        FavoriteNext.AWAY_TEAM to matchGo?.strAwayTeam,
                        FavoriteNext.DATE to matchGo?.dateEvent,
                        FavoriteNext.SCORE_HOME to matchGo?.intHomeScore,
                        FavoriteNext.SCORE_AWAY to matchGo?.intAwayScore,
                        FavoriteNext.ID_HOME_TEAM to matchGo?.idHomeTeam,
                        FavoriteNext.ID_AWAY_TEAM to matchGo?.idAwayTeam
                    )
                }
                Toast.makeText(this, "Data Next Match Berhasil di Tambahkan", Toast.LENGTH_SHORT).show()
            } catch (e: SQLiteConstraintException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }

        } else {
            try {
                database.use {
                    insert(
                        FavoritePrevious.TABLE_FAVORITE_PREVIOUS,
                        FavoritePrevious.ID_EVENT to matchGo?.idEvent,
                        FavoritePrevious.HOME_TEAM to matchGo?.strHomeTeam,
                        FavoritePrevious.AWAY_TEAM to matchGo?.strAwayTeam,
                        FavoritePrevious.DATE to matchGo?.dateEvent,
                        FavoritePrevious.SCORE_HOME to matchGo?.intHomeScore,
                        FavoritePrevious.SCORE_AWAY to matchGo?.intAwayScore,
                        FavoritePrevious.ID_HOME_TEAM to matchGo?.idHomeTeam,
                        FavoritePrevious.ID_AWAY_TEAM to matchGo?.idAwayTeam
                    )
                }
                Toast.makeText(this, "Data Prev Match Berhasil di Tambahkan", Toast.LENGTH_SHORT).show()

            } catch (e: SQLiteConstraintException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun removeFromFavorite() {

        if (matchGo?.intHomeScore == null) {
            try {
                database.use {
                    delete(FavoriteNext.TABLE_FAVORITE_NEXT, "(ID_EVENT = {id})", "id" to id_event)
                }
                Toast.makeText(this, "Berhasil menghapus Data", Toast.LENGTH_SHORT).show()

            } catch (e: SQLiteConstraintException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }

        } else {
            try {
                database.use {
                    delete(FavoritePrevious.TABLE_FAVORITE_PREVIOUS, "(ID_EVENT = {id})", "id" to id_event)
                }
                Toast.makeText(this, "Berhasil menghapus Data", Toast.LENGTH_SHORT).show()

            } catch (e: SQLiteConstraintException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun setFavorite() {

        Log.i("suryaf","inside setfavorite "+isFavorite.toString())

        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) {
                    removeFromFavorite()
                    isFavorite = false
                }
                else  {

                    addToFavorite()
                    isFavorite = true
                }
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu

        return true
    }

    private fun favoriteStateNext() {
        Log.i("suryaid next",id_event)
        database.use {
            val result = select(FavoriteNext.TABLE_FAVORITE_NEXT)
                .whereArgs("(ID_EVENT = {id})", "id" to id_event)
            val favorite = result.parseList(classParser<FavoriteNext>())
            Log.i("suryafavnext",favorite.toString()+" favoritesss ${favorite.isNullOrEmpty()}")
            if (!favorite.isNullOrEmpty()) {
                isFavorite = true
            }
        }
    }

    private fun favoriteStatePrev() {
        Log.i("suryaid prev",id_event)
        database.use {
            val result = select(FavoritePrevious.TABLE_FAVORITE_PREVIOUS)
                .whereArgs("(ID_EVENT = {id})", "id" to id_event)
            val favorite = result.parseList(classParser<FavoritePrevious>())
            Log.i("suryafav",favorite.toString()+" favoritesss ${favorite.isNullOrEmpty()}")

            if (!favorite.isNullOrEmpty()) {
                isFavorite = true
            }
        }
    }

}
