package nl.kmartin.musicplayer.api

import com.google.gson.annotations.SerializedName

data class LyricsResponse(@SerializedName("lyrics") val lyrics: String)