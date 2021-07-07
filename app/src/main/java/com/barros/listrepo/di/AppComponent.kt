package com.barros.listrepo.di

import com.barros.listrepo.di.AppModule.viewModelModules
import com.barros.listrepo.di.DataModule.pagingModules
import com.barros.listrepo.di.DataModule.repositoryModules
import com.barros.listrepo.di.DomainModule.domainModules
import org.koin.core.module.Module

object AppComponent {

    fun getAllModules(): List<Module> =
        listOf(*getDomainModules(), *getDataModules(), *getViewModelModules())

    private fun getViewModelModules(): Array<Module> = arrayOf(viewModelModules)

    private fun getDomainModules(): Array<Module> = arrayOf(domainModules)

    private fun getDataModules(): Array<Module> = arrayOf(repositoryModules, pagingModules)
}