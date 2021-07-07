package com.barros.listrepo.di

import com.barros.listrepo.ui.home.data.HomeDataSourceFactory
import com.barros.listrepo.ui.home.data.HomePageKeyedDataSource
import com.barros.listrepo.ui.home.data.HomeRepository
import com.barros.listrepo.ui.home.data.HomeRepositoryImpl
import org.koin.dsl.module

object DataModule {

    val pagingModules = module {
        single {
            HomePageKeyedDataSource(
                homeRepository = get(),
                homeUseCase = get()
            )
        }
        single { HomeDataSourceFactory(tmdbDataSource = get()) }
    }

    val repositoryModules = module {
        single<HomeRepository> { HomeRepositoryImpl() }
    }
}