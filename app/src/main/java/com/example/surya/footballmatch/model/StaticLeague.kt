package com.example.surya.footballmatch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StaticLeague (val name : String?, val image : Int?, val idLiga : String? ) : Parcelable