package br.com.geocdias.radiolacompose.ui.screens.player

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.ui.PlayerNotificationManager
import br.com.geocdias.radiolacompose.domain.model.Song
import br.com.geocdias.radiolacompose.domain.repository.SongsRepository
import br.com.geocdias.radiolacompose.ui.notification.RadiolaNotificationManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class PlayerViewModel(
    private val repository: SongsRepository,
    val player: ExoPlayer
) : ViewModel() {

    private val _songFlow  = MutableStateFlow(PlayerScreenUiState())
    val songFlow = _songFlow.asStateFlow()

    private val playerScope = CoroutineScope(Dispatchers.Main + Job())
    private lateinit var mediaSession: MediaSession
    private lateinit var notificationManager: RadiolaNotificationManager
    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    private var isStarted = false

    fun getSongById(mediaId: String) {
        viewModelScope.launch {
            val song = repository.fetchSongById(mediaId)
            song?.let {
                _songFlow.update { currentState ->
                    currentState.copy(song = it)
                }
                initPlayer(it)
            }
        }
    }

    private fun initPlayer(song: Song) {
        player.apply {
            setMediaItem(MediaItem.fromUri(Uri.parse(song.songUrl)))
            addListener(playerListener)
            prepare()
        }
    }

    fun startPlayer() {
        player.play()
        updatePlayerSlider()
    }

    fun pausePlayer() {
        player.pause()
    }

    private fun updatePlayerSlider() {
        playerScope.launch {
            do {
                val currentDuration = player.currentPosition.coerceAtLeast(0L)
                val totalDuration = player.duration.coerceAtLeast(0L)
                val progress = if (totalDuration > 0) currentDuration.toFloat() / totalDuration.toFloat() else 0F
                _songFlow.update { currentState ->
                    currentState.copy(
                        totalDuration = totalDuration,
                        currentDuration = currentDuration,
                        progress = progress
                    )
                }
                delay(500L)
            } while (player.isPlaying)
        }
    }

    fun releasePlayer() {
        player.removeListener(playerListener)
        player.release()
        playerScope.cancel()
    }

    private val  playerListener = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)

            when (playbackState) {
                Player.STATE_READY -> {
                    _songFlow.update { currentState ->
                        currentState.copy(
                            totalDuration = player.duration.coerceAtLeast(0L),
                            currentDuration = player.currentPosition.coerceAtLeast(0L),
                        )
                    }
                }
            }
        }
    }


//    fun startPlayer(context: Context) {
//        if (isStarted) return
//        isStarted = true
////        player.prepare()
////        player.play()
//
//        // Build a PendingIntent that can be used to launch the UI.
//        val sessionActivityPendingIntent =
//            context.packageManager?.getLaunchIntentForPackage(context.packageName)
//                ?.let { sessionIntent ->
//                    PendingIntent.getActivity(
//                        context,
//                        SESSION_INTENT_REQUEST_CODE,
//                        sessionIntent,
//                        PendingIntent.FLAG_IMMUTABLE
//                    )
//                }
//
//        // Create a new MediaSession.
//        mediaSession = MediaSession.Builder(context, player)
//            .setSessionActivity(sessionActivityPendingIntent!!).build()
//
//        notificationManager = RadiolaNotificationManager(
//            context = context,
//            sessionToken = mediaSession.token,
//            player = player,
//            notificationListener = object : PlayerNotificationManager.NotificationListener {
//                override fun onNotificationPosted(
//                    notificationId: Int,
//                    notification: Notification,
//                    ongoing: Boolean
//                ) {
//
//                }
//            }
//        )
//        notificationManager.showNotification()
//
//    }

    companion object {
        const val SESSION_INTENT_REQUEST_CODE = 0
    }
}
