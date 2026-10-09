package nl.kmartin.musicplayer.ui.player

import android.app.Application
import android.support.v4.media.session.PlaybackStateCompat
import nl.kmartin.musicplayer.base.BaseViewModel
import nl.kmartin.musicplayer.util.MediaSessionConnection
import nl.kmartin.musicplayer.util.SingleLiveEvent

class MusicPlayerViewModel(application: Application) : BaseViewModel(application) {

	val navigateLyricsEvent = SingleLiveEvent<Any?>()
	private val mediaSessionConnection = MediaSessionConnection.getInstance(application.applicationContext)
	val currentTrack = mediaSessionConnection.currentTrack
	val playBackState = mediaSessionConnection.playBackState

	fun resumeTrack() {
		if (playBackState.value?.state == PlaybackStateCompat.STATE_PAUSED)
			mediaSessionConnection.transportControls.play()
	}

	fun pauseTrack() {
		if (playBackState.value?.state == PlaybackStateCompat.STATE_PLAYING)
			mediaSessionConnection.transportControls.pause()
	}

	fun skipToNext() {
		if (playBackState.value?.state != null && playBackState.value?.state != PlaybackStateCompat.STATE_STOPPED)
			mediaSessionConnection.transportControls.skipToNext()
	}

	fun skipToPrevious() {
		if (playBackState.value?.state != null && playBackState.value?.state != PlaybackStateCompat.STATE_STOPPED)
			mediaSessionConnection.transportControls.skipToPrevious()
	}

	fun onLyricsClick() {
		navigateLyricsEvent.call()
	}
}