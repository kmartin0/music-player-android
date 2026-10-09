# Music Player Android

Native Android music player application created during the Mobile Application Development course.

## Features

- Display audio files from the user's device, including artist, album and album artwork.
- Play and pause audio.
- Play the next or previous track.
- Display a blurred version of the current album artwork in the player background.
- Media notification with playback controls.
- Respond to audio focus changes when another application requests audio playback.
- Pause playback when an audio device is disconnected.
- Display track lyrics.

## Technical Overview

- Kotlin.
- MVVM architecture using Android Architecture Components.
- Audio file indexing using MediaStore.
- Background audio playback using `MediaBrowserService`.
- Media playback integration using `MediaSession` and `MediaController`.

## Screenshots

<img src="https://github.com/kmartin0/assets/blob/master/music-player-android/android-music-player_home.png?raw=true" alt="Home screenshot" width="300" />

<img src="https://github.com/kmartin0/assets/blob/master/music-player-android/android-music-player-home_playing.png?raw=true" alt="Home playing screenshot" width="300" />

<img src="https://github.com/kmartin0/assets/blob/master/music-player-android/android-music-player_playing.png?raw=true" alt="Player screenshot" width="300" />

<img src="https://github.com/kmartin0/assets/blob/master/music-player-android/android-music-player_lyrics.png?raw=true" alt="Lyrics screenshot" width="300" />

<img src="https://github.com/kmartin0/assets/blob/master/music-player-android/android-music-player-notification.png?raw=true" alt="Media notification screenshot" width="300" />