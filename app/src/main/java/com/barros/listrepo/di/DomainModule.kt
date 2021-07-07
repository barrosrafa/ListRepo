package com.barros.listrepo.di

import com.barros.listrepo.ui.home.domain.HomeUseCase
import org.koin.dsl.module

object DomainModule {

    val domainModules = module {
        single { HomeUseCase(homeRepository = get()) }
    }
}