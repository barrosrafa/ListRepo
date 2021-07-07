package com.barros.listrepo.di

import com.barros.listrepo.ui.home.presentation.HomeViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val viewModelModules = module {
        viewModel {
            HomeViewModel(
                homeDataSourceFactory = get(),
                homeUseCase = get()
            )
        }
    }
}