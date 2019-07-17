package com.example.surya.footballmatch.presenter.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.surya.footballmatch.model.FavoriteNext
import com.example.surya.footballmatch.model.FavoritePrevious
import org.jetbrains.anko.db.*

class MyDb(context: Context):ManagedSQLiteOpenHelper(
    context, "favorite.db",null, 1
){

    companion object{
        private var instances: MyDb? = null

        fun getInstance(context: Context): MyDb{
            if(instances == null){
                instances = MyDb(context.applicationContext)
            }
            return instances as MyDb
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        
        db?.createTable(FavoriteNext.TABLE_FAVORITE_NEXT, true,
        FavoriteNext.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteNext.ID_EVENT to TEXT + UNIQUE,
            FavoriteNext.HOME_TEAM to TEXT,
            FavoriteNext.AWAY_TEAM to TEXT,
            FavoriteNext.DATE to TEXT,
            FavoriteNext.SCORE_HOME to TEXT,
            FavoriteNext.SCORE_AWAY to TEXT,
            FavoriteNext.ID_HOME_TEAM to TEXT,
            FavoriteNext.ID_AWAY_TEAM to TEXT
        )

        db?.createTable(
            FavoritePrevious.TABLE_FAVORITE_PREVIOUS, true,
            FavoritePrevious.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoritePrevious.ID_EVENT to TEXT + UNIQUE,
            FavoritePrevious.HOME_TEAM to TEXT,
            FavoritePrevious.AWAY_TEAM to TEXT,
            FavoritePrevious.DATE to TEXT,
            FavoritePrevious.SCORE_HOME to TEXT,
            FavoritePrevious.SCORE_AWAY to TEXT,
            FavoritePrevious.ID_HOME_TEAM to TEXT,
            FavoritePrevious.ID_AWAY_TEAM to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoritePrevious.TABLE_FAVORITE_PREVIOUS,true)
        db?.dropTable(FavoriteNext.TABLE_FAVORITE_NEXT,true)
    }

}

val Context.database: MyDb
get() = MyDb.getInstance(applicationContext)