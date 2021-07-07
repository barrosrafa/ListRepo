package com.barros.listrepo.ui.home.data

import androidx.paging.PageKeyedDataSource
import com.barros.listrepo.model.RepositoriesResponse
import com.barros.listrepo.model.Repository
import com.barros.listrepo.ui.home.domain.HomeUseCase
import com.barros.listrepo.utils.Constants.Paging.FIRST_PAGE
import com.barros.listrepo.utils.ResponseApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageKeyedDataSource(
    private val homeRepository: HomeRepository,
    private val homeUseCase: HomeUseCase
) : PageKeyedDataSource<Int, Repository>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Repository>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val movies: List<Repository> = callRepositoriesApi(FIRST_PAGE)
            callback.onResult(movies, null, FIRST_PAGE + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {
        loadData(params.key, params.key - 1, callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {
        loadData(params.key, params.key + 1, callback)
    }

    private fun loadData(page: Int, nextPage: Int, callback: LoadCallback<Int, Repository>) {
        CoroutineScope(Dispatchers.IO).launch {
            val movies: List<Repository> = callRepositoriesApi(page)
            callback.onResult(movies, nextPage)
        }
    }

    private suspend fun callRepositoriesApi(page: Int): List<Repository> {
        return when (
            val response = homeRepository.getRepositories(page)
        ) {
            is ResponseApi.Success -> {
                val list = response.data as? RepositoriesResponse
                homeUseCase.setupMoviesList(list)
            }
            is ResponseApi.Error -> {
                listOf()
            }
        }
    }
}