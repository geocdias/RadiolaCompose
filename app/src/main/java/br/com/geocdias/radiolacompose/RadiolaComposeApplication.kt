package br.com.geocdias.radiolacompose

import android.app.Application
import androidx.media3.exoplayer.ExoPlayer
import br.com.geocdias.radiolacompose.data.database.RadiolaComposeDatabase
import br.com.geocdias.radiolacompose.data.database.dao.SongDAO
import br.com.geocdias.radiolacompose.data.datasources.LocalSongsDatasource
import br.com.geocdias.radiolacompose.data.datasources.LocalSongsDatasourceImpl
import br.com.geocdias.radiolacompose.data.datasources.RemoteSongsDatasource
import br.com.geocdias.radiolacompose.data.datasources.RemoteSongsDatasourceImpl
import br.com.geocdias.radiolacompose.data.repositories.SongsRepositoryImpl
import br.com.geocdias.radiolacompose.domain.repository.SongsRepository
import br.com.geocdias.radiolacompose.ui.screens.home.HomeViewModel
import br.com.geocdias.radiolacompose.ui.screens.player.PlayerViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class RadiolaComposeApplication : Application() {

    private val appModule = module {
        singleOf(::SongsRepositoryImpl) { bind<SongsRepository>() }
        viewModel { HomeViewModel(get()) }
        viewModel { PlayerViewModel(get(), get()) }

        single<RadiolaComposeDatabase> {
            RadiolaComposeDatabase.getInstance(get())
        }
        single<SongDAO> { get<RadiolaComposeDatabase>().songDao() }
        factory<RemoteSongsDatasource> { RemoteSongsDatasourceImpl() }
        factory<LocalSongsDatasource> { LocalSongsDatasourceImpl(get()) }
    }

    private val serviceModule = module {
        single {
            ExoPlayer.Builder(get()).build()
        }
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RadiolaComposeApplication)
            modules(
                appModule,
                serviceModule
            )
        }
    }
}
