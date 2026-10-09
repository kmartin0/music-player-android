package nl.kmartin.musicplayer.repository

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import nl.kmartin.musicplayer.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import nl.kmartin.musicplayer.api.LyricsApi
import nl.kmartin.musicplayer.dao.LyricsDao
import nl.kmartin.musicplayer.database.room.MusicRoomDatabase
import nl.kmartin.musicplayer.model.Lyrics

class LyricsRepository(val context: Context) {
	private var lyricsDao: LyricsDao

	init {
		val musicRoomDataBase = MusicRoomDatabase.getDatabase(context)
		lyricsDao = musicRoomDataBase!!.lyricsDao()
	}

	/**
	 * Get the [nl.kmartin.musicplayer.model.Lyrics] object from the [MusicRoomDatabase] for the [artist] and [title].
	 * If the Lyrics are not yet stored in the database an api request will be made which will
	 * then insert the retrieved [nl.kmartin.musicplayer.model.Lyrics] in the database.
	 *
	 * @return [LiveData] object which will contain the [nl.kmartin.musicplayer.model.Lyrics]
	 */
	suspend fun getLyrics(artist: String, title: String): Flow<Lyrics?> {
		return lyricsDao.getLyrics(artist, title).onEach { lyrics ->
			if (lyrics == null) {
				try {
					val apiLyrics = LyricsApi.create().getLyrics(artist, title)
					if (apiLyrics.lyrics.isBlank()) insertLyrics(
                        Lyrics(
                            title,
                            artist,
                            context.getString(R.string.no_lyrics_found)
                        )
                    )
					else insertLyrics(Lyrics(title, artist, apiLyrics.lyrics))
				} catch (ex: Exception) {
					ex.printStackTrace()
					insertLyrics(Lyrics(title, artist, context.getString(R.string.no_lyrics_found)))
				}
			}
		}
	}


	/**
	 * Insert the [Lyrics] in the [MusicRoomDatabase]
	 */
	@WorkerThread
	suspend fun insertLyrics(lyrics: Lyrics) {
		lyricsDao.insert(lyrics)
	}
}