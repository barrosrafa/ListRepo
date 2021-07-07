package com.barros.listrepo.ui.home.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.barros.listrepo.model.Repository

class HomeDataSourceFactory(
    private val tmdbDataSource: HomePageKeyedDataSource
): DataSource.Factory<Int, Repository>() {

    private val tmdbLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Repository>>()
    override fun create(): DataSource<Int, Repository> {
        tmdbLiveDataSource.postValue(tmdbDataSource)
        return tmdbDataSource
    }

    fun getLiveDataSource() : MutableLiveData<PageKeyedDataSource<Int, Repository>> {
        return tmdbLiveDataSource
    }
}